package simulations;
import cellpackage.Cell;
import cellpackage.State;

public class SpreadingOfFire extends SimulationSuperClass{

//	private Cell[][] myGrid;
	private double myProbCatch;
	private State myEmptyState;
	private State myTreeState;
	private State myBurningState;

	public SpreadingOfFire(Cell[][] grid, double probCatch, State state1, State state2, State state3){
		super(grid);
	//	myGrid = grid;
		myProbCatch = probCatch;
		myEmptyState = state1;
		myTreeState = state2;
		myBurningState = state3;
	}

	private boolean checkIfTree(int row, int column){
		return super.getGrid()[row][column].getCellCurrentState().getStateID() ==myTreeState.getStateID();
	}
	
	private boolean checkOnGrid(int row, int column){
		return row>=0 && row<super.getGrid().length && column >= 0 && column < super.getGrid()[row].length;
	}

	private void checkSpreadFire( int row, int column){
		if(checkOnGrid(row + 1, column)  && checkIfTree(row+1, column) && setFire()){
			super.getGrid()[row+1][column].setNextState(myBurningState);
		}
		if(checkOnGrid(row - 1, column)  && checkIfTree(row-1, column) && setFire()){
			super.getGrid()[row-1][column].setNextState(myBurningState);
		}
		if(checkOnGrid(row, column + 1) && checkIfTree(row, column+1) && setFire()){
			super.getGrid()[row][column+1].setNextState(myBurningState);
		}
		if(checkOnGrid(row, column -1)  && checkIfTree(row, column-1) && setFire()){
			super.getGrid()[row][column-1].setNextState(myBurningState);
		}
	}

	private boolean setFire(){
		return  Math.random()<=myProbCatch;
	}

	private void spreadFire(){
		for(int i = 0; i<super.getGrid().length;i++){
			for (int j = 0; j<super.getGrid().length; j++){
				if (checkIfFire(i,j)){
					checkSpreadFire( i, j);
				}
			}
		}
	}


	private void checkBurningCells(){
		for(int i = 0; i<super.getGrid().length;i++){
			for(int j = 0; j<super.getGrid()[i].length;j++){
				if (super.getGrid()[i][j].getCellCurrentState().getStateID() == myBurningState.getStateID()){
					super.getGrid()[i][j].setNextState(myEmptyState);
				}
			}
		}
	}

	private boolean checkIfFire(int row, int column){
		return super.getGrid()[row][column].getCellCurrentState().getStateID() ==myBurningState.getStateID() ;
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
	
	@Override
	public void updateSimulation(){
		checkBurningCells();
		spreadFire();
		updateCells();
	}
}