import java.util.*;

class LinkedLists {
	
	static class Node{
		int data;
		Node next;

		Node(int data){
			this.data = data;
		}
	}

	//goal : remove duplicates from unsorted linked list
	//approach : make one pass, store nodes is hashmap, remove duplicates  
	static void removeDuplicates(Node root){
		
		HashMap<Integer, Integer> visited = new HashMap<Integer, Integer>();
		Node node = root;
		Node prev = null;

		while(node != null){
			if(!visited.containsKey(node.data)){
				visited.put(node.data, 1);
				prev = node;
			}else{
				prev.next = node.next;
			}

			node = node.next;
		}
	}

	static void removeDuplicates2(Node root){
		Node node = root;

		while(node != null){
			Node runner = node;
			while(runner.next != null){
				if(runner.next.data == node.data){
					runner.next = runner.next.next; 
				}
				runner = runner.next;
			}

			node = node.next;
		}
	}


	static Node makeLinkedList(int[] arr){
		if(arr.length == 0) return null;

		Node root = new Node(arr[0]);
		Node node = root;

		for(int i=1; i<arr.length; i++){
			node.next = new Node(arr[i]); 
			node = node.next;
		}

		return root;
	} 

	/*
	approach : 
	define k=1 as the last node (node n-1)
	start node at root 
	start scoot at root + k
	iterate until scout is null, return node
	*/
	static Node kthToLast(Node root, int k){
		if(root == null) return null;

		Node node = root;
		Node scout = root;

		for(int i=0; i<k; i++){
			if(scout == null) return null;
			scout = scout.next;
		}

		while(scout != null){
			scout = scout.next;
			node = node.next;
		}

		return node;
	}

	static void deleteMiddleNode(Node node){
		if(node.next == null){
			return;
		}
		node.data = node.next.data;
		node.next = node.next.next;
	}

	static void print(Node root){
		Node node = root;
		while(node != null){
			System.out.print(node.data + " ");
			node = node.next;
		}
		System.out.println();
	}

	static Node partition(Node root, int x){
		
		Node leftIndex = null;
		Node leftRoot = null;
		Node rightIndex = null;
		Node rightRoot = null;
		
		Node node = root;

		while(node != null){
			if(node.data < x){
				if(leftIndex == null){
					leftRoot = new Node(node.data);
					leftIndex = leftRoot;
				}else{
					leftIndex.next = new Node(node.data);
					leftIndex = leftIndex.next;
				}
			}else{
				if(rightIndex == null){
					rightRoot = new Node(node.data);
					rightIndex = rightRoot;
				}else{
					rightIndex.next = new Node(node.data);
					rightIndex = rightIndex.next;
				}
			}
			node = node.next;
		}

		if(leftIndex == null){
			return rightRoot;
		}
		leftIndex.next = rightRoot;
		return leftRoot;
	}

	public static void main(String[] args){	
		int arr[] = {1, 2, 3, 3, 4, 4, 1, 2, 3, 109, 4, 5, 5, 2, 7, 314159265};
		Node root = makeLinkedList(arr);
		print(root);

		removeDuplicates2(root);
		print(root);

		int[] arr2 = {9, 8, 7, 6, 5, 4, 3, 2, 1};
		Node root2 = makeLinkedList(arr2);
		System.out.println(kthToLast(root2, 4).data);

		deleteMiddleNode(kthToLast(root2, 4));
		print(root2);

		Node root3 = partition(root2, 4);
		print(root3);
	}
}