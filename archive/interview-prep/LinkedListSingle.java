public class LinkedListSingle {
	
	static class Node{
		int data;
		Node next;
		Node(int data){
			this.data = data;
		}
	}

	Node root;

	public void addFirst(int data){
		if (root == null){
			root = new Node(data);
			return;
		}

		Node newNode = new Node(data);
		newNode.next = root;
		root = newNode;
	}

	public void addLast(int data){
		if (root == null){
			root = new Node(data);
			return;
		}

		Node current = root;
		while(current.next != null){
			current = current.next;
		}
		current.next = new Node(data);
	}

	public int removeFirst(){
		if(root == null){
			throw new IllegalStateException();
		}
		int result = root.data;
		root = root.next;
		return result;
	}

	public boolean isEmpty(){
		return root == null;
	}

	public int removeLast(){
		if (root == null){
			throw new IllegalStateException();
		}

		int result;
		if(root.next == null){
			result = root.data;
			root = null;
			return result;
		}

		Node beforeLast = root;
		while(beforeLast.next.next != null){
			beforeLast = beforeLast.next;
		}
		result = beforeLast.next.data;
		beforeLast.next = null;
		return result;
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

	public void reverseList(){
		if(root == null){
			return;
		}
		Node current = root;
		Node prev = null;

		while(current != null){
			Node originalNext = current.next;
			current.next = prev;
			prev = current;
			current = originalNext;
		}

		root = prev;
	}

	public void reset(){
		root = null;
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

		lls.reverseList();
		lls.recursivePrint();

		lls.reset();

		lls.addFirst(12);
		lls.addFirst(11);
		lls.addFirst(10);
		lls.addFirst(9);
		lls.addFirst(8);
		lls.addFirst(6);
		lls.addFirst(5);

		lls.iterativePrint();	
		lls.reverseList();
		lls.recursivePrint();	
	}
}