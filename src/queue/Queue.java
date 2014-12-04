package queue;

import java.util.ArrayList;

public class Queue<T> {
	ArrayList<T> queue = new ArrayList<T>();
	int tail;

	public Queue(ArrayList<T> queue) {
		this.queue = queue;
		tail= queue.size()-1;
	}

	public Queue() {
		tail = -1;
	}

	public void Enqueue(T data) {
		queue.add(data);
		tail += 1;
	}

	public T Dequeue() {
		T res;
		if(queue.isEmpty()) return null;
		else {
			tail --;
			res = queue.get(0);
			queue.remove(0);
			return res;
		}
	}

	public boolean isEmpty() {
		if(queue.size() == 0) return true;
		else return false;
	}

	public String toString() {
		String res = "";
		while(!this.isEmpty()) res += this.Dequeue().toString() + " ";
		return res;
	}
}
