import java.util.*;

public class AnimalQueue{
	
	//linked lists simulate queues when using add/remove
	LinkedList<Animal> dogs;
	LinkedList<Animal> cats;

	int nextId;

	public AnimalQueue(){
		dogs = new LinkedList<Animal>();
		cats = new LinkedList<Animal>();
		nextId = 0;
	}

	public int size(){
		return dogs.size() + cats.size();
	}

	public void enqueue(Animal a){
		a.id = nextId;
		nextId++;

		if(a.type == AnimalType.dog){
			dogs.add(a);
		}else if(a.type == AnimalType.cat){
			cats.add(a);
		}
	}

	public Animal dequeueAny(){
		int catLatest = cats.size() == 0 ? Integer.MAX_VALUE : cats.get(0).id;
		int dogLatest = dogs.size() == 0 ? Integer.MAX_VALUE : dogs.get(0).id;

		if(dogs.size() == 0 && cats.size() == 0){
			return null;
		}else if(catLatest < dogLatest){
			return cats.remove(0);
		}else{
			return dogs.remove(0);
		}
	}

	public Animal dequeueDog(){
		if(dogs.size() == 0) return null;
		return dogs.remove();
	}

	public Animal dequeueCat(){
		if(cats.size() == 0) return null;
		return cats.remove();
	}

	public static void main(String[] args){
		AnimalQueue aq = new AnimalQueue();
		aq.enqueue(new Animal(AnimalType.dog));
		aq.enqueue(new Animal(AnimalType.cat));
		aq.enqueue(new Animal(AnimalType.dog));
		aq.enqueue(new Animal(AnimalType.dog));
		aq.enqueue(new Animal(AnimalType.dog));
		aq.enqueue(new Animal(AnimalType.dog));
		aq.enqueue(new Animal(AnimalType.dog));
		aq.enqueue(new Animal(AnimalType.cat));
		aq.enqueue(new Animal(AnimalType.cat));
		aq.enqueue(new Animal(AnimalType.cat));

		Animal a;
		while(aq.size() != 0){
			a = aq.dequeueAny();
			System.out.println(a.type + " " + a.id);
		} 
	}
}

//constants
class AnimalType{
	static final String dog = "DOG";
	static final String cat = "CAT";
}


//animal
class Animal{
	String type;
	int id;

	Animal(String type){
		this.type = type;
	}
}