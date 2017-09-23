package api.modules;

import java.util.LinkedList;

import com.google.gson.Gson;

import api.AstraApi;
import api.modules.utils.FormattingService;
import api.modules.utils.PositionUnityJson;
import astra.core.Module;

/**
 * Handles events of type 'position' sent from Unity. Based on the previous
 * coordinates the axis (x,y or z) are checked to determine the direction of
 * movement. The direction for the Agent is preserved and the coordinates that
 * had changed are returned with rate 0.5 added to it either negative or
 * positive back to Unity.</br>
 * </br>
 * 
 * <b>Expect input as a Json in the format of:</b></br>
 * <ul>
 * <li><b>very first time</b></li>
 * {"type":"position","x":1.649999976158142,"y":1.0,"z":2.700000047683716,"cardinalDirection":"South"}
 * <li><b>every next time</b></li>
 * {"type":"position","x":1.649999976158142,"y":1.0,"z":2.700000047683716} </br>
 * and compare it with the previous recorded coordinates
 * </ul>
 * <b>Returns:</b></br>
 * {"x":1.649999976158142,"y":1.0,"z":2.200000047683716,"type":"position"}
 */
public class Position extends Module {

	private Gson gson = new Gson();

	private LinkedList<PositionUnityJson> directions = new LinkedList<PositionUnityJson>();

	@TERM
	public String getDirections(String position) {

		Double lastX = null;
		Double lastY = null;
		Double lastZ = null;

		if (!directions.isEmpty()) {
			// get the last coordinates if exist
			PositionUnityJson recordedCoordinates = null;
			if (directions.size() > 7) {
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
			// this tell us which direction to go for the very first time, when agent just
			// created
			String cardinalDirection = initialPosition.getCardinalDirection();

			switch (cardinalDirection) {
			case AstraApi.NORTH:
				initialPosition.setZ(initialPosition.getZ() + AstraApi.API_CHANGE_RATE);
				break;
			case AstraApi.SOUTH:
				initialPosition.setZ(initialPosition.getZ() - AstraApi.API_CHANGE_RATE);
				break;
			case AstraApi.WEST:
				initialPosition.setX(initialPosition.getX() - AstraApi.API_CHANGE_RATE);
				break;
			case AstraApi.EAST:
				initialPosition.setX(initialPosition.getX() + AstraApi.API_CHANGE_RATE);
				break;
			default:
			}

			// add the current/initial agent position
			directions.add(initialPosition);
            //return the position coordinates for the very first time  
			return gson.toJson(initialPosition);
		}

		// get current coordinates
		PositionUnityJson coordinates = gson.fromJson(position, PositionUnityJson.class);
		
		//get the sign of the coordinates
		int signX = FormattingService.signBit(coordinates.getX().floatValue());
		int signY = FormattingService.signBit(coordinates.getY().floatValue());
		int signZ = FormattingService.signBit(coordinates.getZ().floatValue());
		
		//compare the absolute values, manipulate the coordinates and add the sign 
		if (lastX != null && (lastX.doubleValue() != coordinates.getX().doubleValue())) {
			double absValueX = Math.abs(coordinates.getX()) > Math.abs(lastX.doubleValue()) ? Math.abs(coordinates.getX()) + AstraApi.API_CHANGE_RATE : Math.abs(coordinates.getX()) - AstraApi.API_CHANGE_RATE;
			coordinates.setX(signX == 0 ? new Double(absValueX) : new Double(-absValueX));
		}

		if (lastY != null && (lastY.doubleValue() != coordinates.getY().doubleValue())) {
			double absValueY = Math.abs(coordinates.getY()) > Math.abs(lastX.doubleValue()) ? Math.abs(coordinates.getY()) + AstraApi.API_CHANGE_RATE : Math.abs(coordinates.getY()) - AstraApi.API_CHANGE_RATE;
			coordinates.setY(signY == 0 ? new Double(absValueY) : new Double(-absValueY));
		}
		
		if (lastZ != null && (lastZ.doubleValue() != coordinates.getZ().doubleValue())) {
			double absValueZ = Math.abs(coordinates.getZ()) > Math.abs(lastX.doubleValue()) ? Math.abs(coordinates.getZ()) + AstraApi.API_CHANGE_RATE : Math.abs(coordinates.getZ()) - AstraApi.API_CHANGE_RATE;
			coordinates.setZ(signZ == 0 ? new Double(absValueZ) : new Double(-absValueZ));
		}

		return gson.toJson(coordinates);
	}
}
