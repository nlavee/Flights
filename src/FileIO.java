import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class FileIO {
	
	flightGraph fG;
	flightGraph justCities;
	
	public FileIO(String cities, String flights) throws FileNotFoundException {
		Scanner cityInput = new Scanner(new File(cities));
		Scanner flightsInput = new Scanner(new File(flights));

		fG = new flightGraph(new cityComparator<City>());
		justCities = new flightGraph(new cityComparator<City>());
		//TestProcessing test = new TestProcessing();

		//below doesn't work with BST, check insert method
		//create vertices with latitude and longitude from cities.txt
		while(cityInput.hasNext()) {
			String name = cityInput.next();
			//System.out.println(name);
			Double latitudeFile = cityInput.nextDouble();
			//System.out.println(latitude);
			Double longitudeFile = cityInput.nextDouble();
			//System.out.println(longitude);

			City cityNew = new City(name, latitudeFile, longitudeFile);

			//test.addCity(cityNew);

			fG.addVertex(cityNew);
			justCities.addVertex(cityNew);
			//System.out.println(fG);

		}
		//test.setup();
		//test.draw();
		//System.out.println(fG);
		
		//THE PROGRAM WILL NOT WORK IF THE ORIGIN IS NOT FOUND IN THE FLIGHT GRAPH
		//NEED TO EITHER MAKE IT HACKPROOF OR MAKE IT ABLE TO CREATE NEW VERTEX

		//If it doesn't the program will go into overFlow
		while(flightsInput.hasNextLine()) {
			String x = flightsInput.nextLine();
			//System.out.println(x);
			Scanner line = new Scanner(x);
			String nameOri = line.next();
			Vertex<City> ori = new Vertex<City>(new City(nameOri,0.0,0.0));

			//search to see whether this vertex is already in the BST, if it's then
			//we can assign this edge to the existing vertex
			Vertex<City> searchRes = (Vertex<City>) fG.verticesList.searchLoop(ori);

			//System.out.println(searchRes.adjacencyList);
			//System.out.println("city found: " + searchRes);

			while(line.hasNext()) {
				String nameDest = line.next();
				//System.out.println(nameDest);
				double priceDest = line.nextDouble();
				//System.out.println(priceDest);
				Vertex<City> dest = new Vertex<City>(new City(nameDest,0.0,0.0));
				Vertex<City> destRes = (Vertex<City>) fG.verticesList.searchLoop(dest);
				City ques = new City(destRes.cityInQuestion.getName(),destRes.cityInQuestion.getLatitude(),destRes.cityInQuestion.getLongitude());
				destRes = new Vertex(ques);
				
				Edge temp = new Edge(ori, dest, priceDest);
				//System.out.println("the edge just created " + temp);

				searchRes.addEdges(temp);
				//System.out.println(searchRes);
			}
			//System.out.println(ori);
		}
		//System.out.println(fG);
		//Vertex<City> searchRes = (Vertex<City>) fG.verticesList.searchLoop(new City("SFO",0.0,0.0));
		//System.out.println(searchRes);
	}
	
	public flightGraph<Vertex> getGraph() {
		return fG;
	}
	
	public flightGraph<Vertex> getCitiesGraph() {
		return justCities;
	}
}
