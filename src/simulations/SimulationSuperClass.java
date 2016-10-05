package simulations;
import cellpackage.Cell;


/**
 * Superclass for individual Simulation classes
 * @author Ryan Anders
 *@author Blake Becerra
 */
public abstract class SimulationSuperClass {

	private Cell[][] myGrid;

	public SimulationSuperClass(Cell[][] grid){
		myGrid = grid;
	}

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



