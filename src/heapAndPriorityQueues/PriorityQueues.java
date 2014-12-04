package heapAndPriorityQueues;

import java.util.ArrayList;

public class PriorityQueues<T> {
	ArrayList<T> heap; 
	
	public PriorityQueues() {
		heap = new ArrayList<T>();
		
		//we start our queue at index 1 instead of zero
		//easier for getting index of parent, left child, right child
		heap.set(0, null);
	}
	
	/**
	 * get parent index
	 * @param index of element in question
	 * @return index of parent element
	 */
	public int getParent(int i) {
		int parent;
		parent = (int) Math.floor(i/2);
		if(parent != 0) return parent;
		else return -1;
	} //end getParent
	
	public int getLeft(int i) {
		int left;
		left = 2*i;
		if(left <= heap.size()) return left; //check to make sure this index is inside the arrayList
		else return (Integer) null;
	} //end getLeft
	
	public int getRight(int i) {
		int right;
		right = 2*i + 1;
		if(right<=heap.size()) return right; //check to make sure this index is inside the arrayList
		else return (Integer) null;
	} //end getRight
	
	public String toString() {
		String res = "";
		for(T ele: heap) res+= ele.toString() + " ";
		return res;
	}
	
} //end class
