package simulations;
import cellpackage.Cell;



public abstract class SimulationSuperClass {

	//protected genState myGenState; -- This holds the State and the percentages
	//	public SimulationSuperClass(Cell[][] myGrid){
	//		this.myGrid = myGrid;
	//	}

	private Cell[][] myGrid;

	public SimulationSuperClass(Cell[][] grid){
		myGrid = grid;
	}

	//	/**
	//	 * Abstract Class for Calling Simulation
	//	 */
	//
	public Cell[][] getGrid(){
		return myGrid;
	}

	public abstract void updateSimulation();

	protected boolean checkOnGrid(int row, int column){
		return row>=0 && row<myGrid.length && column >= 0 && column < myGrid[row].length;
	}
	
	protected void updateCells() {
		for (int row = 0; row < myGrid.length; row++) {
			for (int column = 0; column < myGrid[row].length; column++) {
				if (myGrid[row][column].getNextState() != null) {
					myGrid[row][column].setCellCurrentState(myGrid[row][column].getNextState());
					myGrid[row][column].setNextState(null);
				}
			}
		}
	}



}



