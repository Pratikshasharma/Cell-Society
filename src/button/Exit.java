package button;

import gui.Controller;
//This entire file is part of my masterpiece.
//Pratiksha Sharma

/** This class creates exitButton. This shows the implementation of ButtonCreater Super class.
 * I also call in the setButtonSettings() method from this constructor 
 * so that I don't have to create another method inside the class and call that method from the MainGUI.
 *
 * Creates Exit Button 
 * @author pratiksha sharma
 * Assumption: Assumes ButtonClass has setButonSettings() method
 * Dependencies: ButtonClass (Super Class)
 */
public class Exit extends ButtonCreater {
	public Exit(){
		super("ExitCommand");
		setButtonSettings(0.7 * Controller.SCENE_WIDTH, Controller.SCENE_HEIGHT / 2, 20);
	}
}
