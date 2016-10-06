package simulations;
import cellpackage.Cell;

//This entire file is part of my masterpiece.
//Blake Becerra

/**
 * Superclass for individual Simulation classes
 * @author Ryan Anders
 *@author Blake Becerra
 */
public abstract class SimulationSuperClass {

	private Cell[][] myGrid;
	
	/**
	 * create SuperClass containing the grid for any simulation to operate upon
	 * @param grid
	 */
	public SimulationSuperClass(Cell[][] grid){
		myGrid = grid;
	}

	/**
	 * @return myGrid
	 */
	public Cell[][] getGrid(){
		return myGrid;
	}

	/**
	 * run simulation logic for current grid layout
	 */
	public abstract void updateSimulation();

	/**
	 * check if a cell is within the bounds of the grid
	 * @param row
	 * @param column
	 * @return true if cell at [row][column] is within grid
	 */
	protected boolean checkOnGrid(int row, int column){
		return row>=0 && row<myGrid.length && column >= 0 && column < myGrid[row].length;
	}
	
	/**
	 * update the grid one step
	 */
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