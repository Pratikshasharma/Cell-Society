package simulations;

import java.util.ArrayList;
import java.util.Random;

import cellpackage.Cell;
import cellpackage.State;


public class SlimeMold extends Simulation{
	private State myNoAmoebaeState;
	private State myAmoebaeState;

	/**
	 * Constructor for the class
	 * @param grid - 2D array of cells
	 * @param state1 - State of empty cells
	 * @param state2 - state of cells with amoebae 
	 */
	public SlimeMold(Cell[][] grid, State state1, State state2) {
		super(grid);
		myNoAmoebaeState = state1;
		myAmoebaeState = state2;
	}

	private void updateCellcAMP(int row, int column, boolean increase) {
		if (increase) {
			super.getGrid()[row][column].getCellCurrentState().setBreedCount(
					super.getGrid()[row][column].getCellCurrentState()
							.getBreedCount());
		} else {
			super.getGrid()[row][column].getCellCurrentState().setBreedCount(
					super.getGrid()[row][column].getCellCurrentState()
							.getBreedCount() - 1);
		}
	}

	private void emptyState(int row, int column) {
		super.getGrid()[row][column].setNextState(myNoAmoebaeState);
		super.getGrid()[row][column].getNextState().setBreedCount(
				super.getGrid()[row][column].getCellCurrentState()
						.getBreedCount());
	}

	private void findEmptyCell(int currentRow, int currentColumn) {
		ArrayList<Coordinates> emptyCells = new ArrayList<Coordinates>();
		for (int i = 0; i < super.getGrid().length; i++) {
			for (int j = 0; j < super.getGrid()[i].length; j++) {
				if (super.getGrid()[i][j].getNextState() == null
						&& super.getGrid()[i][j].getCellCurrentState()
								.getStateID() == myNoAmoebaeState.getStateID()) {
					emptyCells.add(new Coordinates(i, j));
				}
			}
		}
		int targetCell = new Random().nextInt(emptyCells.size());
		moveAmeobe(currentRow, currentColumn,
				emptyCells.get(targetCell).getX(), emptyCells.get(targetCell)
						.getY());
		emptyState(currentRow, currentColumn);
	}

	private void moveAmeobe(int fromRow, int fromColumn, int toRow, int toColumn) {
		State tempState = new State(super.getGrid()[fromRow][fromColumn]
				.getCellCurrentState().getStateName(),
				super.getGrid()[fromRow][fromColumn].getCellCurrentState()
						.getStateColor(), super.getGrid()[fromRow][fromColumn]
						.getCellCurrentState().getStateID());
		tempState.setBreedCount(super.getGrid()[fromRow][fromColumn]
				.getCellCurrentState().getBreedCount());
		super.getGrid()[toRow][toColumn].setNextState(tempState);
	}

	@Override
	public void updateSimulation() {
		for (int i = 0; i < super.getGrid().length; i++) {
			for (int j = 0; j < super.getGrid()[i].length; j++) {
				if (super.getGrid()[i][j].getCellCurrentState().getStateID() == myAmoebaeState
						.getStateID()) {
					updateCellcAMP(i, j, true);
					findEmptyCell(i, j);
				} else {
					updateCellcAMP(i, j, false);
				}
			}
		}
		updateCells();
	}
}