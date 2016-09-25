package Simulations;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.paint.Paint;
import CellPackage.Cell;
import CellPackage.State;

public class SpreadingOfFire {
	
	Cell[][] myGrid;
	double probCatch;
	
	public SpreadingOfFire(Cell[][] myGrid){
		this.myGrid = myGrid;
	}
	
	private boolean checkCell(int row, int column){
		return myGrid[row][column].getCellCurrentState().getStateName().equals("TREE");
	}
	
	private List checkSpreadFire( int row, int column){
		ArrayList<Cell> listAdjacentTree = new ArrayList<Cell>();
		if(myGrid[row][column].getCellCurrentState().getStateName().equals("TREE") && checkCell(row+1, column)){
			listAdjacentTree.add(myGrid[row+1][column]);
		}
		if(myGrid[row][column].getCellCurrentState().getStateName().equals("TREE") && checkCell(row-1, column)){
			listAdjacentTree.add(myGrid[row-1][column]);
		}
		if(myGrid[row][column].getCellCurrentState().getStateName().equals("TREE") && checkCell(row, column+1)){
			listAdjacentTree.add(myGrid[row][column+1]);
		}
		if(myGrid[row][column].getCellCurrentState().getStateName().equals("TREE") && checkCell(row, column-1)){
			listAdjacentTree.add(myGrid[row][column-1]);
		}
		return listAdjacentTree;
	}
	
	private boolean setFire(){
		if(Math.random()<probCatch){
			return true;
		}
		else{
			return false;
		}
	}
	
	private void spreadFire(int row, int column){
		List<Cell> adjacentTrees = checkSpreadFire(row, column);
		for(Cell adjacent: adjacentTrees){
			if (setFire()){
				State tempState = adjacent.getCellCurrentState();
				tempState.setStateID(2);
				tempState.setStateName("BURNING");
				tempState.setStateColor(Paint.valueOf("RED"));
				adjacent.setNextState(tempState);
			}
		}
	}
	
	private void checkBurningCells(){
		for(int i = 0; i<myGrid.length;i++){
			for(int j = 0; j<myGrid[j].length;j++){
				if (myGrid[i][j].getCellCurrentState().getStateID() == 2){
					State tempState = myGrid[i][j].getCellCurrentState();
					tempState.setStateID(0);;
					tempState.setStateColor(Paint.valueOf("YELLOW"));
					tempState.setStateName("EMPTY");
					myGrid[i][j].setNextState(tempState);
				}
			}
		}
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
