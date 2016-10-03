package simulations.sugarscape;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import cellpackage.*;
import simulations.SimulationSuperClass;

/**
 * Runs logic for SugarScape Simulation
 * @author Ryan Anders
 */
public class Sugarscape extends SimulationSuperClass{

	private static int AGENT;
	private static int EMPTY;
	private Cell[][] myGrid;
	private int mySugarGrowBackRate;
	private int mySugarGrowBackInterval;
	private State myEmptyState;
	private State myAgentState;
	
	public Sugarscape(Cell[][] gr, int sugarGrowBackRate, int sugarGrowBackInterval, State s1, State s2) {
		myGrid = gr;
		myEmptyState = s1;
		EMPTY = s1.getStateID();
		AGENT = s2.getStateID();
		mySugarGrowBackRate = sugarGrowBackRate;
		mySugarGrowBackInterval = sugarGrowBackInterval;
	}
	
	private boolean checkOnGrid(int row, int column){
		return row>=0 && row<myGrid.length && column >= 0 && column < myGrid[row].length;
	}
	
	private void sugarGrowBack() {
		for (int i = 0; i<myGrid.length; i++) {
			for (int j = 0; j<myGrid[i].length; j++) {
				if(myGrid[i][j].getCellCurrentState().getTick() == mySugarGrowBackInterval) {
					myGrid[i][j].getCellCurrentState().setSugarAmt(myGrid[i][j].getCellCurrentState().getSugarAmt() + mySugarGrowBackRate);
					myGrid[i][j].getCellCurrentState().setTick(0);
				}
			}
		}
	}
		
	
	private HashMap<Coordinates,Integer> checkSurroundingSugar(int r, int c, int vision) {
		HashMap<Coordinates, Integer> stateLoc = new HashMap<Coordinates, Integer>();
		
		for (int i=1; i<=vision; i++){
			if (checkOnGrid(r+i,c) && (myGrid[r+i][c].getCellCurrentState().getStateID() == EMPTY)) {
				stateLoc.put(new Coordinates(r+i,c), myGrid[r+1][c].getCellCurrentState().getSugarAmt());
			}
			if (checkOnGrid(r,c+i) && (myGrid[r][c+i].getCellCurrentState().getStateID() == EMPTY)) {
				stateLoc.put(new Coordinates(r,c+1), myGrid[r][c+1].getCellCurrentState().getSugarAmt());
			}
			if (checkOnGrid(r-i,c) && (myGrid[r-i][c].getCellCurrentState().getStateID() == EMPTY)) {
				stateLoc.put(new Coordinates(r-i,c), myGrid[r-1][c].getCellCurrentState().getSugarAmt());
			}
			if (checkOnGrid(r,c-i) && (myGrid[r][c-i].getCellCurrentState().getStateID() == EMPTY)) {
				stateLoc.put(new Coordinates(r,c-i), myGrid[r][c-1].getCellCurrentState().getSugarAmt());
			}
		}
		return stateLoc;
	}
	
	private Coordinates chooseBiggestClosestLoc(HashMap<Coordinates,Integer> map, int agentX, int agentY) {
		Coordinates bestCoordinates = new Coordinates(agentX,agentY);
		int bestSugar = -1;
		for (Map.Entry<Coordinates,Integer> entry : map.entrySet()) {
			int tempSugar = entry.getValue();
			Coordinates tempCoor = entry.getKey();
			if(bestSugar == -1) {
				bestCoordinates = tempCoor;
				bestSugar = tempSugar;
			} else if ((bestSugar != -1) && (tempSugar == bestSugar)) {
				if (isCloser(tempCoor, bestCoordinates, agentX, agentY)) {
					bestCoordinates = tempCoor;
					bestSugar = tempSugar;
				}
			} else if ((bestSugar != -1) && (tempSugar > bestSugar)) {
				bestCoordinates = tempCoor;
				bestSugar = tempSugar;
			}
		}
		return bestCoordinates;
	}
	
	private boolean isCloser(Coordinates coord1, Coordinates coord2, int agentX, int agentY) {
		int x1Dist = Math.abs(coord1.getX()-agentX);
		int y1Dist = Math.abs(coord1.getY()-agentY);
		int x2Dist = Math.abs(coord2.getX()-agentX);
		int y2Dist = Math.abs(coord2.getY()-agentY);
		return x1Dist+y1Dist < x2Dist+y2Dist;
	}
	
	private void moveAgent(Agent agent, Coordinates target, int currentX, int currentY) {
		myGrid[currentX][currentY].setCellCurrentState(new State(myGrid[currentX][currentY].getCellCurrentState(), EMPTY));
		myGrid[target.getX()][target.getY()].setCellCurrentState();
	}
	
	private void updateCells(){
		for (int row = 0; row<myGrid.length; row++){
			for(int column = 0; column<myGrid[row].length; column++){
				myGrid[row][column].setCellCurrentState(myGrid[row][column].getNextState());
				myGrid[row][column].setNextState(null);
			}
		}
	}
	
	private void tick() {
		for (int i = 0; i<myGrid.length; i++) {
			for (int j = 0; j<myGrid[i].length; j++) {
				myGrid[i][j].getCellCurrentState().setTick(myGrid[i][j].getCellCurrentState().getTick()+1);
			}
		}
	}
	
	
	public void updateSimulation(){
		sugarGrowBack();
		//choose agent randomly
			//agent looks in 4 directions for unoccupied (checkSurroundingSugar)
			//find largest/closest sugar amt (find largest/closest)
			//go there, add sugar to mySugar, deplete sugar amt to 0
			//subtract metabolism, die if <= 0
		tick();
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
	
	@Override
	public Cell[][] getGrid() {
		return myGrid;
	}
	
}