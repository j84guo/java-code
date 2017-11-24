import java.util.*;

public class BstSequences{

	public static void weaveLists(LinkedList<Integer> first, LinkedList<Integer> second, 
		ArrayList<LinkedList<Integer>> results, LinkedList<Integer> prefix){

		if(first.size() == 0 || second.size() == 0){
			LinkedList<Integer> result = new LinkedList<Integer>();
			if(prefix.size() != 0){
				result.addAll((LinkedList<Integer>) prefix.clone());
			}
			result.addAll(first);
			result.addAll(second);

			//one more array of nodes
			results.add(result);

			return;
		}

		//start with first list 
		int headFirst = first.removeFirst();
		prefix.addLast(headFirst);
		weaveLists(first, second, results, prefix);
		prefix.removeLast();
		first.addFirst(headFirst);

		//start with second list
		int headSecond = second.removeFirst();
		prefix.addLast(headSecond);
		weaveLists(first, second, results, prefix);
		prefix.removeLast();	
		second.addFirst(headSecond);		
	}

	public static void main(String[] args){
		LinkedList<Integer> first = new LinkedList<Integer>();
		for(int i=0; i<3; i++){
			first.add(i);
		}

		LinkedList<Integer> second = new LinkedList<Integer>();
		for(int i=0; i<3; i++){
			second.add(i);
		}

		ArrayList<LinkedList<Integer>> results = new ArrayList<>();
		LinkedList<Integer> prefix = new LinkedList<Integer>();

		weaveLists(first, second, results, prefix);
	
		for(LinkedList<Integer> list : results){
			System.out.println(list);
		}
	}
}