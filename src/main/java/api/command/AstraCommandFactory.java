package api.command;

import api.EventType;
import api.command.collision.CollisionCommand;
import api.command.possition.PositionCommand;

public class AstraCommandFactory extends AstraCommand implements CommandFactory {

	
	public AstraCommandFactory(String type) {
		super(type);
	}

	@Override
	public synchronized Object create(Object[] arguments) {
		String type = super.getType();
		
		switch (type) {
		case EventType.POSITION:
			return new PositionCommand(arguments);
		case EventType.COLLISION:
			return new CollisionCommand(arguments);
		default:
			return null;
		}
	}

}
