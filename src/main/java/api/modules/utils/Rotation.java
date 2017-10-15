package api.modules.utils;

import java.io.Serializable;

public class Rotation implements Serializable {

	private static final long serialVersionUID = -5080728242440736168L;
	public Double x;
	public Double y;
	public Double z;
	
	public Rotation () {}

	public Rotation (Rotation position) {
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
	
	public String toString() {
		return "'rotation':{'x':" + getX() + ", 'y':" + getY() + ", 'z':" +getZ() + "}" ;
	}
}
