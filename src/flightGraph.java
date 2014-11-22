import java.util.Comparator;

/**
 * class to create graph for Flights
 * @author AnhVuNguyen
 *
 * @param <T>
 */
public class flightGraph<T>{
	
	BinarySearchTree<Vertex<T>> verticesList;
	
	vertexComparator<T> comp; 
	
	public flightGraph(Comparator<T> dataComp) {
		comp = new vertexComparator<T>(dataComp);
		verticesList = new BinarySearchTree<Vertex<T>>(comp);
	}
	
	/**
	 * Method to add Vertex based on a piece of data passed in as paramter
	 * @param T data
	 */
	public void addVertex(T data) {
		Vertex<T> v = new Vertex<T>(data);
		verticesList.insert(v);
	}
	
	/**
	 * method to add Edges based on pieces of data that can be used to construct an Edge object
	 * @param from
	 * @param to
	 * @param cost
	 */
	public void addEdges(T from, T to, double cost) {
		//write another method such that it returns node for the one below
		Vertex<T> fromVertex = verticesList.searchLoop(new Vertex<T>(from));
		Vertex<T> toVertex = verticesList.searchLoop(new Vertex<T>(to));
		Edge<T> theEdge = new Edge<T>(fromVertex, toVertex,cost);
		//add Edge into adjacencyList
		fromVertex.addEdges(theEdge);
	}

	@Override
	public String toString() {
		return verticesList.toString();
	}
	
}
