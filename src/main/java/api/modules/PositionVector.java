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
 * returns the direction vector. 
 */
public class PositionVector extends Module {

	private Gson gson = new Gson();

	private static LinkedList<PositionUnityJson> directions = new LinkedList<PositionUnityJson>();

	@TERM
	public String getDirectionsVector(String position) {

		Double lastX = null;
		Double lastY = null;
		Double lastZ = null;

		if (!directions.isEmpty()) {
			// get the last coordinates if exist and remove it if size of list  > 10
			PositionUnityJson recordedCoordinates = null;
			if (directions.size() > 10) {
				recordedCoordinates = directions.pollLast();
			} else {
				recordedCoordinates = directions.getLast();
			}

			lastX = recordedCoordinates.getX();
			lastY = recordedCoordinates.getY();
			lastZ = recordedCoordinates.getZ();
			// add the current agent position
			directions.add(gson.fromJson(position, PositionUnityJson.class));
		} else {
			
			PositionUnityJson initialPosition = gson.fromJson(position, PositionUnityJson.class);
			
			// add the current/initial agent position
			directions.add(initialPosition);
			
			//initlize the response Object 
			PositionUnityJson directionVector = new PositionUnityJson();			
			directionVector.setX(AstraApi.ZERO_CHANGE);
			directionVector.setY(AstraApi.ZERO_CHANGE);
			directionVector.setZ(AstraApi.ZERO_CHANGE);
						
			// this tell us which direction to go for the very first time, when agent just
			// created
			String cardinalDirection = initialPosition.getCardinalDirection();

			if (cardinalDirection != null && cardinalDirection.length() > 0) {
				switch (cardinalDirection) {
				case AstraApi.NORTH:
					directionVector.setZ(AstraApi.DIRECTION);
					break;
				case AstraApi.SOUTH:
					directionVector.setZ(-AstraApi.DIRECTION);
					break;
				case AstraApi.WEST:
					directionVector.setX(-AstraApi.DIRECTION);
					break;
				case AstraApi.EAST:
					directionVector.setX(AstraApi.DIRECTION);
					break;
				default:
				}
			}

			return gson.toJson(directionVector);
		}

		// get current coordinates
		PositionUnityJson coordinates = gson.fromJson(position, PositionUnityJson.class);
		
		//get the sign of the coordinates
		int signX = FormattingService.signBit(coordinates.getX().floatValue());
		int signY = FormattingService.signBit(coordinates.getY().floatValue());
		int signZ = FormattingService.signBit(coordinates.getZ().floatValue());
		
		//compare absolute values to distinguish the direction
		if (lastX != null && (lastX.doubleValue() != coordinates.getX().doubleValue())) {
			double valueX = Math.abs(coordinates.getX()) >= Math.abs(lastX.doubleValue()) ? AstraApi.DIRECTION : -AstraApi.DIRECTION;
			coordinates.setX(signX == 0 ? new Double(valueX) : new Double(-valueX));
			coordinates.setY(AstraApi.ZERO_CHANGE);
			coordinates.setZ(AstraApi.ZERO_CHANGE);
		} else if (lastY != null && (lastY.doubleValue() != coordinates.getY().doubleValue())) {
			double valueY = Math.abs(coordinates.getY()) >= Math.abs(lastY.doubleValue()) ? AstraApi.DIRECTION : -AstraApi.DIRECTION;
			coordinates.setY(signY == 0 ? new Double(valueY) : new Double(-valueY));
			coordinates.setX(AstraApi.ZERO_CHANGE);
			coordinates.setZ(AstraApi.ZERO_CHANGE);
		} else if (lastZ != null && (lastZ.doubleValue() != coordinates.getZ().doubleValue())) {
			double valueZ = Math.abs(coordinates.getZ()) >= Math.abs(lastZ.doubleValue()) ? AstraApi.DIRECTION : -AstraApi.DIRECTION;
			coordinates.setZ(signZ == 0 ? new Double(valueZ) : new Double(-valueZ));
			coordinates.setX(AstraApi.ZERO_CHANGE);
			coordinates.setY(AstraApi.ZERO_CHANGE);
		}

		return gson.toJson(coordinates);
	}
}
