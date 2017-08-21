package api.astrasupport;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.google.gson.Gson;

import astra.core.Module;

/**
 * Handles events of type 'position' sent from Unity.  
 */
public class Position extends Module {

    private Gson gson = new Gson();
	
	public static final String UP = "VerticalUp";
	public static final String DOWN = "VerticalDown";
	public static final String LEFT = "HorizontalLeft";
	public static final String RIGHT = "HorizontalRight";
	
	float min = -0.5f;
	float max = 1f;
	
	@TERM
	public String getDirections(String position) {
		Map<String, Object> result = new HashMap<String, Object>(); 

		Random rand = new Random();

		if (position.equals(DOWN)) {
			result.put("horizontal", (rand.nextFloat() * (max - rand.nextFloat()) + min));
			result.put("vertical", 1.0f);
		} else if (position.equals(UP)) {
			result.put("horizontal", (rand.nextFloat() * (max - rand.nextFloat()) + min));
			result.put("vertical", -1.0f);
		} else if (position.equals(LEFT)) {
			result.put("horizontal", 1.0f);
			result.put("vertical", (rand.nextFloat() * (max - rand.nextFloat()) + min));
		} else if (position.equals(RIGHT)) {
			result.put("horizontal", -1.0f);
			result.put("vertical", (rand.nextFloat() * (max - rand.nextFloat()) + min));
		} else {
			result.put("horizontal", 1.0f);
			result.put("vertical", 0.5f);
		}
		return gson.toJson(result);
	}
}
