/**
 * class ListNode that used data from class Edges
 * @author AnhVuNguyen
 *
 * @param <T>
 */

public class ListNode<T>{

	T data;
	ListNode<T> next;
	ListNode<T> prev;
	
	/**
	 * 
	 * @param data
	 * @param next
	 * @param prev
	 */
	
	public ListNode(T data)
	{
		this.data = data;
		this.next = null;
		this.prev = null;
	}
	
	public ListNode(T data, ListNode<T> next, ListNode<T> prev) {
		this.data = data;
		this.next = next;
		this.prev = prev;
		
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public ListNode<T> getNext() {
		return next;
	}

	public void setNext(ListNode<T> next) {
		this.next = next;
	}

	public ListNode<T> getPrev() {
		return prev;
	}

	public void setPrev(ListNode<T> prev) {
		this.prev = prev;
	}

	@Override
	public String toString() {
		return data.toString();
	}
	
	
	
}