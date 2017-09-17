﻿using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using AssemblyCSharp;
 
[RequireComponent (typeof(Rigidbody))]
public class PlayerMovement : MonoBehaviour {

	public float moveSpeed = 5f;
	public GameManager gameManager;
	private Rigidbody rb;
	public string agentName = "uniqueId";
	float moveX;
	float moveZ;
	bool isCollided = false;
	bool isInitial = false;
	//private Vector3 currentLocation;
	//private Vector3 previousLocation;

	api.AstraApiImpl api = new api.AstraApiImpl ();

	// Use this for initialization
	void Start () {
		rb = GetComponent<Rigidbody> ();
		gameManager = GameObject.Find ("GameManager").GetComponent<GameManager> ();
		agentName = api.createAgent (agentName, "Player");
		Debug.Log ("name: " + agentName);

		isInitial = true;
		//update the position every half second
		//InvokeRepeating ("UpdateAgentPosition", 2.0f, 1.0f);
		//update the direction vector every half second
		InvokeRepeating ("UpdateAgentVectorDirection", 2.0f, 0.3f);

	}

	void Update () {
		
		string directionFromAstra = api.receive (agentName, GameManager.EVENT_DIRECTION_VECTOR);
		string positionFromAstra = api.receive (agentName, GameManager.EVENT_POSITION);
		string collisionFromAstra = api.receive (agentName, GameManager.EVENT_COLLISION);

		if (directionFromAstra != null) {
			Debug.Log ("DIRECTION RECEIVED from Astra: " + directionFromAstra);

			PositionJson directions = new PositionJson ();
			directions = JsonUtility.FromJson<PositionJson> (directionFromAstra); 

			moveX = directions.x;
			moveZ = directions.z;
			Vector3 movement = new Vector3 (moveX, 0f, moveZ);
			Debug.Log ("movement!!!!!!!!!!!!!!!!!!: " + movement);
			transform.Translate (movement * moveSpeed * Time.deltaTime, Space.World);
			//transform.position += movement * Time.deltaTime;
			//rb.AddForce (movement * moveSpeed);
			//GetComponent<Rigidbody> ().velocity = movement * moveSpeed * Time.deltaTime;
			//transform.Translate (movement * moveSpeed * Time.deltaTime, Space.World);
		} else if (positionFromAstra != null && !isCollided) {
			Debug.Log ("POSITION RECEIVED from Astra: " + positionFromAstra);

			PositionJson directions = new PositionJson ();
			directions = JsonUtility.FromJson<PositionJson> (positionFromAstra); 

			moveX = directions.x;
			moveZ = directions.z;
			Vector3 movement = new Vector3 (moveX, 0f, moveZ);
			Debug.Log ("movement!!!!!!!!!!!!!!!!!!: " + movement);
			rb.AddForce (movement * moveSpeed);
			//GetComponent<Rigidbody> ().velocity = movement * moveSpeed * Time.deltaTime;
			//transform.Translate (movement * moveSpeed * Time.deltaTime, Space.World);
		} else if (collisionFromAstra != null) {
			Debug.Log ("COLLISION RECEIVED from Astra: " + collisionFromAstra);

			PositionJson collision = new PositionJson ();
			collision = JsonUtility.FromJson<PositionJson> (collisionFromAstra); 

			moveX = collision.x;
			moveZ = collision.z;

			Vector3 movement = new Vector3 (moveX, 0f, moveZ);

			transform.Translate (movement * moveSpeed * Time.deltaTime, Space.World);

			//api.clear (agentName, GameManager.EVENT_POSITION);
			//align with Astra update
			isCollided = false;
			UpdateAgentPosition ();

			PositionJson directions = new PositionJson ();
			directions.x = collision.x;
			directions.y = collision.y;
			directions.z = collision.z;
			directions.type = "position";
						
			string json = JsonUtility.ToJson (directions);


			Debug.Log ("UpdateAgentPosition AFTER COLLISION ===========> position send to Astra: " + json);
			api.syncEvent (agentName, GameManager.EVENT_POSITION, new object[] { json });



		} else {
			//moveX = Input.GetAxis ("Horizontal");
			//moveZ = Input.GetAxis ("Vertical");

			//Vector3 movement = new Vector3 (moveX, 0f, moveZ);
			//transform.rotation = Quaternion.LookRotation (movement);
			//Debug.Log ("transform.position: " + transform.position );
			//Debug.Log ("transform.rotation: " + transform.rotation );
			//transform.Translate (movement * moveSpeed * Time.deltaTime, Space.World);
			//Debug.Log ("transform.position!!!!!!!!!!!!!: " + transform.position);
			//Debug.Log ("moveX: " + moveX + " moveZ: " + moveZ);
		}	


		//GetComponent<Rigidbody> ().velocity = movement * moveSpeed * Time.deltaTime;
	}		

