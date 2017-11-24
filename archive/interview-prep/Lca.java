class Lca{
	
	static class Node{
		int data;
		Node left;
		Node right;
		Node parent;

		Node(int data){
			this.data = data;
		}
	}

	Node root;

	// first approach : 
	// requires parent pointers, no external storage
	// use parent reference to traverse upwards, checking necessary subtrees on the way
	// O(n)... worst case performs 1 pass of the entire tree, searching all nodes 
	public Node getLowestCommonAncestor1(Node u, Node v){
		if(u == null || v == null) return null;

		Node node = u;
		while(node != null){
			if(node == v) return v;
			if(covers(getSibling(node), v)){
				return node.parent;
			}
			node = node.parent;
		}

		return null;
	}

	private Node getSibling(Node node){
		if(node.parent == null) return null;
		if(node.parent.left == node){
			return node.parent.right;
		}else{
			return node.parent.left;
		}
	}

	private boolean covers(Node source, Node node){
		if(source == null || node == null) return false; 
		if(source == node) return true;
		return covers(source.left, node) || covers(source.right, node);
	}

	// second approach : 
	// no parent pointers, no external storage
	// starting from root, go to the subtree where both nodes reside
	// once they are in different subtrees, the lca is found
	// O(n)... 
	// covers is called on 2n nodes, 2n/2, 2n/4, 2n/8... which approaches 2n
	public Node getLowestCommonAncestor2(Node u, Node v){
		
		// are nodes non-null and in the tree
		if(!covers(root, u) || !covers(root, v)) return null;
		return getLowestCommonAncestor2(root, u, v);
	}

	private Node getLowestCommonAncestor2(Node source, Node u, Node v){
		
		//source is one of the nodes
		if(source == null || source == u || source == v) return source;

		boolean uLeft = covers(source.left, u);
		boolean vLeft = covers(source.left, v);

		if(uLeft != vLeft){
			return source;
		}else if(uLeft && vLeft){
			return getLowestCommonAncestor2(source.left, u, v);
		}else{
			return getLowestCommonAncestor2(source.right, u, v);
		}
	}

	// check if tree 2 is a subtree of tree 1
	// first approach : 
	// conduct pre-order traversals and compare 
	public boolean containsTree(Node root1, Node root2){

		StringBuilder string1 = new StringBuilder();
		StringBuilder string2 = new StringBuilder();
		
		getOrderString(root1, string1);
		getOrderString(root2, string2);

		return string1.indexOf(string2.toString()) != -1;
	}

	public void getOrderString(Node node, StringBuilder sb){
		
		if(node == null){
			sb.append("X");
			return;
		}
		sb.append(node.data + " ");
		getOrderString(node.left, sb);
		getOrderString(node.right, sb);
	}

	public boolean containsTree2(Node root1, Node root2){

		if(root2 == null) return true;
		return subTree(root1, root2);
	}

	public boolean subTree(Node root1, Node root2){
		if(root1 == null) return false;
		if(root1.data == root2.data && matchTree(root1, root2)) return true;
		return subTree(root1.left, root2) || subTree(root1.right, root2);
	}

	boolean matchTree(Node root1, Node root2){
		if(root1 == null && root2 == null){
			return true;
		}else if(root1 == null || root2 == null){
			return false;
		}else if(root1.data != root2.data){
			return false;
		}else{
			return matchTree(root1.left, root2.left) && matchTree(root1.right, root2.right);
		}
	}

	public int countPathsToSum(int sum){
		return countPathsToSum(root, sum);
	}

	private int countPathsToSum(Node node, int sum){
		if(node == null) return 0;

		int paths = 0;
		paths += fromNodeCountPathsToSum(node, 0, sum);

		paths += countPathsToSum(node.right, sum);
		paths += countPathsToSum(node.left, sum);

		return paths;
	}

	private int fromNodeCountPathsToSum(Node node, int currentSum, int sum){
		if(node == null) return 0;
		
		int paths = 0;
		if(node.data + currentSum == sum) paths++;

		paths += fromNodeCountPathsToSum(node.left, currentSum+node.data, sum);
		paths += fromNodeCountPathsToSum(node.right, currentSum+node.data, sum);

		return paths;
	}

	public static void main(String[] args){
		
		Lca bt = new Lca();
		bt.root = new Node(10);
		bt.root.left = new Node(7); bt.root.left.parent = bt.root;
  		bt.root.left.left = new Node(1); bt.root.left.left.parent = bt.root.left;
		bt.root.left.right = new Node(8); bt.root.left.right.parent = bt.root.left;
		bt.root.right = new Node(13); bt.root.right.parent = bt.root;
		bt.root.right.left = new Node(-8); bt.root.right.left.parent = bt.root.right;
		bt.root.right.right = new Node(-8); bt.root.right.right.parent = bt.root.right;

		// StringBuilder sb1 = new StringBuilder();
		// StringBuilder sb2 = new StringBuilder();
		// System.out.println(bt.containsTree(bt.root, bt.root.left));
		// System.out.println(bt.containsTree2(bt.root, bt.root.left));
		
		System.out.println(bt.countPathsToSum(15));
	}
}