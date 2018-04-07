import java.util.*;

public class MyBinarySearchTree {
	
	static class Node {
		int data;
		Node left;
		Node right;
		Node parent;

		Node(int data){
			this.data = data;
		}
	}

	private Node root;

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

	public void delete(int id){	
		root = delete(root, id);
	}

	private Node delete(Node node, int id){
		if(node == null) return null;

		if(id < node.data){
			node.left = delete(node.left, id);
		}
		else if(id > node.data){
			node.right = delete(node.right, id);
		}
		else{
			if(node.left == null && node.right == null){
				return null;
			}
			else if (node.left == null){
				return node.right;
			}
			else if(node.right == null){
				return node.left;
			}
			else{
				node.data = getMinValue(node.right);
				node.right = delete(node.right, id);
			}
		}

		return node;
	}

	private int getMinValue(Node node){
		while(node != null){
			node = node.left;
		}
		return node.data;
	}

	public void inOrder(){
		inOrder(root);
	}
	private void inOrder(Node node){
		if(node == null) return;

		inOrder(node.left);
		System.out.println(node.data);
		inOrder(node.right);
	}

	public void preOrder(){
		preOrder(root);
	}
	private void preOrder(Node node){
		if(node == null) return;
		System.out.println(node.data);
		preOrder(node.left);
		preOrder(node.right);
	}

	public void postOrder(){
		postOrder(root);
	}
	private void postOrder(Node node){
		if(node == null) return;

		postOrder(node.left);
		postOrder(node.right);
		System.out.println(node.data);
	}

	public static void main(String[] args){
		MyBinarySearchTree tree = new MyBinarySearchTree();
		tree.insert(10);
		tree.insert(5);
		tree.insert(15);
		tree.insert(2);
		tree.insert(7);
		tree.insert(12);
		tree.insert(17);

		tree.inOrder();

		Node node = tree.getNode(15);
		Node parent = node.parent;
		System.out.println("parent of 15 is " + parent.data);
	}
}