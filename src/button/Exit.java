package button;

import gui.Controller;

/** Creates Exit Button 
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
