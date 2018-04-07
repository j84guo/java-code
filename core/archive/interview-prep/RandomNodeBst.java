import java.util.*;

public class RandomNodeBst{

	private static class Node{
		int data;
		int size;

		Node left;
		Node right;

		Node(int data){
			this.data = data;
			this.size = 1;
		}
	}

	public Node root;

	public void insert(int data){
		root = insert(root, data);
	}

	private Node insert(Node node, int data){
		if(node == null) return new Node(data);
		
		node.size++;
		if(data <= node.data){
			node.left = insert(node.left, data);
		}else{
			node.right = insert(node.right, data);
		}

		return node;
	}

	// first approach : 
	// track subtree size at its root
	// generate random number based on tree size where each node gets 1/n probability
	// O(n)... in a balanced tree 
	public int getRandomNodeValue(){
		return getRandomNodeValue(root);
	}

	private int getRandomNodeValue(Node node){

		if(node == null) return -1;
		
		int numNodes = node.size;
		int leftSize = node.left == null? 0 : node.left.size;
		int rightSize = node.right == null? 0 : node.right.size;
	
		Random r = new Random();
		int value = r.nextInt(numNodes);
		
		System.out.println("left size : " + leftSize);
		System.out.println("right size : " + rightSize);
		System.out.println("random value : " + value);

		if(value < leftSize){
			return getRandomNodeValue(node.left);
		}else if(value == leftSize){
			return node.data;
		}else{
			return getRandomNodeValue(node.right);
		}
	}

	// second approach :
	// random number calls can be expensive
	public int getRandomNodeValue2(){
		if(root == null) return -1;

		Random r = new Random();
		int index = r.nextInt(root.size);
		Node node = getNode(root, index);

		if(node != null)
			return node.data;
		else 
			return -1;
	}

	private Node getNode(Node node, int index){
		if(node == null) return null;

		int leftSize = node.left == null? 0 : node.left.size;
		int rightSize = node.right == null? 0 : node.right.size;

		if(index < leftSize){
			return getNode(node.left, index);
		}else if(index == leftSize){
			return node;
		}else{
			return getNode(node.right, index - leftSize - 1);
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

		RandomNodeBst b = new RandomNodeBst();		
		b.insert(10);
		b.insert(5);
		b.insert(15);
		b.insert(2);
		b.insert(7);
		b.insert(12);
		b.insert(17);

		System.out.println(b.countPathsToSum(27));
	}	
}