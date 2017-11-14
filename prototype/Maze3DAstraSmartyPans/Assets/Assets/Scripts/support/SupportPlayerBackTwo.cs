using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using AssemblyCSharp;

public class SupportPlayerBackTwo : MonoBehaviour {

	public KnowledgeWorkerTwo workerTwo;

	// Use this for initialization
	void Start () {
		workerTwo = (KnowledgeWorkerTwo)GameObject.Find("WorkerTwo").GetComponent("KnowledgeWorkerTwo");
	}

	void OnCollisionEnter (Collision collision) {
		if (workerTwo.shouldMove && collision.gameObject.CompareTag ("Wall") && !workerTwo.currentCollider.Equals(GameManager.CARDINAL_DIRECTION_SOUTH)) {
			workerTwo.currentCollider = GameManager.CARDINAL_DIRECTION_SOUTH;

			workerTwo.isCollided = true;

			AstraJson collisionDirection = new AstraJson (this.transform);
			collisionDirection.instanceId = collision.collider.GetInstanceID ();
			collisionDirection.cardinalDirection = GameManager.CARDINAL_DIRECTION_SOUTH;
			collisionDirection.type = GameManager.EVENT_COLLISION;

			string collisionDirectionJson = JsonUtility.ToJson (collisionDirection);
			string collisionFromAstra = GameManager.api.syncEvent (workerTwo.agentName, GameManager.EVENT_COLLISION, new object[] { collisionDirectionJson });

			AstraJson collisionResponse = JsonUtility.FromJson<AstraJson> (collisionFromAstra); 

			//align with Astra update and change the direction for position_vector
			workerTwo.UpdateAgentInitialVectorDirection (collisionResponse.astraCardinalDirection);	
		}
	}

	void OnTriggerEnter(Collider other) {
		if (other.gameObject.CompareTag("PickUp")) {
			AstraJson collisionDirection = new AstraJson (this.transform);
			collisionDirection.instanceId = GameManager.MAZE_END_INSTANCE_ID;
			collisionDirection.cardinalDirection = GameManager.CARDINAL_DIRECTION_SOUTH;
			collisionDirection.type = GameManager.EVENT_COLLISION;

			string collisionDirectionJson = JsonUtility.ToJson (collisionDirection);
			GameManager.api.syncEvent (workerTwo.agentName, GameManager.EVENT_COLLISION, new object[] { collisionDirectionJson });
		}
	}
}
