package api.modules.ai;

import java.util.LinkedHashSet;
import java.util.Random;

public class PathFinder {

	final int gridSize;
	final boolean[][] isBlocked;
	final Coordinates goal;
	final LinkedHashSet<Coordinates> path = new LinkedHashSet<>();
	final Random gen = new Random();

	public PathFinder(int gridSize, int nBlocked) {
		this.gridSize = gridSize;
		this.isBlocked = new boolean[gridSize][gridSize];
		this.goal = new Coordinates(gridSize - 1, gridSize - 1, "");
		// This gets really inefficient if nBlocked is too big.
		for (int n = 0; n < nBlocked; ++n) {
			int x, y;
			do {
				x = gen.nextInt(gridSize);
				y = gen.nextInt(gridSize);
			} while (isBlocked[x][y] || (x == gridSize - 1 && y == gridSize - 1));
			isBlocked[x][y] = true;
		}
	}

	public void printAllPaths() {
		searchFrom(new Coordinates(1, 2, ""));
	}

	public void searchFrom(Coordinates coord) {
		if (path.contains(coord)) {
			return;
		}
		
		path.add(coord);
		if (coord.equals(goal)) {
			System.out.println(path);
		}
		if (coord.getX() > 0 && !isBlocked[coord.getX() - 1][coord.getY()]) {
			searchFrom(new Coordinates(coord.getX() - 1, coord.getY(), ""));
		}
		if (coord.getY() > 0 && !isBlocked[coord.getX()][coord.getY() - 1]) {
			searchFrom(new Coordinates(coord.getX(), coord.getY() - 1, ""));
		}
		if (coord.getX() < gridSize - 1 && !isBlocked[coord.getX() + 1][coord.getY()]) {
			searchFrom(new Coordinates(coord.getX() + 1, coord.getY(), ""));
		}
		if (coord.getY() < gridSize - 1 && !isBlocked[coord.getX()][coord.getY() + 1]) {
			searchFrom(new Coordinates(coord.getX(), coord.getY() + 1, ""));
		}
		path.remove(coord);
	}
	
	public static void main(String[] args) {
		new PathFinder(5, 5).printAllPaths();
	}
}
