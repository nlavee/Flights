import javax.xml.crypto.Data;

import processing.core.PApplet;


public class Edge<T> {
	Vertex<T> origin;
	Vertex<T> destination;
	double price;
	PApplet parent;
	
	
	public Edge(Vertex<T> origin, Vertex<T> destination, double price) {
		this.origin = origin;
		this.destination = destination;
		this.price = price;
	}
	
	//this constructor is for Processing use
	public Edge(Vertex<T> origin, Vertex<T> destination, double price, PApplet p) {
		this.origin = origin;
		this.destination = destination;
		this.price = price;
		this.parent = p;
	}

	@Override
	public String toString() {
		return "|| Origin=" + origin + ", destination=" + destination
				+ ", price=" + price + " ";
	}

	public Vertex<T> getOrigin() {
		return origin;
	}

	public Vertex<T> getDestination() {
		return destination;
	}

	public double getPrice() {
		return price;
	}

	public PApplet getParent() {
		return parent;
	}

}
