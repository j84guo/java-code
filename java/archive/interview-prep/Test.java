class Test{
	static class Node{
		int data;
		Node next;

		Node(int data){
			this.data = data;
		}
	}

	public static void main(String[] args){

		//null is just a placeholder for a reference, it refers to no address in particular
		//only once an object has been allocated can a reference refer to another
		Node a = new Node(12);
		Node b = a;
		a.data = 123;

		//prints 123
		System.out.println("b : " + b.data);

		//assigns a to a different block of memory
		a = new Node(1234);
		System.out.println("a : " + a.data);
	}
}