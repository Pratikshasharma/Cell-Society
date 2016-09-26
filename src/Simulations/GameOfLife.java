package Simulations;

import javafx.scene.paint.Paint;
import CellPackage.Cell;
import CellPackage.State;

public class GameOfLife extends SimulationSuperClass {
	
	private Cell[][] myGrid;
	private State emptyState;
	private State fullState;
	
	public GameOfLife(Cell[][] grid, State s1, State s2){
		this.myGrid = grid;
		this.emptyState = s1;
		this.fullState = s2;
	}
	
	private boolean checkAlive(int row, int column){
		return myGrid[row][column].getCellCurrentState().getStateID() == 1;
	}
	
	
	private boolean checkDead(int row, int column){
		return myGrid[row][column].getCellCurrentState().getStateID() == 0;
	}
	
	public void updateState(int row, int column, int id){
		State tempState = new State("DEAD", Paint.valueOf("WHITE"), id);
		myGrid[row][column].setNextState(tempState);
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
}
