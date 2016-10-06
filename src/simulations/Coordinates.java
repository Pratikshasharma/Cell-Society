package simulations;

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
	
	/**
	 * Getter for the X value of the coordinates
	 * @return myX
	 */
	public int getX() {
		return myX;
	}
	
	/**
	 * Getter for Y value of coordinate 
	 * @return myY
	 */
	public int getY() {
		return myY;
	}
}
