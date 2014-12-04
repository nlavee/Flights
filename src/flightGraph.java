import heapAndPriorityQueues.Heap;
import heapAndPriorityQueues.HeapData;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

import Debugging.Trace;
import processing.core.PApplet;
import queue.Queue;
import stack.Stack;


/**
 * class to create graph for Flights
 * @author AnhVuNguyen
 *
 * @param <T>
 */
public class flightGraph<T>{

	redBlackTree<Vertex<T>> verticesList;

	vertexComparator<T> comp; 

	PApplet parent;

	public flightGraph(Comparator<T> dataComp) {
		comp = new vertexComparator<T>(dataComp);
		verticesList = new redBlackTree<Vertex<T>>(comp);
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

	public String shortFlight(Vertex<T> from, Vertex<T> to) {
		String res = "";
		double price = directConnect(from,to);
		if(price == 0.0) {
			res = "No direct flight between cities";
			double transitPrice = transitConnect(from, to);
			if(transitPrice == 0.0) res += ". There's also no flight with 1 stop between cities";
			else res += ". Price for flight with 1 transit: $" + transitPrice;
		}
		else res += "Direct flight between " + from.getCityInQuestion().toString() + " and " + to.getCityInQuestion().toString() + ": $" + price;

		return res;
	}
	/**
	 * Get the price of the direct Flight between two cities
	 * If there's no direct flight, the method will return 0.0
	 * @param String origin name
	 * @param String destination name
	 * @return double price of the direct flight
	 */
	public double directConnect(Vertex<T> from, Vertex<T> to) {
		double res = 0.0;
		//System.out.println(searchRes);
		Iterator<Edge<T>> it = from.getAdjacencyList().iterator();
		//System.out.println(searchRes.getAdjacencyList());
		Vertex<T> destination = null;
		while(it.hasNext()) {
			Edge<T> ele = it.next();
			//System.out.println(ele);
			if(verticesList.searchLoop(ele.getDestination()).equals(to)) {
				res = ele.getPrice();
				destination = verticesList.searchLoop(ele.getDestination());
			}
		}
		return res;
	}

	/**
	 * Get the price of the flight with at most 1 transit between two cities
	 * If there's no flights, the method will return 0.0
	 * @param String origin name
	 * @param String destination name
	 * @return double price of the flights plus transit
	 */
	private double transitConnect(Vertex<T> from, Vertex<T> to) {
		double res = 0.0;

		Iterator<Edge<T>> it = from.getAdjacencyList().iterator();

		//Vertex<T> destination = null;

		//Stack<Vertex<City>> stack = new Stack<Vertex<City>>();
		//push all vertex with city can go to from origin in to a stack
		while(it.hasNext()) {
			Edge<T> ele = it.next();
			Vertex<T> transit = verticesList.searchLoop(ele.getDestination());
			//System.out.println(transit);
			Vertex<T> search = verticesList.searchLoop(transit);
			//System.out.println(search);
			Iterator<Edge<T>> it2 = search.getAdjacencyList().iterator();
			while(it2.hasNext()) {
				Edge<T> ele2 = it2.next();
				if(verticesList.searchLoop(ele2.getDestination()).equals(to)) {
					res = ele2.getPrice() + ele.getPrice();
				}
			}
		}
		return res;
	}

	/**
	 * method to search for route, depend on string to know what type of search to do
	 * @param T origin 
	 * @param T destination 
	 * @param String the type of search to do, can be "DFS", "BFS" or "Dijkstra"
	 * @return String result route
	 */
	public String SearchRoute(Vertex<T> from, Vertex<T> to, String c) {
		String res ="";

		if(c == "DFS") {
			Stack<Vertex<T>> ans = DFSAct(from,to);
			while(!ans.isEmpty()) {
				String city = ans.Pop().getCityInQuestion().toString();
				if(!city.equals(to.getCityInQuestion().toString())) res += city + " ==> ";
				else res += city;
			}
			if(res.equals("")) res += "Cannot find a route between these destinations.";
		} else if(c=="BFS") {
			Stack<Vertex<T>> ans = BFSAct(from,to);
			while(!ans.isEmpty()) {
				String city = ans.Pop().getCityInQuestion().toString();
				if(!city.equals(to.getCityInQuestion().toString())) res += city + " ==> ";
				else res += city;
			}
			if(res.equals("")) res += "Cannot find a route between these destinations.";
		} else if(c == "Dijkstra") {
			Price price = new Price(0.0);
			Stack<Vertex<T>> ans = Dijkstra(from,to, price);
			int i = 0;
			while(!ans.isEmpty()) {
				i++;
				Vertex<T> route = ans.Pop();
				String city = route.getCityInQuestion().toString();
				if(!city.equals(to.getCityInQuestion().toString())) res += city + " ==> ";
				else res += city;
			}
			res += ". \nPrice = " + price;
			if(i==1) res = "Cannot find a route between these destinations.";
		} else res = "Wrong parameter. Please input only 'DFS', 'BFS', 'Dijkstra' for the last parameter.";
		return res;
	}

	/**
	 * search for any possible way from one place to another
	 * use iterator
	 * @param Vertex start
	 * @param Vertex end
	 * @return stack of destination
	 */
	private Stack<Vertex<T>> DFSAct(Vertex<T> a, Vertex<T> b) {
		boolean find = false;
		Stack<Vertex<T>> stack = new Stack<Vertex<T>>();
		HashSet<Vertex<T>> visited = new HashSet<Vertex<T>>();
		HashMap<Vertex<T>, Vertex<T>> route = new HashMap<Vertex<T>, Vertex<T>>();

		stack.Push(a);
		visited.add(a);

		while(!stack.isEmpty() && find == false) {
			Trace.println("it runs the while loop");
			Vertex<T> u = stack.Pop();

			if(u.getCityInQuestion().equals(b.getCityInQuestion())) {
				find = true;
				route.put(a,b);
			} else {
				LinkedList<Edge<T>> temp = u.getAdjacencyList();
				Trace.println(temp);
				Iterator<Edge<T>> iterator = temp.iterator();
				while(iterator.hasNext()) {
					Edge<T> ele = iterator.next();
					if(visited.add(verticesList.searchLoop(ele.getDestination()))) {
						Trace.println(visited);
						stack.Push(verticesList.searchLoop(ele.getDestination()));
						route.put(verticesList.searchLoop(ele.getDestination()), u);
						Trace.println(stack);
					}
				}
			}
		}
		Stack<Vertex<T>> ans = new Stack<Vertex<T>>();
		if(find) {
			Vertex<T> need = b;
			ans.Push(need);
			while(!need.equals(a) && !route.isEmpty()) {
				need = route.get(need);
				ans.Push(need);
			}
		}
		return ans;
	}

	/**
	 * search for the shortest way from one destination to another
	 * use iterator
	 * @param Vertex start
	 * @param Vertex end
	 * @return stack of the route
	 */
	private Stack<Vertex<T>> BFSAct(Vertex<T> a, Vertex<T> b) {
		boolean find = false;
		Queue<Vertex<T>> queue = new Queue<Vertex<T>>();
		HashSet<Vertex<T>> visited = new HashSet<Vertex<T>>();
		HashMap<Vertex<T>, Vertex<T>> route = new HashMap<Vertex<T>, Vertex<T>>();

		queue.Enqueue(a);
		visited.add(a);

		while(!queue.isEmpty() && find == false) {
			Trace.println("it runs the while loop");
			Vertex<T> u = queue.Dequeue();

			if(u.equals(b)) {
				find = true;
				route.put(a,b);
			} else {
				LinkedList<Edge<T>> temp = u.getAdjacencyList();
				Trace.println(temp);
				Iterator<Edge<T>> iterator = temp.iterator();
				while(iterator.hasNext()) {
					Edge<T> ele = iterator.next();
					if(visited.add(verticesList.searchLoop(ele.getDestination()))) {
						queue.Enqueue(verticesList.searchLoop(ele.getDestination()));
						route.put(verticesList.searchLoop(ele.getDestination()), u);
						Trace.print(queue);
					}
				}
			}
		}
		Stack<Vertex<T>> ans = new Stack<Vertex<T>>();
		if(find) {
			Vertex<T> need = b;
			ans.Push(need);
			while(!need.equals(a) && !route.isEmpty() ) {
				need = route.get(need);
				ans.Push(need);
			}
		}
		return ans;
	}

	/**
	 * Choose the cheapest way based on priority queue
	 * use iterator
	 * @param Vertex start
	 * @param Vertex end
	 * @return stack of route
	 */
	private Stack<Vertex<T>> Dijkstra(Vertex<T> a, Vertex<T> b, Price c) {
		boolean find = false;
		Heap<Vertex<T>> PQ = new Heap<Vertex<T>>();
		HashMap<Vertex<T>, Vertex<T>> route = new HashMap<Vertex<T>, Vertex<T>>();

		PQ.insert(a, 0); //initialize the PQ with the starting point, key =  0
		//initialize the rest of the PQ with other vertices with key = max value

		Iterator<Vertex<T>> iteratorTree = verticesList.iterator();
		while(iteratorTree.hasNext()) {
			Vertex<T> ele = iteratorTree.next();
			if(!ele.equals(a)) PQ.insert(ele, Integer.MAX_VALUE);
		}
		Trace.println(PQ);

		ArrayList<HeapData<Vertex<T>>> trial = new ArrayList<HeapData<Vertex<T>>>();

		while(!PQ.isEmpty() && find == false) {
			HeapData<Vertex<T>> out = PQ.Min();
			Trace.println(out);
			
			//get the price
			if(out.getData().equals(b))	c.setPrice(out.getKey());
			
			Vertex<T> u = out.getData();

			Trace.println(u);
			

			if(u.equals(b)) {
				find = true;
			} else {
				LinkedList<Edge<T>> temp = u.getAdjacencyList();
				Trace.println(temp);
				
				if(temp!= null) {
					Iterator<Edge<T>> iterator = temp.iterator();
					while(iterator.hasNext()) {
						Edge<T> ele = iterator.next();
						double weight = ele.getPrice();
						if(weight + PQ.getKey(u) < PQ.getKey(verticesList.searchLoop(ele.getDestination()))) {
							PQ.decreaseKey(verticesList.searchLoop(ele.getDestination()), weight + PQ.getKey(u));
							Trace.println(PQ.getKey(verticesList.searchLoop(ele.getDestination())));
							
							route.put(verticesList.searchLoop(ele.getDestination()),u);

						}
					} 
				}
			}
			//this ArrayList has all the lowest price
			trial.add(PQ.extractMin());
		}

		Stack<Vertex<T>> ans = new Stack<Vertex<T>>();
		if(find) {
			Vertex<T> need = b;
			ans.Push(need);
			while(!need.equals(a) && !route.isEmpty() ) {
				if(route.get(need) == null) {
					need = a;
				}
				else {
					need = route.get(need);
					ans.Push(need);
				}
			}
		}
		return ans;
	}


	@Override
	public String toString() {
		return verticesList.toString();
	}


}
