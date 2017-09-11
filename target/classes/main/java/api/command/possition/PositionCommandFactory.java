package api.command.possition;

import api.command.AstraCommand;
import api.command.CommandFactory;

public class PositionCommandFactory implements CommandFactory {
	
	public synchronized AstraCommand create(Object[] arguments) {
		return new Position(arguments);
	}
}
