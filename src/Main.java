
import gui.Controller;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author pratiksha sharma (ps179)
 * Main program- sets up the Stage to create animated scene
 * Dependencies: GUIController Class
 * Assumption: Assumes there is getTitle() and init() function inside GUIController class
 */

public class Main extends Application{
	private Controller myController;

	@Override
	public void start(Stage myStage) throws Exception{
		myController = new Controller();
		myStage.setTitle(myController.getTitle());
		Scene scene = myController.init();
		myStage.setScene(scene);
		myStage.show();
	}

	/**
	 * Start the program.
	 */
	public static void main (String[] args) {
		launch(args);
	}
}
