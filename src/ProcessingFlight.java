import java.awt.Component;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Scanner;
import processing.core.PApplet;
import processing.core.PImage;

public class ProcessingFlight extends PApplet{

	PImage img;
	City ori, dest;
	drawEdge trial = new drawEdge(this);
	drawName test = new drawName(this);
	FileIO hw2;

	public void setup() {
		size(1280,800);
		img = loadImage("flight.jpg");
		//load file to get the graph
		try {
			hw2 = new FileIO("cities.txt", "flights.txt");
		} catch (FileNotFoundException e) {
			System.out.println("Check file!");
			e.printStackTrace();
		}
	}

	public void draw() {
		//background image
		image(img,0,0);

		//reproduce flight graph from input file
		flightGraph p=hw2.getGraph();
		flightGraph o = hw2.getCitiesGraph(); //only name, latitude and longitude

		if(p != null && o != null) {
			//Print the whole graph into a string with only city name, latitude and longitude
			String name = o.toString();

			//the method below draws all city from toString of graph o
			Scanner cityScan = new Scanner(name);

			//scan the print out line. Order in the line: name, latitude, longitude
			while(cityScan.hasNext()) {
				String n = cityScan.next();
				double lati = cityScan.nextDouble();
				double longi = cityScan.nextDouble();
				//City fromScan = new City(n, lati,longi, this); //create city to display

				//Search for Vertex with the city with name,latitude and longitude above
				Vertex<City> fromScan = (Vertex<City>) p.verticesList.searchLoop(new Vertex<City>(new City(n,lati,longi))); //create city to display
				//create city based on the vertex's information
				City inQuestion = new City(fromScan.cityInQuestion.getName(),fromScan.cityInQuestion.getLatitude(),fromScan.cityInQuestion.getLongitude(),this);

				test.drawNameCity(n, lati, longi); //draw the name for each city
				inQuestion.display(); //draw the location for the city

				/* ********************************************************************************************************************************************************************
				 * **************** UNCOMMENTED THIS PART WHEN ITERATOR FAILS *********************************************************************************************************
				 * ********************************************************************************************************************************************************************
				//Basically, this is a for loop that goes through the linked list and get data from each list node
				//this is the head of the list that keep tracks of edges
				for(ListNode<Edge<City>> y = fromScan.adjacencyList.head;y != null;y = y.getNext()) {
					//get the edge from the list node
					Edge<City> w = y.getData();

					//get the two Vertex from the Edge
					Vertex<City> originalCity = w.getOrigin();
					Vertex<City> destinationCity = w.getDestination();

				 ********************************************************************************************************************************************************************
				 * **************** UNCOMMENTED THIS PART WHEN ITERATOR FAILS, FOR LOOP WORKS BUT DOESN NOT HELP WITH ENCAPSULATION *************************************************
				 * ******************************************************************************************************************************************************************
				 */
				
				
				/* ******************************************************************************************************************************************************************
				 * **************** ITERATOR BELOW WORKS, ITERATOR IS BETTER BECAUSE IT HELPS WITH ENCAPSULATION  *******************************************************************
				 * ******************************************************************************************************************************************************************
				*/
				LinkedList edgesList = fromScan.adjacencyList;

				if(edgesList != null)  {
					Iterator<Edge<City>> iterator = edgesList.iterator();

					Edge<City> test = new Edge<City>(null, null, 0);

					if(iterator.hasNext()) {
						test = iterator.next();
						Vertex<City> originalCity = test.getOrigin();
						Vertex<City> destinationCity = test.getDestination();


						//Find it from the BST in graphFlight
						originalCity = (Vertex<City>) p.verticesList.searchLoop(originalCity);
						destinationCity = (Vertex<City>) p.verticesList.searchLoop(destinationCity);

						//create city based on the information from the search, with added parameter for Processing
						ori = new City(originalCity.cityInQuestion.getName(), originalCity.cityInQuestion.getLatitude(),originalCity.cityInQuestion.getLongitude(),this);
						dest = new City(destinationCity.cityInQuestion.getName(), destinationCity.cityInQuestion.getLatitude(),destinationCity.cityInQuestion.getLongitude(),this);

						//draw the edge
						trial.display(ori, dest);
						test = iterator.next();
					}
				}
			}
		} else {
			System.out.println("Cannot find flights graph, please check the input files!");
		}
	}
}
