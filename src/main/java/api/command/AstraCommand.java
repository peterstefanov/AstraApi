package api.command;

import com.google.gson.Gson;

public class AstraCommand {
	
	public transient Gson gson = new Gson();
	
	public String type;
	
	public AstraCommand(String type) {
		this.type = type;
	}
	
	public String getType() {
		return type;
	}
}
