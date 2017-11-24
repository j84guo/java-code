import java.util.*;

/*

height of tree : O(log(n))
heapify up : O(log(n))
heapify down : O(log(n))

*/
public class MinIntHeap{

	private int capacity;
	private int size;
	private int[] items;

	public MinIntHeap(int capacity){
		this.size = 0;
		this.capacity = capacity;
		items = new int[capacity];
	}

	//get indices
	private int getLeftChildIndex(int parentIndex) {
		return 2 * parentIndex + 1;
	}
	private int getRightChildIndex(int parentIndex) {
		return 2 * parentIndex + 2;
	}
	private int getParentIndex(int childIndex){
		return (childIndex-1) / 2; 
	}

	//has child/parent
	private boolean hasLeftChild(int index) {
		return getLeftChildIndex(index) < size;
	}
	private boolean hasRightChild(int index) {
		return getRightChildIndex(index) < size;
	}
	private boolean hasParent(int index) {
		return getParentIndex(index) >= 0;
	}

	//get data
	private int leftChild(int index){
		return items[getLeftChildIndex(index)];
	}
	private int rightChild(int index){
		return items[getRightChildIndex(index)];
	}
	private int parent(int index){
		return items[getParentIndex(index)];
	}

	//swap items
	private void swap(int a, int b){
		int temp = items[a];
		items[a] = items[b];
		items[b] = temp;
	}
	
	//capacity extension
	private void ensureExtraCapacity(){
		if(size == capacity){
			items = Arrays.copyOf(items, capacity * 2);
			capacity *= 2;
		}
	}

	//check empty
	public boolean isEmpty(){
		return size == 0;
	}

	//get size
	public int size(){
		return size;
	}

	//get root
	public int peek(){
		if(size == 0){
			throw new IllegalStateException(); 
		}
		return items[0];
	}

	//pop
	public int poll(){ // remove
		if(size == 0){
			throw new IllegalStateException(); 
		}
		int item = items[0];
		items[0] = items[size-1];
		size--;
		heapifyDown();
		return item;
	}

	//insert
	public void add(int item){ // add 	
		ensureExtraCapacity();
		items[size] = item;
		size++;
		heapifyUp();
	}

	//used when inserting
	public void heapifyUp(){
			int index = size - 1;
			while(hasParent(index) && parent(index) > items[index]){ 
				swap(getParentIndex(index), index);
				index = getParentIndex(index);
			}
	}

	//used when popping
	public void heapifyDown(){
		int index = 0;
		while (hasLeftChild(index)){
			int smallerChildIndex = getLeftChildIndex(index);
			if(hasRightChild(index) && rightChild(index) < leftChild(index)){
				smallerChildIndex = getRightChildIndex(index); 
			}

			if(items[index] < items[smallerChildIndex]){
				break;
			}else{
				swap(index, smallerChildIndex);
			}
			index = smallerChildIndex;
		}
	}

	public static void main(String[] args){
		MinIntHeap minHeap = new MinIntHeap(10);
		minHeap.add(3);
		minHeap.add(4);
		minHeap.add(6);
		minHeap.add(2);
		minHeap.add(102);
		minHeap.add(39329374);
		minHeap.add(19);

		while(!minHeap.isEmpty()){
			System.out.println(minHeap.poll());
		}
	}
}