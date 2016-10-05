
package gui;
import java.io.File;
import java.util.Map;
import simulations.SimulationManager;
import simulations.SimulationSuperClass;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.util.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import cellpackage.Cell;
import xml.MainXML;
import xml.model.SimModel;

/**
 * @author pratiksha sharma
 * This class interacts both with the front end and the back end. 
 * Dependencies: Objects: MainXML, SimulationManager, SimModel, SimulationSuperClass,  
 */
public class Controller {
	private SimulationManager mySimulationManager;
	private MainXML myXMLReader;
	private SimModel mySimModel;
	private SimulationSuperClass mySuperClass;
	private Scene myScene;
	private StartScreen initialScreen;
	private ChooseFile myFileChooser;
	private String TITLE = "Cell Society Simulation";
	private MainGUI myGUI;
	private Timeline animation;
	private Map<String,Paint> myStateColorMap = new HashMap<String,Paint>();

	public static final int DEFAULT_FRAMES_PER_SECOND = 7;
	public static final int MAX_FRAMES_PER_SECOND = 12;
	public static final int MIN_FRAMES_PER_SECOND = 5;
	public static final double SCENE_WIDTH = 1100;
	public static final double SCENE_HEIGHT = 600;
	public static final double TIME_VALUE = 5000;

	private Paint[][] myGridColor;

	/**
	 * Constructor
	 */
	public Controller() {
		initialScreen = new StartScreen();
		myFileChooser = new ChooseFile();
		myXMLReader = new MainXML();
	}

	/**
	 * Method called by Main to set up the Scene
	 * @return myScene Scene to be set on the Stage
	 */
	public Scene init() {
		myScene = new Scene(initialScreen.createRoot(), SCENE_WIDTH, SCENE_HEIGHT, Color.WHITE);
		initialScreen.getOpenFileButton().setOnAction(e -> chooseSimulationFile());
		return myScene;
	}

	private void resetSimulation() {
		initializeCellsAndGridVisualization();
		updateGridAndGraph(true);
		stopSimulation();
	}

	private void chooseSimulationFile() {
		readFile(myFileChooser.chooseFile());
		createGUIObject();
		setEventHandlersOnButtons();
		initializeCellsAndGridVisualization();	
		myScene.setRoot(myGUI.setScene(myGridColor, myStateColorMap));
	}

	private void readFile(File myFile){
		mySimModel = myXMLReader.xmlRead(myFile);
		if(mySimModel!=null){
		mySimulationManager = new SimulationManager(mySimModel);
		}else{
			initialScreen.exitSimulation();
		}
	}

	private void createGUIObject(){
		List<String> mainGUIParameters = new ArrayList<String>();
		mainGUIParameters.add(mySimModel.getMySimName());
		mainGUIParameters.add(Integer.toString(mySimModel.getMySimWidth()));
		mainGUIParameters.add(Integer.toString(mySimModel.getMySimHeight()));
		myGUI = new MainGUI(mainGUIParameters);
	}
	
	
	/**
	 * Returns title for the Stage
	 * @return 
	 */
	public String getTitle() {
		return this.TITLE;
	}

	private void stopSimulation() {
		if (animation != null)
			animation.stop();
	}

	private void update() {
		mySuperClass.updateSimulation();
		getMyGridColor(mySuperClass.getGrid());
		updateGridAndGraph(false);
	}

	private void updateGridAndGraph(boolean resetLineGraph) {
		myGUI.resetStatePopulationMap();
		for (int i = 0; i < myGridColor[0].length; i++) {
			for (int j = 0; j < myGridColor.length; j++) {
				myGUI.updateGridColor(i, j, myGridColor[i][j]);
				myGUI.updatePopulationCount(myGridColor[i][j]);
			}
		}
		myGUI.updatePopulationGraph(resetLineGraph);	
	}

	private void runContinuousSimulation() {
		animation = new Timeline();
		KeyFrame frame = new KeyFrame(Duration.millis(TIME_VALUE / myGUI.getSimulationSpeedSlider().getValue()),e -> this.update());
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.getKeyFrames().add(frame);
		animation.play();
	}

	private void setEventHandlersOnButtons() {
		myGUI.getResetButton().setOnAction(e -> resetSimulation());
		myGUI.getStartSimulationButton().setOnAction(e -> runContinuousSimulation());
		myGUI.getStepSimulationButton().setOnAction(e -> update());
		myGUI.getStopSimulationButton().setOnAction(e -> stopSimulation());
	}

	private void getMyGridColor(Cell[][] myCell) {
		for( int i = 0; i<mySimModel.getMySimWidth();i++ ){
			for (int j= 0;j<mySimModel.getMySimHeight();j++){
				myGridColor[i][j] = myCell[i][j].getCellCurrentState().getStateColor();
				createStateColorMap(myCell[i][j].getCellCurrentState().getStateName(),myCell[i][j].getCellCurrentState().getStateColor());
			}
		}
	}

	private void initializeCellsAndGridVisualization(){
		mySimulationManager.initializeMyCells(mySimModel.getMySimName());
		mySuperClass = mySimulationManager.getSimulationType(mySimModel.getMySimName());
		myGridColor= new Paint[mySimModel.getMySimHeight()][mySimModel.getMySimWidth()];
		getMyGridColor(mySimulationManager.getMyGrid());
	}

	private void createStateColorMap(String stateName, Paint stateColor){
		if(!myStateColorMap.containsKey(stateName)){
			myStateColorMap.put(stateName, stateColor);
		}
	}

}




