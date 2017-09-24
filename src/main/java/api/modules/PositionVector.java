package api.modules;

import java.util.LinkedList;

import com.google.gson.Gson;

import api.AstraApi;
import api.modules.utils.FormattingService;
import api.modules.utils.PositionUnityJson;
import astra.core.Module;

/**
 * Handles events of type 'position_vector' sent from Unity. Based on the previous
 * coordinates the axis (x,y or z) are checked to determine the direction of
 * movement. Based on the change from the last available position coordinates
 * returns the direction vector</br>
 * </br>
 * 
 * <b>Expect input as a Json in the format of:</b></br>
 * <ul>
 * <li><b>very first time</b></li>
 * {"type":"position_vector","x":1.649999976158142,"y":1.0,"z":2.700000047683716,"cardinalDirection":"South"}
 * <li><b>every next time</b></li>
 * {"type":"position_vector","x":1.649999976158142,"y":1.0,"z":2.500000047683716} </br>
 * and compare it with the previous recorded coordinates
 * </ul>
 * <b>Returns:</b></br>
 * {"x":0.0,"y":0.0,"z":-1.0,"type":"position_vector"}
 */
public class PositionVector extends Module {

	private Gson gson = new Gson();

	private LinkedList<PositionUnityJson> directions = new LinkedList<PositionUnityJson>();
	private String currentCardinalDirection = "";

	@TERM
	public String getDirectionsVector(String position) {

		Double lastX = null;
		Double lastY = null;
		Double lastZ = null;

		//initialize the response Object 
		PositionUnityJson responseVector = new PositionUnityJson();			
		responseVector.setX(AstraApi.ZERO);
		responseVector.setY(AstraApi.ZERO);
		responseVector.setZ(AstraApi.ZERO);
		
		if (!directions.isEmpty()) {
			
			// get the last coordinates if exist and remove it if size of list  > 10
			PositionUnityJson recordedCoordinates = null;
			if (directions.size() > 10) {
				recordedCoordinates = directions.pollLast();
			} else {
				recordedCoordinates = directions.getLast();
			}

			// add the current agent position
			directions.add(gson.fromJson(position, PositionUnityJson.class));
			
			lastX = recordedCoordinates.getX();
			lastY = recordedCoordinates.getY();
			lastZ = recordedCoordinates.getZ();			

			// get current coordinates
			PositionUnityJson coordinates = gson.fromJson(position, PositionUnityJson.class);

			//get the sign of the coordinates
			int signX = FormattingService.signBit(coordinates.getX().floatValue());
			int signY = FormattingService.signBit(coordinates.getY().floatValue());
			int signZ = FormattingService.signBit(coordinates.getZ().floatValue());
			
			//compare the absolute values with accuracy of 0.001, manipulate the coordinates and add the sign 
			if (lastX != null && !(Math.abs(coordinates.getX().doubleValue() - lastX.doubleValue()) < FormattingService.EPSILON)) {
				double valueX = (Math.abs(coordinates.getX().doubleValue()) > Math.abs(lastX.doubleValue()) &&  !(Math.abs(lastX.doubleValue() - coordinates.getX().doubleValue()) < FormattingService.EPSILON)) ? 
						           AstraApi.ONE : -AstraApi.ONE;
				
				responseVector.setX(signX == 0 ? new Double(valueX) : new Double(-valueX));
			} 
			if (lastY != null && !(Math.abs(coordinates.getY().doubleValue() - lastY.doubleValue()) < FormattingService.EPSILON)) {
				double valueY = (Math.abs(coordinates.getY().doubleValue()) > Math.abs(lastX.doubleValue()) && !(Math.abs(lastY.doubleValue() - coordinates.getY().doubleValue()) < FormattingService.EPSILON)) ? 
						           AstraApi.ONE : -AstraApi.ONE;
				
				responseVector.setY(signY == 0 ? new Double(valueY) : new Double(-valueY));
			} 
			if (lastZ != null && !(Math.abs(coordinates.getZ().doubleValue() - lastZ.doubleValue()) < FormattingService.EPSILON)) {
				double valueZ = (Math.abs(coordinates.getZ().doubleValue()) > Math.abs(lastX.doubleValue()) && !(Math.abs(lastZ.doubleValue() - coordinates.getZ().doubleValue()) < FormattingService.EPSILON)) ? 
						           AstraApi.ONE : -AstraApi.ONE;
				
				responseVector.setZ(signZ == 0 ? new Double(valueZ) : new Double(-valueZ));
			}

			//check if all three coordinates are zero, if so use the recorded cardinal direction to amend the appropriate axis
			checkForZeroVector(responseVector);
			return gson.toJson(responseVector);
			
		} else {
			
			// add the current/initial agent position
			directions.add(gson.fromJson(position, PositionUnityJson.class));
			
			PositionUnityJson initialPosition = gson.fromJson(position, PositionUnityJson.class);
						
			// this tell us which direction to go for the very first time
			String cardinalDirection = initialPosition.getCardinalDirection();
			//set the current cardinal direction
			currentCardinalDirection = cardinalDirection;
			
			setNonZeroAxis(responseVector, cardinalDirection);

			return gson.toJson(responseVector);
		}
	}

	private void checkForZeroVector(PositionUnityJson responseVector) {
		if (responseVector.getX() == AstraApi.ZERO && responseVector.getY() == AstraApi.ZERO && responseVector.getZ() == AstraApi.ZERO) {
			setNonZeroAxis(responseVector, currentCardinalDirection);
		} 
	}
	
	/**
	 * @param responseVector
	 * @param cardinalDirection
	 */
	private void setNonZeroAxis(PositionUnityJson responseVector, String cardinalDirection) {

		switch (cardinalDirection) {
		case AstraApi.SOUTH:
			responseVector.setZ(-AstraApi.ONE);
			break;
		case AstraApi.WEST:
			responseVector.setX(-AstraApi.ONE);
			break;
		case AstraApi.EAST:
			responseVector.setX(AstraApi.ONE);
			break;
		default:// default North
			responseVector.setZ(AstraApi.ONE);
		}
	}
}
