package api.command.message;

import api.EventType;
import api.command.AstraCommand;
import api.modules.utils.UnityJson;

public class MessageCommand extends AstraCommand {
	
	public String message;
	
	public MessageCommand(final Object[] value) {
		super(EventType.MESSAGE);
		processValues(value);
	}

	private void processValues(Object[] value) {
		UnityJson json = (UnityJson) gson.fromJson(value[0].toString(), UnityJson.class);
		this.message = json.getMessage();
	}
}
 