package GUIPackage;

import javafx.scene.Group;
import javafx.scene.control.Button;

public class StartScreen{
	private final String TITLE = "Cell Society Simulation" ;
	private Button chooseFileButton = new Button ("Open File");
	private Button exitButton = new Button ("EXIT");

	public String getTITLE() {
		return TITLE;
	}

	public Group createRoot(){
		Group root = new Group();
		changeButtonSettings();
		root.getChildren().add(chooseFileButton);
		root.getChildren().add(exitButton);
		return root;
	}

	private void changeButtonSettings(){
		chooseFileButton.setLayoutX(50);
		chooseFileButton.setLayoutY(50);
		exitButton.setLayoutX(250);
		exitButton.setLayoutY(50);
	}
	
	public Button getChooseFileButton(){
		return chooseFileButton;
	}
	
	public Button getExitButton(){
		return exitButton;
	}

}
