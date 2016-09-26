

import GUIPackage.GUIController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{
	private GUIController myGUIController;

	/**
	 * Constructor
	 */
	public Main(){
		myGUIController = new GUIController();
	}

	@Override
	public void start(Stage myStage) throws Exception{
		myStage.setTitle(myGUIController.getTitle());
		Scene scene = myGUIController.init();
		myStage.setScene(scene);
		myStage.show();
	}

	/**
	 * Start the program.
	 */
	public static void main (String[] args) {
		launch(args);
	}

	public static void exitGame(){
		Platform.exit();
		System.exit(0);
	}
	
}
