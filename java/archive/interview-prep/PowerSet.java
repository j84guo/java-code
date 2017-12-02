import java.util.*;

public class PowerSet{
	
	public static ArrayList<LinkedList<Integer>> getPowerSet(LinkedList<Integer> inputSet){

		ArrayList<LinkedList<Integer>> output = new ArrayList<LinkedList<Integer>>();

		int width = inputSet.size();
		int numSubsets = 1 << width;

		for(int i=0; i<numSubsets; i++){
			LinkedList<Integer> set = getSetFromInteger(inputSet, i);
			output.add(set);
		}

		return output;
	}

	private static LinkedList<Integer> getSetFromInteger(LinkedList<Integer> inputSet, int index){
		int width = inputSet.size();
		LinkedList<Integer> set = new LinkedList<Integer>();

		for(int i=0; i<width; i++){
			int value = inputSet.get(i);
			
			if(shouldInclude(index)){
				set.add(value);
			}

			index = index >> 1;
		}

		return set;
	}

	private static boolean shouldInclude(int i){
		if((i & 1) == 1) return true;
		return false;
	}

	public static ArrayList<LinkedList<Integer>> getPowerSetRecursive(LinkedList<Integer> inputSet){
		if(inputSet == null) return null;
		return getPowerSetRecursive(inputSet, 0);
	}

	private static ArrayList<LinkedList<Integer>> getPowerSetRecursive(LinkedList<Integer> inputSet, int index){
		ArrayList<LinkedList<Integer>> output = new ArrayList<LinkedList<Integer>>();
		
		if(index == inputSet.size()){
			output.add(new LinkedList<Integer>());
			return output;
		}

		ArrayList<LinkedList<Integer>> firstHalf = getPowerSetRecursive(inputSet, index+1);
		int item = inputSet.get(index);

		for(LinkedList<Integer> list : firstHalf){
			LinkedList<Integer> listAndOne = new LinkedList<Integer>();
			listAndOne.addAll(list);
			listAndOne.add(item);

			output.add(list);
			output.add(listAndOne);
		}

		return output;
	}

	public static void main(String[] args){
		
		LinkedList<Integer> inputSet = new LinkedList<Integer>();
		for(int i=0; i<3; i++){
			inputSet.add(i);
		}

		ArrayList<LinkedList<Integer>> output;
		output = getPowerSetRecursive(inputSet);

		for(LinkedList<Integer> list : output){
			System.out.println(list);
		}
	}
}