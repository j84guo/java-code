import MyUtilities.SortUtilities;
import java.util.HashSet;
import java.util.HashMap;

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
		//Node a = new Node(12);
		//Node b = a;
		//a.data = 123;

		//prints 123
		//System.out.println("b : " + b.data);

		//assigns a to a different block of memory
		//a = new Node(1234);
		//System.out.println("a : " + a.data);

		//int[] A = {10, 20, 30, 15, 18};
		//System.out.println(SortUtilities.findMinIndex(A, 0, A.length-1));
		
		//HashSet<int[]> set = new HashSet<>();
		//set.add(new int[2]);
		
		//HashMap<Integer, int[]> map = new HashMap<>();
		//map.put(0, new int[2]);	
	
		//System.out.println(set);
		//System.out.println(map);

		//System.out.println("a ".split(" ").length);
	
		//HashMap<String, char[]> map = new HashMap<>();
		//map.put("jackson", new char[2]);
	
		//int[] a = {1, 2};
		//int[] b = {1, 2};		

		//System.out.println(a.equals(b));
	}
}
