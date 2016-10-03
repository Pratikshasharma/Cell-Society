package cellpackage;
import javafx.scene.paint.Paint;

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

	public State(String stateName, Paint myColor, int stateID){
		myStateName = stateName;
		myStateColor = myColor;
		myStateID = stateID;
	}
	
	public State(State s, int breedCount, int starveCount) {
		myStateName = s.getStateName();
		myStateColor = s.getStateColor();
		myStateID = s.getStateID();
		myBreedCount = breedCount;
		myStarveCount = starveCount;
	}
	
	public State(Agent a, State s, int tick) {
		myStateName = s.getStateName();
		myStateColor = s.getStateColor();
		myStateID = 1;
		mySugarAmt = s.getSugarAmt();
		mySugarCapacity = s.getSugarCapacity();
		myAgent = a;
		myTick = tick;
	}
	
	public State(State s, int ID) {
		myStateName = s.getStateName();
		myStateColor = s.getStateColor();
		myStateID = ID;
		mySugarAmt = s.getSugarAmt();
		mySugarCapacity = s.getSugarCapacity();
		myAgent = null;
		myTick = s.getTick(); 
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
		myStarveCount = breedCount;
	}

	public int getSugarAmt() {
		return mySugarAmt;
	}
	
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