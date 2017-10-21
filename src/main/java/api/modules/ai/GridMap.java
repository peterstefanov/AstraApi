package api.modules.ai;

import java.util.LinkedHashSet;

import com.google.gson.Gson;

import api.EventType;
import api.modules.utils.Position;
import api.modules.utils.UnityJson;
import astra.core.Module;

public class GridMap extends Module {

	private Gson gson = new Gson();
	//use LinkedHashSet to avoid duplicates and preserve the order
	//make it static to be available to all agents
	static LinkedHashSet<Coordinates> breadCrumbs = new LinkedHashSet<Coordinates>();


	/**
	 * The goal of this is to record each move made by the agent/s, thus
	 * an knowledge map is build which can be used to navigate directly to the 
	 * end of the maze. Position and collisions are recorded, where when 
	 * collision occurs the blocked side in the cell is recorded.
	 * @param event
	 * @return the same event
	 */
	@ACTION
	public boolean updateGridMap(String eventType, String event) {
		
		//get current collision/position passed from Unity
		UnityJson requestFromUnity = gson.fromJson(event, UnityJson.class);
        Position position = requestFromUnity.getPosition();
        
        //which direction is blocked in the cell, only if its a collision event record the data
        String cardinalDirection = eventType.equals(EventType.COLLISION) ? requestFromUnity.getCardinalDirection() : null;

        //round the double we are concerned 
        Coordinates coord = new Coordinates((int) Math.round(position.getX()), (int) Math.round(position.getZ()), cardinalDirection);
        breadCrumbs.add(coord);
		
		if (!breadCrumbs.isEmpty()) {
			for (Coordinates coords : breadCrumbs) {
				System.out.println(coords.toString());
			}
		}

        return true;
	}
	
	/**
	 * Get the size of the Set, if enough data is generated the 
	 * Agent should know.
	 * @return int - size
	 */
	@TERM
	public int getMapSize() {
		return breadCrumbs.size();
	}
	
	@TERM
	public String getBreadCrumbs() {
		return gson.toJson(breadCrumbs);
	}
}
