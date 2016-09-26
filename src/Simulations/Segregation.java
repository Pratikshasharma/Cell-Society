package Simulations;

import java.util.ArrayList;

import javafx.scene.paint.Paint;
import CellPackage.Cell;
import CellPackage.State;

public class Segregation {

	private Cell[][] myGrid;
	private double percentSatisfaction;
	private State emptyState;
	private State race1State;
	private State race2State;

	public Segregation(Cell[][] grid, double s, State s1, State s2, State s3){
		this.myGrid = grid;
		this.percentSatisfaction = s;
		this.emptyState = s1;
		this.race1State = s2;
		this.race2State = s3;
	}

	private boolean checkOnGrid(int row, int column){
		return row>=0 && row<myGrid.length && column >= 0 && column < myGrid[row].length;
	}
	
	private boolean notEmpty(int row, int column){
		return myGrid[row][column].getCellCurrentState().getStateID() != 0;
	}

	private ArrayList<Integer> getAdjacent(int row, int column){
		ArrayList<Integer> adjacentStates = new ArrayList<Integer>();
		if (checkOnGrid(row+1, column) && notEmpty(row+1, column)){
			adjacentStates.add(myGrid[row+1][column].getCellCurrentState().getStateID());
		}
		if (checkOnGrid(row-1, column) && notEmpty(row-1, column)){
			adjacentStates.add(myGrid[row-1][column].getCellCurrentState().getStateID());
		}
		if (checkOnGrid(row, column+1) && notEmpty(row, column+1)){
			adjacentStates.add(myGrid[row][column+1].getCellCurrentState().getStateID());
		}
		if (checkOnGrid(row, column-1) && notEmpty(row, column-1)){
			adjacentStates.add(myGrid[row][column-1].getCellCurrentState().getStateID());
		}
		if (checkOnGrid(row+1, column+1) && notEmpty(row+1, column+1)){
			adjacentStates.add(myGrid[row+1][column+1].getCellCurrentState().getStateID());
		}
		if (checkOnGrid(row+1, column-1) && notEmpty(row+1, column-1)){
			adjacentStates.add(myGrid[row+1][column-1].getCellCurrentState().getStateID());
		}
		if (checkOnGrid(row-1, column+1) && notEmpty(row-1, column+1)){
			adjacentStates.add(myGrid[row-1][column+1].getCellCurrentState().getStateID());
		}
		if (checkOnGrid(row-1, column-1) && notEmpty(row-1, column-1)){
			adjacentStates.add(myGrid[row-1][column-1].getCellCurrentState().getStateID());
		}
		return adjacentStates;
	}

	private boolean isSatisfied(double percent){
		return percent >= percentSatisfaction;
	}

	private double countRace(ArrayList<Integer> adjacent, int race){
		double raceCount = 0.0;
		for (int state: adjacent){
			if (race == state){
				raceCount += 1.0;
			}
		}
		return raceCount;
	}

	private void keepState(int row, int column){
		State tempState = new State(myGrid[row][column].getCellCurrentState().getStateName(), 
				myGrid[row][column].getCellCurrentState().getStateColor(),
				myGrid[row][column].getCellCurrentState().getStateID());
		myGrid[row][column].setNextState(tempState);

	}

	private void emptyState(int row, int column){
		State tempState = new State("EMPTY", Paint.valueOf("WHITE"), 0);
		myGrid[row][column].setNextState(tempState);
	}

	private void move(int fromRow, int fromColumn, int toRow, int toColumn){
		State tempState = new State(myGrid[fromRow][fromColumn].getCellCurrentState().getStateName(),
				myGrid[fromRow][fromColumn].getCellCurrentState().getStateColor(),
				myGrid[fromRow][fromColumn].getCellCurrentState().getStateID());
		myGrid[toRow][toColumn].setNextState(tempState);
	}

	private void findEmptyCell(int currentRow, int currentColumn){
		for(int i = 0; i<myGrid.length; i++){
			for (int j = 0; j<myGrid[i].length; j++){
				if(myGrid[i][j].getNextState() == null && 
						myGrid[i][j].getCellCurrentState().getStateID() ==0){
					move(currentRow, currentColumn, i, j);
					emptyState(currentRow, currentColumn);
					return;
				}
			}
		}
	}

	public void updateSimulation(){
		for(int i = 0; i<myGrid.length; i++){
			for(int j = 0; j<myGrid[i].length; j++){
				if(myGrid[i][j].getCellCurrentState().getStateID() != 0){
					ArrayList<Integer> adjacentStates = getAdjacent(i, j);
					double myRaceCount = countRace(adjacentStates, myGrid[i][j].getCellCurrentState().getStateID());
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
	//help

	public void printGrid(){
		for (int i = 0; i < myGrid.length;i++){
			System.out.println();
			for(int j = 0 ; j<myGrid[i].length; j++){
				System.out.print(myGrid[i][j].getCellCurrentState().getStateID());
			}
		}
	}
}
