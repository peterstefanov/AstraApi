package api.modules.ai;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import com.google.gson.Gson;

import api.AstraApi;
import api.EventType;
import api.modules.utils.FormattingService;
import api.modules.utils.Position;
import api.modules.utils.Rotation;
import api.modules.utils.Scale;
import api.modules.utils.UnityJson;
import astra.core.Module;

public class GridMap extends Module {

	private Gson gson = new Gson();
	//use LinkedHashSet to avoid duplicates and preserve the order
	private static LinkedHashSet<Coordinates> breadCrumbs = new LinkedHashSet<Coordinates>();
	
	private LinkedList<UnityJson> directions = new LinkedList<UnityJson>();
	private String currentCardinalDirection = "";
	private int endInstanceId = 7777777;
	
	/**
	 * The goal of this is to record each move made by the agent/s, thus
	 * a knowledge map is build which can be used to navigate smoothly 
	 * in the maze. Position and collisions are recorded, where when 
	 * collision occurs the blocked side in the cell is recorded. As
	 * this module is designed to be used to reach the end of a maze,
	 * once the maze end is reached its also recorded.
	 * @param event
	 * @return the same event
	 */
	@ACTION
	public boolean updateGridMap(String eventType, String event) {
		
		//get current collision/position passed from Unity
		UnityJson requestFromUnity = gson.fromJson(event, UnityJson.class);
        Position position = requestFromUnity.getPosition();
        
        //if instanceId is passed and match the expected one 
        //set the end flag to true, means the end of maze was reached
        int instanceId = requestFromUnity.getInstanceId();
        boolean isEnd = false;
        if (instanceId == endInstanceId) {
        	isEnd = true;
        }
        //which direction is blocked in the cell, only if its a collision event record the data
        String cardinalDirection = eventType.equals(EventType.COLLISION) ? requestFromUnity.getCardinalDirection() : null;

        //round the double we are concerned 
        Coordinates coord = new Coordinates((int) Math.round(position.getX()), (int) Math.round(position.getZ()), cardinalDirection, isEnd);
        breadCrumbs.add(coord);

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
	
	/**
	 * Return how many Coordinates instances have finish set to true
	 * @return
	 */
	@TERM
	public int getFinishCount() {
		return (int) breadCrumbs.stream().filter(obj -> obj.isFinish()).count();
	}
	
	@TERM
	public String getBreadCrumbs() {
		return gson.toJson(breadCrumbs);
	}

	/**
	 * This method tries to decides which way to go based on current position and
	 * the knowledge map. How it works current position is rounded to whole number
	 * and it is compared with knowledge map. From knowledge map we can decide which
	 * direction there is no obstacles and direct the agent in this direction. We do
	 * use the previous Agent position to gather the direction from which he is
	 * coming.
	 * 
	 * @param event
	 * @return Json in format:</br>
	 *         {"position":{"x":0.0,"y":0.0,"z":-1.0},"scale":{"x":0.5,"y":1.0,"z":0.5},"rotation":{"x":0.0,"y":0.0,"z":0.0},"type":"position_vector"}
	 */
	@TERM
	public String getValidatedDirectionsVector(String event) {
		System.out.println("breadSize!!!!!!!!!!!!!: " + breadCrumbs.size());
		System.out.println("event: " + event);       
		System.out.println("breadSize!!!!!!!!!!!!!: " + breadCrumbs.size());
		for (Coordinates bread : breadCrumbs) {
        	System.out.println("bread: " + bread.toString());
        }
		
		Double lastX = null;
		Double lastZ = null;

		//initialize the response Object 
		UnityJson responseVector = new UnityJson();	
		api.modules.utils.Position responsePosition = new Position();
		responsePosition.setX(AstraApi.ZERO);
		responsePosition.setY(AstraApi.ZERO);
		responsePosition.setZ(AstraApi.ZERO);
		responseVector.setPosition(responsePosition);
		
		//get current coordinates
		UnityJson coordinates = gson.fromJson(event, UnityJson.class);
		//add Scale to the response json
		Scale coordinatesScale = coordinates.getScale();
		responseVector.setScale(coordinatesScale);
		
		//add Rotation to the response json
		Rotation coordinatesRotation = coordinates.getRotation();
		responseVector.setRotation(coordinatesRotation);
		
		//set direction list to empty if cardinal direction is passed in the event Object
		//idea here is that Agent has collided and started moving in a new direction all together
		//so no need to hold the list with direction from before, it is a new beginning
		api.modules.utils.Position coordinatesPosition = coordinates.getPosition();
		if (coordinates.getCardinalDirection() != null && AstraApi.LIST_CARDINAL_DIRECTIONS.contains(coordinates.getCardinalDirection())) {
			directions.clear();
		}
		
		if (!directions.isEmpty()) {
			
			// get the last coordinates if exist and remove it if size of list  > 10
			UnityJson recordedCoordinates = null;
			if (directions.size() > 10) {
				recordedCoordinates = directions.pollLast();
			} else {
				recordedCoordinates = directions.getLast();
			}

			// add the current agent position
			directions.add(gson.fromJson(event, UnityJson.class));
			
			api.modules.utils.Position recordedPosition = recordedCoordinates.getPosition();
			lastX = recordedPosition.getX();
			lastZ = recordedPosition.getZ();			

			//get the sign of the coordinates
			int signX = FormattingService.signBit(coordinatesPosition.getX().floatValue());
			int signZ = FormattingService.signBit(coordinatesPosition.getZ().floatValue());
			
			//compare the absolute values with accuracy of 0.1, manipulate the coordinates and add the sign 
			if (lastX != null && !(Math.abs(coordinatesPosition.getX().doubleValue() - lastX.doubleValue()) < FormattingService.EPSILON)) {
				double valueX = (Math.abs(coordinatesPosition.getX().doubleValue()) > Math.abs(lastX.doubleValue()) &&  !(Math.abs(lastX.doubleValue() - coordinatesPosition.getX().doubleValue()) < FormattingService.EPSILON)) ? 
						           AstraApi.ONE : -AstraApi.ONE;
				
				responsePosition.setX(signX == 0 ? new Double(valueX) : new Double(-valueX));
			} 
 
			if (lastZ != null && !(Math.abs(coordinatesPosition.getZ().doubleValue() - lastZ.doubleValue()) < FormattingService.EPSILON)) {
				double valueZ = (Math.abs(coordinatesPosition.getZ().doubleValue()) > Math.abs(lastZ.doubleValue()) && !(Math.abs(lastZ.doubleValue() - coordinatesPosition.getZ().doubleValue()) < FormattingService.EPSILON)) ? 
						           AstraApi.ONE : -AstraApi.ONE;
				
				responsePosition.setZ(signZ == 0 ? new Double(valueZ) : new Double(-valueZ));
			}

			//check if all three coordinates are zero, if so use the recorded cardinal direction to amend the appropriate axis
			checkForZeroVector(responsePosition);
			
			validate(responsePosition, coordinates);
			System.out.println("!!!!!!!!!!responseVector: " + responseVector.toString());
			return gson.toJson(responseVector);
			
		} else {
			
			// add the current/initial agent position to the holding list
			directions.add(gson.fromJson(event, UnityJson.class));
			
			UnityJson initialPosition = gson.fromJson(event, UnityJson.class);
						
			// this tell us which direction to go for the very first time
			String cardinalDirection = initialPosition.getCardinalDirection();
			//set the current cardinal direction
			currentCardinalDirection = cardinalDirection;
			
			setNonZeroAxis(responsePosition, cardinalDirection);
			
			System.out.println("!!!!!!!!!!!!responseVector: " + responseVector.toString());
			return gson.toJson(responseVector);
		}
	}

	/**
	 * This method is used to validate the directions to be passed with knowledge
	 * map built. If the position holds direction that will lead the Agent to
	 * collide with an obstacle then the position Json attribute will be amended
	 * accordingly using the information from the map.
	 * 
	 * @param responsePosition
	 * @param currentCoordinates 
	 */
	private void validate(Position responsePosition, UnityJson currentCoordinates) {

        Position position = currentCoordinates.getPosition();

        //round the double we are concerned 
        Coordinates coord = new Coordinates((int) Math.round(position.getX()), (int) Math.round(position.getZ()), null, false);
        
        LinkedHashSet<Coordinates> breadCrumbsCollision = new LinkedHashSet<Coordinates>();
        //iterate through the knowledge map and check if same 
		for (Coordinates crumb : breadCrumbs) {
			//check if for this position we have blocked direction and record it
			if (crumb.getX() == coord.getX() && crumb.getY() == coord.getY() && crumb.isBlocked()) {
				breadCrumbsCollision.add(crumb);
			}	
        }
		
		List<String> cardinalDirections = new ArrayList<String>(Arrays.asList(AstraApi.CARDINAL_DIRECTION));
		//take action if there is a block/obstacle and override the response vector(position)
		if (!breadCrumbsCollision.isEmpty()) {
			//iterate over and remove the direction/s that Agent cannot be heading
			for (Coordinates crumb : breadCrumbsCollision) {
				if (cardinalDirections.contains(crumb.getDirection())) {
					cardinalDirections.remove(crumb.getDirection());
				}
			}
		}

		//if nothing left in cacardinalDirections list, means all possible direction are blocked
		//set position vector to zero, basically stop the Agent from moving, it is an impossible scenario
		//as the Agent should has been come there from unblocked direction, but we never know - Unity is a
		//dynamic environment
		if (cardinalDirections.size() == 0) {
			responsePosition.setX(AstraApi.ZERO);
			responsePosition.setY(AstraApi.ZERO);
			responsePosition.setZ(AstraApi.ZERO);
		} else {
			//whatever left in cardinalDirections list is the allowed/possible direction without obstacle
			//verify the one assigned in the responseVector is valid and if not the case, override the choice
			int isValid = 0;
			for (String cardinalDirection : cardinalDirections) {
				if (cardinalDirection.equals(AstraApi.SOUTH) && responsePosition.getZ().equals(-AstraApi.ONE)) {
					isValid ++;	
				} else if (cardinalDirection.equals(AstraApi.WEST) && responsePosition.getX().equals(-AstraApi.ONE)) {
					isValid ++;	
				} else if (cardinalDirection.equals(AstraApi.EAST) && responsePosition.getX().equals(AstraApi.ONE)) {
					isValid ++;	
				} else if (cardinalDirection.equals(AstraApi.NORTH) && responsePosition.getZ().equals(AstraApi.ONE)) {
					isValid ++;	
				} 
			}
			
			if (isValid == 0) {
				//reset the position vector
				responsePosition.setX(AstraApi.ZERO);
				responsePosition.setY(AstraApi.ZERO);
				responsePosition.setZ(AstraApi.ZERO);
				// get random index for the remaining cardinal direction
				int randomIndex = ThreadLocalRandom.current().nextInt(0, cardinalDirections.size());
				setNonZeroAxis(responsePosition, cardinalDirections.get(randomIndex));
			}
		}
	}

	/**
	 * Check if the vector contains only zero entries and set the current one.
	 * Basically there is no change from the event passed from Unity, therefore
	 * position won't change.
	 * @param responsePosition
	 */
	private void checkForZeroVector(Position responsePosition) {
		if (responsePosition.getX() == AstraApi.ZERO && responsePosition.getY() == AstraApi.ZERO && responsePosition.getZ() == AstraApi.ZERO) {
			setNonZeroAxis(responsePosition, currentCardinalDirection);
		} 
	}
	
	/**
	 * Set coordinates that have non zero entry 
	 * @param responsePosition
	 * @param cardinalDirection
	 */
	private void setNonZeroAxis(Position responsePosition, String cardinalDirection) {

		switch (cardinalDirection) {
		case AstraApi.SOUTH:
			responsePosition.setZ(-AstraApi.ONE);
			break;
		case AstraApi.WEST:
			responsePosition.setX(-AstraApi.ONE);
			break;
		case AstraApi.EAST:
			responsePosition.setX(AstraApi.ONE);
			break;
		default:// default North
			responsePosition.setZ(AstraApi.ONE);
		}
	}
}
