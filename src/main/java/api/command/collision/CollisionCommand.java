package api.command.collision;

import api.EventType;
import api.command.AstraCommand;
import api.modules.utils.PositionUnityJson;

public class CollisionCommand extends AstraCommand {

	public Double x;
	public Double y;
	public Double z;
	public int instanceId;
	public String cardinalDirection;
	public String astraCardinalDirection;
	
	public CollisionCommand(Object[] value) {
		super(EventType.COLLISION);
		processValues(value);
	}

	private void processValues(Object[] value) {
		PositionUnityJson values = (PositionUnityJson) gson.fromJson(value[0].toString(), PositionUnityJson.class);
					
		this.x = values.getX();
		this.y = values.getY();
		this.z = values.getZ();
		this.cardinalDirection = values.getCardinalDirection();
		this.astraCardinalDirection = values.getAstraCardinalDirection();
		this.instanceId = values.getInstanceId();
	}	
}