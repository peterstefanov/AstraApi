package api.command.possition;

import api.EventType;
import api.command.AstraCommand;
import api.modules.utils.Coordinates;

public class Position extends AstraCommand {

	public Double x;
	public Double y;
	public Double z;
	
	public Position(final Object[] value) {
		super(EventType.POSITION);
		processValues(value);
	}

	private void processValues(Object[] value) {
		Coordinates values = (Coordinates) gson.fromJson(value[0].toString(), Coordinates.class);
					
		this.x = values.getX();
		this.y = values.getY();
		this.z = values.getZ();
	}
}
