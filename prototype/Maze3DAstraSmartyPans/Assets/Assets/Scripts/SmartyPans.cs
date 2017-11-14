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
	public int received = 0;
	public int count = 0;

	void Start () {
		// Hide Smarty pans
		this.transform.localScale = new Vector3(0, 0, 0);
		agentName = GameManager.api.createAgent (agentName, "SmartyPans");
		winText.text = "";
	}

	void Update () {
		string message = GameManager.api.receive(agentName, GameManager.EVENT_MESSAGE);

		if (message != null) {	

			AstraJson messageResponse = JsonUtility.FromJson<AstraJson> (message); 
			if (messageResponse.message.Equals("Ready") && received < 1) {
				received = 1;
				//set workers to active - false
				GameObject.FindGameObjectWithTag("WorkerOne").SetActive(false);
				GameObject.FindGameObjectWithTag("WorkerTwo").SetActive(false);
				//show Smarty Pans 
				this.transform.localScale = new Vector3(0.3f, 1.0f, 0.3f);
				//this is the starting position, top left corner
				this.transform.position = new Vector3(-10.0f, 0.56f, 9.0f);
				shouldMove = true;
				UpdateAgentInitialVectorDirection ("East");
				InvokeRepeating ("UpdateAgentPosition", 2.0f, 0.5f);
			}
		}

		if (shouldMove) {
			count = count + 1;
			//listen for events response from Astra
			string positionVectorFromAstra = GameManager.api.receive(agentName, GameManager.EVENT_POSITION_VECTOR);

			if (positionVectorFromAstra != null ) {
				AstraJson positionVector = JsonUtility.FromJson<AstraJson> (positionVectorFromAstra); 
				Vector3 movement = new Vector3 (positionVector.position.x, 0.56f, positionVector.position.z);
				this.transform.position = movement;
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

	private void UpdateAgentPosition () {
		//only if no collision and game is not over keep sending position updates
		if (shouldMove) {
			AstraJson directions = new AstraJson (this.transform);
			directions.type = GameManager.EVENT_POSITION_VECTOR;

			string json = JsonUtility.ToJson (directions);
			GameManager.api.asyncEvent (agentName, GameManager.EVENT_POSITION_VECTOR, new object[] { json });
		}
	}	
	public void UpdateAgentInitialVectorDirection (string cardinalDirection) {
		//make the agent move
		AstraJson directions = new AstraJson (this.transform);
		directions.type = GameManager.EVENT_POSITION;
		directions.cardinalDirection = cardinalDirection;
		string initialJson = JsonUtility.ToJson (directions);
		string response = GameManager.api.syncEvent (agentName, GameManager.EVENT_POSITION_VECTOR, new object[] { initialJson });

		AstraJson positionInitialVector = JsonUtility.FromJson<AstraJson> (response); 
		Vector3 movement = new Vector3 (positionInitialVector.position.x, 0.56f, positionInitialVector.position.z);
		//directly assign the coordinates
		this.transform.position = movement;
	}
}
