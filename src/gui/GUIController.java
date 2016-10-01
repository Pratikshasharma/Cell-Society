
package GUIPackage;
import java.io.File;
import Simulations.SimulationController;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.util.Duration;

/**
 * @author pratiksha sharma
 *
 */
public class GUIController{
	private Scene myScene;
	private StartScreen initialScreen;
	private ChooseFile myFileChooser;
	private String TITLE = "Cell Society Simulation";
	private File myChosenFile;
	private Paint [][] myGridColor;
	private CreateGrid myWindow;
	private SimulationController mySimulationController;
	private int numCellsWidth;
	private int numCellsHeight;
	private String mySimulationName;
	private Timeline animation;

	public static final int DEFAULT_FRAMES_PER_SECOND = 3;
	public static final int MAX_FRAMES_PER_SECOND = 5;
	public static final int MIN_FRAMES_PER_SECOND = 1;
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
		myWindow.getStopSimulationButton().getButton().setOnAction(e-> stopSimulation());
		return myScene;
	} 

	
	private void resetSimulation() {
		myGridColor = mySimulationController.initializeCellsAndGridVisualization();
		updateGridVisualization();
		if(animation!=null) stopSimulation();
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
	
	public void stopSimulation(){
		animation.stop();
	}

	public void chooseSimulationFile(){
		
		myChosenFile = myFileChooser.chooseFile();
		
		mySimulationController.readFile(myChosenFile);
		
		this.mySimulationName = mySimulationController.getSimulationName();
		this.numCellsHeight = mySimulationController.getNumCellsHeight();
		this.numCellsWidth = mySimulationController.getNumCellsWidth();
		
		myWindow.setNumCellsHeight(this.numCellsHeight);
		myWindow.setNumCellsWidth(this.numCellsWidth);
		myWindow.setSimulationName(this.mySimulationName);

		myGridColor = mySimulationController.initializeCellsAndGridVisualization();
		myScene.setRoot(myWindow.createCellsList(myGridColor));	
	}

	private void updateGrid(){
		mySimulationController.updateCells();
		// Call the updated Color Array	
		updateGridVisualization();
		
	}

	private void runContinuousSimulation(){
		animation =  new Timeline();
		KeyFrame frame = new KeyFrame(Duration.millis(5000/myWindow.getSimulationSpeedSlider().getValue()),e -> this.updateGrid());
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.getKeyFrames().add(frame);
		animation.play();
			
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
	
	public int getNumCellWidth(){
		return this.numCellsWidth;
	}
	
	public int getNumCellHeight(){
		return this.numCellsHeight;
	}

	public String getSimulationName(){
		return this.mySimulationName;
	}
	
}
