package Simulations;

import CellPackage.*;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Random;

public class PredPrey {

	private static final int FISH = 1;
	private static final int SHARK = 2;
	private static final int EMPTY = 0;
	private Cell[][] myGrid;
	private int fishTurnsToBreed;
	private int sharkTurnsToBreed;
	private int sharkTurnsToStarve;
	private State emptyState;
	private State fishState;
	private State sharkState;
	
	public PredPrey(Cell[][] gr, int fishBreed, int sharkBreed, int sharkStarve, State s1, State s2, State s3) {
		this.myGrid = gr;
		this.fishTurnsToBreed = fishBreed;
		this.sharkTurnsToBreed = sharkBreed;
		this.sharkTurnsToStarve = sharkStarve;
		this.emptyState = s1;
		this.fishState = s2;
		this.sharkState = s3;
	}
	
	private boolean checkOnGrid(int row, int column){
		return row>=0 && row<myGrid.length && column >= 0 && column < myGrid[row].length;
	}
	
	private ArrayList<Integer> checkNeighborCurrentState(int r, int c, int stateID) {
		ArrayList<Integer> stateLoc = new ArrayList<Integer>();
		if (checkOnGrid(r+1,c) && (myGrid[r+1][c].getCellCurrentState().getStateID() == stateID)) {
			stateLoc.add(r+1);
			stateLoc.add(c);
		}
		if (checkOnGrid(r,c+1) && (myGrid[r][c+1].getCellCurrentState().getStateID() == stateID)) {
			stateLoc.add(r);
			stateLoc.add(c+1);
		}
		if (checkOnGrid(r-1,c) && (myGrid[r-1][c].getCellCurrentState().getStateID() == stateID)) {
			stateLoc.add(r-1);
			stateLoc.add(c);
		}
		if (checkOnGrid(r,c-1) && (myGrid[r][c-1].getCellCurrentState().getStateID() == stateID)) {
			stateLoc.add(r);
			stateLoc.add(c-1);
		}
		return stateLoc;
	}
	
	private ArrayList<Integer> checkNextEmptyState(int r, int c) {
		ArrayList<Integer> emptyLoc = new ArrayList<Integer>();
		if (checkOnGrid(r+1,c) && ((myGrid[r+1][c].getNextState() == null) || (myGrid[r+1][c].getNextState().getStateID() == EMPTY))) {
			emptyLoc.add(r+1);
			emptyLoc.add(c);
		}
		if (checkOnGrid(r,c+1) && ((myGrid[r][c+1].getNextState() == null) || (myGrid[r][c+1].getNextState().getStateID() == EMPTY))) {
			emptyLoc.add(r);
			emptyLoc.add(c+1);
		}
		if (checkOnGrid(r-1,c) && ((myGrid[r-1][c].getNextState() == null) || (myGrid[r-1][c].getNextState().getStateID() == EMPTY))) {
			emptyLoc.add(r-1);
			emptyLoc.add(c);
		}
		if (checkOnGrid(r,c-1) && ((myGrid[r][c-1].getNextState() == null) || (myGrid[r][c-1].getNextState().getStateID() == EMPTY))) {
			emptyLoc.add(r);
			emptyLoc.add(c-1);
		}
		return emptyLoc;
	}
	
	private void findSharks() {
		Random rand = new Random();
		
		for(int i = 0; i<myGrid.length; i++) {
			for(int j = 0; j<myGrid[i].length; j++) {
				if(myGrid[i][j].getCellCurrentState().getStateID() == SHARK) {
					boolean didMove = false;
					boolean didEat = false;
					boolean didBreed = false;
					int moveR = -1;
					int moveC = -1;
					int eatR = -1;
					int eatC = -1;
					
					ArrayList<Integer> fishLoc = checkNeighborCurrentState(i,j, FISH);

					
					if(myGrid[i][j].getCellCurrentState().getStarveCount() == sharkTurnsToStarve) {
						State deadShark = new State("empty",Color.WHITE,EMPTY);
						myGrid[i][j].setCellCurrentState(deadShark);
					} else {
					
						if(fishLoc.size() == 0) {
						
							//find adjacent that will be empty next turn and move to it
							
							ArrayList<Integer> emptyLoc = checkNextEmptyState(i,j);
						
							if(emptyLoc.size() != 0) {
								didMove = true;
								int randInt = rand.nextInt(emptyLoc.size()/2);
								moveR = emptyLoc.get(randInt*2);
								moveC = emptyLoc.get((randInt*2)+1);
							}

					
						} else {
						
							//choose random fish, eat it (set it instantly to empty so that it doesn't move)
							//(reset starve count) and move shark to it on next turn
						
							didEat = true;
							int randInt = rand.nextInt(fishLoc.size()/2);
							eatR = fishLoc.get(randInt*2);
							eatC = fishLoc.get((randInt*2)+1);

						}
					
						if (myGrid[i][j].getCellCurrentState().getBreedCount() == sharkTurnsToBreed) {
							didBreed = true;
					
						}
						sharkMoves(didMove, didEat, didBreed, i, j, moveR, moveC, eatR, eatC);
					}
				}
			}
			
		}
	}
	
