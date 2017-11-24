import java.util.*;

class MyQueue {
	
	Stack<Integer> newestOnTop;
	Stack<Integer> oldestOnTop;

	MyQueue(){
		newestOnTop = new Stack<Integer>();
		oldestOnTop = new Stack<Integer>();
	}

	void enqueue(int value){
		newestOnTop.push(value);
	}

	int dequeue(){
		if(oldestOnTop.empty()){
			while(!newestOnTop.empty()){
				oldestOnTop.push(newestOnTop.pop());
			}	
		}
		
		return oldestOnTop.empty()? -1 : oldestOnTop.pop();
	}

	int size(){
		return newestOnTop.size() + oldestOnTop.size();
	}

	public static void main(String[] args){
		MyQueue mq = new MyQueue();
		mq.enqueue(1);
		mq.enqueue(2);
		mq.enqueue(3);
		mq.enqueue(4);
		System.out.println(mq.dequeue());
		System.out.println(mq.dequeue());
		System.out.println(mq.dequeue());
		System.out.println(mq.dequeue());
		
		//error code returned
		System.out.println(mq.dequeue());
		System.out.println(mq.dequeue());
	}
}