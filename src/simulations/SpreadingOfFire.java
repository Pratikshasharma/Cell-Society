package simulations;
import cellpackage.Cell;
import cellpackage.State;

public class SpreadingOfFire extends SimulationSuperClass{

	private Cell[][] myGrid;
	private double myProbCatch;
	private State myEmptyState;
	private State myTreeState;
	private State myBurningState;

	public SpreadingOfFire(Cell[][] grid, double probCatch, State state1, State state2, State state3){
		myGrid = grid;
		myProbCatch = probCatch;
		myEmptyState = state1;
		myTreeState = state2;
		myBurningState = state3;
	}

	private boolean checkIfTree(int row, int column){
		return myGrid[row][column].getCellCurrentState().getStateID() ==myTreeState.getStateID();
	}
	
	private boolean checkOnGrid(int row, int column){
		return row>=0 && row<myGrid.length && column >= 0 && column < myGrid[row].length;
	}

	private void checkSpreadFire( int row, int column){
		if(checkOnGrid(row + 1, column)  && checkIfTree(row+1, column) && setFire()){
			myGrid[row+1][column].setNextState(myBurningState);
		}
		if(checkOnGrid(row - 1, column)  && checkIfTree(row-1, column) && setFire()){
			myGrid[row-1][column].setNextState(myBurningState);
		}
		if(checkOnGrid(row, column + 1) && checkIfTree(row, column+1) && setFire()){
			myGrid[row][column+1].setNextState(myBurningState);
		}
		if(checkOnGrid(row, column -1)  && checkIfTree(row, column-1) && setFire()){
			myGrid[row][column-1].setNextState(myBurningState);
		}
	}

	private boolean setFire(){
		return  Math.random()<=myProbCatch;
	}

	private void spreadFire(){
		for(int i = 0; i<myGrid.length;i++){
			for (int j = 0; j<myGrid.length; j++){
				if (checkIfFire(i,j)){
					checkSpreadFire( i, j);
				}
			}
		}
	}

 void checkBurningCells(){
		for(int i = 0; i<myGrid.length;i++){
			for(int j = 0; j<myGrid[i].length;j++){
				if (myGrid[i][j].getCellCurrentState().getStateID() == myBurningState.getStateID()){
					myGrid[i][j].setNextState(myEmptyState);
				}
			}
		}
	}

	private boolean checkIfFire(int row, int column){
		return myGrid[row][column].getCellCurrentState().getStateID() ==myBurningState.getStateID() ;
	} 

	private void updateCells(){
		for (int row = 0; row<myGrid.length; row++){
			for(int column = 0; column<myGrid[row].length; column++){
				if(myGrid[row][column].getNextState() != null){
					myGrid[row][column].setCellCurrentState(myGrid[row][column].getNextState());
					myGrid[row][column].setNextState(null);
				}
			}
		}
	}
	public void updateSimulation(){
		checkBurningCells();
		spreadFire();
		updateCells();
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
		return myGrid;
	}
}