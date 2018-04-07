import java.util.*;

public class RandomNodeTree {
	
	//tree node
	static class Node {
		int data;
		int size;
		Node left;
		Node right;
		Node parent;

		Node(int data){
			this.data = data;
			this.size = 1;
		}
	}

	//root
	private Node root;

	//empty constructor
	public RandomNodeTree(){
		this.root = null;
	}

	//optimal insertion based on medians
	public RandomNodeTree	(int[] input){
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
		}

		if(data <= node.data){
			node.left = insert(node.left, node, data);
		}else{
			node.right = insert(node.right, node, data);
		}
		node.size++; 
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

		node.size--;
		return node;
	}
	private int getMinValue(Node node){
		while(node.left != null){
			node = node.left;
		}
		return node.data;
	}

	public void inOrder(){
		inOrder(root);
	}

	private void inOrder(Node node){
		
		if(node == null){
			return;
		}

		inOrder(node.left);
		System.out.println(node.data + " " + node.size);
		inOrder(node.right);
	}

	public Node getRandomNode(){
		if(root == null) return null;
		return getRandomNode(root);
	}

	private Node getRandomNode(Node node){
		
		int leftSize = node.left == null? 0 : node.left.size;
		int rightSize = node.right == null? 0 : node.right.size;
		
		Random r = new Random();
		int index = 1 + r.nextInt(leftSize + 1 + rightSize);

		if(index == leftSize+1){
			return node;
		}else if(index < leftSize + 1){
			return getRandomNode(node.left);
		}else{
			return getRandomNode(node.right);
		}
	}

	public Node getRandomNodeOptimal(){
		if(root == null) return null;
		Random r = new Random();
		int index = 1 + r.nextInt(root.size);
	
		return getRandomNodeOptimal(root, index);
	}

	private Node getRandomNodeOptimal(Node node, int index){
		int leftSize = node.left == null? 0 : node.left.size;
		
		if(index == leftSize+1){
			return node;
		}else if(index < leftSize + 1){
			return getRandomNodeOptimal(node.left, index);
		}else{
			return getRandomNodeOptimal(node.right, index - leftSize - 1);
		}
	}

	public static void main(String[] args){
		
		RandomNodeTree tree = new RandomNodeTree();
		tree.insert(10);
		tree.insert(5);
		tree.insert(15);
		tree.insert(2);
		tree.insert(7);
		tree.insert(12);
		tree.insert(17);
		
		Node node = tree.getRandomNodeOptimal();
		System.out.println(node.data);
	}
}