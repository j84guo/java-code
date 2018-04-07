import java.util.*;

class StackSort{
	
	static Stack<Integer> sort(Stack<Integer> s1){

		Stack<Integer> s2 = new Stack<Integer>();
		while(!s1.empty()){

			int value = s1.pop();
			while(!s2.empty() && s2.peek() > value){
				s1.push(s2.pop());
			}
			s2.push(value);
		}

		return s2;
	}

	public static void main(String[] args){
		
		Stack<Integer> s = new Stack<Integer>();
		s.push(12);
		s.push(9);
		s.push(11);
		s.push(2);
		s.push(34);
		s.push(7);
		s.push(100);

		s = sort(s);

		while(!s.empty()){
			System.out.println(s.pop());
		}
	}
}