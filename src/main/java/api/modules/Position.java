package api.modules;

import java.util.LinkedList;

import com.google.gson.Gson;

import api.modules.utils.Coordinates;
import astra.core.Module;

/**
 * Handles events of type 'position' sent from Unity. Based on the previous
 * coordinates the axis (x,y or z) are checked to determine the direction of
 * movement. The direction for the Agent is preserved and the coordinates that
 * had changed are returned with velocity 0.25 where for the rest zero is passed
 * back to Unity.
 */
public class Position extends Module {

	private Gson gson = new Gson();

	private static LinkedList<String> directions = new LinkedList<String>();

	public static final Double API_MARGIN = new Double("0.25");
	public static final Double ZERO_CHANGE = new Double("0");
	public static final Double INITIAL_CHANGE = new Double("0.1");

	@TERM
	public String getDirections(String position) {
		Double lastX = null;
		Double lastY = null;
		Double lastZ = null;

		Coordinates recordedCoordinates = null;
				
		// get the last agent position if exist
		if (!directions.isEmpty()) {
			recordedCoordinates = gson.fromJson(directions.pollLast(), Coordinates.class);
			lastX = recordedCoordinates.getX();
			lastY = recordedCoordinates.getY();
			lastZ = recordedCoordinates.getZ();
		} else {
			// add the current/initial agent position
			directions.add(position);
			recordedCoordinates = gson.fromJson(directions.pollLast(), Coordinates.class);
			lastX = recordedCoordinates.getX();
			lastY = recordedCoordinates.getY();
			// modify the initial position by altering the 'z' coordinates
			lastZ = new Double(recordedCoordinates.getZ().doubleValue() - INITIAL_CHANGE.doubleValue());
		}

		// get current position
		Coordinates coordinates = gson.fromJson(position, Coordinates.class);

		//decide in which direction was moving the agent and set the relevant coordinate axis  
		if (lastX != null && lastX.doubleValue() != coordinates.getX().doubleValue()) {
			coordinates.setX(API_MARGIN);
		} else {
			coordinates.setX(ZERO_CHANGE);
		}

		if (lastY != null && lastY.doubleValue() != coordinates.getY().doubleValue()) {
			coordinates.setY( API_MARGIN);
		} else {
			coordinates.setY(ZERO_CHANGE);
		}
		
		if (lastZ != null && lastZ.doubleValue() != coordinates.getZ().doubleValue()) {
			coordinates.setZ(API_MARGIN);
		} else {
			coordinates.setZ(ZERO_CHANGE);
		}

		return gson.toJson(coordinates);
	}
}
