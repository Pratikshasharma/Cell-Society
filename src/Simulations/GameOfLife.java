package Simulations;

import CellPackage.Cell;
import CellPackage.State;

public class GameOfLife extends SimulationSuperClass {

	private Cell[][] myGrid;
	private State myEmptyState;
	private State myFullState;

	public GameOfLife(Cell[][] grid, State state1, State state2){
		myGrid = grid;
		myEmptyState = state1;
		myFullState = state2;
	}

	private boolean checkAlive(int row, int column){
		return myGrid[row][column].getCellCurrentState().getStateID() == myFullState.getStateID();
	}


	private boolean checkDead(int row, int column){
		return myGrid[row][column].getCellCurrentState().getStateID() == myEmptyState.getStateID();
	}

	public void updateState(int row, int column, int id){
		if(id == myEmptyState.getStateID()){
			myGrid[row][column].setNextState(myEmptyState);
		}else{
			myGrid[row][column].setNextState(myFullState);
		}
	}

	public boolean onGrid(int row, int column){
		return row>=0 && row<myGrid.length && column >= 0 && column < myGrid[row].length;
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

	public void updateSimulation(){
		for (int i = 0; i< myGrid.length; i++){
			for (int j = 0; j < myGrid[i].length; j++){
				int numAlive = countAlive(i, j);
				//System.out.println("number of alive " + numAlive + " at row " + i + " and column " + j);
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
		for (int row = 0; row<myGrid.length; row++){
			for(int column = 0; column<myGrid[row].length; column++){
				if(myGrid[row][column].getNextState() != null){
					myGrid[row][column].setCellCurrentState(myGrid[row][column].getNextState());
					myGrid[row][column].setNextState(null);
					//Give GUI row & column
				}
			}
		}
	}

	public void printGrid(){
		for (int i = 0; i < myGrid.length;i++){
			System.out.println();
			for(int j = 0 ; j<myGrid[i].length; j++){
				System.out.print(myGrid[i][j].getCellCurrentState().getStateID());
			}
		}
	}
	@Override
	public Cell[][] getGrid() {
		// TODO Auto-generated method stub
		return myGrid;
	}
}
