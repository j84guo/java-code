import java.util.*;

public class MyBinarySearchTree {

	//tree node
	static class Node {
		int data;
		Node left;
		Node right;
		Node parent;

		Node(int data){
			this.data = data;
		}
	}

	//root
	private Node root;

	//empty constructor
	public MyBinarySearchTree(){
		this.root = null;
	}

	//optimal insertion based on medians
	public MyBinarySearchTree(int[] input){
		this.root = null;
		insertFromArray(input, 0, input.length-1);
	}
	private void insertFromArray(int[] input, int left, int right){
		if(left > right){
			return;
		}

		int midpoint = (left + right)/2;
		insert(input[midpoint]);

		insertFromArray(input, left, midpoint-1);
		insertFromArray(input, midpoint+1, right);
	}

	//find a node
	public Node getNode(int data){
		return getNode(root, data);
	}
	private Node getNode(Node node, int data){
		if(node == null) return null;

		if(node.data == data){
			return node;
		}else if (data <= node.data){
			return getNode(node.left, data);
		}else{
			return getNode(node.right, data);
		}
	}

	//insert 
	void insert(int data){
		root = insert(root, null, data);
	}
	private Node insert(Node node, Node parent, int data){
		if(node == null){
			Node newNode = new Node(data); 
			newNode.parent = parent; 
			return newNode;
		}else if(data <= node.data){
			node.left = insert(node.left, node, data);
		}else{
			node.right = insert(node.right, node, data);
		}
		return node;
	}

	//deletion cases
	//leaf (delete)
	//single child (replace)
	//2 children (replace with inorder successor)
	public void delete(int id){	
		root = delete(root, id);
	}
	private Node delete(Node node, int id){
		if(node == null) return null;
		if(id < node.data){
			node.left = delete(node.left, id);
		}else if(id > node.data){
			node.right = delete(node.right, id);
		}
		else{
			if(node.left == null && node.right == null){
				return null;
			}else if (node.left == null){
				return node.right;
			}else if(node.right == null){
				return node.left;
			}else{
				node.data = getMinValue(node.right);
				node.right = delete(node.right, node.data);
			}
		}

		return node;
	}
	private int getMinValue(Node node){
		while(node.left != null){
			node = node.left;
		}
		return node.data;
	}

	//in order traversal
	public void inOrder(){
		inOrder(root);
	}
	private void inOrder(Node node){
		if(node == null) return;

		inOrder(node.left);
		System.out.println(node.data);
		inOrder(node.right);
	}

	//pre order traversal
	public void preOrder(){
		preOrder(root);
	}
	private void preOrder(Node node){
		if(node == null) return;
		System.out.println(node.data);
		preOrder(node.left);
		preOrder(node.right);
	}

	//post order traversal
	public void postOrder(){
		postOrder(root);
	}
	private void postOrder(Node node){
		if(node == null) return;

		postOrder(node.left);
		postOrder(node.right);
		System.out.println(node.data);
	}

	//post order traversal
	public int getHeight(){
		return getHeight(root);
	}
	public int getHeight(Node node){
		if(node == null){
			return -1;
		}

		int leftHeight = getHeight(node.left);
		int rightHeight = getHeight(node.right);
		return Math.max(leftHeight, rightHeight) + 1;
	}

	//ineffecient method
	//recurses (post order) down tree 
	//find subtree heights at each node and compare
	//this is inefficient... each node is has its height calculated by its ancestors
	public boolean isBalancedSimple(){
		return isBalancedSimple(root);	
	} 
	private boolean isBalancedSimple(Node node){
		if(node == null){
			return true;
		}

		int leftHeight = getHeight(node.left);
		int rightHeight = getHeight(node.right);	
		
		if(Math.abs(leftHeight - rightHeight) > 1){
			return false;
		}else{
			return isBalancedSimple(node.left) && isBalancedSimple(node.right);
		}
	}

	//however, one recursion through the tree should be enough to determine balance
	//an error code is bubbled up in place of height 
	public boolean isBalanced(){
		int height = getEnhancedHeight(root);
		if(height == Integer.MIN_VALUE){
			return false;
		}else{
			return true;
		}
	}
	private int getEnhancedHeight(Node node){
		if(node == null){
			return -1;	
		}

		int leftHeight = getEnhancedHeight(node.left);
		if(leftHeight == Integer.MIN_VALUE) return Integer.MIN_VALUE;

		int rightHeight = getEnhancedHeight(node.right);
		if(rightHeight == Integer.MIN_VALUE) return Integer.MIN_VALUE;

		if(Math.abs(leftHeight - rightHeight) > 1){
			return Integer.MIN_VALUE;
		}else{
			return Math.max(leftHeight, rightHeight) + 1;
		}
	}

	//basically a post order traversal
	//taking O(n) time
	public boolean validateBst(){
		return validateBst(root, 0, Integer.MAX_VALUE);
	}
	private boolean validateBst(Node node, int min, int max){
		if(node == null) return true;
		if(node.data < min || node.data > max) return false;
		return validateBst(node.left, min, node.data-1) && validateBst(node.right, node.data+1, max);
	}

	//either minimum of right subtree of first greater ancestor
	public int successorValue(int id){
		Node node = getNode(id);

		if(node.right != null){
			return getMinValue(node.right);
		}else{
			Node parent = node.parent;
			while(parent != null){
				if(parent.data > node.data){
					return parent.data;
				}
				parent = parent.parent;
			}
			return -1;
		}
	}

	public static void main(String[] args){

		//minimum height tree based on sorted array
		int[] input = {2, 5, 7, 10, 12, 15, 17};
		MyBinarySearchTree tree = new MyBinarySearchTree(input);
		
		/*
		tree.insert(21);
		tree.insert(28);
		tree.insert(1);
		tree.insert(4);
		tree.insert(14);
		tree.insert(13);
		tree.insert(11);
		tree.insert(16);
		tree.insert(20);
		tree.insert(38);
		*/

		//check tree balance
		System.out.println(tree.isBalanced());

		//check valid BST
		System.out.println(tree.validateBst());

		//print in order successor
		System.out.println("successor of 12 : " + tree.successorValue(12));
		
		//in order 
		tree.inOrder();
	}
}