using System;
using UnityEngine;

namespace AssemblyCSharp{

	[Serializable]
	public class Scale {

		public float x;
		public float y;
		public float z;

		public Scale (){}

		public Scale (Transform transform) {
			this.x = transform.localScale.x;
			this.y = transform.localScale.y;
			this.z = transform.localScale.z;
		}
	}
}

