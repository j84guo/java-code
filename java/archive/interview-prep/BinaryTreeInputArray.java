import java.util.*;

public class BinaryTreeInputArray{
	
	static class Node{
		int data;
		
		Node left;
		Node right;
		
		Node(int data){
			this.data = data;
		}
	}

	Node root;

	void insert(int data){
		if(root == null){
			root = new Node(data);
		}else{
			insert(data, root);
		}
	}

	void insert(int data, Node node){
		if(data <= node.data){
			if(node.left == null){
				node.left = new Node(data);
			}else{
				insert(data, node.left);
			}
		}else{
			if(node.right == null){
				node.right = new Node(data);
			}else{
				insert(data, node.right);
			}
		}
	}

	void inOrderTraversal(){
		inOrderTraversal(root);
	}

	void inOrderTraversal(Node node){
		if(node == null) return;

		inOrderTraversal(node.left);
		System.out.println(node.data);
		inOrderTraversal(node.right);
	}

	void preOrderTraversal(){
		preOrderTraversal(root);
	}

	void preOrderTraversal(Node node){
		if(node == null) return;

		System.out.println(node.data);
		preOrderTraversal(node.left);
		preOrderTraversal(node.right);
	}

	void postOrderTraversal(){
		postOrderTraversal(root);
	}

	void postOrderTraversal(Node node){
		if(node == null) return;

		postOrderTraversal(node.left);
		postOrderTraversal(node.right);
		System.out.println(node.data);
	}

	void delete(int data){
		root = delete(data, root);
	}

	Node delete(int data, Node node){
		if(node == null) return null;

		if(data < node.data){
			node.left = delete(data, node.left);
		}else if(data > node.data){
			node.right = delete(data, node.right);
		}else{

			if(node.left == null && node.right == null){
				node = null;
			}else if(node.left == null){
				node = node.right;
			}else if(node.right == null){
				node = node.left;
			}else{
				node.data = getMinValue(node.right);
				node.right = delete(node.data, node.right);
			}
		}

		return node;
	}

	int getMinValue(Node node){
		if(node.left == null) return node.data;
		return getMinValue(node.left);
	}

	/*******************************************************
	Generating all possible inputs that built a binary tree.
	********************************************************/
	
	public LinkedList<LinkedList<Integer>> getInputArrays(){
		return getInputArrays(root);
	}

	//todo : add base case
	private LinkedList<LinkedList<Integer>> getInputArrays(Node node){
		
		LinkedList<LinkedList<Integer>> results = new LinkedList<LinkedList<Integer>>();

		if(node == null){
			results.add(new LinkedList<Integer>());
			return results;	
		}

		LinkedList<LinkedList<Integer>> leftInputArrays = getInputArrays(node.left);
		LinkedList<LinkedList<Integer>> rightInputArrays = getInputArrays(node.right);
		
		LinkedList<Integer> prefix = new LinkedList<Integer>();
		prefix.addLast(node.data);

		for(LinkedList<Integer> left : leftInputArrays){
			for(LinkedList<Integer> right : rightInputArrays){
				interMingle(left, right, results, prefix);
			}
		}

		return results;
	}

	private void interMingle(LinkedList<Integer> left, LinkedList<Integer> right, 
		LinkedList<LinkedList<Integer>> results, LinkedList<Integer> prefix){

		if(left.size() == 0 || right.size() == 0){
			LinkedList<Integer> result = new LinkedList<Integer>();
			if(prefix.size() != 0){
				result.addAll((LinkedList<Integer>) prefix.clone());
			}
			result.addAll(left);
			result.addAll(right);
			results.add(result);
			return;
		}

		int leftHead = left.removeFirst();		
		prefix.addLast(leftHead);
		interMingle(left, right, results, prefix);
		prefix.removeLast();
		left.addFirst(leftHead);

		int rightHead = right.removeFirst();
		prefix.addLast(rightHead);
		interMingle(left, right, results, prefix);
		prefix.removeLast();
		right.addFirst(rightHead);
	}

	public static void main(String[] args){

		BinaryTreeInputArray bt = new BinaryTreeInputArray();
		bt.insert(10);
		bt.insert(5);
		bt.insert(15);
		bt.insert(2);
		bt.insert(7);
		bt.insert(12);
		bt.insert(17);

		LinkedList<Integer> first = new LinkedList<Integer>();
		for(int i=1; i<4; i++){
			first.add(i);
		}
		LinkedList<Integer> second = new LinkedList<Integer>();
		for(int i=1; i<4; i++){
			second.add(i);
		}

		LinkedList<LinkedList<Integer>> results = new LinkedList<LinkedList<Integer>>();
		results = bt.getInputArrays();

		for(LinkedList<Integer> result : results){
			System.out.println(result);
		}
	}
}