package Simulations;
import java.io.File;
import CellPackage.Cell;
import javafx.scene.paint.Paint;
import xml.MainXML;
import xml.model.SimModel;
public class SimulationController {
	private SimulationManager mySimulationManager;
	private MainXML myXMLReader;
	private SimModel mySimModel;
	private String mySimulationName;
	private Paint [][] myGridColor;
	private SimulationSuperClass mySuperClass;

	public SimulationController(){
		myXMLReader = new MainXML();		
	}
	public void readFile(File myFile){
		//public Paint[][] readFile(File myFile){
		mySimModel = myXMLReader.xmlRead(myFile);
		//Initialize Cells
		mySimulationManager = new SimulationManager(mySimModel);
		this.mySimulationName = mySimModel.getMySimName();
	}

	private void getMyCellsColor(Cell[][] myCell) {
		for( int i = 0; i<mySimulationManager.getNumCellsWidth();i++ ){
			for (int j=0;j<mySimulationManager.getNumCellsHeight();j++){
				myGridColor[i][j]= myCell[i][j].getCellCurrentState().getStateColor();
			}
		}
	}

	//reset
	public Paint[][] initializeCellsAndGridVisualization(){
		mySimulationManager.initializeMyCells(mySimModel.getMySimName());
		mySuperClass = mySimulationManager.getSimulationType(mySimulationName);
		myGridColor= new Paint[mySimModel.getMySimHeight()][mySimModel.getMySimWidth()];
		getMyCellsColor(mySimulationManager.getMyGrid());
		return myGridColor;
	}

	public String getSimulationName(){
		return this.mySimulationName;
	}

	// Update the Cells per Simulation
	public void updateCells(){
		//mySuperClass.printGrid(); //for testing
		mySuperClass.updateSimulation();
		//mySuperClass.printGrid(); //for testing
		getMyCellsColor(mySuperClass.getGrid());
		//		mySimulationManager.getSimulationType(mySimulationName);
		//		getMyCellsColor(mySimulationManager.getSimulationType(mySimulationName).myGrid); ----This caused a nullpointer!!!
		//mySimulationManager.getSimulationType.updateSimulation();
		//return myGridColor;
	}

	public Paint[][] getColorGrid(){
		return myGridColor;
	}
}