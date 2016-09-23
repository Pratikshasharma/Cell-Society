package xml.model;

/**
 * Object for general state, contains only a name, a color, and a percentage (0-1.0) of cells
 * in the grid that will initially be in this state
 * @author Ryan Anders
 */
public class GenState {
	private String myName;
	private String myColor;
	private double myPercentage;
	
	public GenState (String name, String color, String perc) {
		myName = name;
		myColor = color;
		myPercentage = Double.parseDouble(perc);
	}

	/**
	 * @return myName
	 */
	public String getMyName() {
		return myName;
	}

	/**
	 * @return myColor
	 */
	public String getMyColor() {
		return myColor;
	}

	/**
	 * @return myPercentage
	 */
	public double getMyPercentage() {
		return myPercentage;
	}
}
