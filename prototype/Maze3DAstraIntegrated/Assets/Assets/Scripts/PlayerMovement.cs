﻿using System;
using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;
using AssemblyCSharp;

public class PlayerMovement : MonoBehaviour {
	
	public float moveSpeed = 5.0f;
	public string agentName = "uniqueId";
    public Text winText;
	public bool isCollided = false;
	public bool shouldMove = true;
	private Rigidbody player;
	//prevent collison data to be send twice from the same Support Player
	//idea here is that when collision occur send data and act 
	public string currentCollider = "";

	void Start () {
		agentName = GameManager.api.createAgent (agentName, "Player");
        winText.text = "";
		
		UpdateAgentInitialVectorDirection ("North");
		if (!isCollided && shouldMove) {
			InvokeRepeating ("UpdateAgentVectorDirection", 2.0f, 0.1f);
		}
	}

	void Update () {
		if (shouldMove) {
			//listen for events response from Astra
			string positionVectorFromAstra = GameManager.api.receive(agentName, GameManager.EVENT_POSITION_VECTOR);

			if (positionVectorFromAstra != null && !isCollided) {
				//Debug.Log ("POSITION VECTOR RECEIVED from Astra: " + positionVectorFromAstra);
				AstraJson positionVector = JsonUtility.FromJson<AstraJson> (positionVectorFromAstra); 
				//Debug.Log ("positionVector: " + positionVector);
				Vector3 movement = new Vector3 (positionVector.position.x, 0.0f, positionVector.position.z);
				//for direction vector-use transform Translate to apply the direction vector
				this.transform.Translate (movement * moveSpeed * Time.deltaTime, Space.Self);
				//if position event used - directly assign the coordinates
				//this.transform.position = movement;
			} 
		}
	}		

	void OnTriggerEnter(Collider other) {
		if (other.gameObject.CompareTag("PickUp")) {
			other.gameObject.SetActive(false);
			winText.text = "Founded";
			shouldMove = false;
		}
	}
	
	private void UpdateAgentVectorDirection () {
		//only if no collision and game is not over keep sending position updates
		if (!isCollided && shouldMove) {
			AstraJson directions = new AstraJson (this.transform);
			directions.type = GameManager.EVENT_POSITION_VECTOR;

			string json = JsonUtility.ToJson (directions);
			//Debug.Log ("UpdateAgentVectorDirection: " + json);
			GameManager.api.asyncEvent (agentName, GameManager.EVENT_POSITION_VECTOR, new object[] { json });
		}
	}

	public void UpdateAgentInitialVectorDirection (string cardinalDirection) {
		//make the agent move
		AstraJson directions = new AstraJson (this.transform);
		directions.type = GameManager.EVENT_POSITION;
		directions.cardinalDirection = cardinalDirection;
	    string initialJson = JsonUtility.ToJson (directions);
		//Debug.Log ("UpdateAgentInitialVectorDirection: " + initialJson);
		string response = GameManager.api.syncEvent (agentName, GameManager.EVENT_POSITION_VECTOR, new object[] { initialJson });

		//Debug.Log ("POSITION INITIAL VECTOR RECEIVED from Astra: " + response);
		AstraJson positionInitialVector = JsonUtility.FromJson<AstraJson> (response); 
		Vector3 movement = new Vector3 (positionInitialVector.position.x, 0.0f, positionInitialVector.position.z);
		this.transform.Translate (movement * moveSpeed * Time.deltaTime, Space.Self);

		//enable UpdateAgentVectorDirection to send updates to Astra and Update to listen for position changes
		isCollided = false;
	}
}
