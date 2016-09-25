package GUIPackage;

import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class StartScreen{
	private final String TITLE = "Cell Society Simulation" ;
	private final String CELL_IMAGE = "cells.jpg";
	private ButtonCreater exitButton;
	private ButtonCreater chooseFileButton;
	
	public String getTITLE() {
		return TITLE;
	}

	public StartScreen(){
		exitButton = new ButtonCreater("Exit",event -> GUIController.exitGame());
		chooseFileButton = new ButtonCreater("Open File", null);
	}
	
	public Group createRoot(){
		Group root = new Group();
		changeButtonSettings();
		ImageView myStartImage =new ImageView(new Image(getClass().getClassLoader().getResourceAsStream(CELL_IMAGE)));
		root.getChildren().add(myStartImage);
		root.getChildren().add(chooseFileButton.getButton());
		root.getChildren().add(exitButton.getButton());
		
		System.out.println(" Creates Root ");
		
		return root;
	
	}

	private void changeButtonSettings(){
		chooseFileButton.setButtonSettings(0.4*GUIController.SCENE_WIDTH, GUIController.SCENE_HEIGHT/2,20);
		exitButton.setButtonSettings(0.7*GUIController.SCENE_WIDTH,GUIController.SCENE_HEIGHT/2,20);	
	}
	
	public Button getChooseFileButton(){
		return chooseFileButton.getButton();
	}
	
	public Button getExitButton(){
		return exitButton.getButton();
	}

}
