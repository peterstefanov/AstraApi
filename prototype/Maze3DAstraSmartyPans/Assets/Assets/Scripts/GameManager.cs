using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class GameManager : MonoBehaviour {

	public static string EVENT_POSITION = "position";
	public static string EVENT_COLLISION = "collision";
	public static string EVENT_MESSAGE = "messaging";
	public static string EVENT_POSITION_VECTOR = "position_vector";

	public static string CARDINAL_DIRECTION_NORTH = "North";
	public static string CARDINAL_DIRECTION_SOUTH = "South";
	public static string CARDINAL_DIRECTION_WEST = "West";	
	public static string CARDINAL_DIRECTION_EAST = "East";

	public static int MAZE_END_INSTANCE_ID = 7777777;

	public static api.AstraApiImpl api = new api.AstraApiImpl ();
}
