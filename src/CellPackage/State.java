package CellPackage;
import java.awt.Color;

public class State {
private String stateName;
private Color stateColor;

public State(String myStateName, Color myColor){
	this.stateName = myStateName;
	this.stateColor = myColor;
}

public String getStateName() {
	return stateName;
}

public void setStateName(String stateName) {
	this.stateName = stateName;
}

public Color getStateColor() {
	return stateColor;
}

public void setStateColor(Color stateColor) {
	this.stateColor = stateColor;
}
}
