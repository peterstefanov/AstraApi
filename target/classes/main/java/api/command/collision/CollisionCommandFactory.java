package api.command.collision;

import api.command.AstraCommand;
import api.command.CommandFactory;

public class CollisionCommandFactory implements CommandFactory {

	public synchronized AstraCommand create(Object[] arguments) {
		return new Collision(arguments);
	}
}
