 ## NOTES 
  ASTRA API is a public API that can be used with Unity. This Api provides the ability of multi - agent system to be integrated within Unity along with the option to externalized the AI out of Unity. Support of three types of event - position, collision and messaging. 
  Considering Unity3D as our VR/AR environment the main goal of this work was to find the most efficient way of connecting ASTRA - an Agent Oriented Programming language to the VR/AR environment. Number of different approaches were examined initially as a possible solution - using RESTful Web Service over TCP/IP for communication between the agents and the environment, using Java Native Interface (JNI) to call directly methods within the environment and using Unity3D Network API for 'hooking' ASTRA.
  The bridge between both, outlined in this paper is based on the fact that ASTRA is compiled to a Java byte code, hence IKVM.NET was introduced to allow the conversion of the Java code to .NET dll libraries. This way ASTRA can be deployed directly into Unity3D.
 ## USAGE
 Unity game Object is bound to an ASTRA agent and the agent allow the Game Object in terms of Unity environment to be fully autonomous. In other words ASTRA Agent attached to it take care of all decision of movement and collisions when occur.
 ## PREREQUSITES 
 
ASTRA - http://astralanguage.com/wordpress/docs/introduction-to-agentspeakl/
ASTRA is an Agent Programming Language for people who wish to create intelligent distributed/concurrent systems that are built on the Java platform. It is designed to be easy to learn and familiar to developers who are experienced in using mainstream Object-Oriented Programming Languages.
AgentSpeak(L) is an agent programming language, that is based on Belief-Desire-Intention (BDI) theory. This theory models rational decision making as a process of state manipulation. The idea is that the current state of the world can be represented as a set of beliefs (basically facts about the state of the world) and the ideal state of the world can be represented as a set of desires. The theory then maps out how a rational decision maker achieves its desires – that is, how it changes the world so that its desires become its beliefs.

IKVM.NET - https://www.ikvm.net/index.html
As ASTRA language compiles to Java and Unity engine runs as a .NET platform IKVM.NET was used for converting ASTRA Api to dll libraries.
IKVM.NET is useful for several different software development scenarios. Here is a sampling of some of the possibilities.
Drop-in JVM
Use Java libraries in your .NET applications
Develop .NET applications in Java
 ## AUTHOR
 * **Petar Stefanov** - *inital work* - [git(peterstefanov)](https://github.com/peterstefanov/AstraApi)