package Simulations;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.paint.Paint;
import CellPackage.Cell;
import CellPackage.State;

public class SpreadingOfFire {

	private Cell[][] myGrid;
	private double probCatch;

	public SpreadingOfFire(Cell[][] myGrid, double p){
		this.myGrid = myGrid;
		this.probCatch = p;
	}

	private boolean checkCell(int row, int column){
		return myGrid[row][column].getCellCurrentState().getStateID() ==1;
	}
	
	private boolean checkOnGrid(int row, int column){
		return row>=0 && row<myGrid.length && column >= 0 && column < myGrid.length;
	}

	private void checkSpreadFire( int row, int column){
		//ArrayList<Cell> listAdjacentTree = new ArrayList<Cell>();
		if(checkOnGrid(row + 1, column) && checkIfFire(row, column) && checkCell(row+1, column) && setFire()){
			State tempState = new State("BURNING",Paint.valueOf("RED"), 2 );
			myGrid[row+1][column].setNextState(tempState);
		}
		if(checkOnGrid(row - 1, column) && checkIfFire(row, column) && checkCell(row-1, column) && setFire()){
			State tempState = new State("BURNING",Paint.valueOf("RED"), 2 );
			myGrid[row-1][column].setNextState(tempState);
		}
		if(checkOnGrid(row, column + 1) && checkIfFire(row, column) && checkCell(row, column+1) && setFire()){
			State tempState = new State("BURNING",Paint.valueOf("RED"), 2 );
			myGrid[row][column+1].setNextState(tempState);
		}
		if(checkOnGrid(row, column -1) && checkIfFire(row, column) && checkCell(row, column-1) && setFire()){
			State tempState = new State("BURNING",Paint.valueOf("RED"), 2 );
			myGrid[row][column-1].setNextState(tempState);
		}
		//return listAdjacentTree;
	}

	private boolean setFire(){
		return Math.random()<=probCatch;
	}

	public void spreadFire(int row, int column){
		checkSpreadFire(row, column);
	}


	public void checkBurningCells(){
		for(int i = 0; i<myGrid.length;i++){
			for(int j = 0; j<myGrid[i].length;j++){
				if (myGrid[i][j].getCellCurrentState().getStateID() == 2){
					State tempState = new State("EMPTY", Paint.valueOf("YELLOW"), 0);
					myGrid[i][j].setNextState(tempState);
				}
			}
		}
	}

	public boolean checkIfFire(int row, int column){
		return myGrid[row][column].getCellCurrentState().getStateID() == 2;
	}

	public void updateCells(){
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
			for(int j = 0 ; j<myGrid.length; j++){
				System.out.print(myGrid[i][j].getCellCurrentState().getStateID());
			}
		}
	}

}