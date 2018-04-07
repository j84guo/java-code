import java.util.*;

public class SortingThings {
	
	public static void main(String[] args){

		// 1. sorting arrays
		String[] fruits = {"pineapple", "apple", "orange", "banana"};
		Arrays.sort(fruits);
		for(String s : fruits){
			System.out.println(s);
		}
		System.out.println();

		Arrays.sort(fruits, new Comparator<String>(){
		    public int compare(String s1, String s2){
				return s2.compareTo(s1);
			}
		});
		for(String s : fruits){
			System.out.println(s);
		}
		System.out.println();

		// 2. sorting arraylists
		ArrayList<String> fruitsList = new ArrayList<String>();
		fruitsList.add("pineapple"); 
		fruitsList.add("apple"); 
		fruitsList.add("orange");
		fruitsList.add("banana");
		Collections.sort(fruitsList);  
		for(String s : fruitsList){
			System.out.println(s);
		}
		System.out.println();

		// 3. sorting objects which implement Comparable<Object> 
		Fruit p = new Fruit("pineapple");
		Fruit a = new Fruit("apple");
		Fruit o = new Fruit("orange");
		Fruit b = new Fruit("banana");
		
		ArrayList<Fruit> objList = new ArrayList<Fruit>();
		objList.add(p);
		objList.add(a);
		objList.add(o);
		objList.add(b);

		Collections.sort(objList);
		
		for(Fruit f : objList){
			System.out.println(f.name);
		}

	}
}


class Fruit implements Comparable<Fruit> {
	
	String name;
	
	Fruit(String name){
		this.name = name;
	}

	public int compareTo(Fruit f){
		return this.name.compareTo(f.name);
	}
}
