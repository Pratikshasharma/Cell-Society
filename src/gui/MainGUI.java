package gui;
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
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 * @author pratiksha sharma
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
	private int numCellsWidth;
	private int numCellsHeight;
	private Grid myGrid;
	private PopulationGraph myPopulationGraph;
	private Map<String, Paint> myStateColorMap;
	private Map<String,Integer>myStatePopulationMap;
	private SimulationSpeedSlider mySpeedSlider;

	public MainGUI() {
		this.myStartSimulationButton = new Start();
		this.myStepSimulationButton = new Step();
		this.myStopSimulationButton = new Stop();
		this.myResetButton = new Reset();
		myPopulationGraph = new PopulationGraph();
	}

	public Group setScene(Paint[][] myGridColor, Map<String,Paint> myStateColorMap) {
		this.myStateColorMap = myStateColorMap;
		Group root = new Group();
		myVBox = new VBox(20);
		HBox tempHBox = new HBox(20);
		myVBox.setPadding(new Insets(10));
		
		myGrid = new Grid(this.numCellsWidth, this.numCellsHeight);
		
		this.myStatePopulationMap = myGrid.createGrid(myGridColor, myStateColorMap);
		
		myPopulationGraph.createLineChart(myStatePopulationMap,numCellsWidth);
		
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
		Text title = new Text(mySimulationName);
		title.setFont(Font.font("Comic Sans", FontWeight.BOLD, 15));
		return title;
	}

	private void addButtons() {
		myHBox = new HBox(10);
		myHBox.getChildren().addAll(myStopSimulationButton.getButton(), myStartSimulationButton.getButton(),
				myResetButton.getButton(), myStepSimulationButton.getButton());
	}


	public Button getResetButton() {
		return this.myResetButton.getButton() ;
	}

	public Button getStopSimulationButton() {
		return this.myStopSimulationButton.getButton();
	}

	public Button getStepSimulationButton() {
		return this.myStepSimulationButton.getButton();
	}

	public Button getStartSimulationButton() {
		return this.myStartSimulationButton.getButton();
	}

	public Slider getSimulationSpeedSlider() {
		return mySpeedSlider.mySlider;
	}

	public void setNumCellsWidth(int numCells) {
		this.numCellsWidth = numCells;
	}

	public void setNumCellsHeight(int numCells) {
		this.numCellsHeight = numCells;
	}

	public void setSimulationName(String simulationName) {
		this.mySimulationName = simulationName;
	}

	public Grid getGrid() {
		return this.myGrid;
	}

	public void updateGridColor(int row, int col, Paint color) {
		myGrid.changeMyGridColor(row, col, color);
	}

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

	public void updatePopulationGraph(boolean resetLineGraph){
		myPopulationGraph.drawLineGraph(myStatePopulationMap,resetLineGraph);
	}

	public void resetStatePopulationMap(){
		for(String key: myStatePopulationMap.keySet()){
			myStatePopulationMap.put(key, 0);
		}
	}
}
