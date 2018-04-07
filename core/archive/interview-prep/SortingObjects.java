import java.util.*;

public class SortingObjects{
	public static void main(String[] args){
		
		//array of strings
		String[] fruits = new String[] {"Pineapple","Apple", "Orange", "Banana"};
		Arrays.sort(fruits);
		int i=0;
		for(String temp: fruits){
			System.out.println("fruits " + ++i + " : " + temp);
		}

		//arrayList of strings
		List<String> fruitsList = new ArrayList<String>();
		fruitsList.add("Pineapple");
		fruitsList.add("Apple");
		fruitsList.add("Orange");
		fruitsList.add("Banana");
		Collections.sort(fruitsList);
		i=0;
		for(String temp: fruitsList){
			System.out.println("fruitsList " + ++i + " : " + temp);
		}

		//array of objects
		Fruit[] fruitObjs = new Fruit[4];

		Fruit pineappale = new Fruit("Pineapple", "Pineapple description",70);
		Fruit apple = new Fruit("Apple", "Apple description",100);
		Fruit orange = new Fruit("Orange", "Orange description",80);
		Fruit banana = new Fruit("Banana", "Banana description",90);
		fruitObjs[0]=pineappale;
		fruitObjs[1]=apple;
		fruitObjs[2]=orange;
		fruitObjs[3]=banana;
		Arrays.sort(fruitObjs);
		i=0;
		for(Fruit temp: fruitObjs){
		   System.out.println("fruits " + ++i + " : " + temp.getFruitName() +
			", Quantity : " + temp.getQuantity());
		}
	}
}

class Fruit implements Comparable<Fruit>{

	private String fruitName;
	private String fruitDesc;
	private int quantity;

	public Fruit(String fruitName, String fruitDesc, int quantity) {
		super();
		this.fruitName = fruitName;
		this.fruitDesc = fruitDesc;
		this.quantity = quantity;
	}

	public String getFruitName() {
		return fruitName;
	}
	public void setFruitName(String fruitName) {
		this.fruitName = fruitName;
	}

	public String getFruitDesc() {
		return fruitDesc;
	}
	public void setFruitDesc(String fruitDesc) {
		this.fruitDesc = fruitDesc;
	}

	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	//implement Comparable interface and sort on ascending quantity
	public int compareTo(Fruit compareFruit) {
		int compareQuantity = ((Fruit) compareFruit).getQuantity();
		return this.quantity - compareQuantity;
	}

	//local class
	public static Comparator<Fruit> FruitNameComparator = new Comparator<Fruit>() {

	    public int compare(Fruit fruit1, Fruit fruit2) {

	      String fruitName1 = fruit1.getFruitName().toUpperCase();
	      String fruitName2 = fruit2.getFruitName().toUpperCase();
	      return fruitName1.compareTo(fruitName2);
	    }
	};
}