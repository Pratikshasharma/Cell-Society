package simulations;

import java.util.ArrayList;
import java.util.Random;

import cellpackage.*;

/**
 * Runs logic for Predator-Prey Simulation
 * @author Ryan Anders
 */
public class PredPrey extends SimulationSuperClass{

	private static int FISH;
	private static int SHARK;
	private static int EMPTY;
//	private Cell[][] super.getGrid();

	private int myFishTurnsToBreed;
	private int mySharkTurnsToBreed;
	private int mySharkTurnsToStarve;
	private State myEmptyState;
	private State myFishState;
	private State mySharkState;
	
	public PredPrey(Cell[][] gr, int fishBreed, int sharkBreed, int sharkStarve, State s1, State s2, State s3) {
		super(gr);
		myFishTurnsToBreed = fishBreed;
		mySharkTurnsToBreed = sharkBreed;
		mySharkTurnsToStarve = sharkStarve;
		myEmptyState = s1;
		myFishState = s2;
		mySharkState = s3;
		EMPTY = s1.getStateID();
		FISH = s2.getStateID();
		SHARK = s3.getStateID();
	}
	
	private boolean checkOnGrid(int row, int column){
		return row>=0 && row<super.getGrid().length && column >= 0 && column < super.getGrid()[row].length;
	}
	
	private ArrayList<Coordinates> checkNeighborCurrentState(int r, int c, int stateID) {
		ArrayList<Coordinates> stateLoc = new ArrayList<Coordinates>();
		if (checkOnGrid(r+1,c) && (super.getGrid()[r+1][c].getCellCurrentState().getStateID() == stateID)) {
			stateLoc.add(new Coordinates(r+1,c));
		}
		if (checkOnGrid(r,c+1) && (super.getGrid()[r][c+1].getCellCurrentState().getStateID() == stateID)) {
			stateLoc.add(new Coordinates(r,c+1));
		}
		if (checkOnGrid(r-1,c) && (super.getGrid()[r-1][c].getCellCurrentState().getStateID() == stateID)) {
			stateLoc.add(new Coordinates(r-1,c));
		}
		if (checkOnGrid(r,c-1) && (super.getGrid()[r][c-1].getCellCurrentState().getStateID() == stateID)) {
			stateLoc.add(new Coordinates(r,c-1));
		}
		return stateLoc;
	}
	
	private ArrayList<Coordinates> checkEmptyNextState(int r, int c) {
		ArrayList<Coordinates> emptyLoc = new ArrayList<Coordinates>();
		if (checkOnGrid(r+1,c) && ((super.getGrid()[r+1][c].getNextState() == null) || (super.getGrid()[r+1][c].getNextState().getStateID() == EMPTY))) {
			emptyLoc.add(new Coordinates(r+1,c));
		}
		if (checkOnGrid(r,c+1) && ((super.getGrid()[r][c+1].getNextState() == null) || (super.getGrid()[r][c+1].getNextState().getStateID() == EMPTY))) {
			emptyLoc.add(new Coordinates(r,c+1));
		}
		if (checkOnGrid(r-1,c) && ((super.getGrid()[r-1][c].getNextState() == null) || (super.getGrid()[r-1][c].getNextState().getStateID() == EMPTY))) {
			emptyLoc.add(new Coordinates(r-1,c));
		}
		if (checkOnGrid(r,c-1) && ((super.getGrid()[r][c-1].getNextState() == null) || (super.getGrid()[r][c-1].getNextState().getStateID() == EMPTY))) {
			emptyLoc.add(new Coordinates(r,c-1));
		}
		return emptyLoc;
	}
	
