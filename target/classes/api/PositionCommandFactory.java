package api;

public class PositionCommandFactory implements CommandFactory {

	
	public class Position extends AstraCommand {
		public Object[] value;
		
		public Position(Object[] value) {
			super("position");
			this.value = value;
		}
	}
	
	public AstraCommand create(Object[] arguments) {
		return new Position(arguments);
	}

}
