package api.command.possition;

import api.EventType;
import api.command.AstraCommand;
import api.modules.utils.Position;
import api.modules.utils.UnityJson;

public class PositionCommand extends AstraCommand {
	
	public Position position;
	
	public PositionCommand(final Object[] value) {
		super(EventType.POSITION);
		processValues(value);
	}

	private void processValues(Object[] value) {
		UnityJson json = (UnityJson) gson.fromJson(value[0].toString(), UnityJson.class);
		this.position = new Position(json.getPosition());
	}
}
 