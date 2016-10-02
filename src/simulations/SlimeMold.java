package simulations;

import cellpackage.Cell;
import cellpackage.State;

public class SlimeMold extends SimulationSuperClass{

	private Cell[][] myGrid;
	private State myNoAmoebeState;
	private State myAmeobeState;

	public SlimeMold(Cell[][] grid, State state1, State state2){
		myGrid = grid;
		myNoAmoebeState = state1;
		myAmeobeState = state2;
		//initializeBreedCount();
	}

	private boolean checkOnGrid(int row, int column){
		return row>=0 && row<myGrid.length && column >= 0 && column < myGrid[row].length;
	}
	
	private void updateCellcAMP(int row, int column, boolean increase){
		if(increase){
			System.out.println("SHOULD UPDATE cAMP at (" + row + ", " + column + ")");
			int val = myGrid[row][column].getCellCurrentState().getBreedCount();
			val += 1;
			myGrid[row][column].getCellCurrentState().setBreedCount(val);
			System.out.println(myGrid[row][column].getCellCurrentState().getBreedCount());
		}else{
			myGrid[row][column].getCellCurrentState().setBreedCount(myGrid[row][column].getCellCurrentState().getBreedCount() - 1);
		}
	}

	private void emptyState(int row, int column){
		myGrid[row][column].setNextState(myNoAmoebeState);
		//myGrid[row][column].getNextState().setStateColor(myNoAmoebeState.getStateColor());
		//myGrid[row][column].getNextState().setStateID(myNoAmoebeState.getStateID());
		
		myGrid[row][column].getNextState().setBreedCount(myGrid[row][column].getCellCurrentState().getBreedCount());
	}
	
	private void findEmptyCell(int currentRow, int currentColumn){
		for(int i = 0; i<myGrid.length; i++){
			for (int j = 0; j<myGrid[i].length; j++){
				if(myGrid[i][j].getNextState() == null &&
						myGrid[i][j].getCellCurrentState().getStateID() == myNoAmoebeState.getStateID()){
					moveAmeobe(currentRow, currentColumn, i, j);
					emptyState(currentRow, currentColumn);
					return;
				}
			}
		}
	}
	
	private void moveAmeobe(int fromRow, int fromColumn, int toRow, int toColumn){
		State tempState = new State(myGrid[fromRow][fromColumn].getCellCurrentState().getStateName(),
				myGrid[fromRow][fromColumn].getCellCurrentState().getStateColor(),
				myGrid[fromRow][fromColumn].getCellCurrentState().getStateID());
		tempState.setBreedCount(myGrid[fromRow][fromColumn].getCellCurrentState().getBreedCount());
		myGrid[toRow][toColumn].setNextState(tempState);
	}
	/*
	private List<Cell> getAvailableNeighbors(int row, int column){
		ArrayList<Cell> neighbors = new ArrayList<Cell>();
		if (checkOnGrid(row+1, column) && isEmpty(row+1, column) && noNextState(row+1, column)){
			neighbors.add(myGrid[row+1][column]);
		}
		if (checkOnGrid(row-1, column) && isEmpty(row-1, column) && noNextState(row-1, column)){
			neighbors.add(myGrid[row-1][column]);
		}
		if (checkOnGrid(row, column+1) && isEmpty(row, column+1) && noNextState(row, column+1)){
			neighbors.add(myGrid[row][column+1]);
		}
		if (checkOnGrid(row, column-1) &&isEmpty(row, column-1) &&noNextState(row, column-1)){
			neighbors.add(myGrid[row][column-1]);
		}
		if (checkOnGrid(row+1, column+1) && isEmpty(row+1, column+1) && noNextState(row+1, column+1)){
			neighbors.add(myGrid[row+1][column+1]);
		}
		if (checkOnGrid(row+1, column-1) && isEmpty(row+1, column-1) && noNextState(row+1, column-1)){
			neighbors.add(myGrid[row+1][column-1]);
		}
		if (checkOnGrid(row-1, column+1) && isEmpty(row-1, column+1) && noNextState(row-1, column+1)){
			neighbors.add(myGrid[row-1][column+1]);
		}
		if (checkOnGrid(row-1, column-1) && isEmpty(row-1, column-1) && noNextState(row-1, column-1)){
			neighbors.add(myGrid[row-1][column-1]);
		}
		return neighbors;
	}
	*/
	
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


	@Override
	public void updateSimulation() {
		for(int i = 0; i<myGrid.length;i++){
			for (int j = 0; j<myGrid[i].length; j++){
				if(myGrid[i][j].getCellCurrentState().getStateID() == myAmeobeState.getStateID()){
					updateCellcAMP(i, j, true);
					//List<Cell> available = getAvailableNeighbors(i, j);
					findEmptyCell(i, j);
				}
				else {
					updateCellcAMP(i, j, false);
				}
			}
		}
		updateCells();
	}
	
	private void printGridcAMP(){
		System.out.println("Breed Count Grid");
		for (int i = 0; i < myGrid.length;i++){
			System.out.println();
			for(int j = 0 ; j<myGrid[i].length; j++){
				System.out.print(myGrid[i][j].getCellCurrentState().getBreedCount());
			}
		}
		System.out.println();
	}

	@Override
	public void printGrid() {
		for (int i = 0; i < myGrid.length;i++){
			System.out.println();
			for(int j = 0 ; j<myGrid[i].length; j++){
				System.out.print(myGrid[i][j].getCellCurrentState().getStateID());
			}
		}
		System.out.println();
		printGridcAMP();
	}

	@Override
	public Cell[][] getGrid() {
		return myGrid;
	}


}
