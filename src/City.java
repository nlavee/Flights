import java.util.Comparator;

import processing.core.PApplet;

/**
 * class to create City object that can be fit into the Vertex object in a graph object
 * @author AnhVuNguyen
 *
 */
public class City{

	String name;
	double latitude;
	double longitude;
	PApplet parent;
	
	public City(String name, Double latitude, Double longitude) {
		this.name = name;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	//this constructor is for Processing use
	public City(String name, Double latitude, Double longitude, PApplet p) {
		this.name = name;
		this.latitude = latitude;
		this.longitude = longitude;
		parent = p;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(int latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(int longitude) {
		this.longitude = longitude;
	}

	@Override
	public String toString() {
		if(latitude != 0.0 && longitude != 0.0) {
			return name + " "+ latitude + " " + longitude;
		}
		else {
			return name;
		}
	}
	
	/**
	 * The method to get data, which is name in this scenario
	 * @return String
	 */
	public String getData() {
		return name;
	}
	
	/**
	 * Method to draw in Processing. In order to use this, the City has be constructed with this as a paramter
	 * A city will be drawn as a red point
	 */
	void display() {
		parent.fill(255,0,0);
		//parent.ellipse((float) (latitude*4.7*3), (float) (longitude*1.8*1.5), 3,3);
		parent.ellipse((float) (latitude*latitude*latitude/100), (float) (longitude*longitude*longitude/2900), 5,5);
	}
}
