package gui;
import button.Exit;
import button.OpenFile;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
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

	public Group createRoot() {
		Group root = new Group();
		myExitButton.getButton().setOnAction(e-> exitSimulation());
		ImageView myStartImage = new ImageView(new Image(getClass().getClassLoader().getResourceAsStream(CELL_IMAGE)));
		root.getChildren().add(myStartImage);
		root.getChildren().add(myOpenFileButton.getButton());
		root.getChildren().add(myExitButton.getButton());
		return root;
	}

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