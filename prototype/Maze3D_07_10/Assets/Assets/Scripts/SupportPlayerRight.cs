using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using AssemblyCSharp;

public class SupportPlayerRight : MonoBehaviour {

	public PlayerMovement avatar;

	// Use this for initialization
	void Start () {
		avatar = (PlayerMovement)GameObject.Find("Avatar").GetComponent("PlayerMovement");
	}

	void OnCollisionEnter (Collision collision) {
		if (avatar.shouldMove && collision.gameObject.CompareTag ("Wall") && !avatar.currentCollider.Equals(GameManager.CARDINAL_DIRECTION_EAST)) {
			avatar.currentCollider = GameManager.CARDINAL_DIRECTION_EAST;

			avatar.isCollided = true;

			AstraJson collisionDirection = new AstraJson (this.transform);
			collisionDirection.instanceId = collision.collider.GetInstanceID ();
			collisionDirection.cardinalDirection = GameManager.CARDINAL_DIRECTION_EAST;
			collisionDirection.type = GameManager.EVENT_COLLISION;

			string collisionDirectionJson = JsonUtility.ToJson (collisionDirection);
			Debug.Log ("COLLISION DATA SEND TO ASTRA???????SupportPlayerRight?????????: " + collisionDirectionJson);
			string collisionFromAstra = GameManager.api.syncEvent (avatar.agentName, GameManager.EVENT_COLLISION, new object[] { collisionDirectionJson });

			Debug.Log ("COLLISION RECEIVED SupportPlayerLeft from Astra: " + collisionFromAstra);
			AstraJson collisionResponse = JsonUtility.FromJson<AstraJson> (collisionFromAstra); 

			//align with Astra update and change the direction for position_vector
			GameManager.api.clear(avatar.agentName, GameManager.EVENT_POSITION_VECTOR);
			avatar.UpdateAgentInitialVectorDirection (collisionResponse.astraCardinalDirection);	
		}
	}
}
