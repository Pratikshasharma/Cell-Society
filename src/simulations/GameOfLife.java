package simulations;

import cellpackage.Cell;
import cellpackage.State;

public class GameOfLife extends SimulationSuperClass {

	//private Cell[][] super.getGrid();
	private State myEmptyState;
	private State myFullState;

	public GameOfLife(Cell[][] grid, State state1, State state2){
		super(grid);
		//myGrid = grid;
		myEmptyState = state1;
		myFullState = state2;
	}

	private boolean checkAlive(int row, int column){
		return super.getGrid()[row][column].getCellCurrentState().getStateID() == myFullState.getStateID();
	}


	private boolean checkDead(int row, int column){
		return super.getGrid()[row][column].getCellCurrentState().getStateID() == myEmptyState.getStateID();
	}

	public void updateState(int row, int column, int id){
		if(id == myEmptyState.getStateID()){
			super.getGrid()[row][column].setNextState(myEmptyState);
		}else{
			super.getGrid()[row][column].setNextState(myFullState);
		}
	}

	public boolean onGrid(int row, int column){
		return row>=0 && row<super.getGrid().length && column >= 0 && column < super.getGrid()[row].length;
	}

	public int countAlive(int row, int column){
		int numAlive = 0;
		if (onGrid(row+1, column) && checkAlive(row+1, column)){
			numAlive++;
		}
		if (onGrid(row-1, column) && checkAlive(row-1, column)){
			numAlive++;
		}
		if (onGrid(row, column+1) && checkAlive(row, column+1)){
			numAlive++;
		}
		if (onGrid(row, column-1) && checkAlive(row, column-1)){
			numAlive++;
		}
		if (onGrid(row+1, column+1) && checkAlive(row+1, column+1)){
			numAlive++;
		}
		if (onGrid(row+1, column-1) && checkAlive(row+1, column-1)){
			numAlive++;
		}
		if (onGrid(row-1, column+1) && checkAlive(row-1, column+1)){
			numAlive++;
		}
		if (onGrid(row-1, column-1) && checkAlive(row-1, column-1)){
			numAlive++;
		}
		return numAlive;
	}

	@Override
	public void updateSimulation(){
		for (int i = 0; i< super.getGrid().length; i++){
			for (int j = 0; j < super.getGrid()[i].length; j++){
				int numAlive = countAlive(i, j);
				if (checkAlive(i, j) && (numAlive<2 || numAlive>3)){
					updateState(i,j, 0);

				}else if(checkAlive(i,j) || (checkDead(i, j) && numAlive ==3)){
					updateState(i, j, 1);
				}
			}
		}
		updateCells();
	}

	private void updateCells(){
		for (int row = 0; row<super.getGrid().length; row++){
			for(int column = 0; column<super.getGrid()[row].length; column++){
				if(super.getGrid()[row][column].getNextState() != null){
					super.getGrid()[row][column].setCellCurrentState(super.getGrid()[row][column].getNextState());
					super.getGrid()[row][column].setNextState(null);
				}
			}
		}
	}
}
