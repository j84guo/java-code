import java.util.*;

public class StackQuestions {
	
	//stack that tracks minimum
	//option 1 : second stack
	static class MinStack {

		Stack<Integer> data;
		Stack<Integer> min;

		MinStack() {
			data = new Stack<Integer>();
			min = new Stack<Integer>();
		}

		void push(int value) {
			data.push(value);
			if(min.empty() || value <= min.peek()) min.push(value); 
		}

		int pop() {
			int value = data.pop();
			if(value == min.peek()) min.pop();
			return value;
		}

		int min() {
			return min.peek();
		}
	}
	// option 2 : have each element track the minimum
	static class StackWithMin extends Stack<NodeWithMin> {
		public void push(int value) {
			int newMin = Math.min(value, min());
			super.push(new NodeWithMin(value, newMin));
		} 

		public int min() {
			if(this.empty()) {
				return Integer.MAX_VALUE;
			}else {
				return this.peek().min;
			}
		}
	}
	static class NodeWithMin {
		public int value;
		public int min;
		public NodeWithMin (int value, int min) {
			this.value = value;
			this.min = min;
		}
	}

	public static void main(String[] args) {

		/*
		minimum stack
		*/
		StackWithMin ms = new StackWithMin();
		ms.push(2);
		System.out.println(ms.min());
		ms.push(1);
		System.out.println(ms.min());
		ms.push(10);
		System.out.println(ms.min());
		ms.push(-1);
		System.out.println(ms.min());
		ms.pop();
		System.out.println(ms.min());
		ms.pop();
		System.out.println(ms.min());
		ms.pop();
		System.out.println(ms.min());



	}
}




