package simulations.sugarscape;

/**
 * simple class to hold coordinates for HashMap key
 * @author Ryan Anders
 *
 */
public class Coordinates {
	private int myX;
	private int myY;
	
	public Coordinates(int x, int y) {
		myX = x;
		myY = y;
	}
	
	public int getX() {
		return myX;
	}
	
	public int getY() {
		return myY;
	}
}
