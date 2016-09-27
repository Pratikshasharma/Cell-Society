package Simulations;
import java.io.File;
import CellPackage.Cell;
import javafx.scene.paint.Paint;
import xml.MainXML;
import xml.model.SimModel;

/**
 * @author pratikshasharma
 *
 */
public class SimulationController {
	private SimulationManager mySimulationManager;
	private MainXML myXMLReader;
	private SimModel mySimModel;
	private String mySimulationName;
	private Paint [][] myGridColor;
	private SimulationSuperClass mySuperClass;
	private int numCellsWidth;
	private int numCellsHeight;

	public SimulationController(){
		myXMLReader = new MainXML();		
	}
	public void readFile(File myFile){
		mySimModel = myXMLReader.xmlRead(myFile);
		mySimulationManager = new SimulationManager(mySimModel);
		
		this.mySimulationName = mySimModel.getMySimName();
		this.numCellsHeight = mySimModel.getMySimHeight();
		this.numCellsWidth = mySimModel.getMySimWidth();

	}

	private void getMyCellsColor(Cell[][] myCell) {
		for( int i = 0; i<mySimulationManager.getNumCellsWidth();i++ ){
			for (int j= 0;j<mySimulationManager.getNumCellsHeight();j++){
					 myGridColor[i][j] = myCell[i][j].getCellCurrentState().getStateColor();
			}
		}
	}

	public Paint[][] initializeCellsAndGridVisualization(){
		mySimulationManager.initializeMyCells(mySimModel.getMySimName());
		mySuperClass = mySimulationManager.getSimulationType(mySimulationName);
		myGridColor= new Paint[mySimModel.getMySimHeight()][mySimModel.getMySimWidth()];
		getMyCellsColor(mySimulationManager.getMyGrid());
		return myGridColor;
	}


	public void updateCells(){
		System.out.println(" UPDATES HERE ");
		//mySuperClass.printGrid(); //for testing
		mySuperClass.updateSimulation();
		//mySuperClass.printGrid(); //for testing
		getMyCellsColor(mySuperClass.getGrid());
	}

	public Paint[][] getColorGrid(){
		return myGridColor;
	}
	
	public int getNumCellsWidth(){
		return this.numCellsWidth;
	}

	public int getNumCellsHeight(){
		return this.numCellsHeight;
	}
	public String getSimulationName(){
		return this.mySimulationName;
	}
}