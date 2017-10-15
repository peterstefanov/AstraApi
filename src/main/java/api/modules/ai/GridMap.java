package api.modules.ai;

import java.util.LinkedHashSet;
import java.util.Set;

import com.google.gson.Gson;

import api.modules.utils.Position;
import api.modules.utils.UnityJson;
import astra.core.Module;

public class GridMap extends Module {

	private Gson gson = new Gson();
	//use LinkedHashSet to avoid duplicates and preserve the order
	private LinkedHashSet<Coordinates> breadCrumbs = new LinkedHashSet<Coordinates>();


	@TERM
	public String updateGridMap(String event) {

		if (!breadCrumbs.isEmpty()) {
			for (Coordinates coord : breadCrumbs) {
				System.out.println(coord.toString());
			}
		}
		//get current collision passed from Unity
		UnityJson requestFromUnity = gson.fromJson(event, UnityJson.class);
        Position position = requestFromUnity.getPosition();
        //which direction is blocked in the cell, if its a collision event
        String cardinalDirection = requestFromUnity.getCardinalDirection();
        
        Coordinates coord = new Coordinates((int) Math.round(position.getX()), (int) Math.round(position.getZ()), cardinalDirection);
        breadCrumbs.add(coord);
		
        System.out.println(event);
        return event;
	}
		
	public Set<Coordinates> getBreadCrumbs() {
		return breadCrumbs;
	}
}
