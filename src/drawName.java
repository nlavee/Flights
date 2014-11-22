import processing.core.PApplet;
import processing.core.PFont;


public class drawName {
	PApplet parent;
	PFont f;
	
	public drawName(PApplet p) {
		parent = p;
		f= parent.createFont("Arial",16,true);
	}
	
	/**
	 * Draw the name for the city based on the latitude or longitude of that city
	 * @param String
	 * @param double
	 * @param double
	 */
	public void drawNameCity(String nameOri, double xOri, double yOri) {
		parent.textFont(f,8);
		//parent.text(nameOri,(float)(xOri*4.7*3-14),(float)(yOri*1.8*1.5+20));
		parent.text(nameOri,(float)(xOri*xOri*xOri/100-10),(float)(yOri*yOri*yOri/2900-5));
	}
}
