package api.modules;

import java.util.LinkedList;

import com.google.gson.Gson;

import api.AstraApi;
import api.modules.utils.FormattingService;
import api.modules.utils.Position;
import api.modules.utils.Rotation;
import api.modules.utils.Scale;
import api.modules.utils.UnityJson;
import astra.core.Module;

/**
 * Handles events of type 'position_vector' sent from Unity. Based on the previous
 * coordinates the axis (x,y or z) are checked to determine the direction of
 * movement. Based on the change from the last available position coordinates
 * returns the direction vector.</br> When <code>cardinalDirection</code>
 * is part of the Unity input json, reset the <code>directions</code> map and use
 * the <coe>cardinalDirection</code> to generate the Astra response json.
 * </br></br>
 * <b>Expect input as a Json in the format of:</b></br>
 * <ul>
 * <li><b>very first time</b></li>
 * {"type":"position_vector","position":{"x":1.649999976158142,"y":1.0,"z":2.700000047683716},"rotation":{"x":0.0,"y":0.0,"z":0.0},"scale":{"x":0.5,"y":1.0,"z":0.5},"cardinalDirection":"South"}
 * <li><b>every next time</b></li>
 * {"type":"position_vector","position":{"x":1.649999976158142,"y":1.0,"z":2.500000047683716},"rotation":{"x":0.0,"y":0.0,"z":0.0},"scale":{"x":0.5,"y":1.0,"z":0.5}} </br>
 * and compare it with the previous recorded coordinates
 * </ul>
 * <b>Returns:</b></br>
 * {"position":{"x":0.0,"y":0.0,"z":-1.0},"scale":{"x":0.5,"y":1.0,"z":0.5},"rotation":{"x":0.0,"y":0.0,"z":0.0},"type":"position_vector"}
 */
public class PositionVector extends Module {

	private Gson gson = new Gson();

	private LinkedList<UnityJson> directions = new LinkedList<UnityJson>();
	private String currentCardinalDirection = "";

