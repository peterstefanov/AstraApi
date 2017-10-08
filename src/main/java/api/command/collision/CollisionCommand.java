package api.command.collision;

import api.EventType;
import api.command.AstraCommand;
import api.modules.utils.Position;
import api.modules.utils.UnityJson;

public class CollisionCommand extends AstraCommand {

	public Position position;
	public int instanceId;
	public String cardinalDirection;
	public String astraCardinalDirection;
	
	public CollisionCommand(Object[] value) {
		super(EventType.COLLISION);
		processValues(value);
	}

	private void processValues(Object[] value) {
		UnityJson json = (UnityJson) gson.fromJson(value[0].toString(), UnityJson.class);
		
		this.position = new Position(json.getPosition());		
		this.cardinalDirection = json.getCardinalDirection();
		this.astraCardinalDirection = json.getAstraCardinalDirection();
		this.instanceId = json.getInstanceId();
	}	
}
