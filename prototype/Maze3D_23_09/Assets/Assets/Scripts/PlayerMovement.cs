using System;
using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;
using AssemblyCSharp;

public class PlayerMovement : MonoBehaviour {

	public float moveSpeed = 2.0f;
	public string agentName = "uniqueId";
    public Text winText;
	bool isCollided = false;
	bool shouldMove = true;
	private Rigidbody player;

	api.AstraApiImpl api = new api.AstraApiImpl ();

	void Start () {
		agentName = api.createAgent (agentName, "Player");
		Debug.Log ("name: " + agentName);
        winText.text = "";
		
		UpdateAgentInitialVectorDirection ("West");
		if (!isCollided && shouldMove) {
			InvokeRepeating ("UpdateAgentVectorDirection", 2.0f, 0.08f);
		}
	}

	void Update () {
		if (shouldMove) {
			//listen for events response from Astra
			string positionVectorFromAstra = api.receive(agentName, GameManager.EVENT_POSITION_VECTOR);

			if (positionVectorFromAstra != null && !isCollided) {
				//Debug.Log ("POSITION VECTOR RECEIVED from Astra: " + positionVectorFromAstra);
				PositionJson positionVector = JsonUtility.FromJson<PositionJson> (positionVectorFromAstra); 
				Vector3 movement = new Vector3 (positionVector.x, 0.0f, positionVector.z);
				this.transform.Translate (movement * moveSpeed * Time.deltaTime, Space.World);
			} 
		}
	}		

	void OnCollisionEnter (Collision collision) {
		if (!collision.gameObject.CompareTag ("Ground")) {
			isCollided = true;

			// Get a copy of the forward vector
			Vector3 forward = this.transform.forward;
			//Debug.Log ("FORWARD VECTOR: " +  forward);
			// Zero out the y component of the forward vector to only get the direction in the X,Z plane
			forward.y = 0;
			forward.Normalize();

			//get the cardinal direction of the collision
			string cardinalDirection = null;
			if (Vector3.Angle(forward, Vector3.forward) <= 45.0) {
				cardinalDirection = "North";
			} else if (Vector3.Angle(forward, Vector3.right) <= 45.0) {
				cardinalDirection = "East";
			} else if (Vector3.Angle(forward, Vector3.back) <= 45.0) {
				cardinalDirection = "South";
			} else {
				cardinalDirection = "West";
			}
            //Debug.Log ("cardinalDirection: " +  cardinalDirection);
			
			PositionJson collisionDirection = new PositionJson ();
			collisionDirection.x = this.transform.position.x;
			collisionDirection.y = this.transform.position.y;
			collisionDirection.z = this.transform.position.z;
			collisionDirection.instanceId = collision.collider.GetInstanceID ();
			collisionDirection.cardinalDirection = cardinalDirection;
			collisionDirection.type = GameManager.EVENT_COLLISION;

			string collisionDirectionJson = JsonUtility.ToJson (collisionDirection);
			//Debug.Log ("COLLISION DATA SEND TO ASTRA????????????????: " + collisionDirectionJson);
			string collisionFromAstra = api.syncEvent (agentName, GameManager.EVENT_COLLISION, new object[] { collisionDirectionJson });

			//Debug.Log ("COLLISION RECEIVED from Astra: " + collisionFromAstra);
			PositionJson collisionResponse = JsonUtility.FromJson<PositionJson> (collisionFromAstra); 

			//align with Astra update and change the direction for position_vector
			api.clear(agentName, GameManager.EVENT_POSITION_VECTOR);
			UpdateAgentInitialVectorDirection (collisionResponse.astraCardinalDirection);	
		}
	}

	void OnTriggerEnter(Collider other) {
		if (other.gameObject.CompareTag("PickUp")) {
			other.gameObject.SetActive(false);
			winText.text = "Winner";
			shouldMove = false;
		}
	}
	
	private void UpdateAgentVectorDirection () {
		//only if no acting collision keep sending position updates
		if (!isCollided && shouldMove) {
			PositionJson directions = new PositionJson ();
			directions.x = this.transform.position.x;
			directions.y = this.transform.position.y;
			directions.z = this.transform.position.z;
			directions.type = GameManager.EVENT_POSITION_VECTOR;

			string json = JsonUtility.ToJson (directions);
			//Debug.Log ("UpdateAgentVectorDirection: " + json);
		    api.asyncEvent (agentName, GameManager.EVENT_POSITION_VECTOR, new object[] { json });
		}
	}

	private void UpdateAgentInitialVectorDirection (string cardinalDirection) {
		//make the agent move
		PositionJson directions = new PositionJson ();
		directions.x = this.transform.position.x;
		directions.y = this.transform.position.y;
		directions.z = this.transform.position.z;
		directions.type = GameManager.EVENT_POSITION_VECTOR;
		directions.cardinalDirection = cardinalDirection;
	    string initialJson = JsonUtility.ToJson (directions);
		//Debug.Log ("UpdateAgentInitialVectorDirection: " + initialJson);
		string response = api.syncEvent (agentName, GameManager.EVENT_POSITION_VECTOR, new object[] { initialJson });

		//Debug.Log ("POSITION INITIAL VECTOR RECEIVED from Astra: " + response);
		PositionJson positionInitialVector = JsonUtility.FromJson<PositionJson> (response); 
		Vector3 movement = new Vector3 (positionInitialVector.x, 0.0f, positionInitialVector.z);
		this.transform.rotation = Quaternion.LookRotation(movement);
		this.transform.Translate (movement * moveSpeed * Time.deltaTime, Space.World);

		//enable UpdateAgentVectorDirection to send updates to Astra and Update to listen for position changes
		isCollided = false;
	}
}
