package api.modules.ai;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import api.AstraApi;

public class PathFinder {

	final private static int TRESHOLD_GRID = 11;
	final private int initX = -10;
	/** In Unity Y is equivalent to Z coordinate */
	final private int initY = 9;
	final private Coordinates initialCoordinates;
	final private List<Coordinates> goal;
	private LinkedHashSet<Coordinates> path = new LinkedHashSet<>();
	final Random gen = new Random();
	private LinkedHashSet<Coordinates> breadCrumbs;

	public PathFinder(LinkedHashSet<Coordinates> breadCrumbs, String direction) {
		// knowledge map with positions/cells visited by the worker Agents
		this.breadCrumbs = breadCrumbs;
		// this is predefine as the start position of the Agent
		this.initialCoordinates = new Coordinates(initX, initY, null, false);

		// two final destination positions
		this.goal = setGoal();
		System.out.println("breadSize: " + breadCrumbs.size());
		for (Coordinates bread : breadCrumbs) {
        	System.out.println("bread: " + bread.toString());
        }
		if (goal.size() > 1) {
			findPath(initialCoordinates, direction);
		}
	}

	/**
	 * Returns the next item from the LinkedHashSet containing the direct path to
	 * the finish and removing it from the Set.
	 * 
	 * @return
	 */
	public Optional<Coordinates> getNextMove() {
		return path.stream().findFirst().map(position -> {
			path.remove(position);
			return position;
		});
	}

	/**
	 * Iterates over the data for the grid and sets a list with the final/end
	 * Coordinates of the maze
	 * 
	 * @return list with end Coordinates of the maze
	 */
	private List<Coordinates> setGoal() {
		List<Coordinates> result = new ArrayList<Coordinates>();

		breadCrumbs.stream().filter(obj -> obj.isFinish()).forEach(item -> {
			result.add(new Coordinates(item.getX(), item.getY(), item.getDirection(), item.isFinish()));
		});

		return result;
	}

