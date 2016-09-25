package CellPackage;
import javafx.scene.paint.Paint;

public class State {
<<<<<<< HEAD
private String stateName;
private Paint stateColor;

public State(String myStateName, Paint myColor){
	this.stateName = myStateName;
	this.stateColor = myColor;
}
=======
	private String stateName;
	private int stateID;
	private Paint stateColor;

	public State(String myStateName, Paint myColor, int stateID){
		this.stateName = myStateName;
		this.stateColor = myColor;
		this.stateID = stateID;
	}
>>>>>>> 0e979deb8d6f5bca0d228532e844d216b4e3df28

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

<<<<<<< HEAD
public Paint getStateColor() {
	return stateColor;
}

public void setStateColor(Paint stateColor) {
	this.stateColor = stateColor;
}
=======
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
>>>>>>> 0e979deb8d6f5bca0d228532e844d216b4e3df28
}
