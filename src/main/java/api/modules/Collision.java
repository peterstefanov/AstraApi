package api.modules;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import com.google.gson.Gson;

import api.modules.utils.CollisionUnityJson;
import astra.core.Module;

public class Collision extends Module {

	private Gson gson = new Gson();
	private HashMap<String, LinkedList<CollisionUnityJson>> cardinalDirections = new HashMap<String, LinkedList<CollisionUnityJson>>();
	

	public static final String NORTH = "North";
	public static final String EAST = "East";
	public static final String SOUTH = "South";
	public static final String WEST = "West";
	public static final String[] CARDINAL_DIRECTION = {NORTH, EAST, SOUTH, WEST};
	
	@TERM
	public String getDirections(String unityCollision) {
		//////////////////////////////////TEST////////////////////////////////
		LinkedList<CollisionUnityJson> testList = new LinkedList<CollisionUnityJson>();
		CollisionUnityJson ss = new CollisionUnityJson();
		ss.setCardinalDirection(NORTH);
		ss.setInstanceId(9670);
		ss.setX(1d);
		ss.setY(1d);
		ss.setZ(1d);
		
		CollisionUnityJson ss1 = new CollisionUnityJson();
		ss1.setCardinalDirection(SOUTH);
		ss1.setInstanceId(9670);
		ss1.setX(1d);
		ss1.setY(1d);
		ss1.setZ(1d);
		
		CollisionUnityJson ss2 = new CollisionUnityJson();
		ss2.setCardinalDirection(EAST);
		ss2.setInstanceId(9670);
		ss2.setX(1d);
		ss2.setY(1d);
		ss2.setZ(1d);
		
		
		testList.add(ss);
		testList.add(ss1);
		testList.add(ss2);
		//test
		cardinalDirections.put("9670South", testList);
       //////////////////////////////////TEST////////////////////////////////
		
		List<String> listCardinals = new ArrayList<String>(Arrays.asList(CARDINAL_DIRECTION));
		// get current collision passed from Unity
		CollisionUnityJson collision = gson.fromJson(unityCollision, CollisionUnityJson.class);
		
		String cardinalDirection = collision.getCardinalDirection();
		int instanceId = collision.getInstanceId();
        //construct key for the map		
		String key = instanceId + cardinalDirection;


		CollisionUnityJson collisionToUnityJson = new CollisionUnityJson();
		LinkedList<CollisionUnityJson> collisionPerInstance = null;

		// get the last agent collision data if exist
		if (!cardinalDirections.isEmpty()) {
			LinkedList<CollisionUnityJson> list = cardinalDirections.get(key);
			
			//iterate and remove the last cardinal directions that were send to Unity
			//until one left  
			Iterator<CollisionUnityJson> it = list.descendingIterator();
			while(it.hasNext()) {
				CollisionUnityJson item = (CollisionUnityJson) it.next();
				if (listCardinals.size() == 1) {
					break;
				}				
				if (listCardinals.contains(item.getCardinalDirection())) {
					listCardinals.remove(item.getCardinalDirection());
				}
			}
  
			if (listCardinals.size() == 1) {
				collisionToUnityJson.setCardinalDirection(listCardinals.get(0));
			} else {
				Random random = new Random();
				int index = random.nextInt(listCardinals.size());
				collisionToUnityJson.setCardinalDirection(listCardinals.get(index));
			}
			
			Random random = new Random();
			int index = random.nextInt(listCardinals.size());
			collisionToUnityJson.setCardinalDirection(listCardinals.get(index));
			
			collisionToUnityJson.setInstanceId(collision.getInstanceId());
			//based on cardinal direction modify coordinates
			collisionToUnityJson.setX(collision.getX());
			collisionToUnityJson.setY(collision.getY());
			collisionToUnityJson.setZ(collision.getZ());
			
			return gson.toJson(collisionToUnityJson);
			
		} else {
			
			collisionToUnityJson.setInstanceId(collision.getInstanceId());
			//based on cardinal direction modify coordinates
			collisionToUnityJson.setX(collision.getX());
			collisionToUnityJson.setY(collision.getY());
			collisionToUnityJson.setZ(collision.getZ());
			
			return gson.toJson(collisionToUnityJson);
		}
	}
}
