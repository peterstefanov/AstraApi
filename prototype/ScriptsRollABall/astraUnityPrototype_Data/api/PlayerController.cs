using System;
using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;
using AssemblyCSharp;


public class PlayerController : MonoBehaviour
{
	public Text countText;
	public Text winText;
	public Text astraText;
	private Rigidbody rb;
	private int count;
	public float speed;
	public string update;
	float moveHorizontal = 0.0f;
	float moveVertical = 0.0f;

	api.AstraApiImpl apiAstra = new api.AstraApiImpl ();

	void Start ()
	{
		rb = GetComponent<Rigidbody> ();
		string name = apiAstra.createAgent ("unity_player", "Player");
		print ("Agent name: " + name);
		count = 0;
		SetCountText ();
		astraText.text = "Astra responded: ";
		winText.text = "";
		SetPosition ("Default");  
	}

	private void SetPosition (string update)
	{
		string directionsFromAstra = apiAstra.syncEvent ("position", new object[] { update });

		Directions directions = new Directions ();
		directions = JsonUtility.FromJson<Directions> (directionsFromAstra);
		astraText.text = "Astra responded: " + " X: " + directions.horizontal + " Y: " + directions.vertical;

		moveHorizontal = directions.horizontal;
		moveVertical = directions.vertical;
	}

	void FixedUpdate ()
	{
		Vector3 movement = new Vector3 (moveHorizontal, 0.0f, moveVertical);
		rb.AddForce (movement * speed);
	}

	void OnCollisionEnter (Collision other)
	{
		if (other.gameObject.CompareTag ("EastWall")) {
			other.gameObject.SetActive (true);
			SetPosition ("HorizontalRight");
		} else if (other.gameObject.CompareTag ("WestWall")) {
			other.gameObject.SetActive (true);
			SetPosition ("HorizontalLeft");
		} else if (other.gameObject.CompareTag ("SouthWall")) {
			other.gameObject.SetActive (true);
			SetPosition ("VerticalUp");

		} else if (other.gameObject.CompareTag ("NorthWall")) {
			other.gameObject.SetActive (true);
			SetPosition ("VerticalDown");
		}
	}

	void OnTriggerEnter (Collider other)
	{
		if (other.gameObject.CompareTag ("PickUp")) {
			other.gameObject.SetActive (false);
			count = count + 1;
			SetCountText ();
		}
	}

	void SetCountText ()
	{
		countText.text = "Count: " + count.ToString ();
		if (count > 11) {
			winText.text = "Winner";
		}
       
	}
}
