import processing.core.PApplet;
import processing.core.PFont;


public class Button {
	float x;
	float y;
	float height;
	float width;
	String function;
	PFont f;

	public Button(float x, float y, float height, float width) {
		this.x = x;
		this.y = y;
		this.height = height;
		this.width = width;
		this.function = "";
	}

	public void display(PApplet main) {
		main.fill(255,255,255);
		main.stroke(0);
		main.rect(x, y, height, width);
		main.fill(0, 102, 153);
		f= main.createFont("Arial",11,true);
		main.textFont(f);
		main.text(function, x+10, y+25);
	}

	void setFunction(String function) {
		this.function = function;
	}

	boolean checkCoordinate(float mouseX, float mouseY) {
		boolean result = false;
		if (mouseX >= this.x
				&& mouseX <= this.x + this.height
				&& mouseY >= this.y
				&& mouseY <= this.y + this.width) {
			result = true;
		} // end if
		return result;
	} // end checkCoordinate()
	
	String runFunction() {
		String res = "";
		if(function == "Direct Flight") {
			res = "short flight";
		} else if(function == "Transit 1 Stop Flight") {
			res = "transit flight";
		} else if(function == "Any Route (DFS)") {
			res = "DFS";
		} else if(function == "Shortest Route (BFS)") {
			res = "BFS";
		} else {
			res = "Dijkstra";
		}
		return res;
	}
}
