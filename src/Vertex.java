import java.util.Comparator;

/**
 * class to create Vertex for the Graph that keep track of flights
 * @author AnhVuNguyen
 *
 * @param <T>
 */
public class Vertex<T>{
	T cityInQuestion;
	LinkedList<Edge<T>> adjacencyList;
	
	
	public Vertex(T data) {
		cityInQuestion = data;
		adjacencyList= new LinkedList(null);
	}

	
	LinkedList<Edge<T>> getAdjacencyList() {
		return adjacencyList;
	}


	/**
	 * method to add Edges into a Vertex
	 * @param theEdge
	 */
	public void addEdges(Edge<T> theEdge) {
		//System.out.println("Edge in addEdges method" +theEdge);
		adjacencyList.addToFront(theEdge);
	}
	
	/**
	 * method to remove an Edge from a Vertex
	 * @param theEdge
	 */
	public void removeEdges(Edge<T> theEdge) {
		adjacencyList.deleteNode(theEdge);
	}

	@Override
	public String toString() {
		if(adjacencyList.head == null){
			return cityInQuestion.toString();
		}
		else {
			return cityInQuestion.toString() + "["
					+ adjacencyList + "]";
		}
	}

	public T getCityInQuestion() {
		return cityInQuestion;
	}
	
	

}
