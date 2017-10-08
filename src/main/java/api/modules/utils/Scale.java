package api.modules.utils;

import java.io.Serializable;

public class Scale implements Serializable {

	private static final long serialVersionUID = -1752766862727895761L;
	
	public Double x;
	public Double y;
	public Double z;
	
	public Scale () {}

	public Scale (Scale position) {
		this.x = position.getX();
		this.y = position.getY();
		this.z = position.getZ();
	}
	
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
