public class LinkedListDouble{
	
	static class Node{
		int data;
		Node next;
		Node prev;
		public Node(int data){
			this.data = data;
			next = null;
			prev = null;
		}
	}

	Node root;
	Node tail;

	public void addFirst(int data){
		if (root == null){ 
			root = new Node(data);
			tail = root;
		}

		Node newNode = new Node(data);
		newNode.next = root;
		root.prev = newNode;
		root = newNode;
	}

	public void addLast (int data){
		if (tail == null){ 
			tail = new Node(data);
			root = tail;
		}

		Node newNode = new Node(data);
		newNode.prev = tail;
		tail.next = newNode;
		tail = newNode;
	}

	public int removeFirst(){
		if(root == null){
			throw new IllegalStateException();
		}
		int result = root.data;
		root = root.next;
		if(root != null) root.prev = null; 
		return result;
	}

	public int removeLast(){
		if(tail == null){
			throw new IllegalStateException();
		}
		int result = tail.data;
		tail = tail.prev;
		if(tail != null) tail.next = null; 
		return result;
	}

	public boolean isEmpty(){
		return root == null || tail == null;
	}

	public void recursivePrint(){
		recursivePrint(root);
	}
	public void recursivePrint(Node node){
		if (node == null){
			return;
		}
		System.out.println("forwards :" + node.data);
		recursivePrint(node.next);
		System.out.println("backwards :" + node.data);
	}

	public void iterativePrint(){
		Node current = root;
		while(current != null){
			System.out.println(current.data);
			current = current.next;
		}
	}

	public static void main(String[] args){
		LinkedListSingle lls = new LinkedListSingle();
		lls.addLast(1);
		lls.addLast(2);
		lls.addLast(3);
		lls.addLast(4);
		lls.addLast(5);
		lls.addLast(6);
		lls.addLast(7);
		lls.addLast(8);

		lls.recursivePrint();
		lls.reset();

		lls.addFirst(12);
		lls.addFirst(11);
		lls.addFirst(10);
		lls.addFirst(9);
		lls.addFirst(8);
		lls.addFirst(7);
		lls.addFirst(6);

		lls.iterativePrint();	
		lls.recursivePrint();	
	}
}