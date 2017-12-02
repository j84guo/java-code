import java.util.*;

public class PermutationsBruteForce {
	
	public static ArrayList<LinkedList<Integer>> getPermutationsUnique(LinkedList<Integer> inputList) {
		return getPermutationsUnique(inputList, new LinkedList<Integer>());
	}

	private static ArrayList<LinkedList<Integer>> getPermutationsUnique(LinkedList<Integer> inputList, LinkedList<Integer> prefix) {
		
		ArrayList<LinkedList<Integer>> output = new ArrayList<LinkedList<Integer>>();
		
		if(inputList.size() == 0){
			LinkedList<Integer> permutation = new LinkedList<Integer>();
			permutation.addAll((LinkedList<Integer>) prefix.clone());
			output.add(permutation);
			return output;
		}

		for(int i=0; i<inputList.size(); i++){
			int item = inputList.remove(i);
			prefix.addLast(item);
			
			ArrayList<LinkedList<Integer>> permutationsGivenPrefix = getPermutationsUnique(inputList, prefix);
			output.addAll(permutationsGivenPrefix);

			prefix.removeLast();
			inputList.add(i, item);
		}

		return output;
	}

	public static void main(String[] args) {

		LinkedList<Integer> inputList = new LinkedList<Integer>();
		for(int i=0; i<5; i++){
			inputList.add(i);
		}

		ArrayList<LinkedList<Integer>> output = getPermutationsUnique(inputList);
		for(LinkedList<Integer> list : output){
			System.out.println(list);
		}
	}
}