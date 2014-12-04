import processing.core.PApplet;

public class drawEdge {
	PApplet parent;
	
	public drawEdge(PApplet p){
		parent = p;
	}
	
	/**
	 * Draw the Edge between two cities.
	 * The Edge would be a blue arrow with the arrow pointing at the destination
	 * @param City
	 * @param City
	 */
	public void display(City first, City second) {
		//getting information from the two cities
		float oriLat = first.getLatitude().floatValue();
		float oriLong = first.getLongitude().floatValue();
		float destLat = second.getLatitude().floatValue();
		float destLong = second.getLongitude().floatValue();

		parent.stroke(0, 255, 0);
		parent.strokeWeight(1);
		parent.line( (float)(oriLat*oriLat*oriLat/100),(float) (oriLong*oriLong*oriLong/2900),(float)(destLat*destLat*destLat/100),(float)(destLong*destLong*destLong/2900));
		parent.pushMatrix();
		  parent.translate((float)(destLat*destLat*destLat/100), (float)(destLong*destLong*destLong/2900));
		  float a = parent.atan2((oriLat*oriLat*oriLat/100-destLat*destLat*destLat/100), (destLong*destLong*destLong/2900-oriLong*oriLong*oriLong/2900));
		  parent.rotate(a);
		  parent.line(0, 0, -4, -4);
		  parent.line(0, 0, 4, -4);
		parent.popMatrix();
		//easier to see the city in different part of the picture
		parent.stroke(parent.random(255),parent.random(255),parent.random(255));
	}
	
	//can be used to display flight route for search methods
	public void displayFlight(City first, City second) {
		//getting information from the two cities
		float oriLat = first.getLatitude().floatValue();
		float oriLong = first.getLongitude().floatValue();
		float destLat = second.getLatitude().floatValue();
		float destLong = second.getLongitude().floatValue();

		parent.stroke(0, 0, 0);
		parent.strokeWeight(4);
		parent.line( (float)(oriLat*oriLat*oriLat/100),(float) (oriLong*oriLong*oriLong/2900),(float)(destLat*destLat*destLat/100),(float)(destLong*destLong*destLong/2900));
		parent.pushMatrix();
		  parent.translate((float)(destLat*destLat*destLat/100), (float)(destLong*destLong*destLong/2900));
		  float a = parent.atan2((oriLat*oriLat*oriLat/100-destLat*destLat*destLat/100), (destLong*destLong*destLong/2900-oriLong*oriLong*oriLong/2900));
		  parent.rotate(a);
		  parent.line(0, 0, -4, -4);
		  parent.line(0, 0, 4, -4);
		parent.popMatrix();
		//easier to see the city in different part of the picture
		parent.stroke(parent.random(255),parent.random(255),parent.random(255));
	}
}
