import java.awt.Component;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import processing.core.PApplet;
import processing.core.PImage;
import stack.Stack;

public class ProcessingFlight extends PApplet{

	////fields////
	PImage img;
	City ori, dest;
	drawEdge trial = new drawEdge(this);
	drawName test = new drawName(this);
	FileIO hw2;
	ArrayList<Button> buttonList = new ArrayList<Button>();
	ArrayList<Vertex<City>> list = new ArrayList<Vertex<City>>();

	boolean buttonClicked = false;
	flightGraph p ;
	Button Price = new Button(0,645,500,50);
	Button exit = new Button(0,0,40,30);

	boolean originForPath = false;
	boolean destinationForPath = false;
	boolean chosenCity = false;
	boolean newFunction = false;
	boolean firstTime = true;
	Stack<Vertex<City>> ans = new Stack<Vertex<City>>();

	Vertex<City> origin;
	Vertex<City> destination;
	////methods////

	public void setup() {
		size(1200,800);
		img = loadImage("flight.jpg");
		exit.setFunction("Quit");
		float x = 120;
		float y = 10;
		while(buttonList.size() < 5) {
			Button button = new Button(x,y, 150,50);
			buttonList.add(button);
			x += 200;
		}
		buttonList.get(0).setFunction("Direct Flight");
		buttonList.get(1).setFunction("Transit 1 Stop Flight");
		buttonList.get(2).setFunction("Any Route (DFS)");
		buttonList.get(3).setFunction("Shortest Route (BFS)");
		buttonList.get(4).setFunction("Cheapest Route (Dijkstra)");

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

		//buttons for different operations
		for(Button button : buttonList) button.display(this);

		//reproduce flight graph from input file
		p = hw2.getGraph();

		if(p != null) {

			Iterator<Vertex<City>> treeIt = p.verticesList.iterator();
			while(treeIt.hasNext()) {
				Vertex<City> vertex = treeIt.next();
				City fromScan1 = vertex.getCityInQuestion();
				fromScan1.display(this);

				test.drawNameCity(fromScan1.getName(), fromScan1.getLatitude(), fromScan1.getLongitude());

				//draw Edges from down here!
				LinkedList edgesList = vertex.getAdjacencyList();

				if(edgesList != null)  {
					Iterator<Edge<City>> iterator = edgesList.iterator();

					Edge<City> test = new Edge<City>(null, null, 0);

					while(iterator.hasNext()) {
						test = iterator.next();
						Vertex<City> originalCity = test.getOrigin();
						Vertex<City> destinationCity = test.getDestination();

						//Find it from the BST in graphFlight
						originalCity = (Vertex<City>) p.verticesList.searchLoop(originalCity);
						destinationCity = (Vertex<City>) p.verticesList.searchLoop(destinationCity);

						//draw the edge
						trial.display(originalCity.getCityInQuestion(), destinationCity.getCityInQuestion());
					}
				}
			}
			Price.display(this);
			exit.display(this);

			fill(255);
			this.text("anguyen1@skidmore.edu", 1070, 7);

			if(!ans.isEmpty()) {;
			while(!ans.isEmpty()) {
				list.add(ans.Pop());
			}
			}

			if(!newFunction) {
				for(int i = 0 ; i < list.size()-1; i++) {
					trial.displayFlight(list.get(i).getCityInQuestion(), list.get(i+1).getCityInQuestion());
				}
			} else {
				list = new ArrayList<Vertex<City>>();
			}
			
			//instruction for users
			if(firstTime) {
				fill(255);
				rect(150,170,900,200);
				fill(0);
				text("Welcome to Flight Program by NLAVu. Here's how the program runs:\n\nYou can click "
						+ "any two cities and the dialog at bottom left will show the first one as origin and the second one as "
						+ "the destination.\n"
						+ "You then can click on any button on top to find different routes between the points. \nYou can try those steps"
						+ " even when this message is popping up. \nOnce you are"
						+ " done with the application, you can simply click the Quit button on top left corner and you "
						+ "will exit the program. You can press any key to exit this message and start your"
						+ " experience! Thank you!\n\nanguyen1@skidmore.edu", 200,200, 700,400);
				
				if(keyPressed) firstTime = false;
			}
		}
		else 
		{
			System.out.println("Cannot find flights graph, please check the input files!");
		}
	}


	public void mousePressed() {
		if(exit.checkCoordinate(mouseX, mouseY)) {
			this.exit();
		}

		else if(!chosenCity) {
			Iterator<Vertex<City>> iterator = p.verticesList.iterator();
			while(iterator.hasNext()) {
				Vertex<City> question = iterator.next();
				if(question.getCityInQuestion().checkCity(mouseX, mouseY)) {
					if(!originForPath) {
						origin = question;
						Price.setFunction("Original City: " + origin.getCityInQuestion().toString());
						originForPath = true;
						newFunction = true;
					} else if(!destinationForPath) {
						destination = question;
						Price.setFunction("Destination: " + destination.getCityInQuestion().toString());
						destinationForPath= true;
						chosenCity = true;
					} else {
						System.out.println(question.getCityInQuestion().getName());
					}
				}
			}
		} else {
			for(Button theButton : this.buttonList) {
				if(theButton.checkCoordinate(mouseX, mouseY)) {
					String toDo = theButton.runFunction();
					if(toDo=="DFS") {	
						String text = "";
						ans = p.SearchRoute(origin, destination, toDo);
						if(ans.isEmpty()) text = "Cannot get route between these two cities";
						Price.setFunction(text);
						origin = null;
						destination = null;
						originForPath = false;
						destinationForPath = false;
						chosenCity = false;
						newFunction = false;
					} else if (toDo =="BFS") {
						String text = "";
						ans = p.SearchRoute(origin, destination, toDo);
						if(ans.isEmpty()) text = "Cannot get route between these two cities";
						Price.setFunction(text);
						origin = null;
						destination = null;
						originForPath = false;
						destinationForPath = false;
						chosenCity = false;
						newFunction = false;
					} else if (toDo =="Dijkstra") {
						String text = "";
						Price price = new Price(0.0);
						ans = p.Dijkstra(origin, destination, price);
						if(price.getPrice() != Double.MAX_VALUE)	Price.setFunction("Price for cheapest route: " + price.getPrice());
						else Price.setFunction("Cannot get route between these two cities");
						origin = null;
						destination = null;
						originForPath = false;
						destinationForPath = false;
						chosenCity = false;
						newFunction = false;
					} else if (toDo =="short flight") {
						String text = "";
						text += p.directConnect(origin, destination) +"";
						if(text.equals("0.0")) text = "Cannot find direct flight between the cities";
						Price.setFunction(text);
						origin = null;
						destination = null;
						originForPath = false;
						destinationForPath = false;
						chosenCity = false;
						newFunction = false;
					} else if (toDo =="transit flight") {
						String text = "";
						text += p.shortFlight(origin, destination) +"";
						Price.setFunction(text);
						origin = null;
						destination = null;
						originForPath = false;
						destinationForPath = false;
						chosenCity = false;
						newFunction = false;
					}
				}
			}
		}
	}
}
