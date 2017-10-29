package api.modules;

import astra.core.Module;

/**
 * Provides the ability to send and receive message and act on depends on the
 * content of the message. For now this is used only for communication from
 * ASTRA to Unity.
 */
public class Messaging extends Module {

	@TERM
	public String sendMessage(String json) {
		
		return json;
	}
}
