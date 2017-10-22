package api.modules.utils;

import java.io.Serializable;

public class UnityJson implements Serializable {

	private static final long serialVersionUID = -5120509323047032926L;
	
	public Position position;
	public Scale scale;
	public Rotation rotation;
	
	public String cardinalDirection;
	
	/** Used for collision event */
	public int instanceId;
	public String astraCardinalDirection;

	public String message;
	
	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public Scale getScale() {
		return scale;
	}

	public void setScale(Scale scale) {
		this.scale = scale;
	}

	public Rotation getRotation() {
		return rotation;
	}

	public void setRotation(Rotation rotation) {
		this.rotation = rotation;
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

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