	private void sharkMoves( boolean didMove, boolean didEat, boolean didBreed, int i, int j, int moveR, int moveC, int eatR, int eatC) {
		if (didMove && didBreed && !didEat) {
			State newShark = new State("shark",Color.YELLOW,SHARK,0,0);
			State moveShark = new State("shark",Color.YELLOW,SHARK, 0, myGrid[i][j].getCellCurrentState().getStarveCount()+1);
			myGrid[i][j].setNextState(newShark);
			myGrid[moveR][moveC].setNextState(moveShark);
		}
		if (didMove && !didBreed && !didEat) {
			State moveShark = new State("shark",Color.YELLOW,SHARK, myGrid[i][j].getCellCurrentState().getBreedCount()+1, myGrid[i][j].getCellCurrentState().getStarveCount()+1);
			myGrid[moveR][moveC].setNextState(moveShark);
		}
		if (!didMove && !didEat) {
			State sameShark = new State("shark",Color.YELLOW,SHARK, myGrid[i][j].getCellCurrentState().getBreedCount()+1, myGrid[i][j].getCellCurrentState().getStarveCount()+1);
			myGrid[i][j].setNextState(sameShark);
		}
		if (!didMove && !didBreed && didEat) {
			State sharkAte = new State("shark",Color.YELLOW,SHARK, myGrid[i][j].getCellCurrentState().getBreedCount()+1, 0);
			State eatenFish = new State("empty",Color.WHITE,EMPTY);
			myGrid[eatR][eatC].setCellCurrentState(eatenFish);
			myGrid[eatR][eatC].setNextState(sharkAte);
		}
		if (!didMove && didBreed && didEat) {
			State sharkAte = new State("shark",Color.YELLOW,SHARK, 0, 0);
			State eatenFish = new State("empty",Color.WHITE,EMPTY);
			State newShark = new State("shark",Color.YELLOW,SHARK,0,0);
			myGrid[eatR][eatC].setCellCurrentState(eatenFish);
			myGrid[eatR][eatC].setNextState(sharkAte);
			myGrid[i][j].setNextState(newShark);
		}
	
	}
	
	private void findFish() {
		Random rand = new Random();
		
		for(int i = 0; i<myGrid.length; i++) {
			for(int j = 0; j<myGrid[i].length; j++) {
				if(myGrid[i][j].getCellCurrentState().getStateID() == FISH) {
					
					boolean didMove = false;
					boolean didBreed = false;
					int moveR = -1;
					int moveC = -1;
					
					ArrayList<Integer> emptyLoc = checkNextEmptyState(i,j);
					
					// are there open adjacent spaces
					if (emptyLoc.size() != 0) {
						didMove = true;
						int randInt = rand.nextInt(emptyLoc.size()/2);
						moveR = emptyLoc.get(randInt*2);
						moveC = emptyLoc.get((randInt*2)+1);
					}
					
					// did fish breed
					if (myGrid[i][j].getCellCurrentState().getBreedCount() == fishTurnsToBreed) {
						didBreed = true;
					}
				fishMoves(didMove, didBreed, i, j, moveR, moveC);
				}
			}
		}
	}
	
	private void fishMoves(boolean didMove, boolean didBreed, int i, int j, int moveR, int moveC) {
		if (didMove && !didBreed) {
			State moveFish = new State("fish", Color.GREEN, FISH, myGrid[i][j].getCellCurrentState().getBreedCount()+1);
			myGrid[moveR][moveC].setNextState(moveFish);
		}
		if (didMove && didBreed) {
			State moveFish = new State("fish", Color.GREEN, FISH, 0);
			State newFish = new State("fish", Color.GREEN, FISH, 0);
			myGrid[moveR][moveC].setNextState(moveFish);
			myGrid[i][j].setNextState(newFish);
		}
		if (!didMove) {
			State sameFish = new State("fish", Color.GREEN, FISH, myGrid[i][j].getCellCurrentState().getBreedCount()+1);
			myGrid[i][j].setNextState(sameFish);
		}
	}
	
	private void updateCells(){
		for (int row = 0; row<myGrid.length; row++){
			for(int column = 0; column<myGrid[row].length; column++){
				if(myGrid[row][column].getNextState() == null) {
					State empty = new State ("empty",Color.WHITE,EMPTY);
					myGrid[row][column].setCellCurrentState(empty);
				} else {
					myGrid[row][column].setCellCurrentState(myGrid[row][column].getNextState());
					myGrid[row][column].setNextState(null);
					//Give GUI row & column
				}
				
			}
		}
	}
	
	public void updateSimulation(){
		findSharks();
		findFish();
		updateCells();
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
