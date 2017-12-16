import java.util.*;

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

	public static Node addIterative(Node a, Node b){

		Node head = null;
		Node tail = null; 
		int carry = 0;

		while(a != null || b != null || carry != 0){

			int value = carry;
			if(a != null) value += a.data;
			if(b != null) value += b.data;
			
			if(head == null){
				tail = new Node(value % 10);
				head = tail;
			}else{
				tail.next = new Node(value % 10);
				tail = tail.next;
			}
			
			carry = value / 10;

			if(a != null) a = a.next;
			if(b != null) b = b.next;
		}

		return head;
	}

	static class Partial {
		Node node;
		int carry;

		Partial(Node node, int carry){
			this.node = node;
			this.carry = carry;
		}
	}

	public static Node addReversed(Node a, Node b){
		int aLength = getLength(a);
		int bLength = getLength(b);

		Node shorter = (aLength < bLength ? a : b);
		Node longer =  (aLength < bLength ? b : a);
		shorter = padLeadingZeroes(shorter, Math.abs(aLength - bLength));

		Partial p = addReversedRecursive(shorter, longer);
		
		Node node;
		if(p.carry != 0) {
			node = new Node(p.carry);
			node.next = p.node;
		}else{
			node = p.node;
		}

		return node;
	} 

	private static int getLength(Node node){
		int i = 0;
		while(node != null){
			i++;
			node = node.next;
		}
		return i;
	}

	private static Node padLeadingZeroes(Node head, int numZeroes){
		
		for(int i=0; i<numZeroes; i++){
			Node node = new Node(0);
			node.next = head;
			head = node;
		}

		return head;
	}

	private static Partial addReversedRecursive(Node a, Node b){
		if(a == null && b == null){
			return new Partial(null, 0);
		}

		Partial p = addReversedRecursive(a.next, b.next);
		int value = a.data + b.data + p.carry;
		
		Node node = new Node(value % 10);
		node.next = p.node;

		return new Partial(node, value / 10);	
	}

	public static boolean isPalindromeIterative(Node node){
		Stack<Node> s = new Stack<Node>();
		
		Node slow = node;
		Node fast = node;

		while(fast != null && fast.next != null){
			s.push(slow);
			//System.out.println("pushed " + slow.data);
			slow = slow.next;
			fast = fast.next.next;
		}

		int length = getLength(node);

		if(length % 2 != 0){
			slow = slow.next;
		}

		while(!s.empty()){
			//System.out.println("popping " + s.peek().data);
			if (slow.data != s.pop().data){
				return false;
			}
			slow = slow.next;
		}

		return true;
	}

	public static boolean isPalindromeReverseAndCompare(Node node){

		if (node == null || node.next == null) return true;
		Node clone = reverseAndClone(node);

		while(node != null && clone != null){
			if(node.data != clone.data){
				return false;
			}

			node = node.next;
			clone = clone.next;
		}

		return true;
	}

	private static Node reverseAndClone(Node node){
		Node head = null;

		while(node != null){
			if(head == null){
				head = new Node(node.data);
			}else{
				Node pre = new Node(node.data);
				pre.next = head;
				head = pre;
			}

			node = node.next;
		}

		return head;
	}

	static class NodeAndBoolean {
		Node node;
		boolean isPalindrome;

		NodeAndBoolean(Node node, boolean isPalindrome){
			this.node = node;
			this.isPalindrome = isPalindrome;
		}
	}

	public static boolean isPalindromeRecursive(Node node){
		if(node == null || node.next == null) return true;
		NodeAndBoolean result = recurseToMiddleAndBack(node, getLength(node)); 
		return result.isPalindrome;
	}

	private static NodeAndBoolean recurseToMiddleAndBack(Node node, int length){
		if(length == 0){
			return new NodeAndBoolean(node, true);
		}else if(length == 1){
			return new NodeAndBoolean(node.next, true);
		}

		NodeAndBoolean partial = recurseToMiddleAndBack(node.next, length - 2);

		if(!partial.isPalindrome){
			return partial;
		}else{
			return new NodeAndBoolean(partial.node.next, partial.node.data == node.data);
		}
	}

	public static void main(String[] args){

		/*
		addition of linked lists
		*/
		Node a = new Node(1);
		a.next = new Node(2);
		a.next.next = new Node(7);

		Node b = new Node(3);
		b.next = new Node(2);
		b.next.next = new Node(9);
		b.next.next.next = new Node(5);

		Node result = addReversed(a, b);
		while(result != null){
			System.out.print(result.data + " ");
			result = result.next;
		}

		/*
		check if linked list palindrome
		*/
		System.out.println();
		Node c = new Node(1);
		c.next = new Node(2);
		c.next.next = new Node(9);
		c.next.next.next = new Node(2);
		c.next.next.next.next = new Node(1);

		System.out.println(isPalindromeRecursive(new Node(11)));
		System.out.println(isPalindromeRecursive(b));
		System.out.println(isPalindromeRecursive(c));
	}
}