package gui;
import button.Exit;
import button.OpenFile;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/** Purpose: Creates a root for the Initial Splash Screen to be put in the Scene
 * Dependencies: Exit, OpenFile Classes
 * @author pratiksha sharma
 *
 */
public class StartScreen {
	private final String TITLE = "Cell Society Simulation";
	private final String CELL_IMAGE = "cells.jpg";
	private Exit myExitButton;
	private OpenFile myOpenFileButton;

	public String getTITLE() {
		return TITLE;
	}

	public StartScreen() {
		myOpenFileButton = new OpenFile();
		myExitButton = new Exit();
	}
	
	/**
	 * Adds nodes to the main root of the Scene
	 * @return root: Main root for the Scene in Splash Screen
	 */

	public Group createRoot() {
		Group root = new Group();
		myExitButton.getButton().setOnAction(e-> exitSimulation());
		ImageView myStartImage = new ImageView(new Image(getClass().getClassLoader().getResourceAsStream(CELL_IMAGE)));
		myStartImage.setFitWidth(Controller.SCENE_WIDTH);
		myStartImage.setFitHeight(Controller.SCENE_WIDTH);
		root.getChildren().addAll(myStartImage,myOpenFileButton.getButton(),(myExitButton.getButton()));
		return root;
	}

	/**
	 * 
	 * @return OpenFile Button
	 */
	public Button getOpenFileButton() {
		return myOpenFileButton.getButton();
	}

	/**
	 * Exit the program.
	 */
	public void exitSimulation() {
		Platform.exit();
		System.exit(0);
	}

}