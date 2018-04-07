import java.util.*;

public class TowersOfHanoi {
	
	public static void moveDisks(Stack<Integer> source, Stack<Integer> buffer, Stack<Integer> destination) {
		if(source == null || buffer == null || destination == null) return;
		moveDisks(source.size(), source, buffer, destination);
	}

	private static void moveDisks(int n, Stack<Integer> source, Stack<Integer> buffer, Stack<Integer> destination) {

		int disk;

		if(n == 0) {
			return;
		}else if(n == 1) {
			disk = source.pop();
			destination.push(disk);
			return;
		}

		moveDisks(n-1, source, destination, buffer);
		disk = source.pop();
		destination.push(disk);
		moveDisks(n-1, buffer, source, destination);
	}

	public static void main(String[] args) {

		Stack<Integer> stack1 = new Stack<Integer>();
		for(int i=25; i>=0; i--){
			stack1.push(i);
		}

		Stack<Integer> stack2 = new Stack<Integer>();
		Stack<Integer> stack3 = new Stack<Integer>();

		moveDisks(stack1, stack2, stack3);

		System.out.println("Contents of stack3 :");
		while(!stack3.empty()){
			System.out.print(stack3.pop() + " ");
		}
		System.out.println();

		System.out.println("Contents of stack2 :");
		while(!stack2.empty()){
			System.out.print(stack2.pop() + " ");
		}
		System.out.println();

		System.out.println("Contents of stack1 :");
		while(!stack1.empty()){
			System.out.print(stack1.pop() + " ");
		}
		System.out.println();
	}
}