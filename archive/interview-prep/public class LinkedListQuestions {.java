public class LinkedListQuestions {

	static class Node {
		int data;
		Node next;

		Node(int data){
			this.data = data;
			this.next = null;
		}
	}
	
	public static Node addRecursive(Node a, Node b){
		return addRecursive(a, b, 0);
	}	

	private static Node addRecursive(Node a, Node b, int carry){
		if(a == null && b == null && carry == 0) {
			return null;
		}

		int value = carry;
		if(a != null) value += a.data;
		if(b != null) value += b.data;

		Node node = new Node(value % 10);
		node.next = addRecursive((a == null ? null : a.next), (b == null ? null : b.next), value / 10);
		return node;
	}

	public static void main(String[] args){

		Node a = new Node(1);
		a.next = new Node(2);
		a.next.next = new Node(7);

		Node b = new Node(3);
		b.next = new Node(2);
		b.next.next = new Node(9);
		b.next.next.next = new Node(5);

		Node result = addRecursive(a, b);
		while(result != null){
			System.out.print(result.data + " ");
			result = result.next;
		}
	}
}