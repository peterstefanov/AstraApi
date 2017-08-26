import api.AstraApiImpl;

public class Test {

	public static void main(String[] args) {
		AstraApiImpl api = new AstraApiImpl();
			
		//Agent one created
		String agentOne = api.createAgent("agentOne", "Player");		
		System.out.println(" Agent created with name: " + agentOne);		
		
		//Agent two created
		String agentTwo = api.createAgent("agentTwo", "Player");
		System.out.println("Agent created with name: " + agentTwo);		
		
		//Agent three created
		String agentThree = api.createAgent("agentThree", "Player");
		System.out.println("Agent created with name: " + agentThree);
		
		String syncEventPosition = api.syncEvent(agentOne, "position", new Object[] {"HorizontalRight"});
		System.out.println("syncEventPosition invoked on agentOne: " + syncEventPosition);
				
		String syncEventPosition1 = api.syncEvent(agentTwo, "position", new Object[] {"VerticalDown"});
		System.out.println("syncEventPosition1 invoked on agentTwo: " + syncEventPosition1);
		
		api.asyncEvent(agentOne, "position", new Object[] {"VerticalUp"});
		String asyncEventPosition2 = null;
		while ((asyncEventPosition2 = api.receive()) == null) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}		
		System.out.println("asyncEventPosition2 invoked on agentOne: " + asyncEventPosition2);
		
		String syncEventPosition3 = api.syncEvent(agentThree, "position", new Object[] {"VerticalUp"});
		System.out.println("syncEventPosition3 invoked on agentTwo: " + syncEventPosition3);
		
		String syncEventPosition4 = api.syncEvent("notExists", "position", new Object[] {"VerticalDown"});
		System.out.println("syncEventPosition4 invoked on last initalized agent: " + syncEventPosition4);
		
		String syncEventPosition5 = api.syncEvent(agentOne, "wrongType", new Object[] {"VerticalUp"});
		System.out.println("syncEventPosition5 invoked on agentTwo with wrong event type: " + syncEventPosition5);
	}
}
