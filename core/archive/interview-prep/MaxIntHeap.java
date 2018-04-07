import java.util.*; 

public class MaxIntHeap{
	
	//heap properties
	private int capacity;
	private int size;
	private int[] items;

	//multiple contructors
	public MaxIntHeap(int capacity){
		this.size = 0;
		this.capacity = capacity;
		ensureCapacity();
		items = new int[capacity];
	}
	public MaxIntHeap(int[] array){
		buildHeap(array);
	}

	//get items
	public int[] getArrayCopy(){
		return items.clone();
	}

	//builds from array
	private void buildHeap(int[] array){

		/*
		this.size = 0;
		this.capacity = array.length;
		ensureCapacity();
		items = new int[capacity];

		for(int i=0; i<array.length; i++){
			add(array[i]);
		}
		*/

		items = array;
		size = array.length;
		capacity = array.length;
		ensureCapacity();

        //note that we start at the leaves and heapify up!
        for(int i=size/2; i>=0; i--){
            heapifyDown(i);
        }
	}

	//reset heap
	public void reset(int capacity){
		size = 0;
		this.capacity = capacity;
		ensureCapacity();
		items = new int[this.capacity];
	}

	//get indices
	private int getLeftChildIndex(int index){
		return 2*index + 1;
	}
	private int getRightChildIndex(int index){
		return 2*index + 2;
	}
	private int getParentIndex(int index){
		return (index-1)/2;
	}

	//get data
	private int getLeftChild(int index){
		return items[getLeftChildIndex(index)];
	}
	private int getRightChild(int index){
		return items[getRightChildIndex(index)];
	}
	private int getParent(int index){
		return items[getParentIndex(index)];
	}

	//has child or parent
	private boolean hasLeftChild(int index){
		return getLeftChildIndex(index) < size;
	}
	private boolean hasRightChild(int index){
		return getRightChildIndex(index) < size;
	}
	private boolean hasParent(int index){
		return getParentIndex(index) >= 0;
	}

	//size and empty
	public int size(){
		return this.size;
	}
	public boolean isEmpty(){
		return this.size == 0;
	}

	//capacity
	private void ensureCapacity(){
		if(capacity == 0) capacity = 10;
		if(size == capacity){
			items = Arrays.copyOf(items, capacity*2);
			capacity *= 2;
		}
	}

	//insert
	public void add(int value){
		ensureCapacity();
		items[size] = value;
		size++;
		heapifyUp();
	}

	//swap 
	private void swap(int[] array, int i, int j){
		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}

	//used after inserting
	public void heapifyUp(){
		int index = size-1;
		
		while(hasParent(index) && items[index] > getParent(index)){
			swap(items, index, getParentIndex(index));
			index = getParentIndex(index);
		}
	}

	//see top
	public int peek(){
		if(isEmpty()){
			throw new IllegalStateException(); 
		}
		return items[0];
	}

	//pop
	public int poll(){
		if(isEmpty()){
			throw new IllegalStateException(); 
		}
		int value = items[0];
		swap(items, 0, size-1); //for heap sort
		size--;
		heapifyDown(0);	
		return value;
	}

	//used after popping and building heap
	public void heapifyDown(int index){
		while (hasLeftChild(index)){
			int largerChildIndex = getLeftChildIndex(index);
			if(hasRightChild(index) && getRightChild(index) > getLeftChild(index)){
				largerChildIndex = getRightChildIndex(index); 
			}

			if(items[index] > items[largerChildIndex]){
				break;
			}else{
				swap(items, index, largerChildIndex);
			}
			index = largerChildIndex;
		}
	}

	public static void main(String[] args){
		int[] array = {213, 234, 1, 324, 12, 323, 123, 1, 2, 3, 4, 5};
		int originalSize = array.length;

		MaxIntHeap maxHeap = new MaxIntHeap(array);

		/*
		maxHeap.reset(0);
		maxHeap.add(98);
		maxHeap.add(12309421);
		maxHeap.add(0);
		maxHeap.add(1);
		maxHeap.add(100982);
		maxHeap.add(20);
		maxHeap.add(21);
		maxHeap.add(5);
		*/

		//heap sort can be implemented by repeated polling if the poll method swaps last and root
		
		while(!maxHeap.isEmpty()){
			maxHeap.poll();
		}

		array = maxHeap.getArrayCopy();
		for(int i=0; i<originalSize; i++){
			System.out.println(array[i]);
		}
	}
}