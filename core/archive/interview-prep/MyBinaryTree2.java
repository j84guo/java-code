import java.util.*;

public class MyBinaryTree2{

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

	/*****************************************************************
	all possible arrays that could be inserted to make the binary tree
	******************************************************************/

	public ArrayList<LinkedList<Integer>> getPossibleArrays(){
		return getPossibleArrays(root);
	} 

	private ArrayList<LinkedList<Integer>> getPossibleArrays(Node node){
        
		ArrayList<LinkedList<Integer>> results = new ArrayList<LinkedList<Integer>>(); 

		//null tree has no permutations
		if(node == null){
			results.add(new LinkedList<Integer>());
			return results;
		}

		//prefix holds the partial selected order
		LinkedList<Integer> prefix = new LinkedList<Integer>();
		prefix.add(node.data);

		//calculate list of permutations for each subtree, based on the current prefix
		ArrayList<LinkedList<Integer>> left = getPossibleArrays(node.left);
		ArrayList<LinkedList<Integer>> right = getPossibleArrays(node.right);

		//for all possible combinations of left and right permutations, weave them and add to results
		for(LinkedList<Integer> leftList : left){
			for(LinkedList<Integer> rightList : right){
				ArrayList<LinkedList<Integer>> weaved = new ArrayList<LinkedList<Integer>>(); 
				weaveLists(leftList, rightList, weaved, prefix);
				results.addAll(weaved);
			}
		}

		//return result including permutations for this subtree
		return results;
	}

	//given two lists, weave their items
	private void weaveLists(LinkedList<Integer> first, LinkedList<Integer> second, 
		ArrayList<LinkedList<Integer>> results, LinkedList<Integer> prefix){

		if(first.size() == 0 || second.size() == 0){

			//while generating permutations for the current subtree, don't break prefix for the next subtree
			LinkedList<Integer> result = new LinkedList<Integer>();
			if(prefix.size() != 0) result.addAll((LinkedList<Integer>) prefix.clone());
			result.addAll(first);
			result.addAll(second);
			results.add(result);
			return;		
		}

		int headFirst = first.removeFirst();
		prefix.addLast(headFirst);
		weaveLists(first, second, results, prefix);
		prefix.removeLast();
		first.addFirst(headFirst);

		int headSecond = second.removeFirst();
		prefix.addLast(headSecond);
		weaveLists(first, second, results, prefix);
		prefix.removeLast();
		second.addFirst(headSecond);		
	}

	public static void main(String[] args){

		MyBinaryTree2 bt = new MyBinaryTree2();
		bt.insert(10);
		bt.insert(5);
		bt.insert(15);
		bt.insert(2);
		bt.insert(7);
		bt.insert(12);
		bt.insert(17);

		LinkedList<Integer> first = new LinkedList<Integer>();
		for(int i=0; i<3; i++){
			first.add(i);
		}
		LinkedList<Integer> second = new LinkedList<Integer>();
		for(int i=0; i<3; i++){
			second.add(i);
		}

		LinkedList<Integer> prefix = new LinkedList<Integer>();
		prefix.add(1000);

		ArrayList<LinkedList<Integer>> results = new ArrayList<LinkedList<Integer>>();
		
		bt.weaveLists(first, second, results, prefix); 
		for(LinkedList<Integer> list : results){
			System.out.println(list);
		}

		//results = bt.getPossibleArrays();
		//for(LinkedList<Integer> list : results){
		//	System.out.println(list);
		//}
	}
}