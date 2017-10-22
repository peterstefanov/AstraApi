package api.modules;

import astra.core.Module;
import astra.core.Module.TERM;

public class Messaging extends Module {

	@TERM
	public String sendMessage(String json) {
		
		return json;
	}
}
