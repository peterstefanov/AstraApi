package api.command.possition;

import api.EventType;
import api.command.AstraCommand;
import api.modules.utils.Position;
import api.modules.utils.Rotation;
import api.modules.utils.Scale;
import api.modules.utils.UnityJson;

public class PositionCommand extends AstraCommand {
	
	public Position position;
	public Scale scale;
	public Rotation rotation;
	
	public PositionCommand(final Object[] value) {
		super(EventType.POSITION);
		processValues(value);
	}

	private void processValues(Object[] value) {
		UnityJson json = (UnityJson) gson.fromJson(value[0].toString(), UnityJson.class);
		
		this.position = new Position(json.getPosition());
		this.scale = new Scale(json.getScale());
		this.rotation = new Rotation(json.getRotation());
	}
}
 