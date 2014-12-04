package heapAndPriorityQueues;

import java.util.ArrayList;

public class PriorityQueueTest {

	public static void main(String[] args) {
		Heap<String> priorityQueue = new Heap<String>();
		
		//test isEmpty
		System.out.println(priorityQueue.isEmpty());
		
		//test toString with no elements
		System.out.println(priorityQueue);
		
		//test insert
		priorityQueue.insert("IBM", 3);
		priorityQueue.insert("Google", 2);
		priorityQueue.insert("Yahoo", 5);
		priorityQueue.insert("Amazon", 6);
		priorityQueue.insert("Microsoft",4);
		priorityQueue.insert("Akamai",7);
		priorityQueue.insert("NASA", 100);
		priorityQueue.insert("Facebook", 5);
		priorityQueue.insert("whatever", 10);
		
		//test toString with element
		System.out.println(priorityQueue.getKey("Microsoft"));
		
		//test isEmpty
		System.out.println(priorityQueue.isEmpty());

		//test decreaseKey
		priorityQueue.decreaseKey("Facebook", 1);
		System.out.println(priorityQueue);

		//testHeapSort
		String heapsort = priorityQueue.heapSort();
		System.out.println(heapsort);

		//test HeapSort with an integer array
		Heap testHeapSort = new Heap();
		int[] a = {1,6,76,45,78,23,65,17,97, 9,87,91,73,25,-1, 45,34, 034, 13,00, 14,298,12, 99, 68, 69};
		int[] b = null;
		int[] c = {};
		int[] d = {1};
		System.out.println(testHeapSort.heapSort(a));
		System.out.println(testHeapSort.heapSort(b));
		System.out.println(testHeapSort.heapSort(c));
		System.out.println(testHeapSort.heapSort(d));

	}
}
