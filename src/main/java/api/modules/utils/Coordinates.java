package api.modules.utils;

import java.io.Serializable;

public class Coordinates implements Serializable{

	private static final long serialVersionUID = -774515350136611320L;
	public Double x;
	public Double y;
	public Double z;

	public Double getX() {
		return x;
	}

	public void setX(Double x) {
		this.x = x;
	}

	public Double getY() {
		return y;
	}

	public void setY(Double y) {
		this.y = y;
	}

	public Double getZ() {
		return z;
	}

	public void setZ(Double z) {
		this.z = z;
	}
}
