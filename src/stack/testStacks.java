package stack;

import java.util.ArrayList;

/**
 * test of Stacks's Push and Pop methods
 * @author AnhVuNguyen
 *
 */
public class testStacks {

	public static void main(String[] args) {
		ArrayList<Integer> a = new ArrayList<Integer>(); 
		for(int i=0;i<101;i ++) a.add(i);
		Stack<Integer> test = new Stack<Integer>(a);

		//System.out.println(test.getTop());
		test.Push(11);
		test.Push(0);
		test.Push(20);
		/*System.out.println(test.Pop());
		System.out.println(test.getTop());
		System.out.println(test.Pop());
		System.out.println(test.getTop());
		System.out.println(test.Pop());
		System.out.println(test.Pop());
		System.out.println(test.getTop());
		*/

		Stack<Integer> t = new Stack<Integer>();
		t.Push(11);
		t.Push(0);
		t.Push(20);
		for(int i = 0; i < 11; i++) t.Push(i);
		System.out.println(t.Pop());
		System.out.println(t.Pop());
		System.out.println(t.toString());
		
		Stack<String> stringStack = new Stack<String>();
		stringStack.Push("first");
		stringStack.Push("Second");
		stringStack.Push("Third");
		stringStack.Push("Fourth");
		System.out.println(stringStack.Pop());
	}
}
