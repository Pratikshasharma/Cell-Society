
package gui;

import java.io.File;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.util.Duration;
import Simulations.SimulationController;

/**
 * @author pratiksha sharma
 *
 */
public class GUIController {
	private Scene myScene;
	private StartScreen initialScreen;
	private ChooseFile myFileChooser;
	private String TITLE = "Cell Society Simulation";
	private File myChosenFile;
	private MainGUI myGUI;
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

	private Paint[][] myGridColor;

	/**
	 * Constructor
	 */
	public GUIController() {
		initialScreen = new StartScreen();
		myFileChooser = new ChooseFile();
		myGUI = new MainGUI();
		mySimulationController = new SimulationController();
	}

	public Scene init() {
		myScene = new Scene(initialScreen.createRoot(), SCENE_WIDTH, SCENE_HEIGHT, Color.WHITE);
		setEventHandlersOnButtons();
		return myScene;
	}


	private void resetSimulation() {
		myGridColor = mySimulationController.initializeCellsAndGridVisualization();
		updateGridVisualization();
		stopSimulation();
	}

	public void setTitle(String myTitle) {
		this.TITLE = myTitle;
	}

	public String getTitle() {
		return this.TITLE;
	}



	public void stopSimulation() {
		if (animation != null)
			animation.stop();
	}

	public void chooseSimulationFile() {
		myChosenFile = myFileChooser.chooseFile();
		mySimulationController.readFile(myChosenFile);

		this.mySimulationName = mySimulationController.getSimulationName();
		this.numCellsHeight = mySimulationController.getNumCellsHeight();
		this.numCellsWidth = mySimulationController.getNumCellsWidth();

		myGUI.setNumCellsHeight(this.numCellsHeight);
		myGUI.setNumCellsWidth(this.numCellsWidth);
		myGUI.setSimulationName(this.mySimulationName);

		myGridColor = mySimulationController.initializeCellsAndGridVisualization();
		myScene.setRoot(myGUI.setScene(myGridColor));
	}

	private void updateGrid() {
		mySimulationController.updateCells();
		// Call the updated Color Array
		updateGridVisualization();
	}

	private void runContinuousSimulation() {
		animation = new Timeline();
		KeyFrame frame = new KeyFrame(Duration.millis(5000 / myGUI.getSimulationSpeedSlider().getValue()),e -> this.updateGrid());
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.getKeyFrames().add(frame);
		animation.play();

	}

	/**
	 * Possibly for the Simulation package to load Chosen XML File
	 * 
	 * @return
	 */
	public File getFile() {
		return this.myChosenFile;
	}

	private void updateGridVisualization() {
		myGridColor = mySimulationController.getColorGrid();
		for (int i = 0; i < myGridColor[0].length; i++) {
			for (int j = 0; j < myGridColor.length; j++) {
				myGUI.updateGridColor(i, j, myGridColor[i][j]);
			}
		}
	}

	public String getSimulationName() {
		return this.mySimulationName;
	}

	private void setEventHandlersOnButtons() {
		initialScreen.getOpenFileButton().setOnAction(e -> chooseSimulationFile());
		myGUI.getResetButton().setOnAction(e -> resetSimulation());
		myGUI.getStartSimulationButton().setOnAction(e -> runContinuousSimulation());
		myGUI.getStepSimulationButton().setOnAction(e -> updateGrid());
		myGUI.getStopSimulationButton().setOnAction(e -> stopSimulation());

	}
}
