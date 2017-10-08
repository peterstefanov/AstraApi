package api.modules.utils;

import java.io.Serializable;

public class UnityJson implements Serializable {

	private static final long serialVersionUID = -5120509323047032926L;
	
	public Position position;
	
	public String cardinalDirection;
	
	/** Used for collision event */
	public int instanceId;
	public String astraCardinalDirection;

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
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
