package simulations;

import java.util.ArrayList;
import java.util.Random;

import cellpackage.Cell;
import cellpackage.State;

public class SlimeMold extends SimulationSuperClass{

	private State myNoAmoebeState;
	private State myAmeobeState;

	public SlimeMold(Cell[][] grid, State state1, State state2){
		super(grid);
		myNoAmoebeState = state1;
		myAmeobeState = state2;
	}

	private void updateCellcAMP(int row, int column, boolean increase){
		if(increase){
			super.getGrid()[row][column].getCellCurrentState().setBreedCount(super.getGrid()[row][column].getCellCurrentState().getBreedCount());
		}else{
			super.getGrid()[row][column].getCellCurrentState().setBreedCount(super.getGrid()[row][column].getCellCurrentState().getBreedCount() - 1);
		}
	}

	private void emptyState(int row, int column){
		super.getGrid()[row][column].setNextState(myNoAmoebeState);
		super.getGrid()[row][column].getNextState().setBreedCount(super.getGrid()[row][column].getCellCurrentState().getBreedCount());
	}

	private void findEmptyCell(int currentRow, int currentColumn){
		ArrayList<Coordinates> emptyCells = new ArrayList<Coordinates>();
		for(int i = 0; i<super.getGrid().length; i++){
			for (int j = 0; j<super.getGrid()[i].length; j++){
				if(super.getGrid()[i][j].getNextState() == null &&
						super.getGrid()[i][j].getCellCurrentState().getStateID() == myNoAmoebeState.getStateID()){
					
				}
			}
		}
		int targetCell = new Random().nextInt(emptyCells.size());
		moveAmeobe(currentRow, currentColumn, emptyCells.get(targetCell).getX(), emptyCells.get(targetCell).getY());
		emptyState(currentRow, currentColumn);
	}

	private void moveAmeobe(int fromRow, int fromColumn, int toRow, int toColumn){
		State tempState = new State(super.getGrid()[fromRow][fromColumn].getCellCurrentState().getStateName(),
				super.getGrid()[fromRow][fromColumn].getCellCurrentState().getStateColor(),
				super.getGrid()[fromRow][fromColumn].getCellCurrentState().getStateID());
		tempState.setBreedCount(super.getGrid()[fromRow][fromColumn].getCellCurrentState().getBreedCount());
		super.getGrid()[toRow][toColumn].setNextState(tempState);
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
	public void updateSimulation() {
		for(int i = 0; i<super.getGrid().length;i++){
			for (int j = 0; j<super.getGrid()[i].length; j++){
				if(super.getGrid()[i][j].getCellCurrentState().getStateID() == myAmeobeState.getStateID()){
					updateCellcAMP(i, j, true);
					findEmptyCell(i, j);
				}
				else {
					updateCellcAMP(i, j, false);
				}
			}
		}
		updateCells();
	}
}