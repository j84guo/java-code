import java.util.*;

public class LinkedListTest {
	
	public static void main(String[] args){

		LinkedList<Integer> l = new LinkedList<Integer>();
		for(int i=0; i<10; i++){
			l.add(i);
		}

		System.out.println(l);

		int item = l.remove(5);
		System.out.println(l);
		l.add(5, item);
		System.out.println(l);
	}
}