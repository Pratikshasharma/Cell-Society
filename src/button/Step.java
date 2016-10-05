package button;

import gui.Controller;

/** Creates Step button on the Scene
 * @author pratiksha sharma
 * Assumption: Assumes ButtonClass has setButonSettings() method
 * Dependencies: ButtonClass (Super Class)
 */
public class Step extends ButtonClass{

	public Step(){
		super("StepSimulationCommand");
		setButtonSettings(0.8 * Controller.SCENE_HEIGHT, 0.8 * Controller.SCENE_WIDTH, 14);
	}
}
