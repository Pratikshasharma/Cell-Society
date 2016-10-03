package cellpackage;
 /**
  * Agent for Sugarscape simulations
  * @author Ryan Anders
  *
  */
public class Agent {

	private int mySugar;
	private int myMetabolism;
	private int myVision;
	
	public Agent(int sugar, int metabolism, int vision) {
		mySugar = sugar;
		myMetabolism = metabolism;
		myVision = vision;
	}

	public int getMySugar() {
		return mySugar;
	}

	public void setMySugar(int sugar) {
		mySugar = sugar;
	}

	public int getMyMetabolism() {
		return myMetabolism;
	}

	public void setMyMetabolism(int metabolism) {
		myMetabolism = metabolism;
	}

	public int getMyVision() {
		return myVision;
	}

	public void setMyVision(int vision) {
		myVision = vision;
	}
	
	
}
