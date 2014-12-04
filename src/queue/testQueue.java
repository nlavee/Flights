package queue;

import java.util.ArrayList;

import stack.Stack;

public class testQueue {

	public static void main(String[] args) {
		ArrayList<Integer> a = new ArrayList<Integer>(); 
		for(int i=0;i<111;i ++) a.add(i);
		Queue<Integer> test = new Queue<Integer>(a);
		
		System.out.println(test.isEmpty());
		test.Enqueue(11);
		test.Enqueue(12);
		System.out.println(test.isEmpty());
		while(!test.isEmpty()) {
			System.out.print(test.Dequeue() + " ");
		}
	}

}
