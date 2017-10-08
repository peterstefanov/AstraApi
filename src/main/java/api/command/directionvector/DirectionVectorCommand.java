package api.command.directionvector;

import api.EventType;
import api.command.AstraCommand;
import api.modules.utils.Position;
import api.modules.utils.UnityJson;

public class DirectionVectorCommand extends AstraCommand {

	public Position position;
	
	public DirectionVectorCommand(final Object[] value) {
		super(EventType.DIRECTION_VECTOR);
		processValues(value);
	}

	private void processValues(Object[] value) {		
		UnityJson json = (UnityJson) gson.fromJson(value[0].toString(), UnityJson.class);
		
		this.position = new Position(json.getPosition());
	}
}
