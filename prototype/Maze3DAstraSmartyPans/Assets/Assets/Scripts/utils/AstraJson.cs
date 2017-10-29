using System;
using UnityEngine;

namespace AssemblyCSharp {

	[Serializable]
	public class AstraJson {

		public Position position;
		public Scale scale;
		public Rotation rotation;
		public string type;
		public int instanceId;
		public string cardinalDirection;
		public string astraCardinalDirection; 
		public string message; 

		public AstraJson (){}

		public AstraJson (Transform transform){
			this.position = new Position(transform);
			this.scale = new Scale(transform);
			this.rotation = new Rotation(transform);
		}
	}
}

