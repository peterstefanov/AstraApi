package api.modules;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import com.google.gson.Gson;

import api.AstraApi;
import api.modules.utils.Position;
import api.modules.utils.UnityJson;
import astra.core.Module;

/**
 * Handles events of type 'collision' sent from Unity. The input should contains
 * all three coordinates x, y and z; the object instance id and the cardinal
 * direction as an event data. Based on the previous five collisions event's
 * data, we determine the direction of movement.</br>
 * </br>
 * The last five event's data are stored in a map with concatenated
 * <code>instanceId</code> and <code>cardinalDirection</code> as a key and an
 * ordered list of all decisions made by Astra as a value. This way we do have
 * the ability to distinguish between different collisions for the same
 * agent.</br>
 * </br>
 * The <code>cardinalDirection</code> is the direction of movement when the
 * Unity player collided, this is ruled out as a possible output from Astra. The
 * relevant axis coordinate is changed and returned with rate 0.7 added to it
 * either negative or positive, which indicates the direction of movement in
 * regards to the coordinates received from Unity. </br>
 * </br>
 * Also Astra provides an <code>astraCardinalDirection</code> as a String value
 * back to Unity.</br>
 * </br>
 * 
 * <b>Expect input as a Json in the format of:</b></br>
 * {"x":1.649999976158142,"y":1.0,"z":2.700000047683716,"instanceId":9670,"cardinalDirection":South}</br>
 * <b>Returns:</b></br>
 * <ul>
 * <li><b>North</b></li>
 * {"x":1.649999976158142,"y":1.0,"z":3.200000047683716,"cardinalDirection":"South","instanceId":9670,"astraCardinalDirection":"North"}
 * <li><b>or West</b></li>
 * {"x":1.149999976158142,"y":1.0,"z":2.700000047683716,"cardinalDirection":"South","instanceId":9670,"astraCardinalDirection":"West"}
 * <li><b>or East</b></li>
 * {"x":2.149999976158142,"y":1.0,"z":2.700000047683716,"cardinalDirection":"South","instanceId":9670,"astraCardinalDirection":"East"}
 * </ul>
 */
public class Collision extends Module {

	private Gson gson = new Gson();
	// map with object id and cardinal direction as a key and an ordered list of all
	// last decisions as a value
	private HashMap<String, LinkedList<UnityJson>> cardinalDirections = new HashMap<String, LinkedList<UnityJson>>();

	@TERM
	public String getDirections(String event) {

		List<String> listCardinals = new ArrayList<String>(Arrays.asList(AstraApi.CARDINAL_DIRECTION));
		// get current collision passed from Unity
		UnityJson requestFromUnity = gson.fromJson(event, UnityJson.class);
        Position reuestFromUnityPosition = requestFromUnity.getPosition();
        
		String cardinalDirection = requestFromUnity.getCardinalDirection();
		int instanceId = requestFromUnity.getInstanceId();
		// construct key for the map
		String key = instanceId + cardinalDirection;

		UnityJson responseFromAstra = new UnityJson();
		LinkedList<UnityJson> responseFromAstraList = null;

		// remove the current cardinal direction passed from Unity
		listCardinals.remove(requestFromUnity.getCardinalDirection());

		responseFromAstra.setInstanceId(requestFromUnity.getInstanceId());
		responseFromAstra.setCardinalDirection(requestFromUnity.getCardinalDirection());
        responseFromAstra.setPosition(reuestFromUnityPosition);
        
		// get the last agent collision data if exist
		if (cardinalDirections.isEmpty() || (!cardinalDirections.isEmpty() && cardinalDirections.get(key) == null)) {

			// get random index for the remaining cardinal direction
			int randomIndex = ThreadLocalRandom.current().nextInt(0, listCardinals.size());
			// set random cardinal direction to be passed to Unity
			responseFromAstra.setAstraCardinalDirection(listCardinals.get(randomIndex));
			updateCoordinates(responseFromAstra, requestFromUnity);
			
			// record the decision and synchronize it 
			responseFromAstraList = new LinkedList<UnityJson>();
			List<UnityJson> requestsLinkedList = Collections.synchronizedList(responseFromAstraList);
			
			synchronized (requestsLinkedList) {
				responseFromAstraList.addLast(responseFromAstra);
			}
			
			cardinalDirections.put(key, responseFromAstraList);

			return gson.toJson(responseFromAstra);

		} else {

			LinkedList<UnityJson> currentResponseFromAstraList = cardinalDirections.get(key);

			// Iterate through the list in reverse order
			Iterator<UnityJson> it = currentResponseFromAstraList.descendingIterator();
			while (it.hasNext()) {
				UnityJson item = (UnityJson) it.next();
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
			responseFromAstra.setAstraCardinalDirection(listCardinals.get(randomIndex));
			updateCoordinates(responseFromAstra, requestFromUnity);
			
			// record the decision and synchronize it 
			List<UnityJson> requestsLinkedList = Collections.synchronizedList(currentResponseFromAstraList);
			
			synchronized (requestsLinkedList) {
				currentResponseFromAstraList.addLast(responseFromAstra);
			}
						
			// check if the list exceed five entry and remove from the tail
			if (currentResponseFromAstraList.size() > 4) {
				currentResponseFromAstraList.removeFirst();
			}
			cardinalDirections.put(key, currentResponseFromAstraList);
			return gson.toJson(responseFromAstra);
		}
	}

	private void updateCoordinates(UnityJson responseFromAstra, UnityJson requestFromUnity) {
		String cardinalDirection = responseFromAstra.getAstraCardinalDirection();

		api.modules.utils.Position position = responseFromAstra.getPosition();
		
		position.setX(position.getX());
		position.setY(position.getY());
		position.setZ(position.getZ());

		// based on astra cardinal direction modify coordinates
		switch (cardinalDirection) {
		case AstraApi.NORTH:
			position.setZ(position.getZ() + AstraApi.API_CHANGE_RATE);
			break;
		case AstraApi.SOUTH:
			position.setZ(position.getZ() - AstraApi.API_CHANGE_RATE);
			break;
		case AstraApi.WEST:
			position.setX(position.getX() - AstraApi.API_CHANGE_RATE);
			break;
		default:
			position.setX(position.getX() + AstraApi.API_CHANGE_RATE);
		}
	}
}
