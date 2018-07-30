public class MinIntHeap{
	private int capacity = 10;
	private int size = 0;

	int[] items = new int[capacity];

	//indices
	private int getLeftChildIndex(int parentIndex) {
		return 2 * parentIndex + 1;
	}
	private int getRightChildIndex(int parentIndex) {
		return 2 * parentIndex + 2;
	}
	private int getParentIndex(int childIndex){
		return (childIndex-1) / 2; //division rounds down I think
	}

	//has
	private boolean hasLeftChild(int index) {
		return getLeftChildIndex(index) < size;
	}
	private boolean hasRightChild(int index) {
		return getRightChildIndex(index) < size;
	}
	private boolean hasParent(int index) {
		return getParentIndex(index) >= 0;
	}

	//data
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

	public int peek(){
		if(size == 0){
			throw new IllegalStateException(); 
		}
		return items[0];
	}

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

	public void add(int item){ // add 	
		ensureExtraCapacity();
		items[size] = item;
		size++;
		heapifyUp();
	}

	public void heapifyUp(){
			int index = size - 1;
			while(hasParent(index) && parent(index) > items[index]){ //or use < if this is a max heap as opposed to a min heap
				swap(getParentIndex(index), index);
				index = getParentIndex(index);
			}
	}

	public void heapifyDown(){
		int index = 0;
		while (hasLeftChild(index)){
			int smallerChildIndex = getLeftChildIndex(index)
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

}