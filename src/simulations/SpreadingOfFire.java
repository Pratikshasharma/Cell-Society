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

	private boolean checkCell(int row, int column){
		return myGrid[row][column].getCellCurrentState().getStateID() ==1;
	}
	
	private boolean checkOnGrid(int row, int column){
		return row>=0 && row<myGrid.length && column >= 0 && column < myGrid[row].length;
	}

	private void checkSpreadFire( int row, int column){
		//ArrayList<Cell> listAdjacentTree = new ArrayList<Cell>();
		if(checkOnGrid(row + 1, column) && checkIfFire(row, column) && checkCell(row+1, column) && setFire()){
			myGrid[row+1][column].setNextState(myBurningState);
		}
		if(checkOnGrid(row - 1, column) && checkIfFire(row, column) && checkCell(row-1, column) && setFire()){
			myGrid[row-1][column].setNextState(myBurningState);
		}
		if(checkOnGrid(row, column + 1) && checkIfFire(row, column) && checkCell(row, column+1) && setFire()){
			myGrid[row][column+1].setNextState(myBurningState);
		}
		if(checkOnGrid(row, column -1) && checkIfFire(row, column) && checkCell(row, column-1) && setFire()){
			myGrid[row][column-1].setNextState(myBurningState);
		}
		//return listAdjacentTree;
	}

	private boolean setFire(){
		return Math.random()<=myProbCatch;
	}

	private void spreadFire(){
		for(int i = 0; i<6;i++){
			for (int j = 0; j<6; j++){
				if (checkIfFire(i,j)){
					checkSpreadFire( i, j);
				}
			}
		}
	}


	private void checkBurningCells(){
		for(int i = 0; i<myGrid.length;i++){
			for(int j = 0; j<myGrid[i].length;j++){
				if (myGrid[i][j].getCellCurrentState().getStateID() == 2){
					myGrid[i][j].setNextState(myEmptyState);
				}
			}
		}
	}

	private boolean checkIfFire(int row, int column){
		return myGrid[row][column].getCellCurrentState().getStateID() == 2;
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
		// TODO Auto-generated method stub
		return myGrid;
	}
}