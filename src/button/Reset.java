package button;

import gui.Controller;

/** Creates Reset Button
 * @author pratiksha sharma
 * * Assumption: Assumes ButtonClass has setButonSettings() method
 * Dependencies: ButtonClass (Super Class)
 */
public class Reset extends ButtonCreater {
	public Reset(){
		super("ResetSimulationCommand");
		setButtonSettings(0.8 * Controller.SCENE_HEIGHT, 0.85 * Controller.SCENE_WIDTH, 14);
	}
}

