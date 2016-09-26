package Simulations;
import java.io.File;
import java.util.HashMap;

import CellPackage.Cell;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import xml.MainXML;
import xml.model.SimModel;

public class SimulationController {
	private SimulationManager mySimulationManager;
	private MainXML myXMLReader;
	private SimModel mySimModel;
	private String mySimulationName;
	private Paint [][] myGridColor;


	public SimulationController(){
		myXMLReader = new MainXML();		
	}

	public Paint[][] readFile(File myFile){
		mySimModel = myXMLReader.xmlRead(myFile);
		//Initialize Cells
		mySimulationManager = new SimulationManager(mySimModel);
		this.mySimulationName = mySimModel.getMySimName();

		// Return the initialized Cells
		initializeCells();
		myGridColor= new Paint[mySimulationManager.getNumCellsWidth()][mySimulationManager.getNumCellsHeight()];
		getMyCellsColor(mySimulationManager.getMyCell());
		return myGridColor;	
	}
	
	public void initializeCells(){
		mySimulationManager.initializeMyCells(mySimModel.getMySimName());	
	}

	private void getMyCellsColor(Cell[][] myCell) {
		for( int i = 0; i<=mySimulationManager.getNumCellsWidth()-1;i++ ){
			for (int j=0;j<=mySimulationManager.getNumCellsHeight()-1;j++){
				myGridColor[i][j]= myCell[i][j].getCellCurrentState().getStateColor();
				System.out.println("j val " + j);
			}
		}
	}

	public String getSimulationName(){
		return this.mySimulationName;
	}
	// Update the Cells per Simulation
	public Paint[][] updateCells(){
		mySimulationManager.getSimulationType(mySimulationName);
		getMyCellsColor(mySimulationManager.getSimulationType(mySimulationName).myGrid);
		return myGridColor;
		//mySimulationManager.getSimulationType.updateSimulation();
	}
}






