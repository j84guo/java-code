import java.util.*;

public class PermutationsInsertAllPositions {
	
	public static ArrayList<String> getPermutations(String str) {
		if(str == null) return null;

		ArrayList<String> permutations = new ArrayList<String>();
		if(str.length() == 0){
			permutations.add("");
			return permutations;
		}

		char c = str.charAt(0);
		String remainder = str.substring(1);

		ArrayList<String> remainderPermutations = getPermutations(remainder);

		for(String rp : remainderPermutations){
			for(int i=0; i<=rp.length(); i++){
				String left = rp.substring(0, i);
				String right = rp.substring(i, rp.length());
				permutations.add(left + c + right);
			}
		}

		return permutations;
	}

	public static void main(String[] args){
		ArrayList<String> permutations = getPermutations("12345678");
		for(String p : permutations){
			System.out.println(p);
		}
	}
}