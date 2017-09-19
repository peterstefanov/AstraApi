package api.modules;

import java.util.LinkedList;

import com.google.gson.Gson;

import api.AstraApi;
import api.modules.utils.PositionUnityJson;
import astra.core.Module;


/**
 * Handles events of type 'direction_vector' sent from Unity. Based on the cardinal direction 
 * returns the Vector 3 coordinates. If no cardinal direction passed from Unity, assumption is that
 * direction isn't change, return the last one.
 */
public class DirectionVector extends Module {

	private Gson gson = new Gson();

	private static LinkedList<PositionUnityJson> directionsVector = new LinkedList<PositionUnityJson>();

	@TERM
	public String getDirections(String position) {

		PositionUnityJson directionVector = gson.fromJson(position, PositionUnityJson.class);
		// this tell us which direction to go for the very first time, when agent just
		// created
		String cardinalDirection = directionVector.getCardinalDirection();

		directionVector.setX(AstraApi.ZERO_CHANGE);
		directionVector.setY(AstraApi.ZERO_CHANGE);
		directionVector.setZ(AstraApi.ZERO_CHANGE);
		
		if (cardinalDirection != null && cardinalDirection.length() > 0) {
			switch (cardinalDirection) {
			case AstraApi.NORTH:
				directionVector.setZ(AstraApi.DIRECTION);
				break;
			case AstraApi.SOUTH:
				directionVector.setZ(- AstraApi.DIRECTION);
				break;
			case AstraApi.WEST:
				directionVector.setX(- AstraApi.DIRECTION);
				break;
			case AstraApi.EAST:
				directionVector.setX(AstraApi.DIRECTION);
				break;
			default:
			}
			
			// add the current/initial agent position
			directionsVector.add(directionVector);

			return gson.toJson(directionVector);
		} else {
			
			PositionUnityJson recordedDirectionVector = null;
			if (directionsVector.size() > 10) {
				recordedDirectionVector = directionsVector.pollLast();
			} else {
				recordedDirectionVector = directionsVector.getLast();
			}
			
			return gson.toJson(recordedDirectionVector);
		}
	}
}
