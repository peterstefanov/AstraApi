package api;

public class EventType {

	public final static String POSITION = "position";
	public final static String COLLISION = "collision";

	public static boolean isEventTypeSupported(String unityEvent) {

		switch (unityEvent) {
		case POSITION:
			return true;
		case COLLISION:
			return true;
		default:
			return false;
		}
	}
}
