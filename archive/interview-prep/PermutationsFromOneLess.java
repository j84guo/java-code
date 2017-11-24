import java.util.*;

public class PermutationsFromOneLess {
	
	public static ArrayList<String> getPermutations(String str) {
		
		ArrayList<String> result = new ArrayList<String>();
		if(str.length() == 0){
			result.add("");
			return result;
		}

		for(int i=0; i<str.length(); i++){
			
			char c = str.charAt(i);
			String left = str.substring(0, i);
			String right = str.substring(i+1, str.length());

			ArrayList<String> partials = getPermutations(left + right);
			for(String s : partials){
				result.add(c + s);
			}
		}

		return result;
	}

	public static void main(String[] args){
		ArrayList<String> permutations = getPermutations("jackson");
		for(String p : permutations){
			System.out.println(p);
		}
	}
}