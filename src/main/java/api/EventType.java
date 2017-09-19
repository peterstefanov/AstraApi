package api;

public class EventType {

	public final static String POSITION = "position";
	public final static String COLLISION = "collision";
	public final static String DIRECTION_VECTOR = "direction_vector";
	public final static String POSITION_VECTOR = "position_vector";
	
	public static boolean isEventTypeSupported(String unityEvent) {

		switch (unityEvent) {
		case POSITION:
			return true;
		case COLLISION:
			return true;
		case DIRECTION_VECTOR:
			return true;
		case POSITION_VECTOR:
			return true;
		default:
			return false;
		}
	}
}
