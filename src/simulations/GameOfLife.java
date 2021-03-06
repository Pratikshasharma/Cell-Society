package simulations;

import cellpackage.Cell;
import cellpackage.State;

public class GameOfLife extends Simulation{

	private State myEmptyState;
	private State myFullState;

	/**
	 * Constructor for the class
	 * @param grid - Grid of Cells used 
	 * @param state1 - state representation if there is no one in the cell
	 * @param state2 - state representation if there is some one in the cell
	 */
	public GameOfLife(Cell[][] grid, State state1, State state2){
		super(grid);
		myEmptyState = state1;
		myFullState = state2;
	}

	private boolean checState(int row, int column, int id){
		return super.getGrid()[row][column].getCellCurrentState().getStateID() == id;
	}

	private void updateState(int row, int column, int id){
		if(id == myEmptyState.getStateID()){
			super.getGrid()[row][column].setNextState(myEmptyState);
		}else{
			super.getGrid()[row][column].setNextState(myFullState);
		}
	}

	private int countAlive(int row, int column){
		int numAlive = 0;
		if (checkOnGrid(row+1, column) && checState(row+1, column, myFullState.getStateID())){
			numAlive++;
		}
		if (checkOnGrid(row-1, column) && checState(row-1, column, myFullState.getStateID())){
			numAlive++;
		}
		if (checkOnGrid(row, column+1) && checState(row, column+1, myFullState.getStateID())){
			numAlive++;
		}
		if (checkOnGrid(row, column-1) && checState(row, column-1, myFullState.getStateID())){
			numAlive++;
		}
		if (checkOnGrid(row+1, column+1) && checState(row+1, column+1, myFullState.getStateID())){
			numAlive++;
		}
		if (checkOnGrid(row+1, column-1) && checState(row+1, column-1, myFullState.getStateID())){
			numAlive++;
		}
		if (checkOnGrid(row-1, column+1) && checState(row-1, column+1, myFullState.getStateID())){
			numAlive++;
		}
		if (checkOnGrid(row-1, column-1) && checState(row-1, column-1, myFullState.getStateID())){
			numAlive++;
		}
		return numAlive;
	}

	/**
	 * Processes the logic to update the cells for the GameOfLife specific model
	 */
	@Override
	public void updateSimulation(){
		for (int i = 0; i< super.getGrid().length; i++){
			for (int j = 0; j < super.getGrid()[i].length; j++){
				int numAlive = countAlive(i, j);
				if (checState(i, j, myFullState.getStateID()) && (numAlive<2 || numAlive>3)){
					updateState(i,j, 0);

				}else if(checState(i,j, myFullState.getStateID()) || (checState(i, j, myEmptyState.getStateID()) && numAlive ==3)){
					updateState(i, j, 1);
				}
			}
		}
		updateCells();
	}
}
