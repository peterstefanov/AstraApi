package api.events;

import java.util.LinkedList;
import java.util.Random;

import org.junit.Before;

import com.google.gson.Gson;

import api.AstraApi;
import api.AstraApiImpl;

public class EventTypeTest {

	public static final int INSTANCE_ID = 9670;
	
	public static final Double X = new Double("1.649999976158142");
	public static final Double Y = new Double("1.0");
	public static final Double Z = new Double("2.700000047683716");
	
	public static final Double X_N = new Double("-1.649999976158142");
	public static final Double Y_N = new Double("-1.0");
	public static final Double Z_N = new Double("-2.700000047683716");
	
	public static final Double X_1 = new Double("2.849999976158142");

	public static final Double Z_1 = new Double("3.900000047683716");
	public static final Double Z_2 = new Double("4.100000047683716");
	public static final Double Z_3 = new Double("5.100000047683716");
	public static final Double Z_4 = new Double("6.200000047683716");
	
	public static final Double Z_1_N = new Double("-3.900000047683716");
	public static final Double Z_2_N = new Double("-4.100000047683716");
	public static final Double Z_3_N = new Double("-5.100000047683716");
	public static final Double Z_4_N = new Double("-6.200000047683716");
	
	public static final String SOUTH_COLLISION = "{\"rotation\":{\"x\":0.0,\"y\":0.0,\"z\":0.0},\"scale\":{\"x\":0.5,\"y\":1.0,\"z\":0.5},\"position\":{\"x\":" + X + ",\"y\":" + Y + ",\"z\":" + Z + "},\"instanceId\":" + INSTANCE_ID + ",\"cardinalDirection\":" + AstraApi.SOUTH +"}";
	public static final String NORTH_COLLISION = "{\"rotation\":{\"x\":0.0,\"y\":0.0,\"z\":0.0},\"scale\":{\"x\":0.5,\"y\":1.0,\"z\":0.5},\"position\":{\"x\":" + X + ",\"y\":" + Y + ",\"z\":" + Z + "},\"instanceId\":" + INSTANCE_ID + ",\"cardinalDirection\":" + AstraApi.NORTH +"}";
	public static final String EAST_COLLISION = "{\"rotation\":{\"x\":0.0,\"y\":0.0,\"z\":0.0},\"scale\":{\"x\":0.5,\"y\":1.0,\"z\":0.5},\"position\":{\"x\":" + X + ",\"y\":" + Y + ",\"z\":" + Z + "},\"instanceId\":" + INSTANCE_ID + ",\"cardinalDirection\":" + AstraApi.EAST +"}";
	public static final String WEST_COLLISION = "{\"rotation\":{\"x\":0.0,\"y\":0.0,\"z\":0.0},\"scale\":{\"x\":0.5,\"y\":1.0,\"z\":0.5},\"position\":{\"x\":" + X + ",\"y\":" + Y + ",\"z\":" + Z + "},\"instanceId\":" + INSTANCE_ID + ",\"cardinalDirection\":" + AstraApi.WEST +"}";

	public static final String NEGATIVE_SOUTH_COLLISION = "{\"rotation\":{\"x\":0.0,\"y\":0.0,\"z\":0.0},\"scale\":{\"x\":0.5,\"y\":1.0,\"z\":0.5},\"position\":{\"x\":" + X_N + ",\"y\":" + Y_N + ",\"z\":" + Z_N + "},\"instanceId\":" + INSTANCE_ID + ",\"cardinalDirection\":" + AstraApi.SOUTH +"}";
	public static final String NEGATIVE_NORTH_COLLISION = "{\"rotation\":{\"x\":0.0,\"y\":0.0,\"z\":0.0},\"scale\":{\"x\":0.5,\"y\":1.0,\"z\":0.5},\"position\":{\"x\":" + X_N + ",\"y\":" + Y_N + ",\"z\":" + Z_N + "},\"instanceId\":" + INSTANCE_ID + ",\"cardinalDirection\":" + AstraApi.NORTH +"}";
	
	
	public AstraApiImpl api;
	public Gson gson;
	
	@Before
	public void setUp() {		
		gson = new Gson();
		api = new AstraApiImpl();
	}
	
	public String createAgent() {
		return api.createAgent(getUniqueString(), "Player");
	}
	
	public String getUniqueString() {
        String alphabet = "abcdefghijklmnoprstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random r = new Random();
        while (salt.length() < 9) { // length of the random string.
            int index = (int) (r.nextFloat() * alphabet.length());
            salt.append(alphabet.charAt(index));
        }
        return salt.toString();
    }
	
	public void getEventResponse(String agent, LinkedList<String> listEvents, String eventType) {
		String asyncEventPosition = null;
		int count = 0;
		while(count < 7) {
			asyncEventPosition = api.receive(agent, eventType);
			if (asyncEventPosition == null) {			
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}	
			}
			if (asyncEventPosition != null) {
				listEvents.add(asyncEventPosition);
				if (listEvents.size() == 3) {
					break;
				}
			}
			count ++;
		}
	}
}
