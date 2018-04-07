import java.util.HashSet;
import java.util.Iterator;

public class HashSetTest{
	
	static class Node{
		int data;
		Node left;
		Node right;

		Node(int data){
			this.data = data;
		}
	}

	public static void main(String[] args){
		HashSet<Integer> hi = new HashSet<>();
		hi.add(1);
		hi.add(2);
		hi.add(3);
	
		//contains 
		System.out.println(hi.contains(1));
		System.out.println(hi.contains(4));
		System.out.println(hi);	
		
		//remove
		System.out.println(hi.remove(1));
		System.out.println(hi.remove(4));
		System.out.println(hi);
		
		//size 
		System.out.println(hi.size());
		System.out.println(hi.isEmpty());

		//iterator
		Iterator<Integer> it = hi.iterator();
		while(it.hasNext()){
			System.out.println(it.next());
		}

		//HashSet of objects
		Node n1 = new Node(1);
		Node n2 = new Node(2);
		Node n3 = new Node(3); 
		
		HashSet<Node> hn = new HashSet<>();
		hn.add(n1);
		hn.add(n2);
		hn.add(n3);

		System.out.println(hn.contains(n1));
		System.out.println(hn.contains(new Node(1)));

		//multiple iterator 
		Iterator<Integer> it2 = hi.iterator(); 
		System.out.println(it2.hasNext());
		while(it2.hasNext()) System.out.println(it2.next());
		
	}
}
