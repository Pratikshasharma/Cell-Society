
package GUIPackage;

import java.io.File;
import Simulations.SimulationController;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.util.Duration;

public class GUIController{
	private Scene myScene;
	private StartScreen initialScreen;
	private ChooseFile myFileChooser;
	private String TITLE = "Cell Society Simulation";
	private File myChosenFile;
	private Paint [][] myGridColor;
	private CreateGrid myWindow;
	private SimulationController mySimulationController;
	private boolean stopSimulation;

	public static final int DEFAULT_FRAMES_PER_SECOND = 10;
	public static final int MAX_FRAMES_PER_SECOND = 50;
	public static final int MIN_FRAMES_PER_SECOND = 1;

	public static final int MILLISECOND_DELAY = 160 / DEFAULT_FRAMES_PER_SECOND;
	public static final double SECOND_DELAY = 1.0 / DEFAULT_FRAMES_PER_SECOND;
	public static final double SCENE_WIDTH = 800;
	public static final double SCENE_HEIGHT = 600;

	/**
	 * Constructor
	 */
	public GUIController(){
		initialScreen = new StartScreen();
		myFileChooser = new ChooseFile();
		myWindow = new CreateGrid();
		mySimulationController = new SimulationController();
	}

	public Scene init () {
		myScene = new Scene(initialScreen.createRoot(),SCENE_WIDTH,SCENE_HEIGHT, Color.WHITE); 
		initialScreen.getChooseFileButton().setOnAction(e -> chooseSimulationFile());
		myWindow.getResetButton().getButton().setOnAction(e -> resetSimulation());
		myWindow.getStartSimulationButton().getButton().setOnAction(e -> runContinuousSimulation());
		myWindow.getStepSimulationButton().getButton().setOnAction( e-> updateGrid());
		return myScene;
	} 

	
	private void resetSimulation() {
		//myGridColor = mySimulationController.initializeCellsAndGridVisualization();
		myGridColor = mySimulationController.initializeCellsAndGridVisualization();
		updateGridVisualization();
	}

	public void setTitle(String myTitle){
		this.TITLE = myTitle;
	}

	public String getTitle(){
		return this.TITLE;
	}

	/**
	 * Exit the program.
	 */
	public static void exitGame(){
		Platform.exit();
		System.exit(0);
	}

	public void chooseSimulationFile(){
		myChosenFile = myFileChooser.chooseFile();
		mySimulationController.readFile(myChosenFile);
		myGridColor = mySimulationController.initializeCellsAndGridVisualization();
		myScene.setRoot(myWindow.createCellsList(myGridColor));
	}

	private void updateGrid(){
		mySimulationController.updateCells();
		// Call the updated Color Array	
		updateGridVisualization();
	}

	private void runContinuousSimulation(){
		KeyFrame frame = new KeyFrame(Duration.millis(10/myWindow.getSimulationSpeedSlider().getValue()),e -> this.updateGrid());
		Timeline animation = new Timeline();
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.getKeyFrames().add(frame);
		animation.play();
		if(stopSimulation){
			animation.stop();
		}
	}
	/**
	 * Possibly for the Simulation package to load Chosen XML File
	 * @return
	 */
	public  File getFile(){
		return this.myChosenFile;
	}

	private void updateGridVisualization(){
		myGridColor = mySimulationController.getColorGrid();
		for (int i=0; i<myGridColor[0].length; i++){
			for(int j=0;j<myGridColor.length;j++){
				myWindow.updateColor(i, j,myGridColor[i][j]);		
			}
		}
	}


}
