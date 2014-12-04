package heapAndPriorityQueues;

import java.util.ArrayList;

public class Heap<T> {
	private boolean DEBUG = false;
	ArrayList<HeapData<T>> heap = null;

	public Heap() {
		this.heap = new ArrayList<HeapData<T>>();
		heap.add(new HeapData<T>(null, 0));
	}

	public void setHeap(ArrayList<HeapData<T>> heap) {
		this.heap = heap;
	}

	public boolean isEmpty() {
		if(heap.size() == 1) return true;
		else return false;
	}

	public Heap<T> buildHeap(ArrayList<T> a) {
		Heap<T> newHeap = new Heap<T>();
		for(int i = 0; i<= a.size(); i++) {
			newHeap.insert(a.get(i), i);
		}
		newHeap.heapify(0);
		return newHeap;
	}

	public void heapify(int i) {
		HeapData<T> inHeap = heap.get(i);
		inHeap.setHeapIndex(i);
		int left = inHeap.getLeftChild();
		int right = inHeap.getRightChild();
		int minimum = i;

		if(left< heap.size() && heap.get(left).compareTo(inHeap) < 0) {

			minimum = left;
		} else {
			minimum = i;
		}
		if(right< heap.size() && heap.get(right).compareTo(heap.get(minimum)) < 0) {
			minimum = right;
		}

		if(minimum == i);
		else{

			this.swap(i, minimum);
			if(DEBUG) {
				System.out.println(i);
				System.out.println(minimum);
			}
			heapify(minimum);
		}
	}

	private void swap(int first, int second) {
		HeapData<T> temp = heap.get(first);
		HeapData<T> secondHeapData = heap.get(second);
		int index1 = temp.getHeapIndex();
		int index2 = secondHeapData.getHeapIndex();
		heap.set(first, secondHeapData);
		heap.get(first).setHeapIndex(index1);
		heap.set(second, temp);
		heap.get(second).setHeapIndex(index2);

	}

	public HeapData<T> insert(T data, double key) {
		HeapData<T> toInsert = new HeapData<T>(data,key);
		if(this.isEmpty()) {
			heap.add(toInsert);
			toInsert.setHeapIndex(1);
		} else {
			//System.out.println(heap.size()-1);
			heap.add(toInsert);
			toInsert.setHeapIndex(heap.size()-1);
			//System.out.println(heap.size()-1);
			siftUp(heap.size()-1);
		}
		return toInsert;
	}

	private void siftUp(int nodeIndex) {
		int parentIndex;
		HeapData<T> tmp;
		HeapData<T> quest = heap.get(nodeIndex);
		if (nodeIndex >1) {
			parentIndex = quest.getParent();
			if (heap.get(parentIndex).compareTo(quest) > 0) {
				swap(parentIndex,nodeIndex);
				siftUp(parentIndex);
			}
		}
	}

	public HeapData<T> Min() {
		if(this.isEmpty()) return null;
		else return heap.get(1);
	}

	public HeapData<T> extractMin() {
		HeapData<T> res = null;
		res = this.Min();
		//System.out.println(res);
		HeapData temp = heap.get(heap.size()-1);
		heap.set(1, temp);
		//System.out.println(this.toString());
		heap.remove(heap.size()-1);
		//System.out.println("after delete last element: " + this.toString());
		if(!this.isEmpty()) { 
			//System.out.println("We heapify!");
			this.heapify(1);
			//System.out.println("after heapify: " + this.toString());
		}
		if(res != null) return res;
		else return null;
	}

	private void decreaseKey(HeapData<T> h, double key) {
		//System.out.println("Start private method!");
		//System.out.println(heap.size());
		//int i = 1;
		int count = 1;
		for(int i=1; i < heap.size(); i++) {
			HeapData assess = heap.get(i);
			if(assess.getData().equals(h.getData())) {
				assess.setKey(key);
				siftUp(i);
				//heapify(1);
			}
		}

	}

	public void decreaseKey(T h, double key) {
		//System.out.println("Start decreasing key!");
		HeapData<T> inQuestion = new HeapData<T>(h, key);
		this.decreaseKey(inQuestion, key);
	}

	public int getKey(T h) {
		int res = 0;
		for(int i = 1; i< heap.size(); i++) {
			HeapData<T> ele = heap.get(i);
			if(ele.getData().equals(h)) res = (int) ele.getKey();
		}
		return res;
	}

	public String heapSort() {
		String heapsort ="";
		while(!this.isEmpty()) {
			HeapData<T> res = this.extractMin();
			heapsort += res.toString() + " ";
		}
		return heapsort;
	}

	public String heapSort(int[] a) {
		String res = "Nothing to Sort";
		if(a!=null && a.length != 0) {
			Heap<Integer> minHeap = new Heap<Integer>();
			for(int ele: a) minHeap.insert(ele, ele);
			res = minHeap.heapSort();
		}
		return res;
	}

	public String toString() {
		String res ="";
		for(HeapData<T> ele: heap) res += (ele.toString() + " ");
		return res;
	}
}
