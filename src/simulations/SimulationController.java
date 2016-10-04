package simulations;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import cellpackage.Cell;
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
	private Map<String,Paint> myStateColorMap = new HashMap<String,Paint>();


	public SimulationController(){
		myXMLReader = new MainXML();	
	}

	public void readFile(File myFile){
		mySimModel = myXMLReader.xmlRead(myFile);
		mySimulationManager = new SimulationManager(mySimModel);
		mySimulationName = mySimModel.getMySimName();
	}
	
	private void getMyCellsColor(Cell[][] myCell) {
		for( int i = 0; i<mySimulationManager.getNumCellsWidth();i++ ){
			for (int j= 0;j<mySimulationManager.getNumCellsHeight();j++){
				myGridColor[i][j] = myCell[i][j].getCellCurrentState().getStateColor();
				createStateColorMap(myCell[i][j].getCellCurrentState().getStateName(),myCell[i][j].getCellCurrentState().getStateColor());
			}
		}
	}

	public Paint[][] initializeCellsAndGridVisualization(int numCellsWidth, int numCellsHeight) {
		
		System.out.println(" Simulation Name Passed " + mySimulationName);
		System.out.println(" sim model name null ? : " + mySimModel.getMySimName());
		
		mySimulationManager.initializeMyCells(mySimulationName,numCellsWidth, numCellsHeight);
		
		mySuperClass = mySimulationManager.getSimulationType(mySimulationName);

		myGridColor= new Paint[numCellsWidth][numCellsHeight];

		getMyCellsColor(mySimulationManager.getMyGrid());
		
		return myGridColor;
	}

	public void updateCells(){
		mySuperClass.updateSimulation();
		getMyCellsColor(mySuperClass.getGrid());
	}

	public Paint[][] getColorGrid(){
		return myGridColor;
	}

	public String getSimulationName(){
		return mySimulationName;
	}
	

	private void createStateColorMap(String stateName, Paint stateColor){
		if(!myStateColorMap.containsKey(stateName)){
			myStateColorMap.put(stateName, stateColor);
		}
	}
	
	public Map<String,Paint> getStateColorMap(){
		return myStateColorMap;
	}

}