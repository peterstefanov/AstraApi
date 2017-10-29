using System;
using UnityEngine;

namespace AssemblyCSharp{

	[Serializable]
	public class Rotation {

		public float x;
		public float y;
		public float z;

		public Rotation (){}

		public Rotation (Transform transform) {
			this.x = transform.rotation.x;
			this.y = transform.rotation.y;
			this.z = transform.rotation.z;
		}
	}
}

