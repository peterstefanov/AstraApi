using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using AssemblyCSharp;

public class SupportPlayerLeftTwo : MonoBehaviour {

	public KnowledgeWorkerTwo workerTwo;

	// Use this for initialization
	void Start () {
		workerTwo = (KnowledgeWorkerTwo)GameObject.Find("WorkerTwo").GetComponent("KnowledgeWorkerTwo");
	}

	void OnCollisionEnter (Collision collision) {
		if (workerTwo.shouldMove && collision.gameObject.CompareTag ("Wall") && !workerTwo.currentCollider.Equals(GameManager.CARDINAL_DIRECTION_WEST)) {
			workerTwo.currentCollider = GameManager.CARDINAL_DIRECTION_WEST;

			workerTwo.isCollided = true;

			AstraJson collisionDirection = new AstraJson (this.transform);
			collisionDirection.instanceId = collision.collider.GetInstanceID ();
			collisionDirection.cardinalDirection = GameManager.CARDINAL_DIRECTION_WEST;
			collisionDirection.type = GameManager.EVENT_COLLISION;

			string collisionDirectionJson = JsonUtility.ToJson (collisionDirection);
			//Debug.Log ("COLLISION DATA SEND TO ASTRA SupportPlayerLeft: " + collisionDirectionJson);
			string collisionFromAstra = GameManager.api.syncEvent (workerTwo.agentName, GameManager.EVENT_COLLISION, new object[] { collisionDirectionJson });

			//Debug.Log ("COLLISION RECEIVED SupportPlayerLeft from Astra: " + collisionFromAstra);
			AstraJson collisionResponse = JsonUtility.FromJson<AstraJson> (collisionFromAstra); 

			//align with Astra update and change the direction for position_vector
			workerTwo.UpdateAgentInitialVectorDirection (collisionResponse.astraCardinalDirection);	
		}
	}
}
