package simulations;
import cellpackage.Cell;
import cellpackage.State;

public class SpreadingOfFire extends Simulation{

	private double myProbCatch;
	private State myEmptyState;
	private State myTreeState;
	private State myBurningState;

	public SpreadingOfFire(Cell[][] grid, double probCatch, State state1, State state2, State state3){
		super(grid);
		myProbCatch = probCatch;
		myEmptyState = state1;
		myTreeState = state2;
		myBurningState = state3;
	}

	private boolean checkIfTree(int row, int column){
		return super.getGrid()[row][column].getCellCurrentState().getStateID() ==myTreeState.getStateID();
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

	@Override
	public void updateSimulation(){
		checkBurningCells();
		spreadFire();
		updateCells();
	}
}