import java.util.*;

public class SetOfStacks{

	ArrayList<Stack> stacks = new ArrayList<Stack>();
	
	public int capacity;
	
	public SetOfStacks(int capacity){
		this.capacity = capacity;
	}

	public Stack getLastStack(){
		if(stacks.size() == 0) return null;
		return stacks.get(stacks.size() - 1);
	}

	public void push(int v){
		Stack s = getLastStack();
		if(s != null && !s.isFull()){
			s.push(v);
		}else{
			Stack newStack = new Stack(capacity);
			newStack.push(v);
			stacks.add(newStack);
		}
	}

	public int pop(){
		Stack s = getLastStack();
		if(s == null) return -1;
		int v = s.pop();
		if(s.size == 0) stacks.remove(stacks.size() - 1);
		return v;
	}

	public boolean isEmpty(){
		Stack s = getLastStack();
		return s == null || s.isEmpty(); 
	}

	public int popAt(int index){
		Stack s = stacks.get(index);
		int v = s.pop();
		
		while(stacks.size() > index + 1){
			Stack next = stacks.get(index + 1);
			s.push(next.removeBottom());
			s = next;

			if(next.isEmpty()){
				stacks.remove(index + 1);
			}

			index++;
		}

		return v;
	}

	public static void main(String[] args){
		
		SetOfStacks sos = new SetOfStacks(3);
		
		sos.push(1);
		sos.push(2);
		sos.push(3);
		sos.push(4);
		sos.push(5);
		sos.push(6);

		System.out.println(sos.popAt(1));
	}
}

class Stack{
	private int capacity;
	Node top;
	Node bottom;
	int size = 0;

	Stack(int capacity){
		this.capacity = capacity;
	} 

	boolean isFull(){
		return capacity == size;
	}

	void join(Node above, Node below){
		if(below != null) below.above = above;
		if(above != null) above.below = below;
	}

	boolean push(int v){
		if(size >= capacity){
			return false;
		}
		size++;
		Node n = new Node(v);
		if(size == 1) bottom = n;
		join(n, top);
		top = n;
		return true;
	}

	int pop(){
		Node t = top;
		top = top.below;
		size--;
		return t.value;
	}

	boolean isEmpty(){
		return size == 0;
	}

	int removeBottom(){
		Node b = bottom;
		bottom = bottom.above;
		if(bottom != null){
			bottom.below = null;
		}
		size--;
		return b.value;
	}
}

class Node{
	int value;
	Node above;
	Node below;

	Node(int value){
		this.value = value;
	}
}