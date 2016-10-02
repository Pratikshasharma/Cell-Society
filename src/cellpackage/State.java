package cellpackage;
import javafx.scene.paint.Paint;

public class State {
	private String myStateName;
	private int myStateID;
	private Paint myStateColor;
	private int myBreedCount;
	private int myStarveCount;

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
	
//	//fish state
//	public State(String stateName, Paint myColor, int stateID, int breedCount){
//		myStateName = stateName;
//		myStateColor = myColor;
//		myStateID = stateID;
//		myBreedCount = breedCount;
//	}
//	
//	//shark state
//	public State(String stateName, Paint myColor, int stateID, int breedCount, int starveCount){
//		myStateName = stateName;
//		myStateColor = myColor;
//		myStateID = stateID;
//		myBreedCount = breedCount;
//		myStarveCount = starveCount;
//	}

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

}