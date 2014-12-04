import java.util.Iterator;

/**
 * class Linked List that used data from ListNode
 * @author AnhVuNguyen
 *
 * @param <T>
 */

public class LinkedList<T> {
	ListNode<T> head = new ListNode<T>(null);
	int size = 0;

	public LinkedList(ListNode<T> head, int size) {
		//super();
		this.head = head;
		this.size = size;

	}

	public LinkedList(ListNode<T> head) {
		this.head = head;
	}


	//toString method to print out Linked List
	public String toString() {
		String result ="";
		for(ListNode<T> curr = head; curr != null; curr = curr.getNext()){
			result += curr.getData();
		}
		return result;
	}
	
	/**
	 * Method to add the data into the node as a root
	 * @param data
	 */
	//add to front, old head prev = new head next, new head prev = null
	public void addToFront(T data) {
		//if(this != null) {//take into account empty linked list
		//System.out.println("head in the add to front method " + head);
		//System.out.println("Method is considering that data is " + data);
			if(head != null){
				//System.out.println("Method is considering that head is not null");
				ListNode<T> newNode = new ListNode<T>(data, head, null);
				head.prev = newNode;
				this.head = newNode;
				//System.out.println("**LinkedList after changing: " + this);
				
			}
			else{
				//head = new ListNode<T>(null,null,null);
				//System.out.println("Method is considering that head is null");
				ListNode<T> newNode = new ListNode<T>(data, null, null);
				//System.out.println("data from new Node: " + newNode);
				//System.out.println("This is the current head before assigning to new Node: " + head);
				//head.setData(data);
				this.head = newNode;
				//System.out.println("*head after assigning to new Node: " + head);
				//System.out.println("**LinkedList after changing: " + this);
			}
		/*} else {
			//ListNode<T> n = new ListNode<T>(data,null,null);
			//new LinkedList(n);
			System.out.println("Linked List == null");
		}*/
	} //end addToFront

	/**
	 * Method to swap two nodes
	 * @param first
	 * @param second
	 */
	// take the data of the first node and put it in a temporary node, as normal after
	public void swapNode(ListNode<T> first, ListNode<T> second) {
		T temp = first.getData();
		first.data = second.getData();
		second.data = temp;
	} //end swapNode

	/**
	 * Method to compare the string of
	 * @param first
	 * @param second
	 * @return 
	 */
	//compare String value of data of Node
	public int compare(ListNode<T> first, ListNode<T> second) {
		return ((String) first.getData()).compareTo((String) second.getData());
	}

	/**
	 * Selection sort method
	 * @param start
	 */
	public void selectionSort(ListNode<T> start) {
		if(start != null) {
			ListNode<T> minNode = findIndexOfMin(start);
			swapNode(minNode, start);
			selectionSort(start.getNext());
		}
	}

	/**
	 * Method to find out which Node is the min of the linked list
	 * @param start
	 * @return
	 */
	public ListNode<T> findIndexOfMin(ListNode<T> start) {
		ListNode<T> minNodeOfSub = new ListNode<T>(null, null, null);
		if(start.getNext() != null)
		{
			minNodeOfSub = findIndexOfMin(start.getNext());
			if(((String) minNodeOfSub.getData()).compareTo((String) start.getData()) < 0) {
				return minNodeOfSub;
			}
			else {
				return start;
			}
		}
		else
		{
			return start;
		}
	}

	/**
	 * Method to do quick sort for Linked List
	 * @param start
	 * @param end
	 */
	public void quickSortLList(ListNode<T> start, ListNode<T> end) {
		ListNode pivot = partitionLList();
		quickSortLList(start,pivot.getPrev());
		quickSortLList(pivot.getNext(), end);
	}

	private ListNode<T> partitionLList() {
		ListNode lastLHS = this.head;
		ListNode firstUnknown = head.getNext();
		ListNode pivot = head;

		while(firstUnknown != null) {
			if(((String) firstUnknown.getData()).compareTo((String) pivot.getData()) < 0) {
				this.swapNode(firstUnknown, lastLHS);
				lastLHS = lastLHS.getNext();
				firstUnknown = firstUnknown.getNext();
			}
			else {
				firstUnknown = firstUnknown.getNext();
			}
			this.swapNode(pivot,lastLHS);
		}
		return lastLHS.getNext();
	} // end partitionLList

	/**
	 * Method to search for a node with specific data and return that node
	 * @param data
	 * @return ListNode
	 */
	ListNode<T> searchNode(T data) {
		ListNode<T> result = null;
		ListNode<T> curr;
		for (curr = head; 
				curr != null && !data.equals(curr.getData()) ;
				curr = curr.getNext());
		// empty body
		if (curr != null) {
			result = curr;
		} // end if 

		return result;
	} // end search()

	/**
	 * method to search for data from the node with specific data
	 * @param data
	 * @return data
	 */
	T search(T data) {
		T result = null;
		ListNode<T> curr;
		for (curr = head; 
				curr != null && !data.equals(curr.getData()) ;
				curr = curr.getNext());
		// empty body
		if (curr != null) {
			result = curr.getData();
		} // end if 

		return result;
	} // end search()

	/**
	 * Method to delete a Node with specific data from a Linked List.
	 * If node is not found, it'll say Node not found
	 * Do not pass in null
	 * @param data
	 */
	public void deleteNode(T data) {
		ListNode<T> curr;
		if(data != null) {
			curr =  this.searchNode(data);
			if(curr != null) {
				ListNode<T> previousNode = curr.getPrev();
				ListNode<T> nextNode = curr.getNext();
				if(previousNode != null && nextNode != null) {
					previousNode.setNext(nextNode);
					nextNode.setPrev(previousNode);
				} //end if
				else if(previousNode == null) {
					this.head = nextNode;
				}
				else if(nextNode == null ) {
					previousNode.next = null;
				}
			}
			else {
				System.out.println("Node not found.");
			}
		}
	}

	Iterator<T> iterator() {
		return new LLIterator();
	}
	
	/**
	 * 
	 * @author AnhVuNguyen
	 *
	 */
	private class LLIterator implements Iterator<T> {

		private ListNode<T> curr;

		private LLIterator() {
			curr = (ListNode<T>) head;
		}

		@Override
		public boolean hasNext() {
			return curr != null;
		}

		@Override
		public T next() {
			T res = curr.getData();
			curr = curr.getNext();
			return res;
		} 

		public ListNode<T> nextNode() {
			return curr.getNext();
		}

		@Override
		public void remove() {
			//
		}		
	}

	/**
	 * 
	 * @author AnhVuNguyen
	 *
	 */
	private class LLIteratorTraverse implements Iterator<T> {

		ListNode<T> curr;

		public boolean hasPrevious() {
			return (curr.getPrev() != null);
		}

		public ListNode<T> previous() {
			return curr.prev;
		}

		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public T next() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void remove() {
			// TODO Auto-generated method stub

		}
	}
}
