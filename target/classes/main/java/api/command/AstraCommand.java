package api.command;

import com.google.gson.Gson;

public class AstraCommand {
	
	public Gson gson = new Gson();
	
	public String type;
	
	public AstraCommand(String type) {
		this.type = type;
	}
}
