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
		newNode.next = root.next;
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
		while(current.next.next != null){
			beforeLast = beforeLast.next;
		}
		result = beforeLast.next.data;
		beforeLast.next = null;
		return result;
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
	}
}