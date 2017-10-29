using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using AssemblyCSharp;

public class SupportPlayerFrontTwo : MonoBehaviour {

	public KnowledgeWorkerTwo workerTwo;

	// Use this for initialization
	void Start () {
		workerTwo = (KnowledgeWorkerTwo)GameObject.Find("WorkerTwo").GetComponent("KnowledgeWorkerTwo");
	}

	void OnCollisionEnter (Collision collision) {
		if (workerTwo.shouldMove && collision.gameObject.CompareTag ("Wall") && !workerTwo.currentCollider.Equals(GameManager.CARDINAL_DIRECTION_NORTH)) {
			workerTwo.currentCollider = GameManager.CARDINAL_DIRECTION_NORTH;

			workerTwo.isCollided = true;

			AstraJson collisionDirection = new AstraJson (this.transform);
			collisionDirection.instanceId = collision.collider.GetInstanceID ();
			collisionDirection.cardinalDirection = GameManager.CARDINAL_DIRECTION_NORTH;
			collisionDirection.type = GameManager.EVENT_COLLISION;

			string collisionDirectionJson = JsonUtility.ToJson (collisionDirection);
			//Debug.Log ("COLLISION DATA SEND TO ASTRA SupportPlayerFront : " + collisionDirectionJson);
			string collisionFromAstra = GameManager.api.syncEvent (workerTwo.agentName, GameManager.EVENT_COLLISION, new object[] { collisionDirectionJson });

			//Debug.Log ("COLLISION RECEIVED SupportPlayerFront from Astra: " + collisionFromAstra);
			AstraJson collisionResponse = JsonUtility.FromJson<AstraJson> (collisionFromAstra); 

			//align with Astra update and change the direction for position_vector
			workerTwo.UpdateAgentInitialVectorDirection (collisionResponse.astraCardinalDirection);	
		}
	}
}
