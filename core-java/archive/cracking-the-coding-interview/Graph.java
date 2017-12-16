public class Graph(){
	private HashMap<Integer, Node> nodeLookup = new HashMap<Integer, Node>();

	public static class node(){
		private int id;
		LinkedList<Node> adjacent = new LinkedList<Node>();
		private Node(int id){
			this.id = id;
		}
	}

	private Node getNode(int id){
		return nodeLookup.get(id);
	}

	public void addEdge(int source, int destination){

	}

	public boolean hasPathDFS(int source, int destination){
		Node s = getNode(source);
		Node d = getNode(destination);
		HashSet<Integer> visited = new HashSet<Integer>();
		return hasPathDFS(s, d, visited);
	}

	private boolean hasPathDFS(Node source, Node destination, HashSet<Integer> visited){
		//already checked all of that nodes branches, no path exists
		if (visited.contains(source.id)){
			return false;
		} 

		//add node to visited
		visited.add(source.id);
		
		//trvial/base case
		if(source == destination){
			return true;
		}

		//look in adjacent (goes all the way down to leaf before up again)
		for(Node child : source.adjacent){
			if(hasPathDFS(child, destination, visited)){
				return true;
			}
		}
		return false;
	}	


	public boolean hasPathBFS(int source, int destination){
		return hasPathBFS(getNode(source), getNode(destination));
	}

	private boolean hasPathBFS(Node source, Node destination){
		//queue of nodes to check
		LinkedList<Node> nextToVisit = new LinkedList<Node>();
		
		//track visited nodes
		HashSet<Integer> visited = new HashSet<Integer>();
		nextToVisit.add(source);
		
		//while haven't checked all nodes
		while(!nextToVisit.isEmpty()){
			//pop
			Node node = nextToVisit.remove();
			
			//check if destination
			if(node == destination){
				return true;
			}

			//not sure
			if(visited.contains(node.id)){
				continue;
			}

			//add to visited list
			visited.add(node.id);

			//add all children to visited list (breadth)
			for(Node child : node.adjacent){
				nextToVisit.add(child);
			}
		}
		return false;
	}
}