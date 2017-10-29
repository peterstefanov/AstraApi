using System;
using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;
using AssemblyCSharp;

public class SmartyPans : MonoBehaviour {

	public float moveSpeed = 5.0f;
	public string agentName = "smartyPans";
	public Text winText;
	public bool shouldMove = false;

	void Start () {
		// Hide Smarty pans
		this.transform.localScale = new Vector3(0, 0, 0);
		agentName = GameManager.api.createAgent (agentName, "SmartyPans");
		Debug.Log ("Agent created with name: " + agentName);
		winText.text = "";
	}

	void Update () {
		string message = GameManager.api.receive(agentName, GameManager.EVENT_MESSAGE);

		if (message != null) {	
			Debug.Log ("!SmartyPans received a message!: " + message);
			AstraJson messageResponse = JsonUtility.FromJson<AstraJson> (message); 
			if (messageResponse.message.Equals("Ready")) {
				Debug.Log ("Message received is: " + messageResponse);
				//set workers to active - false
				GameObject.FindGameObjectWithTag("WorkerOne").SetActive(false);
				GameObject.FindGameObjectWithTag("WorkerTwo").SetActive(false);
				//show Smarty Pans 
				this.transform.localScale = new Vector3(0.3f, 1.0f, 0.3f);
				this.transform.position = new Vector3(7.94f, 0.47f, -8.05f);
				shouldMove = true;
				UpdateAgentInitialVectorDirection ("North");
				InvokeRepeating ("UpdateAgentVectorDirection", 2.0f, 0.1f);
			}
		}

		if (shouldMove) {
			//listen for events response from Astra
			string positionVectorFromAstra = GameManager.api.receive(agentName, GameManager.EVENT_POSITION_VECTOR);

			if (positionVectorFromAstra != null ) {
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
		if (shouldMove) {
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
	}
}
