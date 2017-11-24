import java.util.*;

public class PermutationsPreferred {
	
	// approach : 
	// find partial permutations, add current item in all possible locations
	public static ArrayList<String> getPermutations(String str) {
		
		ArrayList<String> output = new ArrayList<String>();

		if(str.length() == 0){
			output.add("");
			return output;
		}

		char c = str.charAt(0);
		ArrayList<String> partials = getPermutations(str.substring(1));

		for(String partial : partials){
			for(int i=0; i <= partial.length(); i++){
				String left = partial.substring(0, i);
				String right = partial.substring(i, partial.length());
				output.add(left + c + right);
			}
		}

		return output;
	}

	public static void main(String[] args){
		ArrayList<String> permutations = getPermutations("jackson");
		for(String p : permutations){
			System.out.println(p);
		}
	}
}