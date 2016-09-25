package CellPackage;
import javafx.scene.paint.Paint;

public class State {
	private String stateName;
	private int stateID;
	private Paint stateColor;

	public State(String myStateName, Paint myColor, int stateID){
		this.stateName = myStateName;
		this.stateColor = myColor;
		this.stateID = stateID;
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
}
