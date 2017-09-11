package api.command;

public interface CommandFactory {
	
	public Object create(Object[] arguments);
}
