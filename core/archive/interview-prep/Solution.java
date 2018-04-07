import java.util.*;

public class Solution {
	
	public static void main(String[] args) {
		MultiStack ms = new MultiStack(2);
		ms.push(5);
		ms.push(4);
		ms.push(3);
		ms.push(2);
		ms.push(1);
		System.out.println(ms.popFrom(0));
		System.out.println(ms.popFrom(0));
		System.out.println(ms.popFrom(0));
		System.out.println(ms.popFrom(0));
		System.out.println(ms.popFrom(0));
		System.out.println(ms.popFrom(0));
	}
}

class MultiStack {

	private int capacity;
	private ArrayList<LinkedList<Integer>> stacks;

	MultiStack (int capacity) {
		this.capacity = capacity;
		stacks = new ArrayList<LinkedList<Integer>>();
	}

	void push(int value) {
		LinkedList<Integer> s;
		if (stacks.size() == 0 || stacks.get(stacks.size() - 1).size() == capacity) {
			s = new LinkedList<Integer>();
			s.addLast(value);
			stacks.add(s);
		} else {
			s = stacks.get(stacks.size() - 1);
			s.addLast(value);
		}
	}

	int pop() {
		if (stacks.size() == 0){
			return -1;
		}
		LinkedList<Integer> s = stacks.get(stacks.size() - 1);
		int value = s.removeLast();
		if (s.size() == 0) stacks.remove(stacks.size() - 1);
		return value;
	}

	int popFrom(int index){
		if(index >= stacks.size()){
			return -1;
		}

		LinkedList<Integer> s = stacks.get(index);
		int value = s.removeLast();
		shiftElements(index);
		return value;
	}

	void shiftElements(int index){
		if(index == stacks.size() - 1){
			if(stacks.get(stacks.size() - 1).size() == 0){
				stacks.remove(stacks.size() - 1);
			}
			return;
		}

		int value = stacks.get(index+1).removeFirst();
		stacks.get(index).addLast(value);

		shiftElements(index + 1);
	}
}