package CellPackage;
import javafx.scene.paint.Paint;

public class State {
private String stateName;
private Paint stateColor;

public State(String myStateName, Paint myColor){
	this.stateName = myStateName;
	this.stateColor = myColor;
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
}
