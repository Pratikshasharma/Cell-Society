
import gui.Controller;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

// This entire file is part of my masterpiece.
//Pratiksha Sharma

/**
 * @author pratiksha sharma (ps179)
 * Purpose- sets up the Stage to create animated scene
 * Dependencies: Controller 
 * Assumption: Assumes there is getTitle() and init() function inside Controller class
 * I think this is a good piece of code because this class performs only a single function, thus abiding by 
 * the Single Responsibility Principle. It also does not have any instance variables and fulfills its sole responsibility of starting the program 
 * and setting up the stage.
 *  
 * 
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
