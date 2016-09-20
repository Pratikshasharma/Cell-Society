package GUIPackage;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.paint.Color;


public class GUIController {
	private Scene myScene;
	private StartScreen initialScreen;
	private String TITLE = "Cell Society Simulation";

	/**
	 * Constructor
	 */
	public GUIController(){
		initialScreen = new StartScreen();
	}

	public Scene init () {
		myScene = new Scene(initialScreen.createRoot(),400,200, Color.WHITE); 
		initialScreen.getExitButton().setOnAction(e -> exitGame());
		//initialScreen.getChooseFileButton().setOnAction(e -> );
		return myScene;
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


}
