package cellpackage;
import javafx.scene.paint.Paint;

/**
 * Object describing a cells state. Contains the state's name, ID, Color, and
 * parameters for certain simulations that require a state to keep some sort
 * counter.
 * 
 * @author Ryan Anders
 * @author Blake Becerra
 *
 */
public class State {
	private String myStateName;
	private int myStateID;
	private Paint myStateColor;
	private int myBreedCount;
	private int myStarveCount;
	private int mySugarAmt;
	private int mySugarCapacity;
	private int myTick;
	private Agent myAgent;

	/**
	 * Create a state with only a name, color, and ID
	 * @param stateName
	 * @param myColor
	 * @param stateID
	 */
	public State(String stateName, Paint myColor, int stateID){
		myStateName = stateName;
		myStateColor = myColor;
		myStateID = stateID;
	}
	
	/**
	 * Add a breed/starve counter onto a state (Predator-Prey)
	 * @param s
	 * @param breedCount
	 * @param starveCount
	 */
	public State(State s, int breedCount, int starveCount) {
		myStateName = s.getStateName();
		myStateColor = s.getStateColor();
		myStateID = s.getStateID();
		myBreedCount = breedCount;
		myStarveCount = starveCount;
	}
	
	/**
	 * add an Agent and life counter onto a state (Sugarscape)
	 * @param a
	 * @param s
	 * @param tick
	 */
	public State(Agent a, State s, int tick) {
		myStateName = s.getStateName();
		myStateColor = s.getStateColor();
		myStateID = 1;
		mySugarAmt = s.getSugarAmt();
		mySugarCapacity = s.getSugarCapacity();
		myAgent = a;
		myTick = tick;
	}
	

	public String getStateName() {
		return myStateName;
	}

	public void setStateName(String stateName) {
		myStateName = stateName;
	}

	public Paint getStateColor() {
		return myStateColor;
	}

	public void setStateColor(Paint stateColor) {
		myStateColor = stateColor;
	}
	
	public int getStateID(){
		return myStateID;
	}
	
	public void setStateID(int stateID){
		myStateID = stateID;
	}

	public int getBreedCount() {
		return myBreedCount;
	}

	public int getStarveCount() {
		return myStarveCount;
	}
	
	public void setBreedCount(int breedCount){
		myBreedCount = breedCount;
	}

	public int getSugarAmt() {
		return mySugarAmt;
	}
	
	/**
	 * Add sugar to Agent's sugar amount, up to sugar capacity
	 * @param x - sugar to add
	 */
	public void setSugarAmt(int x) {
		if (x <= mySugarCapacity) {
			mySugarAmt = x;
		} else {
			mySugarAmt = mySugarCapacity;
		}
	}

	public int getSugarCapacity() {
		return mySugarCapacity;
	}
	
	public Agent getAgent() {
		return myAgent;
	}

	public int getTick() {
		return myTick;
	}
	
	public void setTick(int x) {
		myTick = x;
	}
}