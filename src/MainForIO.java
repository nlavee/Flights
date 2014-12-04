import java.io.FileNotFoundException;
import java.util.Scanner;


public class MainForIO {

	public static void main(String[] args) {
		FileIO hw2 = null;
		
		try {
			hw2 = new FileIO("cities.txt", "flights.txt");
		} catch (FileNotFoundException e) {
			System.out.println("Check file!");
			e.printStackTrace();
		}
		
		flightGraph p = hw2.getGraph();
		flightGraph o = hw2.getCitiesGraph(); //only name, latitude and longitude
		
		String nameOri = "";
		String nameDest = "";
		Scanner name = new Scanner(System.in);
		nameOri = name.next();
		nameDest = name.next();
		name.close();
		
		//can get price correctly!
		Vertex<City> from = new Vertex<City>(new City(nameOri,0.0,0.0));
		from = (Vertex<City>) p.verticesList.searchLoop(from);
		
		Vertex<City> to = new Vertex<City>(new City(nameDest,0.0,0.0));
		to = (Vertex<City>) p.verticesList.searchLoop(to);
		//System.out.println(from);
		//System.out.println(to);
		
		System.out.println(p.SearchRoute(from, to,"Dijkstra"));
		
		System.out.println(p.SearchRoute(from, to,"BFS"));
		
		System.out.println(p.SearchRoute(from, to,"DFS"));

		System.out.println(p.shortFlight(from, to));		
	}

}