	@TERM
	public String getDirectionsVector(String event) {

		Double lastX = null;
		Double lastY = null;
		Double lastZ = null;

		//initialize the response Object 
		UnityJson responseVector = new UnityJson();	
		api.modules.utils.Position responsePosition = new Position();
		responsePosition.setX(AstraApi.ZERO);
		responsePosition.setY(AstraApi.ZERO);
		responsePosition.setZ(AstraApi.ZERO);
		responseVector.setPosition(responsePosition);
		
		//get current coordinates
		UnityJson coordinates = gson.fromJson(event, UnityJson.class);
		//add Scale to the response json
		Scale coordinatesScale = coordinates.getScale();
		responseVector.setScale(coordinatesScale);
		
		//add Rotation to the response json
		Rotation coordinatesRotation = coordinates.getRotation();
		responseVector.setRotation(coordinatesRotation);
		
		api.modules.utils.Position coordinatesPosition = coordinates.getPosition();
		if (coordinates.getCardinalDirection() != null && AstraApi.LIST_CARDINAL_DIRECTIONS.contains(coordinates.getCardinalDirection())) {
			directions.clear();
		}
		
		if (!directions.isEmpty()) {
			
			// get the last coordinates if exist and remove it if size of list  > 10
			UnityJson recordedCoordinates = null;
			if (directions.size() > 10) {
				recordedCoordinates = directions.pollLast();
			} else {
				recordedCoordinates = directions.getLast();
			}

			// add the current agent position
			directions.add(gson.fromJson(event, UnityJson.class));
			
			api.modules.utils.Position recordedPosition = recordedCoordinates.getPosition();
			lastX = recordedPosition.getX();
			lastY = recordedPosition.getY();
			lastZ = recordedPosition.getZ();			

			//get the sign of the coordinates
			int signX = FormattingService.signBit(coordinatesPosition.getX().floatValue());
			int signY = FormattingService.signBit(coordinatesPosition.getY().floatValue());
			int signZ = FormattingService.signBit(coordinatesPosition.getZ().floatValue());
			
			//compare the absolute values with accuracy of 0.001, manipulate the coordinates and add the sign 
			if (lastX != null && !(Math.abs(coordinatesPosition.getX().doubleValue() - lastX.doubleValue()) < FormattingService.EPSILON)) {
				double valueX = (Math.abs(coordinatesPosition.getX().doubleValue()) > Math.abs(lastX.doubleValue()) &&  !(Math.abs(lastX.doubleValue() - coordinatesPosition.getX().doubleValue()) < FormattingService.EPSILON)) ? 
						           AstraApi.ONE : -AstraApi.ONE;
				
				responsePosition.setX(signX == 0 ? new Double(valueX) : new Double(-valueX));
			} 
			if (lastY != null && !(Math.abs(coordinatesPosition.getY().doubleValue() - lastY.doubleValue()) < FormattingService.EPSILON)) {
				double valueY = (Math.abs(coordinatesPosition.getY().doubleValue()) > Math.abs(lastY.doubleValue()) && !(Math.abs(lastY.doubleValue() - coordinatesPosition.getY().doubleValue()) < FormattingService.EPSILON)) ? 
						           AstraApi.ONE : -AstraApi.ONE;
				
				responsePosition.setY(signY == 0 ? new Double(valueY) : new Double(-valueY));
			} 
			if (lastZ != null && !(Math.abs(coordinatesPosition.getZ().doubleValue() - lastZ.doubleValue()) < FormattingService.EPSILON)) {
				double valueZ = (Math.abs(coordinatesPosition.getZ().doubleValue()) > Math.abs(lastZ.doubleValue()) && !(Math.abs(lastZ.doubleValue() - coordinatesPosition.getZ().doubleValue()) < FormattingService.EPSILON)) ? 
						           AstraApi.ONE : -AstraApi.ONE;
				
				responsePosition.setZ(signZ == 0 ? new Double(valueZ) : new Double(-valueZ));
			}

			//check if all three coordinates are zero, if so use the recorded cardinal direction to amend the appropriate axis
			checkForZeroVector(responsePosition);
			return gson.toJson(responseVector);
			
		} else {
			
			// add the current/initial agent position
			directions.add(gson.fromJson(event, UnityJson.class));
			
			UnityJson initialPosition = gson.fromJson(event, UnityJson.class);
						
			// this tell us which direction to go for the very first time
			String cardinalDirection = initialPosition.getCardinalDirection();
			//set the current cardinal direction
			currentCardinalDirection = cardinalDirection;
			
			setNonZeroAxis(responsePosition, cardinalDirection);

			return gson.toJson(responseVector);
		}
	}

	/**
	 * Check if the vector for contains only zero entries and set the current one.
	 * Basically there is no change from the event passed from Unity, therefore
	 * position won't change.
	 * @param responsePosition
	 */
	private void checkForZeroVector(Position responsePosition) {
		if (responsePosition.getX() == AstraApi.ZERO && responsePosition.getY() == AstraApi.ZERO && responsePosition.getZ() == AstraApi.ZERO) {
			setNonZeroAxis(responsePosition, currentCardinalDirection);
		} 
	}
	
	/**
	 * Set coordinates that have non zero entry 
	 * @param responsePosition
	 * @param cardinalDirection
	 */
	private void setNonZeroAxis(Position responsePosition, String cardinalDirection) {

		switch (cardinalDirection) {
		case AstraApi.SOUTH:
			responsePosition.setZ(-AstraApi.ONE);
			break;
		case AstraApi.WEST:
			responsePosition.setX(-AstraApi.ONE);
			break;
		case AstraApi.EAST:
			responsePosition.setX(AstraApi.ONE);
			break;
		default:// default North
			responsePosition.setZ(AstraApi.ONE);
		}
	}
}
