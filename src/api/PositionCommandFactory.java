package api;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;

public class PositionCommandFactory implements CommandFactory {

	Gson gson = new Gson();
	
	public class Position extends AstraCommand {

		public Double horizontal;
		public Double vertical;
		
		public Position(Object[] value) {
			super("position");
			processValues(value);
		}

		@SuppressWarnings("unchecked")
		private void processValues(Object[] value) {
			Map<String, Object> values = new HashMap<String, Object>();
			values = (Map<String,Object>) gson.fromJson(value[0].toString(), values.getClass());
			
			this.horizontal = (Double) values.get("horizontal");
			this.vertical = (Double) values.get("vertical");
		}		
	}
	
	public AstraCommand create(Object[] arguments) {
		return new Position(arguments);
	}
}
