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
 * had changed are returned with rate 0.5 added to it either negative or positive 
 * back to Unity.
 */
public class Position extends Module {

	private Gson gson = new Gson();

	private static LinkedList<PositionUnityJson> directions = new LinkedList<PositionUnityJson>();

	@TERM
	public String getDirections(String position) {
		
		Double lastX = null;
		Double lastY = null;
		Double lastZ = null;
		

		if (!directions.isEmpty()) {
			//get the last coordinates if exist
			PositionUnityJson recordedCoordinates = directions.pollLast();
			lastX = recordedCoordinates.getX();
			lastY = recordedCoordinates.getY();
			lastZ = recordedCoordinates.getZ();
		} else {

			PositionUnityJson initialPosition = gson.fromJson(position, PositionUnityJson.class);
			//this tell us which direction to go for the very first time, when agent just created 
			String cardinalDirection = initialPosition.getCardinalDirection();
			
			switch (cardinalDirection) {
			    case AstraApi.NORTH:
				    initialPosition.setZ(initialPosition.getZ() - AstraApi.INITIAL_CHANGE);
				    break;
			    case AstraApi.SOUTH:
				    initialPosition.setZ(initialPosition.getZ() + AstraApi.INITIAL_CHANGE);
				    break;
			    case AstraApi.WEST:
				    initialPosition.setX(initialPosition.getX() - AstraApi.INITIAL_CHANGE);
				    break;
			    case AstraApi.EAST:
				    initialPosition.setX(initialPosition.getX() + AstraApi.INITIAL_CHANGE);
				    break;
			    default:
				
			}
		
			// add the current/initial agent position
			directions.add(initialPosition);
			
			return gson.toJson(initialPosition);
		}

		//get current coordinates
		PositionUnityJson coordinates = gson.fromJson(position, PositionUnityJson.class);		

		if (lastX != null) {
			if (lastX.doubleValue() == coordinates.getX().doubleValue()) {
				coordinates.setX(coordinates.getX());
			} else {
				coordinates.setX(coordinates.getX() >= 0 ? coordinates.getX() + (AstraApi.API_CHANGE_RATE) : coordinates.getX() + (-AstraApi.API_CHANGE_RATE));
			}			
		} else {
			coordinates.setX(coordinates.getX());
		}
		
		if (lastZ != null) {
			if (lastZ.doubleValue() == coordinates.getZ().doubleValue()) {
				coordinates.setZ(coordinates.getZ());
			} else {
				coordinates.setZ(coordinates.getZ() >= 0 ? coordinates.getZ() + (AstraApi.API_CHANGE_RATE) : coordinates.getZ() + (-AstraApi.API_CHANGE_RATE));
			}			
		} else {
			coordinates.setZ(coordinates.getZ());
		}
		
		if (lastY != null) {
			if (lastY.doubleValue() == coordinates.getY().doubleValue()) {
				coordinates.setY(coordinates.getY());
			} else {
				coordinates.setY(coordinates.getY() >= 0 ? coordinates.getY() + (AstraApi.API_CHANGE_RATE) : coordinates.getY() + (-AstraApi.API_CHANGE_RATE));
			}			
		} else {
			coordinates.setY(coordinates.getY());
		}
				
		return gson.toJson(coordinates);
	}
}