	private void findSharks() {
		Random rand = new Random();
		
		for(int i = 0; i<super.getGrid().length; i++) {
			for(int j = 0; j<super.getGrid()[i].length; j++) {
				if(super.getGrid()[i][j].getCellCurrentState().getStateID() == SHARK) {
					boolean didMove = false;
					boolean didEat = false;
					boolean didBreed = false;
					int moveR = -1;
					int moveC = -1;
					int eatR = -1;
					int eatC = -1;
					
					ArrayList<Coordinates> fishLoc = checkNeighborCurrentState(i,j, FISH);

					
					if(super.getGrid()[i][j].getCellCurrentState().getStarveCount() == mySharkTurnsToStarve) {
						super.getGrid()[i][j].setCellCurrentState(new State(myEmptyState, 0, 0));
					} else {
					
						if(fishLoc.size() == 0) {
						
							//find adjacent that will be empty next turn and move to it
							
							ArrayList<Coordinates> emptyLoc = checkEmptyNextState(i,j);
						
							if(emptyLoc.size() != 0) {
								didMove = true;
								int randInt = rand.nextInt(emptyLoc.size());
								moveR = emptyLoc.get(randInt).getX();
								moveC = emptyLoc.get(randInt).getY();
							}

					
						} else {
						
							//choose random fish, eat it (set it instantly to empty so that it doesn't move)
							//(reset starve count) and move shark to it on next turn
						
							didEat = true;
							int randInt = rand.nextInt(fishLoc.size());
							eatR = fishLoc.get(randInt).getX();
							eatC = fishLoc.get(randInt).getY();

						}
					
						if (super.getGrid()[i][j].getCellCurrentState().getBreedCount() == mySharkTurnsToBreed) {
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
			super.getGrid()[i][j].setNextState(new State(mySharkState,0,0));
			super.getGrid()[moveR][moveC].setNextState(new State(mySharkState,0,super.getGrid()[i][j].getCellCurrentState().getStarveCount()+1));
		} else if (didMove && !didBreed && !didEat) {
			super.getGrid()[moveR][moveC].setNextState(new State(mySharkState, super.getGrid()[i][j].getCellCurrentState().getBreedCount()+1, super.getGrid()[i][j].getCellCurrentState().getStarveCount()+1));
		} else if (!didMove && !didEat && !didBreed) {
			super.getGrid()[i][j].setNextState(new State(mySharkState, super.getGrid()[i][j].getCellCurrentState().getBreedCount()+1, super.getGrid()[i][j].getCellCurrentState().getStarveCount()+1));
		} else if (!didMove && !didEat && didBreed) {
			super.getGrid()[i][j].setNextState(new State(mySharkState, 0, super.getGrid()[i][j].getCellCurrentState().getStarveCount()+1));
		} else if (!didMove && !didBreed && didEat) {
			super.getGrid()[eatR][eatC].setCellCurrentState(new State(myEmptyState,0,0));
			super.getGrid()[eatR][eatC].setNextState(new State(mySharkState,super.getGrid()[i][j].getCellCurrentState().getBreedCount()+1, 0));
		} else if (!didMove && didBreed && didEat) {
			super.getGrid()[eatR][eatC].setCellCurrentState(new State(myFishState,0,0));
			super.getGrid()[eatR][eatC].setNextState(new State(mySharkState,0,0));
			super.getGrid()[i][j].setNextState(new State(mySharkState,0,0));
		}
	
	}
	
	private void findFish() {
		Random rand = new Random();
		
		for(int i = 0; i<super.getGrid().length; i++) {
			for(int j = 0; j<super.getGrid()[i].length; j++) {
				if(super.getGrid()[i][j].getCellCurrentState().getStateID() == FISH) {
					
					boolean didMove = false;
					boolean didBreed = false;
					int moveR = -1;
					int moveC = -1;
					
					ArrayList<Coordinates> emptyLoc = checkEmptyNextState(i,j);
					
					// are there open adjacent spaces
					if (emptyLoc.size() > 0) {
						didMove = true;
						int randInt = rand.nextInt(emptyLoc.size());
						moveR = emptyLoc.get(randInt).getX();
						moveC = emptyLoc.get(randInt).getY();
					}
					
					// did fish breed
					if (super.getGrid()[i][j].getCellCurrentState().getBreedCount() == myFishTurnsToBreed) {
						didBreed = true;
					}
				fishMoves(didMove, didBreed, i, j, moveR, moveC);
				}
			}
		}
	}
	
	private void fishMoves(boolean didMove, boolean didBreed, int i, int j, int moveR, int moveC) {
		if (!didMove && !didBreed) {
			super.getGrid()[i][j].setNextState(new State(myFishState, super.getGrid()[i][j].getCellCurrentState().getBreedCount()+1, 0));
		} else if (!didMove && didBreed) {
			super.getGrid()[i][j].setNextState(new State(myFishState,0,0));
		} else if (didMove && didBreed) {
			super.getGrid()[moveR][moveC].setNextState(new State(myFishState,0,0));
			super.getGrid()[i][j].setNextState(new State(myFishState,0,0));
		} else if (didMove && !didBreed) {
			super.getGrid()[moveR][moveC].setNextState(new State(myFishState, super.getGrid()[i][j].getCellCurrentState().getBreedCount()+1,0));
		}
	}
	
	private void updateCells(){
		for (int row = 0; row<super.getGrid().length; row++){
			for(int column = 0; column<super.getGrid()[row].length; column++){
				if(super.getGrid()[row][column].getNextState() == null) {
					State empty = new State(myEmptyState,0,0);
					super.getGrid()[row][column].setCellCurrentState(empty);
				} else {
					super.getGrid()[row][column].setCellCurrentState(super.getGrid()[row][column].getNextState());
					super.getGrid()[row][column].setNextState(null);
				}
				
			}
		}
	}
	
	@Override
	public void updateSimulation(){
		findSharks();
		findFish();
		updateCells();
	}
}