package api.modules;

import java.util.LinkedList;

import com.google.gson.Gson;

import api.AstraApi;
import api.modules.utils.FormattingService;
import api.modules.utils.UnityJson;
import astra.core.Module;

/**
 * Handles events of type 'position' sent from Unity. Based on the previous
 * coordinates the axis (x,y or z) are checked to determine the direction of
 * movement. The direction for the Agent is preserved and the coordinates that
 * had changed are returned with rate 0.7 added to it either negative or
 * positive back to Unity.</br>
 * </br>
 * 
 * <b>Expect input as a Json in the format of:</b></br>
 * <ul>
 * <li><b>very first time</b></li>
 * {"type":"position","scale":{"x":0.5,"y":1.0,"z":0.5},"position":{"x":1.649999976158142,"y":1.0,"z":2.700000047683716},"cardinalDirection":"South"}
 * <li><b>every next time</b></li>
 * {"type":"position","scale":{"x":0.5,"y":1.0,"z":0.5},"position":{"x":1.649999976158142,"y":1.0,"z":2.700000047683716}} </br>
 * and compare it with the previous recorded coordinates
 * </ul>
 * <b>Returns:</b></br>
 * {"position":{"x":1.649999976158142,"y":1.0,"z":2.200000047683716},"scale":{"x":0.5,"y":1.0,"z":0.5},"type":"position"}
 */
public class Position extends Module {

	private Gson gson = new Gson();

	private LinkedList<UnityJson> directions = new LinkedList<UnityJson>();

	@TERM
	public String getDirections(String json) {

		Double lastX = null;
		Double lastY = null;
		Double lastZ = null;

		if (!directions.isEmpty()) {
			// get the last coordinates if exist
			UnityJson recordedCoordinates = null;
			if (directions.size() > 7) {
				recordedCoordinates = directions.pollLast();
			} else {
				recordedCoordinates = directions.getLast();
			}

			api.modules.utils.Position position = recordedCoordinates.getPosition();
			
			lastX = position.getX();
			lastY = position.getY();
			lastZ = position.getZ();
			
			// add the current agent position
			directions.add(gson.fromJson(json, UnityJson.class));
		} else {

			UnityJson initialPosition = gson.fromJson(json, UnityJson.class);
			api.modules.utils.Position initailPositionJson = initialPosition.getPosition();
			// this tell us which direction to go for the very first time, when agent just
			// created
			String cardinalDirection = initialPosition.getCardinalDirection();

			switch (cardinalDirection) {
			case AstraApi.NORTH:
				initailPositionJson.setZ(initailPositionJson.getZ() + AstraApi.API_CHANGE_RATE);
				break;
			case AstraApi.SOUTH:
				initailPositionJson.setZ(initailPositionJson.getZ() - AstraApi.API_CHANGE_RATE);
				break;
			case AstraApi.WEST:
				initailPositionJson.setX(initailPositionJson.getX() - AstraApi.API_CHANGE_RATE);
				break;
			case AstraApi.EAST:
				initailPositionJson.setX(initailPositionJson.getX() + AstraApi.API_CHANGE_RATE);
				break;
			default:
			}

			// add the current/initial agent position
			directions.add(initialPosition);
            //return the position coordinates for the very first time  
			return gson.toJson(initialPosition);
		}

		// get current coordinates
		UnityJson coordinates = gson.fromJson(json, UnityJson.class);
		api.modules.utils.Position currentPositionJson = coordinates.getPosition();
		//get the sign of the coordinates
		int signX = FormattingService.signBit(currentPositionJson.getX().floatValue());
		int signY = FormattingService.signBit(currentPositionJson.getY().floatValue());
		int signZ = FormattingService.signBit(currentPositionJson.getZ().floatValue());
		
		//compare the absolute values with accuracy of 0.001, manipulate the coordinates and add the sign 
		if (lastX != null && !(Math.abs(lastX.floatValue() - currentPositionJson.getX().floatValue()) < FormattingService.EPSILON)) {
			double absValueX = (Math.abs(currentPositionJson.getX().floatValue()) > Math.abs(lastX.floatValue()) &&  !(Math.abs(lastX.floatValue() - currentPositionJson.getX().floatValue()) < FormattingService.EPSILON)) ? 
					           Math.abs(currentPositionJson.getX()) + AstraApi.API_CHANGE_RATE : Math.abs(currentPositionJson.getX()) - AstraApi.API_CHANGE_RATE;
			currentPositionJson.setX(signX == 0 ? new Double(absValueX) : new Double(-absValueX));
		}

		if (lastY != null && !(Math.abs(lastY.floatValue() - currentPositionJson.getY().floatValue()) < FormattingService.EPSILON)) {
			double absValueY = (Math.abs(currentPositionJson.getY().floatValue()) > Math.abs(lastY.floatValue()) && !(Math.abs(lastY.floatValue() - currentPositionJson.getY().floatValue()) < FormattingService.EPSILON)) ? 
					           Math.abs(currentPositionJson.getY()) + AstraApi.API_CHANGE_RATE : Math.abs(currentPositionJson.getY()) - AstraApi.API_CHANGE_RATE;
			currentPositionJson.setY(signY == 0 ? new Double(absValueY) : new Double(-absValueY));
		}
		
		if (lastZ != null && !(Math.abs(lastZ.floatValue() - currentPositionJson.getZ().floatValue()) < FormattingService.EPSILON)) {
			double absValueZ = (Math.abs(currentPositionJson.getZ().floatValue()) > Math.abs(lastZ.floatValue()) && !(Math.abs(lastZ.floatValue() - currentPositionJson.getZ().floatValue()) < FormattingService.EPSILON)) ? 
					           Math.abs(currentPositionJson.getZ()) + AstraApi.API_CHANGE_RATE : Math.abs(currentPositionJson.getZ()) - AstraApi.API_CHANGE_RATE;
			currentPositionJson.setZ(signZ == 0 ? new Double(absValueZ) : new Double(-absValueZ));
		}

		return gson.toJson(coordinates);
	}
}
