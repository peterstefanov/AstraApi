import api.AstraApiImpl;

public class Test {

	public static void main(String[] args) {
		AstraApiImpl api = new AstraApiImpl();
				
		String agentOne = api.createAgent("agentOne", "Player");		
		System.out.println("Agent created with name: " + agentOne);
		
		String syncEventPosition = api.syncEvent("position", new Object[] {"HorizontalRight"});
		System.out.println("syncEventPosition: " + syncEventPosition);
		
		api.asyncEvent("position", new Object[] {"VerticalUp"});
		String asyncEventPosition = null;
		while ((asyncEventPosition = api.receive()) == null) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("asyncEventPosition: " + asyncEventPosition);
	}
}
