package api.modules;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import com.google.gson.Gson;

import api.AstraApi;
import api.modules.utils.CollisionUnityJson;
import api.modules.utils.FormattingService;
import astra.core.Module;

public class Collision extends Module {

	private Gson gson = new Gson();
	// map with object id and cardinal direction as a key and ordered list of all last decisions
	private HashMap<String, LinkedList<CollisionUnityJson>> cardinalDirections = new HashMap<String, LinkedList<CollisionUnityJson>>();

	@TERM
	public String getDirections(String unityCollision) {

		List<String> listCardinals = new ArrayList<String>(Arrays.asList(AstraApi.CARDINAL_DIRECTION));
		// get current collision passed from Unity
		CollisionUnityJson collision = gson.fromJson(unityCollision, CollisionUnityJson.class);

		String cardinalDirection = collision.getCardinalDirection();
		int instanceId = collision.getInstanceId();
		// construct key for the map
		String key = instanceId + cardinalDirection;

		CollisionUnityJson collisionToUnityJson = new CollisionUnityJson();
		LinkedList<CollisionUnityJson> collisionPerInstance = null;

		// remove the current cardinal direction passed from Unity
		listCardinals.remove(collision.getCardinalDirection());

		collisionToUnityJson.setInstanceId(collision.getInstanceId());
		collisionToUnityJson.setCardinalDirection(collision.getCardinalDirection());

		// get the last agent collision data if exist
		if (cardinalDirections.isEmpty() || (!cardinalDirections.isEmpty() && cardinalDirections.get(key) == null)) {

			// get random index for the remaining cardinal direction
			int randomIndex = ThreadLocalRandom.current().nextInt(0, listCardinals.size());
			// set random cardinal direction to be passed to Unity
			collisionToUnityJson.setAstraCardinalDirection(listCardinals.get(randomIndex));
            updateCoordinates(collisionToUnityJson, collision);
			// record the decision
			collisionPerInstance = new LinkedList<CollisionUnityJson>();
			collisionPerInstance.add(collisionToUnityJson);
			cardinalDirections.put(key, collisionPerInstance);

			return gson.toJson(collisionToUnityJson);

		} else {

			LinkedList<CollisionUnityJson> list = cardinalDirections.get(key);

			// Iterate through the list in reverse order
			Iterator<CollisionUnityJson> it = list.descendingIterator();
			while (it.hasNext()) {
				CollisionUnityJson item = (CollisionUnityJson) it.next();
				// if two left from the base cardinal directions break the loop
				if (listCardinals.size() == 2) {
					break;
				}
				// remove from the list of base cardinal direction if not yet removed
				if (listCardinals.contains(item.getAstraCardinalDirection())) {
					listCardinals.remove(item.getAstraCardinalDirection());
				}
			}

			// get random index for the remaining cardinal direction
			int randomIndex = ThreadLocalRandom.current().nextInt(0, listCardinals.size());
			// set random cardinal direction to be passed to Unity
			collisionToUnityJson.setAstraCardinalDirection(listCardinals.get(randomIndex));
			updateCoordinates(collisionToUnityJson, collision);
			// record the decision
			list.add(collisionToUnityJson);

			// check if the list exceed three entry and remove from the tail
			if (list.size() > 2) {
				list.removeFirst();
			}
			cardinalDirections.put(key, list);
			return gson.toJson(collisionToUnityJson);
		}
	}

	private void updateCoordinates(CollisionUnityJson collisionToUnityJson, CollisionUnityJson collision) {
		String cardinalDirection = collisionToUnityJson.getAstraCardinalDirection();
		
		// based on astra cardinal direction modify coordinates
		if (cardinalDirection == AstraApi.NORTH) {
			collisionToUnityJson.setX(FormattingService.formatDecimal(collision.getX()));
			collisionToUnityJson.setY(FormattingService.formatDecimal(collision.getY()));
			collisionToUnityJson.setZ(FormattingService.formatDecimal(collision.getZ() - AstraApi.API_CHANGE_RATE));
		} else if (cardinalDirection == AstraApi.SOUTH) {
			collisionToUnityJson.setX(FormattingService.formatDecimal(collision.getX()));
			collisionToUnityJson.setY(FormattingService.formatDecimal(collision.getY()));
			collisionToUnityJson.setZ(FormattingService.formatDecimal(collision.getZ() + AstraApi.API_CHANGE_RATE));
		} else if (cardinalDirection == AstraApi.WEST) {
			collisionToUnityJson.setX(FormattingService.formatDecimal(collision.getX() - AstraApi.API_CHANGE_RATE));
			collisionToUnityJson.setY(FormattingService.formatDecimal(collision.getY()));
			collisionToUnityJson.setZ(FormattingService.formatDecimal(collision.getZ()));
		} else {
			collisionToUnityJson.setX(FormattingService.formatDecimal(collision.getX() + AstraApi.API_CHANGE_RATE));
			collisionToUnityJson.setY(FormattingService.formatDecimal(collision.getY()));
			collisionToUnityJson.setZ(FormattingService.formatDecimal(collision.getZ()));
		}		
	}
}
