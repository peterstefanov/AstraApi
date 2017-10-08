package api.command;

import com.google.gson.Gson;

import api.modules.utils.Position;

public class AstraCommand {
	
	public transient Gson gson = new Gson();
	
	public String type;
/*	public Position position;*/
	
	public AstraCommand(String type) {
		this.type = type;
	}
	
	public String getType() {
		return type;
	}
}
