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
 * @author Pratiksha Sharma
 * @author Ryan Anders
 * @author Blake Becerra
 * Dependencies: Classes related to specific simulations: Segregation, SpreadingOfFire, GameOfLife, PredPrey, SLimeMold
 * Manages execution between the specific Simulations, Instantiates Cell[][] in the super class based on the simulation parameters
 * where each Cell has a State, and its other attributes as needed
 */
public class SimulationManager {
	private Segregation mySegregation;
	private SpreadingOfFire mySpreadingFire;
	private GameOfLife myGameOfLife;
	private PredPrey myPredPrey;
	private SlimeMold mySlimeMold;
	private Cell[][] myGrid;

	private int numCellsWidth;
	private int numCellsHeight;
	private Rectangle myRectangle;

	public final String SPREADING_FIRE = "Spreading of Fire";
	public final String SEGREGATION = "Segregation";
	public final String PRED_PREY = "Predator-Prey";
	public final String GAME_OF_LIFE = "Game of Life";
	public final String SLIME_MOLD = "Slime Mold";
	private SimModel mySimModel;

	/**
	 * Constructor
	 * @param myGrid
	 */
	public SimulationManager( SimModel simModel){
		mySimModel = simModel;
		numCellsWidth = simModel.getMySimWidth();
		numCellsHeight = simModel.getMySimHeight();
	}

	/** Returns Simulation that is to be run based on the Simulation name received 
	 * @param mySimulationName Name of the Simulation
	 * @return SimulationSuperClass - the Specific Simulation that is to be run 
	 */
	public SimulationSuperClass getSimulationType(String mySimulationName) {
		if (SPREADING_FIRE.equals(mySimulationName)){
			return mySpreadingFire;
		}
		if (SEGREGATION.equals(mySimulationName)){
			return mySegregation;
		}
		if (PRED_PREY.equals(mySimulationName)){	
			return myPredPrey;
		}
		if (GAME_OF_LIFE.equals(mySimulationName)){
			return myGameOfLife;	
		}
		if (SLIME_MOLD.equals(mySimulationName)){
			return mySlimeMold;
		}
		return null;
	}

	private State getCellState(GenState myGenState){
		State tempState = new State(myGenState.getMyName(),Paint.valueOf(myGenState.getMyColor()),myGenState.getMyStateID());
		return tempState;
	}

	/**
	 * Initializes Cell[][] in SimulationSuperClass based on the Simulation Type that is to be run at the moment
	 * @param mySimulationName Name of the simulation to run
	 * Called from the Controller
	 */
	public void initializeMyCells(String mySimulationName){
            // Initializes Cells before instantiating the Specific type
		if (SPREADING_FIRE.equals(mySimulationName)) {
			createCellsBySimulation(mySimModel.getMyTree(),mySimModel.getMyBurning(),mySimModel.getMyEmptyState());
			mySpreadingFire = new SpreadingOfFire(myGrid, mySimModel.getMyProbCatch(), getCellState(mySimModel.getMyEmptyState()),
					getCellState(mySimModel.getMyTree()), getCellState(mySimModel.getMyBurning()));
		}
		if (SEGREGATION.equals(mySimulationName)) {
			createCellsBySimulation(mySimModel.getMyRace1(),mySimModel.getMyRace2(),mySimModel.getMyEmptyState());
			mySegregation = new Segregation(myGrid, mySimModel.getMySatisfaction(), getCellState(mySimModel.getMyEmptyState()));
		}
		if (PRED_PREY.equals(mySimulationName)) {
			createCellsBySimulation(mySimModel.getMyFish(),mySimModel.getMyShark(),mySimModel.getMyEmptyState());
			myPredPrey = new PredPrey(myGrid, mySimModel.getMyFishTurnsToBreed(), mySimModel.getMySharkTurnsToBreed(), mySimModel.getMySharkTurnsToStarve(),
					getCellState(mySimModel.getMyEmptyState()), getCellState(mySimModel.getMyFish()), getCellState(mySimModel.getMyShark()));
		}
		if(GAME_OF_LIFE.equals(mySimulationName)) {
			createCellsBySimulation(mySimModel.getMyFullState(),null,mySimModel.getMyEmptyState());
			myGameOfLife = new GameOfLife(myGrid,getCellState(mySimModel.getMyEmptyState()), getCellState(mySimModel.getMyFullState()));
		}
		if(SLIME_MOLD.equals(mySimulationName)) {
			createCellsBySimulation(mySimModel.getMyMoldState(),null,mySimModel.getMyEmptyState());
			mySlimeMold = new SlimeMold(myGrid, getCellState(mySimModel.getMyEmptyState()), getCellState(mySimModel.getMyMoldState()));
		}
	}

	private void createCellsBySimulation(GenState myGenState1, GenState myGenState2, GenState myGenState3){
		myGrid = new Cell[numCellsWidth][numCellsHeight];
		if(myGenState1!=null){addGeneralStateCells(myGenState1);}
		if(myGenState2!=null){addGeneralStateCells(myGenState2);}
		if(myGenState3!=null){addEmptyCells(myGenState3);}
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
	 * @return myGrid that is instantiated in the Class based on the Simulation
	 */
	public Cell[][] getMyGrid() {
		return myGrid;
	}
}



