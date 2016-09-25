package Simulations;

import java.util.ArrayList;
import java.util.List;

import CellPackage.Cell;

public class Segregation {

	private Cell[][] myGrid;
	
	public Segregation(Cell[][] grid){
		myGrid = grid;
	}
	
	private boolean checkCell(Cell currentCell, int row, int column){
		return currentCell.getCellCurrentState().getStateID() != myGrid[row][column].getCellCurrentState().getStateID();
	}
	
	private List checkNeighbors( int row, int column){
		ArrayList<Cell> listAdjacentTree = new ArrayList<Cell>();
		if(myGrid[row][column].getCellCurrentState().getStateName().equals("State1") && checkCell(myGrid[row][column], row+1, column)){
			listAdjacentTree.add(myGrid[row+1][column]);
		}
		if(myGrid[row][column].getCellCurrentState().getStateName().equals("State1") && checkCell(myGrid[row][column], row-1, column)){
			listAdjacentTree.add(myGrid[row-1][column]);
		}
		if(myGrid[row][column].getCellCurrentState().getStateName().equals("State1") && checkCell(myGrid[row][column], row, column+1)){
			listAdjacentTree.add(myGrid[row][column+1]);
		}
		if(myGrid[row][column].getCellCurrentState().getStateName().equals("State1") && checkCell(myGrid[row][column], row, column-1)){
			listAdjacentTree.add(myGrid[row][column-1]);
		}
		if(myGrid[row][column].getCellCurrentState().getStateName().equals("State1") && checkCell(myGrid[row][column], row+1, column+1)){
			listAdjacentTree.add(myGrid[row][column-1]);
		}
		if(myGrid[row][column].getCellCurrentState().getStateName().equals("State1") && checkCell(myGrid[row][column], row+1, column-1)){
			listAdjacentTree.add(myGrid[row][column-1]);
		}
		if(myGrid[row][column].getCellCurrentState().getStateName().equals("State1") && checkCell(myGrid[row][column], row-1, column-1)){
			listAdjacentTree.add(myGrid[row][column-1]);
		}
		if(myGrid[row][column].getCellCurrentState().getStateName().equals("State1") && checkCell(myGrid[row][column], row-1, column-1)){
			listAdjacentTree.add(myGrid[row][column-1]);
		}
		return listAdjacentTree;
	}
	
	public void updateCells(){
		for (int row = 0; row<myGrid.length; row++){
			for(int column = 0; column<myGrid[row].length; row++){
				if(myGrid[row][column].getNextState() != null){
					myGrid[row][column].setCellCurrentState(myGrid[row][column].getNextState());
					myGrid[row][column].setNextState(null);
					//Give GUI row & column
				}
			}
		}
	}
}
