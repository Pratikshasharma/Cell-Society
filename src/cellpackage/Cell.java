package cellpackage;


import javafx.scene.shape.Shape;

/**
 * Creates an Object Cell that has attributes- Shape, CurrentState and NextState
 * @author pratiksha sharma
 * Dependencies: State Object
 * 
 */
public class Cell {
	private Shape myCellShape;
	private State myCellCurrentState; 
	private State myCellNextState;

	public Cell(Shape myShape, State myState){
		myCellShape = myShape;
		myCellCurrentState = myState;
	}
	
	public void setShape(Shape myShape){
		myCellShape = myShape;
	}

	public Shape getShape(){
		return myCellShape;
	}

	public State getCellCurrentState() {
		return myCellCurrentState;
	}

	public void setCellCurrentState(State cellCurrentState) {
		myCellCurrentState = cellCurrentState;
	}
	
	public State getNextState(){
		return myCellNextState;
	}
	
	public void setNextState(State cellNextState){
		myCellNextState = cellNextState;
	}
}
