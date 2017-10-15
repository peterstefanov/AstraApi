using System;
using UnityEngine;

namespace AssemblyCSharp{

	[Serializable]
	public class Position {

		public float x;
		public float y;
		public float z;

		public Position (){}

		public Position (Transform transform) {
			this.x = transform.position.x;
			this.y = transform.position.y;
			this.z = transform.position.z;
		}
	}
}

