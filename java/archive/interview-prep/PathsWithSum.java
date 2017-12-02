import java.util.*;

public class TopologicalSort{
	
	static class Node{
		
		// with DFS, nodes point to their dependencies
		// therefore an incoming edge means this is a dependency of something
		int incoming;
		int data;
		LinkedList<Node> adjacent;

		Node(int data){
			this.data = data;
		}
	}

	private int numNodes;
	private ArrayList<Node> nodeMap;

	public TopologicalSort(int numNodes){
		this.numNodes = numNodes;
		
		nodeMap = new ArrayList<Node>(numNodes);
		for(int i=0; i<numNodes; i++){
			nodeMap.add(i, new Node(i));
		}
	}

	public static void main(String[] args){
		TopologicalSort ts = new TopologicalSort(10);
	}
}