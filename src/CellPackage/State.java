package CellPackage;
import javafx.scene.paint.Paint;

public class State {
	private String stateName;
	private int stateID;
	private Paint stateColor;
	private int breedCount;
	private int starveCount;

	public State(String myStateName, Paint myColor, int stateID){
		this.stateName = myStateName;
		this.stateColor = myColor;
		this.stateID = stateID;
	}
	
	//fish state
	public State(String myStateName, Paint myColor, int stateID, int breedCount){
		this.stateName = myStateName;
		this.stateColor = myColor;
		this.stateID = stateID;
		this.breedCount = breedCount;
	}
	
	//shark state
	public State(String myStateName, Paint myColor, int stateID, int breedCount, int starveCount){
		this.stateName = myStateName;
		this.stateColor = myColor;
		this.stateID = stateID;
		this.breedCount = breedCount;
		this.starveCount = starveCount;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public Paint getStateColor() {
		return stateColor;
	}

	public void setStateColor(Paint stateColor) {
		this.stateColor = stateColor;
	}
	
	public int getStateID(){
		return stateID;
	}
	
	public void setStateID(int stateID){
		this.stateID = stateID;
	}

	public int getBreedCount() {
		return breedCount;
	}

	public int getStarveCount() {
		return starveCount;
	}

}