	/**
	 * Build the path by passing the starting position.
	 * 
	 * @param coord
	 */
	public void findPath(Coordinates currentCoordinate, String directionMovement) {
		
		boolean foundInTheMap = false;
		if (path.contains(currentCoordinate)) {
			return;
		}
		
		/** check if the final destination is reached, stop adding to the path*/
		if (goal.size() > 0 && goal.get(0).equals(currentCoordinate)) {
			return;
		}
		if (goal.size() > 1 && goal.get(1).equals(currentCoordinate)) {
			return;
		}
		
		path.add(currentCoordinate);

		/** check the same direction of movement*/
		if (directionMovement.equals(AstraApi.SOUTH)) {
			Iterator<Coordinates> itr = breadCrumbs.iterator();
	        while(itr.hasNext()){
	        	Coordinates coord = itr.next();
	        	/**get next coordinates in the direction of movement*/
	        	if (coord.getX() == currentCoordinate.getX() && coord.getY() == (currentCoordinate.getY() - 1) && coord.getY() > - TRESHOLD_GRID) {
	        		/** Check if the coordinate is already in the path, only if its not set foundInMap flag to true*/
	        		if (!path.contains(coord)) {
	        			foundInTheMap = true;
	        		}
	        		/** Check if for the new coordinate the current direction of movement is blocked 
	        		 * Basically the logic is you arrive on (10, 10), but (10,10) can have up to five entries:
	        		 * without blocked flag and blocked for each direction, so as the Agent is here we need 
	        		 * to decide where to go, can he continue the same direction? This is the question we are
	        		 * trying to answer here.*/
	        		List<Coordinates> blockedCoordinates = new ArrayList<Coordinates>();
	        		breadCrumbs.stream()
	        				.filter(obj -> obj.getX() == currentCoordinate.getX() && obj.getY() == currentCoordinate.getY() - 1 && obj.getDirection().equals(AstraApi.SOUTH))
	        				.forEach(item -> {
	        					Coordinates _coord = new Coordinates(item.getX(), item.getY(), item.getDirection(), item.isFinish());
	        					blockedCoordinates.add(_coord);
	        				});
	        		boolean flag = false;
	        		if (!blockedCoordinates.isEmpty()) {
	        			/**if the new coordinate is blocked for the current direction change the direction of movement*/
	        			changeDirectionOfMovement(blockedCoordinates.get(0), directionMovement);
        				flag = true;
	        		} else {
	        			/**else keep going the same direction*/
		        		Coordinates currentCoord = new Coordinates(coord.getX(), coord.getY(), coord.getDirection(), coord.isFinish());
		        		findPath(currentCoord, directionMovement);
	        		}
	        		if (flag) {
	        			break;
	        		}
	        	}
	        }
		} else if (directionMovement.equals(AstraApi.WEST)) {
			Iterator<Coordinates> itr = breadCrumbs.iterator();
	        while(itr.hasNext()){
	        	Coordinates coord = itr.next();
	        	/**get next coordinates in the direction of movement*/
	        	if (coord.getY() == currentCoordinate.getY() && coord.getX() == (currentCoordinate.getX() - 1) && coord.getX() > - TRESHOLD_GRID) {
	        		/** Check if the coordinate is already in the path, only if its not set foundInMap flag to true*/
	        		if (!path.contains(coord)) {
	        			foundInTheMap = true;
	        		}
	        		/** Check if for the new coordinate the current direction of movement is blocked 
	        		 * Basically the logic is you arrive on (10, 10), but (10,10) can have up to five entries:
	        		 * without blocked flag and blocked for each direction, so as the Agent is here we need 
	        		 * to decide where to go, can he continue the same direction? This is the question we are
	        		 * trying to answer here.*/
	        		List<Coordinates> blockedCoordinates = new ArrayList<Coordinates>();
	        		breadCrumbs.stream()
	        				.filter(obj -> obj.getY() == currentCoordinate.getY() && obj.getX() == currentCoordinate.getX() - 1 && obj.getDirection().equals(AstraApi.WEST))
	        				.forEach(item -> {
	        					Coordinates _coord = new Coordinates(item.getX(), item.getY(), item.getDirection(), item.isFinish());
	        					blockedCoordinates.add(_coord);
	        				});
	        		boolean flag = false;
	        		if (!blockedCoordinates.isEmpty()) {
	        			/**if the new coordinate is blocked for the current direction change the direction of movement*/
	        			changeDirectionOfMovement(blockedCoordinates.get(0), directionMovement);
        				flag = true;
	        		} else {
	        			/**else keep going the same direction*/
		        		Coordinates currentCoord = new Coordinates(coord.getX(), coord.getY(), coord.getDirection(), coord.isFinish());
		        		findPath(currentCoord, directionMovement);
	        		}	
	        		if (flag) {
	        			break;
	        		}
	        	}
	        }
		} else if (directionMovement.equals(AstraApi.EAST)) {
			Iterator<Coordinates> itr = breadCrumbs.iterator();
	        while(itr.hasNext()){
	        	Coordinates coord = itr.next();
	        	/**get next coordinates in the direction of movement*/
	        	if (coord.getY() == currentCoordinate.getY() && coord.getX() == (currentCoordinate.getX() + 1) && coord.getX() < TRESHOLD_GRID) {
	        		/** Check if the coordinate is already in the path, only if its not set foundInMap flag to true*/
	        		if (!path.contains(coord)) {
	        			foundInTheMap = true;
	        		}
	        		/** Check if for the new coordinate the current direction of movement is blocked 
	        		 * Basically the logic is you arrive on (10, 10), but (10,10) can have up to five entries:
	        		 * without blocked flag and blocked for each direction, so as the Agent is here we need 
	        		 * to decide where to go, can he continue the same direction? This is the question we are
	        		 * trying to answer here.*/
	        		List<Coordinates> blockedCoordinates = new ArrayList<Coordinates>();
	        		breadCrumbs.stream()
	        				.filter(obj -> obj.getY() == currentCoordinate.getY() && obj.getX() == currentCoordinate.getX() + 1 && obj.getDirection().equals(AstraApi.EAST))
	        				.forEach(item -> {
	        					Coordinates _coord = new Coordinates(item.getX(), item.getY(), item.getDirection(), item.isFinish());
	        					blockedCoordinates.add(_coord);
	        				});

	        		boolean flag = false;
	        		if (!blockedCoordinates.isEmpty()) {
        				/**if the new coordinate is blocked for the current direction change the direction of movement*/
        				changeDirectionOfMovement(blockedCoordinates.get(0), directionMovement);
        				flag = true;
        				
	        		} else {
	        			/**else keep going the same direction*/
		        		Coordinates currentCoord = new Coordinates(coord.getX(), coord.getY(), coord.getDirection(), coord.isFinish());
		        		findPath(currentCoord, directionMovement);
	        		}
	        		
	        		if (flag) {
	        			break;
	        		}
	        	}
	        }
		} else if (directionMovement.equals(AstraApi.NORTH)) {
			Iterator<Coordinates> itr = breadCrumbs.iterator();
			
	        while(itr.hasNext()) {
	        	Coordinates coord = itr.next();
	        	/**get next coordinates in the direction of movement*/
	        	if (coord.getX() == currentCoordinate.getX() && coord.getY() == (currentCoordinate.getY() + 1) && coord.getY() < TRESHOLD_GRID) {
	        		/** Check if the coordinate is already in the path, only if its not set foundInMap flag to true*/
	        		if (!path.contains(coord)) {
	        			foundInTheMap = true;
	        		}
	        		
	        		/** Check if for the new coordinate the current direction of movement is blocked 
	        		 * Basically the logic is you arrive on (10, 10), but (10,10) can have up to five entries:
	        		 * without blocked flag and blocked for each direction, so as the Agent is here we need 
	        		 * to decide where to go, can he continue the same direction? This is the question we are
	        		 * trying to answer here.*/
	        		List<Coordinates> blockedCoordinates = new ArrayList<Coordinates>();
	        		breadCrumbs.stream()
	        				.filter(obj -> obj.getX() == currentCoordinate.getX() && obj.getY() == currentCoordinate.getY() + 1 && obj.getDirection().equals(AstraApi.SOUTH))
	        				.forEach(item -> {
	        					Coordinates _coord = new Coordinates(item.getX(), item.getY(), item.getDirection(), item.isFinish());
	        					blockedCoordinates.add(_coord);
	        				});
	        		boolean flag = false;
	        		if (!blockedCoordinates.isEmpty()) {
        				/**if the new coordinate is blocked add it to the path and change direction of movement*/
        				changeDirectionOfMovement(blockedCoordinates.get(0), directionMovement);
        				flag = true;
	        		} else {
	        			/**else keep going the same direction*/
		        		Coordinates currentCoord = new Coordinates(coord.getX(), coord.getY(), coord.getDirection(), coord.isFinish());
		        		findPath(currentCoord, directionMovement);
	        		}
	        		if (flag) {
	        			break;
	        		}
	        	} 
	        } 
		}
        if (!foundInTheMap) {
        	changeDirectionOfMovement(currentCoordinate, directionMovement);
        }
	}

