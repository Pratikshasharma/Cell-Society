package CellPackage;


import javafx.scene.shape.Shape;

public class Cell {
	private Shape myCellShape;
	private State cellCurrentState; 
	//private State cellNextState;

	public Cell(Shape myShape, State myState){
		this.myCellShape = myShape;
		this.cellCurrentState = myState;	
	}

	public void setShape(Shape myShape){
		this.myCellShape = myShape;
	}

	public Shape getShape(){
		return this.myCellShape;
	}

	public State getCellCurrentState() {
		return cellCurrentState;
	}

	public void setCellCurrentState(State cellCurrentState) {
		this.cellCurrentState = cellCurrentState;
	}
}
