package api.command.directionvector;

import api.EventType;
import api.command.AstraCommand;
import api.modules.utils.PositionUnityJson;

public class DirectionVectorCommand extends AstraCommand {

	public Double x;
	public Double y;
	public Double z;
	
	public DirectionVectorCommand(final Object[] value) {
		super(EventType.DIRECTION_VECTOR);
		processValues(value);
	}

	private void processValues(Object[] value) {
		PositionUnityJson values = (PositionUnityJson) gson.fromJson(value[0].toString(), PositionUnityJson.class);
					
		this.x = values.getX();
		this.y = values.getY();
		this.z = values.getZ();
	}
}
