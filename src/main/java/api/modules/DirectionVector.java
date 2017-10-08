package api.modules;

import java.util.LinkedList;

import com.google.gson.Gson;

import api.AstraApi;
import api.modules.utils.UnityJson;
import astra.core.Module;


/**
 * Handles events of type 'direction_vector' sent from Unity. Based on the cardinal direction 
 * returns the Vector 3 coordinates. If no cardinal direction passed from Unity, assumption is that
 * direction isn't change, return the last one.
 */
public class DirectionVector extends Module {

	private Gson gson = new Gson();

	private LinkedList<UnityJson> directionsVector = new LinkedList<UnityJson>();

	@TERM
	public String getDirections(String position) {

		UnityJson directionVector = gson.fromJson(position, UnityJson.class);
		// this tell us which direction to go for the very first time, when agent just
		// created
		String cardinalDirection = directionVector.getCardinalDirection();

		api.modules.utils.Position positionJson = directionVector.getPosition();
		
		positionJson.setX(AstraApi.ZERO);
		positionJson.setY(AstraApi.ZERO);
		positionJson.setZ(AstraApi.ZERO);
		
		if (cardinalDirection != null && cardinalDirection.length() > 0) {
			switch (cardinalDirection) {
			case AstraApi.NORTH:
				positionJson.setZ(AstraApi.ONE);
				break;
			case AstraApi.SOUTH:
				positionJson.setZ(- AstraApi.ONE);
				break;
			case AstraApi.WEST:
				positionJson.setX(- AstraApi.ONE);
				break;
			case AstraApi.EAST:
				positionJson.setX(AstraApi.ONE);
				break;
			default:
			}
			
			// add the current/initial agent position
			directionsVector.add(directionVector);

			return gson.toJson(directionVector);
		} else {
			
			UnityJson recordedDirectionVector = null;
			if (directionsVector.size() > 10) {
				recordedDirectionVector = directionsVector.pollLast();
			} else {
				recordedDirectionVector = directionsVector.getLast();
			}
			
			return gson.toJson(recordedDirectionVector);
		}
	}
}
