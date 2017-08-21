package api;

public interface CommandFactory {
	
	public Object create(Object[] arguments);
}
