package api.events;

import java.util.Random;

import org.junit.Before;

import com.google.gson.Gson;

import api.AstraApiImpl;

public class EventTypeTest {

	protected AstraApiImpl api;
	protected Gson gson;
	
	@Before
	public void setUp() {		
		gson = new Gson();
		api = new AstraApiImpl();
	}
	
	public String createAgent() {
		return api.createAgent(getUniqueString(), "Player");
	}
	
	protected String getUniqueString() {
        String alphabet = "abcdefghijklmnoprstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random r = new Random();
        while (salt.length() < 9) { // length of the random string.
            int index = (int) (r.nextFloat() * alphabet.length());
            salt.append(alphabet.charAt(index));
        }
        return salt.toString();
    }
}
