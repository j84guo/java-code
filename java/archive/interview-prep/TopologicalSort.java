import java.util.*;

public class TopologicalSort{
	
	static class Node{

		int incoming;
		int data;
		LinkedList<Node> adjacent;

		Node(int data){
			this.data = data;
			this.incoming = 0;
			adjacent = new LinkedList<Node>();
		}
	}

	private int numNodes;
	private ArrayList<Node> nodeMap;

	private Node getNode(int index){

		return nodeMap.get(index);
	}

	public void addEdge(int uId, int vId){

		Node u = getNode(uId);
		Node v = getNode(vId);

		u.adjacent.add(v);
		v.incoming++;
	}

	public TopologicalSort(int numNodes){

		this.numNodes = numNodes;
		
		nodeMap = new ArrayList<Node>(numNodes);
		for(int i=0; i<numNodes; i++){
			nodeMap.add(new Node(i));
		}
	}

	public void breadthFirstSearch(int rootId){

		LinkedList<Node> queue = new LinkedList<Node>();
		boolean[] visited = new boolean[this.numNodes];
		Node node = getNode(rootId);
		
		visited[node.data] = true;		
		queue.addLast(node);

		System.out.println("Start BFS : ");

		while(queue.size() != 0){
			node = queue.removeFirst();
			System.out.println("visiting " + node.data + " ");

			for(Node neighbour : node.adjacent){
				if(!visited[neighbour.data]){
					visited[neighbour.data] = true;
					queue.addLast(neighbour);
				}
			}
		}

		System.out.println();
	}

	public void depthFirstSearch(int rootId){

		Stack<Node> stack = new Stack<Node>();
		boolean[] visited = new boolean[this.numNodes];
		Node node = getNode(rootId);

		visited[node.data] = true;
		stack.push(node);

		System.out.println("Start iterative DFS : ");

		while(!stack.empty()){
			node = stack.pop();
			System.out.println("visiting " + node.data + " ");

			for(Node neighbour : node.adjacent){
				if(!visited[neighbour.data]){
					visited[neighbour.data] = true;
					stack.push(neighbour);
				}
			}
		}

		System.out.println();
	}

	public void depthFirstSearchRecursive(int rootId){
		
		Node root = getNode(rootId);
		System.out.println("Start recursive DFS : ");
		depthFirstSearchRecursive(root, new boolean[this.numNodes]);
		System.out.println();
	}

	private void depthFirstSearchRecursive(Node node, boolean[] visited){
		
		if(node == null) return;
		System.out.println("visiting " + node.data);

		for(Node neighbour : node.adjacent){
			
			if(!visited[neighbour.data]){
				visited[neighbour.data] = true;
				depthFirstSearchRecursive(neighbour, visited);
			}
		}
	}

	// here edges indicate relative compilation order
	public LinkedList<Node> buildProject(){

		LinkedList<Node> output = new LinkedList<Node>();
		boolean cycleFound = false;

		while(output.size() != this.numNodes && cycleFound == false){
			cycleFound = true;

			for(int i=0; i<this.numNodes; i++){
				Node node = getNode(i);

				if(node.incoming == 0){
					cycleFound = false;
					compileDependency(node, output);
				}
			}

			if(cycleFound == true) return null;
		}

		return output;
	}

	private void compileDependency(Node node, LinkedList<Node> output){

		node.incoming = -1;
		output.add(node);

		for(Node neighbour : node.adjacent){
			neighbour.incoming--;
		}
	}

	public ArrayList<Node> cloneNodes(){
		return cloneNodes(this.nodeMap);
	}

	private ArrayList<Node> cloneNodes(ArrayList<Node> listToClone){

		if(listToClone == null) return null;

		ArrayList<Node> result = new ArrayList<Node>();
		for(int i=0; i<listToClone.size(); i++){
			Node node = listToClone.get(i);
			result.add(new Node(node.data));
		}

		return result;
	}

	// approach : 
	// again edges indicate compilation order
	// repeatedly apply depth first search 
	// process the deepest nodes first, placing them on a stack, after the recursive traversals of all their children
	// the stack reflects the order of compilation order
	// deepest nodes (highest level projects) must be compiled last 
	// the order of DFS doesn't matter, as long as the highest dependencies are placed first on the stack, we end up 
	// popping a valid build order  
	public LinkedList<Node> topologicalSort(){

		Stack<Node> stack = new Stack<Node>();
		boolean[] visited = new boolean[this.numNodes];

		for(int i=0; i<this.numNodes; i++){
			
			Node node = getNode(i);

			if(!visited[i]){
				modifiedDfs(node, visited, stack);
			}
		}

		LinkedList<Node> output = new LinkedList<Node>();
		
		while(!stack.empty()){
			Node node = stack.pop();
			output.addLast(node);
		}
		return output;
	}

	private void modifiedDfs(Node node, boolean[] visited, Stack<Node> stack){
		visited[node.data] = true;

		for(Node neighbour : node.adjacent){
			if(!visited[neighbour.data]){
				modifiedDfs(neighbour, visited, stack);
			}
		}

		stack.push(node);
	}

	public static void main(String[] args){
		
		TopologicalSort ts = new TopologicalSort(12);
		ts.addEdge(0, 5);
		ts.addEdge(0, 1);
		ts.addEdge(1, 2);
		ts.addEdge(2, 4);
		ts.addEdge(2, 3);
		ts.addEdge(3, 4);	
		ts.addEdge(4, 8);	
		ts.addEdge(4, 6);	
		ts.addEdge(4, 7);	
		ts.addEdge(8, 9);	
		ts.addEdge(7, 9);	
		ts.addEdge(4, 10);	
		ts.addEdge(4, 11);	
		ts.addEdge(10, 9);
		ts.addEdge(11, 9);		

		// ts.breadthFirstSearch(0);
		// ts.depthFirstSearch(0);
		// ts.depthFirstSearchRecursive(0);

		LinkedList<Node> output = ts.topologicalSort();
		System.out.println("Starting topological sort :");
		
		if(output == null){
			System.out.println("No valid compilation path!");
		}else{
			for(int i=0; i<output.size(); i++){

				Node node = output.get(i);
				System.out.print(node.data + " ");
			}
		}
	}
}