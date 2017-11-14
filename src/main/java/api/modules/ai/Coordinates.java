package api.modules.ai;

/**
 * x and y are the coordinates in the grid, they define each cell. Each cell has a side 
 * where this side can be either blocked or unblocked, basically the direction to move.
 * The cell where is the end of the maze is marked with finish true.
 */
public class Coordinates {
	
	private int x;
	private int y;
	private String direction;
	private boolean blocked;
	private boolean finish;
	
	public Coordinates(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Coordinates(int x, int y, String direction, boolean isEnd) {
		this.x = x;
		this.y = y;
		this.direction = direction == null ? "" : direction;
		this.blocked = !this.direction.isEmpty();
		this.finish = isEnd;
	}

	public boolean isBlocked () {
		return this.blocked;
	}
	
	public boolean isFinish() {
		return finish;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	@Override
	public int hashCode() {
		
		final int prime = 31;
		int result = 17;
		result = prime * result + (blocked ? 1231 : 1237);
		result = prime * result + ((direction == null) ? 0 : direction.hashCode());
		result = prime * result + (finish ? 1231 : 1237);
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Coordinates other = (Coordinates) obj;
		if (blocked != other.blocked)
			return false;
		if (direction == null) {
			if (other.direction != null)
				return false;
		} else if (!direction.equals(other.direction))
			return false;
		if (finish != other.finish)
			return false;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}

	public String getDirection() {
		return direction;
	}

	@Override
	public String toString() {
		return "Coordinates [x=" + x + ", y=" + y + ", direction=" + direction + ", blocked=" + blocked + ", finish=" + finish
				+ "]";
	}
}
