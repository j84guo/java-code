public class Permutations {
	
	public ArrayList<LinkedList<Integer>> getPermutationsUnique(LinkedLists<Integer> inputList) {
		
		return getPermutationsUnique(0, inputList, new LinkedList<Integer>());
	}

	private ArrayList<LinkedList<Integer>> getPermutationsUnique(LinkedLists<Integer> inputList, LinkedList<Integer> prefix) {
		
		ArrayList<LinkedList<Integer>> output = new ArrayList<LinkedList<Integer>>();
		
		if(inputList.size() == 0){
			LinkedList<Integer> permutation = new LinkedList<Integer>();
			permutation.addAll(prefix);
			output.add(permutation);
			return output;
		}

		for(int i=0; i<inputList.size(); i++){
			int item = inputList.remove(i);
			prefix.add(item);
			
			ArrayList<LinkedList<Integer>> permutationsGivenPrefix = getPermutationsUnique(inputList, prefix);
			output.addAll(permutationsGivenPrefix);

			prefix.remove();
			inputList.add(i, item);
		}

		return output;
	}

	public static void main(String[] args) {

	}
}