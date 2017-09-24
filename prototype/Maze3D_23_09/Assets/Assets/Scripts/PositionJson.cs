using System;

namespace AssemblyCSharp {

	[Serializable]
	public class PositionJson {

		public float x;
		public float y;
		public float z;
		public string type;
		public int instanceId;
		public string cardinalDirection;
		public string astraCardinalDirection; 

		public PositionJson (){}
	}
}

