package api.modules.utils;

import java.io.Serializable;

public class PositionUnityJson implements Serializable {

	private static final long serialVersionUID = -5120509323047032926L;
	
	public Double x;
	public Double y;
	public Double z;
	public String cardinalDirection;
	
	/** Used for collision event */
	public int instanceId;
	public String astraCardinalDirection;

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

	public int getInstanceId() {
		return instanceId;
	}

	public void setInstanceId(int instanceId) {
		this.instanceId = instanceId;
	}

	public String getCardinalDirection() {
		return cardinalDirection;
	}

	public void setCardinalDirection(String cardinalDirection) {
		this.cardinalDirection = cardinalDirection;
	}

	public String getAstraCardinalDirection() {
		return astraCardinalDirection;
	}

	public void setAstraCardinalDirection(String astraCardinalDirection) {
		this.astraCardinalDirection = astraCardinalDirection;
	}
}