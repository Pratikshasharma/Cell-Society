package simulations;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import cellpackage.Cell;
import cellpackage.State;

public class Segregation extends SimulationSuperClass {

	private double myPercentSatisfaction;
	private State myEmptyState;

	public Segregation(Cell[][] grid, double spercentSatisfaction, State state1){
		super(grid);
		myPercentSatisfaction = spercentSatisfaction;
		myEmptyState = state1;
	}

	private boolean notEmpty(int row, int column){
		return super.getGrid()[row][column].getCellCurrentState().getStateID() != myEmptyState.getStateID();
	}

	private List<Integer> getAdjacent(int row, int column){
		ArrayList<Integer> adjacentStates = new ArrayList<Integer>();
		if (checkOnGrid(row+1, column) && notEmpty(row+1, column)){
			adjacentStates.add(super.getGrid()[row+1][column].getCellCurrentState().getStateID());
		}
		if (checkOnGrid(row-1, column) && notEmpty(row-1, column)){
			adjacentStates.add(super.getGrid()[row-1][column].getCellCurrentState().getStateID());
		}
		if (checkOnGrid(row, column+1) && notEmpty(row, column+1)){
			adjacentStates.add(super.getGrid()[row][column+1].getCellCurrentState().getStateID());
		}
		if (checkOnGrid(row, column-1) && notEmpty(row, column-1)){
			adjacentStates.add(super.getGrid()[row][column-1].getCellCurrentState().getStateID());
		}
		if (checkOnGrid(row+1, column+1) && notEmpty(row+1, column+1)){
			adjacentStates.add(super.getGrid()[row+1][column+1].getCellCurrentState().getStateID());
		}
		if (checkOnGrid(row+1, column-1) && notEmpty(row+1, column-1)){
			adjacentStates.add(super.getGrid()[row+1][column-1].getCellCurrentState().getStateID());
		}
		if (checkOnGrid(row-1, column+1) && notEmpty(row-1, column+1)){
			adjacentStates.add(super.getGrid()[row-1][column+1].getCellCurrentState().getStateID());
		}
		if (checkOnGrid(row-1, column-1) && notEmpty(row-1, column-1)){
			adjacentStates.add(super.getGrid()[row-1][column-1].getCellCurrentState().getStateID());
		}
		return adjacentStates;
	}

	private boolean isSatisfied(double percent){
		return percent >= myPercentSatisfaction;
	}

	private double countRace(List<Integer> adjacent, int race){
		double raceCount = 0.0;
		for (int state: adjacent){
			if (race == state){
				raceCount += 1.0;
			}
		}
		return raceCount;
	}

	private void keepState(int row, int column){
		State tempState = new State(super.getGrid()[row][column].getCellCurrentState().getStateName(), 
				super.getGrid()[row][column].getCellCurrentState().getStateColor(),
				super.getGrid()[row][column].getCellCurrentState().getStateID());
		super.getGrid()[row][column].setNextState(tempState);

	}

	private void emptyState(int row, int column){
		super.getGrid()[row][column].setNextState(myEmptyState);
	}

	private void move(int fromRow, int fromColumn, int toRow, int toColumn){
		State tempState = new State(super.getGrid()[fromRow][fromColumn].getCellCurrentState().getStateName(),
				super.getGrid()[fromRow][fromColumn].getCellCurrentState().getStateColor(),
				super.getGrid()[fromRow][fromColumn].getCellCurrentState().getStateID());
		super.getGrid()[toRow][toColumn].setNextState(tempState);
	}

	private void findEmptyCell(int currentRow, int currentColumn){
		ArrayList<Coordinates> emptyCells = new ArrayList<Coordinates>();
		for(int i = 0; i<super.getGrid().length; i++){
			for (int j = 0; j<super.getGrid()[i].length; j++){
				if(super.getGrid()[i][j].getNextState() == null && 
						super.getGrid()[i][j].getCellCurrentState().getStateID() == myEmptyState.getStateID()){
					emptyCells.add(new Coordinates(i, j));
				}
			}
		}
		int toMoveTo = new Random().nextInt(emptyCells.size());
		move(currentRow, currentColumn, emptyCells.get(toMoveTo).getX(), emptyCells.get(toMoveTo).getY());
		emptyState(currentRow, currentColumn);
	}
	@Override
	public void updateSimulation(){
		for(int i = 0; i<super.getGrid().length; i++){
			for(int j = 0; j<super.getGrid()[i].length; j++){
				if(super.getGrid()[i][j].getCellCurrentState().getStateID() != myEmptyState.getStateID()){
					List<Integer> adjacentStates = getAdjacent(i, j);
					double myRaceCount = countRace(adjacentStates, super.getGrid()[i][j].getCellCurrentState().getStateID());
					double otherRaceCount = adjacentStates.size() - myRaceCount;
					if(otherRaceCount ==0 ||isSatisfied(myRaceCount/(myRaceCount+otherRaceCount))){
						keepState(i, j);
					}
					else{
						findEmptyCell(i, j);
					}
				}
			}
		}
		updateCells();
	}

	public void printGrid(){
		for (int i = 0; i < super.getGrid().length;i++){
			System.out.println();
			for(int j = 0 ; j<super.getGrid()[i].length; j++){
				System.out.print(super.getGrid()[i][j].getCellCurrentState().getStateID());
			}
		}
	}
}
