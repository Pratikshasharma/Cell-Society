package Simulations;

import java.util.Random;

import CellPackage.Cell;
import xml.model.GenState;
import xml.model.SimModel;
import CellPackage.State;
import GUIPackage.CreateGrid;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class SimulationManager {
	private Segregation mySegregationSimulation;
	private SpreadingOfFire mySpreadingFire;
	private Cell[][] myGrid;

	private int numCellsWidth;
	private int numCellsHeight;
	private Rectangle myRectangle;

	public final String SPREADING_FIRE = "Spreading of Fire";
	public final String SEGREGATION = "Segregation";
	public final String PREDPREY = "Predator-Prey";
	public final String GAMEOFLIFE = "Game of Life";
	private SimModel mySimModel;


	/**
	 * Constructor
	 * @param myGrid
	 */
	public SimulationManager( SimModel mySimModel){
		this.mySimModel = mySimModel;
		this.numCellsWidth = mySimModel.getMySimWidth();
		this.numCellsHeight = mySimModel.getMySimHeight();
	}


	public SimulationSuperClass getSimulationType(String mySimulationName){
		if (SPREADING_FIRE.equals(mySimulationName)){
			//return fire constructor
		}
		if (SEGREGATION.equals(mySimulationName)){
		}
		if (PREDPREY.equals(mySimulationName)){	
		}
		if (GAMEOFLIFE.equals(mySimulationName)){
			
		}
		return null;
	}

	private State getCellState(GenState myGenState){
		State tempState = new State(myGenState.getMyName(),Paint.valueOf(myGenState.getMyColor()),myGenState.getMyStateID());
		return tempState;
	}

	public Cell[][] getMyCell(){
		return this.myGrid;
	}

	public void initializeMyCells(String mySimulationName){
		if( (SPREADING_FIRE.equals(mySimulationName)));
		{
			//State myState1 = getCellState(mySimModel.getMyBurning());
			//State myState2 = getCellState(mySimModel.getMyEmptyState());
			//State myState3 = getCellState(mySimModel.getMyTree());
			// First Initialize Cells Before Calling in Constructor
			this.myGrid = initializeCells(mySimModel.getMyTree(),mySimModel.getMyBurning(),mySimModel.getMyEmptyState());
		}

		if (SEGREGATION.equals(mySimulationName)){
			//State myState1 = getCellState(mySimModel.getMyBurning());
			//State myState2 = getCellState(mySimModel.getMyEmptyState());
			//State myState3 = getCellState(mySimModel.getMyTree());
			// First Initialize Cells Before Calling in Constructor
			this.myGrid = initializeCells(mySimModel.getMyRace1(),mySimModel.getMyRace2(),mySimModel.getMyEmptyState());
		}
		
		if (PREDPREY.equals(mySimulationName)) {
			//State myState1 = getCellState(mySimModel.getMyFish());
			//State myState3 = getCellState(mySimModel.getMyShark());
			//State myState2 = getCellState(mySimModel.getMyEmpty());
			this.myGrid = initializeCells(mySimModel.getMyFish(),mySimModel.getMyShark(),mySimModel.getMyEmptyState());
		}
		
		if(GAMEOFLIFE.equals(mySimulationName)) {
			//State myState1 = getCellState(mySimModel.getMyFullState());
			//State myState3 = getCellState(mySimModel.getMyEmptyState());
			this.myGrid = initializeCells(mySimModel.getMyFullState(),null,mySimModel.getMyEmptyState());
		}

	}

	public Cell[][] initializeCells(GenState myGenState1, GenState myGenState2, GenState myGenState3){
		this.myGrid = new Cell[numCellsWidth][numCellsHeight];
		if(myGenState1!=null){addGenState(myGenState1);}
		if(myGenState2!=null){addGenState(myGenState2);}
		if(myGenState3!=null){addEmptyCells(myGenState3);}

		// Print all the cells	
		return this.myGrid;
	}

	private void addGenState(GenState myGenState){
		double percentage = 0.0;
		double counter = 0.0;
		while ( percentage <= myGenState.getMyPercentage()){
			Random rand = new Random();
			int i = rand.nextInt(numCellsWidth-1);
			int j = rand.nextInt(numCellsHeight-1);
			if(isEmpty(myGrid[i][j])){
				myGrid [i][j] = createNewCell(myGenState);
				counter = counter+1;
			}
			//counter = counter+1;
			percentage = counter/(numCellsWidth*numCellsHeight);
		}
	}
	
	private void addEmptyCells(GenState myGenState){
		for( int i = 0; i<=numCellsWidth-1;i++ ){
			for (int j=0;j<=numCellsHeight-1;j++){
				if(isEmpty(myGrid[i][j])){
					myGrid[i][j]= createNewCell(myGenState);
				}
			}
		}		
	}

	public int getNumCellsHeight() {
		return numCellsHeight;
	}

	public int getNumCellsWidth() {
		return numCellsWidth;
	}
	private boolean isEmpty(Cell cell){
		return cell==null;
	}
	
	private Cell createNewCell(GenState myGenState){
		State myCellState = getCellState(myGenState);
		myRectangle = new Rectangle(CreateGrid.GRID_WIDTH/numCellsWidth,CreateGrid.GRID_HEIGHT/numCellsHeight);
		Cell myTempCell = new Cell(myRectangle,myCellState);
		return myTempCell;
	}
}