	void OnCollisionEnter (Collision collision) {
		//isCollided = true;
		//var vector = this.transform.forward;
		//vector.y = 0;
		//vector.Normalize();

		//string cardinalDirection = null;
		//if (Vector3.Angle(vector, Vector3.forward) <= 45.0) {
		//	cardinalDirection = "North";
		//} else if (Vector3.Angle(vector, Vector3.right) <= 45.0) {
		//	cardinalDirection = "East";
		//} else if (Vector3.Angle(vector, Vector3.back) <= 45.0) {
		//	cardinalDirection = "South";
		//} else {
		//	cardinalDirection = "West";
		//}
			
		//Debug.Log ("collision.transform.position111111111111111111: " + collision.transform.position);
		//Debug.Log ("collision.collider.transform.position222222222222222: " + this.transform.position);
		//PositionJson direction = new PositionJson ();
		//direction.x = this.transform.position.x;
		//direction.y = this.transform.position.y;
		//direction.z = this.transform.position.z;
		//direction.instanceId = collision.collider.GetInstanceID ();
		//direction.cardinalDirection = cardinalDirection;


		//string json = JsonUtility.ToJson (direction);
		//Debug.Log ("CollisionJson data send to Astra - collisionJson??????????????????: " + json);
		//api.asyncEvent (agentName, GameManager.EVENT_COLLISION, new object[] { json });
	}
		
	private void UpdateAgentVectorDirection () {
		string json = null;

		if (isInitial) {
			//make the agent move
			PositionJson directions = new PositionJson ();
			directions.x = this.transform.position.x;
			directions.y = this.transform.position.y;
			directions.z = this.transform.position.z;
			directions.type = "direction_vector";
			directions.cardinalDirection = "East";
			json = JsonUtility.ToJson (directions);
			Debug.Log ("UpdateAgentDirection ===========> direction vector send to Astra: " + json);
			api.asyncEvent (agentName, GameManager.EVENT_DIRECTION_VECTOR, new object[] { json });
			isInitial = false;
		} else {
			json = JsonUtility.ToJson (this.transform.position);
			Debug.Log ("UpdateAgentPosition ===========> position send to Astra: " + json);
			api.asyncEvent (agentName, GameManager.EVENT_DIRECTION_VECTOR, new object[] { json });
		}
	}

	private void UpdateAgentPosition () {
		string json = null;

		if (isInitial) {
			//make the agent move
			PositionJson directions = new PositionJson ();
			directions.x = this.transform.position.x;
			directions.y = this.transform.position.y;
			directions.z = this.transform.position.z;
			directions.type = "position";
			directions.cardinalDirection = "South";
			json = JsonUtility.ToJson (directions);
			Debug.Log ("UpdateInitialAgentPosition ===========> position send to Astra: " + json);
			api.asyncEvent (agentName, GameManager.EVENT_DIRECTION_VECTOR, new object[] { json });
			isInitial = false;
		} else {
			json = JsonUtility.ToJson (this.transform.position);
			Debug.Log ("UpdateAgentPosition ===========> position send to Astra: " + json);
			api.asyncEvent (agentName, GameManager.EVENT_DIRECTION_VECTOR, new object[] { json });
		}
	}
}