	private void changeDirectionOfMovement(Coordinates currentCoordinate, String currentDirectionMovement) {
		List<String> cardinalDirections = new ArrayList<String>(Arrays.asList(AstraApi.CARDINAL_DIRECTION));

		/** find all coordinates with the same x and y*/
		List<Coordinates> sameCoordinates = new ArrayList<Coordinates>();
		breadCrumbs.stream()
				.filter(obj -> obj.getX() == currentCoordinate.getX() && obj.getY() == currentCoordinate.getY())
				.forEach(item -> {
					Coordinates coord = new Coordinates(item.getX(), item.getY(), item.getDirection(), item.isFinish());
					sameCoordinates.add(coord);
				});

		/** get the blocked directions */
		List<String> blockedDirections = new ArrayList<String>();

		if (!sameCoordinates.isEmpty()) {
			for (Coordinates coord : sameCoordinates) {
				blockedDirections.add(coord.getDirection());
			}
		}
		/** at least the current coordinates and the one from where it comes will be ruled out*/
		blockedDirections.add(currentDirectionMovement);
		blockedDirections.add(getOppositeDirection(currentDirectionMovement));
		
		for (String blocked : blockedDirections) {
			if (cardinalDirections.contains(blocked)) {
				cardinalDirections.remove(blocked);
			}
		}

		/**get random index for the remaining cardinal direction*/
		int randomIndex = ThreadLocalRandom.current().nextInt(0, cardinalDirections.size() == 0 ? 1 : cardinalDirections.size()); 

		findPath(new Coordinates(currentCoordinate.getX(), currentCoordinate.getY(), currentCoordinate.getDirection(), currentCoordinate.isFinish()), cardinalDirections.get(randomIndex));
	}

	private String getOppositeDirection(String currentDirectionMovement) {
		
		switch (currentDirectionMovement) {
		case AstraApi.SOUTH:
			return AstraApi.NORTH;
		case AstraApi.WEST:
			return AstraApi.EAST;
		case AstraApi.EAST:
			return AstraApi.WEST;
		default:
			return AstraApi.SOUTH;
		}
	}
}
