using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using AssemblyCSharp;

public class SupportPlayerLeft : MonoBehaviour {

	public PlayerMovement avatar;

	// Use this for initialization
	void Start () {
		avatar = (PlayerMovement)GameObject.Find("Avatar").GetComponent("PlayerMovement");
	}

	void OnCollisionEnter (Collision collision) {
		if (avatar.shouldMove && collision.gameObject.CompareTag ("Wall") && !avatar.currentCollider.Equals(GameManager.CARDINAL_DIRECTION_WEST)) {
			avatar.currentCollider = GameManager.CARDINAL_DIRECTION_WEST;

			avatar.isCollided = true;

			PositionJson collisionDirection = new PositionJson ();
			collisionDirection.x = avatar.transform.position.x;
			collisionDirection.y = avatar.transform.position.y;
			collisionDirection.z = avatar.transform.position.z;
			collisionDirection.instanceId = collision.collider.GetInstanceID ();
			collisionDirection.cardinalDirection = GameManager.CARDINAL_DIRECTION_WEST;
			collisionDirection.type = GameManager.EVENT_COLLISION;

			string collisionDirectionJson = JsonUtility.ToJson (collisionDirection);
			Debug.Log ("COLLISION DATA SEND TO ASTRA???????SupportPlayerLeft?????????: " + collisionDirectionJson);
			string collisionFromAstra = GameManager.api.syncEvent (avatar.agentName, GameManager.EVENT_COLLISION, new object[] { collisionDirectionJson });

			Debug.Log ("COLLISION RECEIVED SupportPlayerLeft from Astra: " + collisionFromAstra);
			PositionJson collisionResponse = JsonUtility.FromJson<PositionJson> (collisionFromAstra); 

			//align with Astra update and change the direction for position_vector
			GameManager.api.clear(avatar.agentName, GameManager.EVENT_POSITION_VECTOR);
			avatar.UpdateAgentInitialVectorDirection (collisionResponse.astraCardinalDirection);	
		}
	}
}
