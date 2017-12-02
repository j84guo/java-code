import java.util.*;

public class MyGraph{

	private static class Node{
		int data;
		LinkedList<Node> adjacent;
		
		Node(int data){
			this.data = data;
			adjacent = new LinkedList<Node>();
		}
	}

	private Node[] nodeMap;
	private int numNodes;

	public MyGraph(int numNodes){
		this.numNodes = numNodes;
		nodeMap = new Node[numNodes];
		
		for(int i=0; i<numNodes; i++){
			nodeMap[i] = new Node(i);
		} 
	}

	private void checkValidId(int id){
		if(id >= numNodes || 0 > id){
			throw new IndexOutOfBoundsException("Node id is invalid");
		}
	}

	public void addEdge(int uid, int vid){
		checkValidId(uid);
		checkValidId(vid);

		Node u = nodeMap[uid];
		Node v = nodeMap[vid];

		u.adjacent.add(v);
		v.adjacent.add(u);
	}

	public void breadthFirstSearch(int id){
		checkValidId(id);
		
		Node node = nodeMap[id];
		boolean[] visited = new boolean[numNodes];
		LinkedList<Node> queue = new LinkedList<Node>();

		visited[node.data] = true;
		queue.add(node);

		while(queue.size() != 0){
			node = queue.remove();
			System.out.println(node.data);

			for(Node neighbour : node.adjacent){
				if(visited[neighbour.data] == false){
					visited[neighbour.data] = true;
					queue.add(neighbour);
				}
			}
		}
	}

	public void depthFirstSearch(int id){
		checkValidId(id);
		Node root = nodeMap[id];

		boolean[] visited = new boolean[numNodes];
		depthFirstSearch(root, visited);
	}

	public void depthFirstSearch(Node node, boolean[] visited){
		if(visited[node.data] == true){
			return;
		}

		visited[node.data] = true;
		System.out.println(node.data);

		for(Node neighbour : node.adjacent){
			depthFirstSearch(neighbour, visited);
		}
	}

	public void depthFirstSearchIterative(int id){
		checkValidId(id);
		Node node = nodeMap[id]; 

		boolean[] visited = new boolean[numNodes];
		Stack<Node> stack = new Stack<Node>();
		
		visited[node.data] = true;
		stack.push(node);

		while(!stack.empty()){
			node = stack.pop();
			System.out.println(node.data);

			for(Node neighbour : node.adjacent){
				if(visited[neighbour.data] == false){
					visited[node.data] = true;
					stack.push(neighbour);
				}
			}
		}
	}

	public static void main(String[] args){
		MyGraph mg = new MyGraph(10);

		mg.addEdge(0, 4);
		mg.addEdge(0, 3);
		mg.addEdge(0, 2);
		mg.addEdge(1, 2);

		mg.addEdge(3, 5);
		mg.addEdge(3, 6);
		mg.addEdge(6, 8);
		mg.addEdge(8, 9);
		mg.addEdge(8, 7);

		System.out.println("BFS : ");
		mg.breadthFirstSearch(0);

		System.out.println("\nDFS : ");
		mg.depthFirstSearch(0);

		System.out.println();
		mg.depthFirstSearchIterative(0);
	}
}