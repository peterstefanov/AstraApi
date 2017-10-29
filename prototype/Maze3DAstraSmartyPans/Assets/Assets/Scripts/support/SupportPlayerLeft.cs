using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using AssemblyCSharp;

public class SupportPlayerLeft : MonoBehaviour {

	public KnowledgeWorkerOne workerOne;

	// Use this for initialization
	void Start () {
		workerOne = (KnowledgeWorkerOne)GameObject.Find("WorkerOne").GetComponent("KnowledgeWorkerOne");
	}

	void OnCollisionEnter (Collision collision) {
		if (workerOne.shouldMove && collision.gameObject.CompareTag ("Wall") && !workerOne.currentCollider.Equals(GameManager.CARDINAL_DIRECTION_WEST)) {
			workerOne.currentCollider = GameManager.CARDINAL_DIRECTION_WEST;

			workerOne.isCollided = true;

			AstraJson collisionDirection = new AstraJson (this.transform);
			collisionDirection.instanceId = collision.collider.GetInstanceID ();
			collisionDirection.cardinalDirection = GameManager.CARDINAL_DIRECTION_WEST;
			collisionDirection.type = GameManager.EVENT_COLLISION;

			string collisionDirectionJson = JsonUtility.ToJson (collisionDirection);
			//Debug.Log ("COLLISION DATA SEND TO ASTRA SupportPlayerLeft: " + collisionDirectionJson);
			string collisionFromAstra = GameManager.api.syncEvent (workerOne.agentName, GameManager.EVENT_COLLISION, new object[] { collisionDirectionJson });

			//Debug.Log ("COLLISION RECEIVED SupportPlayerLeft from Astra: " + collisionFromAstra);
			AstraJson collisionResponse = JsonUtility.FromJson<AstraJson> (collisionFromAstra); 

			//align with Astra update and change the direction for position_vector
			workerOne.UpdateAgentInitialVectorDirection (collisionResponse.astraCardinalDirection);	
		}
	}
}
