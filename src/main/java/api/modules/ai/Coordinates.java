package api.modules.ai;

/**
 * x and y are the coordinates in the grid, they define each cell. Each cell has a side 
 * where this side can be either blocked or unblocked, basically the direction to move.
 */
public class Coordinates {

	private int x;
	private int y;
	private String direction;
	private boolean blocked;
	
	public Coordinates(int x, int y, String direction) {
		this.x = x;
		this.y = y;
		this.direction = direction == null ? "" : direction;
		this.blocked = direction != null;
	}

	public boolean isBlocked () {
		return this.blocked;
	}
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public String getDirection() {
		return direction;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (blocked ? 1231 : 1237);
		result = prime * result + ((direction == null) ? 0 : direction.hashCode());
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
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}

	public String toString() {
		return '(' + Integer.toString(x) + ',' + Integer.toString(y) + ',' + getDirection() + ',' + isBlocked() + ')';
	}
}
