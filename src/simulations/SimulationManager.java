package simulations;
import java.util.Random;

import cellpackage.Cell;
import cellpackage.State;
import xml.model.GenState;
import xml.model.SimModel;
import gui.MainGUI;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

/**
 * @author pratiksha sharma
 *
 */
public class SimulationManager {
	private Segregation mySegregation;
	private SpreadingOfFire mySpreadingFire;
	private GameOfLife myGameOfLife;
	private PredPrey myPredPrey;
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
			return mySpreadingFire;
		}
		if (SEGREGATION.equals(mySimulationName)){
			return mySegregation;
		}
		if (PREDPREY.equals(mySimulationName)){	
			return myPredPrey;
		}
		if (GAMEOFLIFE.equals(mySimulationName)){
			return myGameOfLife;	
		}
		return null;
	}

	private State getCellState(GenState myGenState){
		State tempState = new State(myGenState.getMyName(),Paint.valueOf(myGenState.getMyColor()),myGenState.getMyStateID());
		return tempState;
	}

	public void initializeMyCells(String mySimulationName){
		if (SPREADING_FIRE.equals(mySimulationName)) {
			this.myGrid = getCellsBySimulation(mySimModel.getMyTree(),mySimModel.getMyBurning(),mySimModel.getMyEmptyState());
			this.mySpreadingFire = new SpreadingOfFire(myGrid, mySimModel.getMyProbCatch(), getCellState(mySimModel.getMyEmptyState()),
					getCellState(mySimModel.getMyTree()), getCellState(mySimModel.getMyBurning()));
		}

		if (SEGREGATION.equals(mySimulationName)) {
			// First Initialize Cells Before Calling in Constructor
			this.myGrid = getCellsBySimulation(mySimModel.getMyRace1(),mySimModel.getMyRace2(),mySimModel.getMyEmptyState());
			this.mySegregation = new Segregation(myGrid, mySimModel.getMySatisfaction(), getCellState(mySimModel.getMyEmptyState()), getCellState(mySimModel.getMyRace1()),
					getCellState(mySimModel.getMyRace2()));
		}

		if (PREDPREY.equals(mySimulationName)) {
			this.myGrid = getCellsBySimulation(mySimModel.getMyFish(),mySimModel.getMyShark(),mySimModel.getMyEmptyState());
			this.myPredPrey = new PredPrey(myGrid, mySimModel.getMyFishTurnsToBreed(), mySimModel.getMySharkTurnsToBreed(), mySimModel.getMySharkTurnsToStarve(),
					getCellState(mySimModel.getMyEmptyState()), getCellState(mySimModel.getMyFish()), getCellState(mySimModel.getMyShark()));
		}
		if(GAMEOFLIFE.equals(mySimulationName)) {
			this.myGrid = getCellsBySimulation(mySimModel.getMyFullState(),null,mySimModel.getMyEmptyState());
			this.myGameOfLife = new GameOfLife(myGrid,getCellState(mySimModel.getMyEmptyState()), getCellState(mySimModel.getMyFullState()));
		}
	}
	public Cell[][] getCellsBySimulation(GenState myGenState1, GenState myGenState2, GenState myGenState3){
		this.myGrid = new Cell[numCellsWidth][numCellsHeight];
		if(myGenState1!=null){addGeneralStateCells(myGenState1);}
		if(myGenState2!=null){addGeneralStateCells(myGenState2);}
		if(myGenState3!=null){addEmptyCells(myGenState3);}
		return this.myGrid;
	}

	private void addGeneralStateCells(GenState myGenState){
		double percentage = 0.0;
		double counter = 0.0;
		while ( percentage < myGenState.getMyPercentage()){
			Random rand = new Random();
			int i = rand.nextInt(numCellsWidth);
			int j = rand.nextInt(numCellsHeight);
			if(isEmpty(myGrid[i][j])){
				myGrid [i][j] = createNewCell(myGenState);
				counter = counter+1;
			}
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
		myRectangle = new Rectangle(MainGUI.GRID_WIDTH/numCellsWidth,MainGUI.GRID_HEIGHT/numCellsHeight);
		Cell myTempCell = new Cell(myRectangle,myCellState);
		return myTempCell;
	}

	/**
	 * @return myGrid
	 */
	public Cell[][] getMyGrid() {
		return myGrid;
	}
}



