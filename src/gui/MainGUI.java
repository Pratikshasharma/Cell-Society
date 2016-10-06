package gui;
import java.util.List;
import java.util.Map;
import button.Reset;
import button.Start;
import button.Step;
import button.Stop;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;


/**
 * @author pratiksha sharma
 * Purpose: Template for Creating all the nodes in the Scene and Updating Shape Color and  
 * Dependencies: Start, Stop, Step, Reset, PopulationGraph, Grid, SimulationSpeedSlider
 *
 */

public class MainGUI {
	public static final int GRID_WIDTH = 400;
	public static final int GRID_HEIGHT = 400;
	public static final String DEFAULT_RESOURCE_PACKAGE = "resources";

	private VBox myVBox;
	private Start myStartSimulationButton;
	private Step myStepSimulationButton;
	private Stop myStopSimulationButton;
	private Reset myResetButton;
	private HBox myHBox;
	private String mySimulationName;
	private int myNumberOfColumns;
	private int myNumberOfRows;
	private Grid myGrid;
	private PopulationGraph myPopulationGraph;
	private Map<String, Paint> myStateColorMap;
	private Map<String,Integer>myStatePopulationMap;
	private SimulationSpeedSlider mySpeedSlider;
	

	/**
	 * constructor
	 * @param myParameterList : List that contains number of Rows, number Of columns and Simulation Name passed in 
	 * from the Controller
	 */
	public MainGUI(List<String> myParameterList) {
		mySimulationName = myParameterList.get(0);
		myNumberOfRows = Integer.parseInt(myParameterList.get(1));
		myNumberOfColumns = Integer.parseInt(myParameterList.get(2));          
		myStartSimulationButton = new Start();
		myStepSimulationButton = new Step();
		myStopSimulationButton = new Stop();
		myResetButton = new Reset();
		myPopulationGraph = new PopulationGraph();
	}

	/**
	 * 
	 * @param myGridColor : Paint Array of the Cells[][]
	 * @param myStateColorMap
	 * @return Group : Returns the main root of the Scene for Simulation
	 */
	public Group setScene(Paint[][] myGridColor, Map<String,Paint> stateColorMap) {
		myStateColorMap = stateColorMap;
		Group root = new Group();
		myVBox = new VBox(30);
		HBox tempHBox = new HBox(20);
		myVBox.setPadding(new Insets(10));

		myGrid = new Grid(myNumberOfColumns,myNumberOfRows );
		this.myStatePopulationMap = myGrid.createGrid(myGridColor, myStateColorMap);
		
		myPopulationGraph.drawGraph(myStatePopulationMap);
		myGrid = new Grid(myNumberOfColumns,myNumberOfRows );
		
		myStatePopulationMap = myGrid.createGrid(myGridColor, myStateColorMap);
		myPopulationGraph.drawGraph(myStatePopulationMap);

		tempHBox.getChildren().addAll(myGrid.getGrid(),myPopulationGraph.getMyStatePopulationChart());
		myVBox.getChildren().addAll(addSimulationTitle(),tempHBox);
		addButtons();
		mySpeedSlider = new SimulationSpeedSlider();
		myHBox.getChildren().addAll(mySpeedSlider.mySlider,mySpeedSlider.mySliderCaption,mySpeedSlider.mySliderValue);
		myVBox.getChildren().add(myHBox);
		root.getChildren().add(myVBox);
		return root;
	}

	private Text addSimulationTitle() {
		SimulationTitle title = new SimulationTitle(mySimulationName);
		return title.getMySimulationTitle();
	}

	private void addButtons() {
		myHBox = new HBox(10);
		myHBox.getChildren().addAll(myStopSimulationButton.getButton(), myStartSimulationButton.getButton(),
				myResetButton.getButton(), myStepSimulationButton.getButton());
	}


	/**
	 * @return Reset Button
	 */
	public Button getResetButton() {
		return myResetButton.getButton() ;
	}

	public Button getStopSimulationButton() {
		return myStopSimulationButton.getButton();
	}

	public Button getStepSimulationButton() {
		return myStepSimulationButton.getButton();
	}

	public Button getStartSimulationButton() {
		return myStartSimulationButton.getButton();
	}

	public Slider getSimulationSpeedSlider() {
		return mySpeedSlider.mySlider;
	}

	/**
	 * Called in from the Controller to change color of a Shape in the Grid 
	 * @param row 
	 * @param col
	 * @param color
	 */
	public void updateGridColor(int row, int col, Paint color) {
		myGrid.changeMyGridColor(row, col, color);
	}

	/**
	 * updates Population count of the State based on the color provided
	 * Called in from the Controller
	 * @param color
	 */
	public void updatePopulationCount(Paint color){
		Integer populationCounter;
		for(String key: myStatePopulationMap.keySet()){
			if(myStateColorMap.get(key).equals(color)){
				populationCounter = myStatePopulationMap.get(key);
				populationCounter+=1;
				myStatePopulationMap.put(key, populationCounter);
			}
		}
	}

	/**
	 * Updates Population Graph in the Scene
	 * @param resetLineGraph
	 * Called in from the Controller
	 */
	public void updatePopulationGraph(boolean resetLineGraph){
		myPopulationGraph.addPointsOnGraph(myStatePopulationMap,resetLineGraph);
	}

	/**
	 * Resets Population Graph in the Scene
	 * Called in from the Controller
	 */
	public void resetStatePopulationMap(){
		for(String key: myStatePopulationMap.keySet()){
			myStatePopulationMap.put(key, 0);
		}
	}
}
