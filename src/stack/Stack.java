package stack;

import java.util.ArrayList;

/**
 * ArrayList Implementation of Stack
 * @author AnhVuNguyen
 *
 * @param <T>
 */
public class Stack<T> {
	ArrayList<T> a;
	int top;
	
	public Stack(ArrayList<T> a) {
		this.a = a;
		top = a.size()-1;
	}
	
	public Stack() {
		a = new ArrayList<T>();
		top = -1;
	}

	/**
	 * get the next element that would be pop without actually removing it
	 * @return
	 */
	public T getTop() {
		return a.get(top);
	}
	
	/**
	 * Push the data into the stack
	 * @param data
	 */
	public void Push(T data){
		top += 1;
		a.add(data);
	}
	
	/**
	 * Pop the data out of the stack
	 * @return
	 */
	public T Pop() {
		T data;
		if(this.isEmpty()) return null;
		else {
			top = top - 1;
			data = a.get(top+1);
			a.remove(top+1);
		}
		return data;
	}
	
	/**
	 * check whether a stack is empty
	 * @return
	 */
	public boolean isEmpty() {
		if(a.size() ==0) return true;
		else return false;
	}

	@Override
	public String toString() {
		String res = "";
		while(!this.isEmpty()) res += this.Pop().toString() + " " ;
		return res;
	}
	
	
}
