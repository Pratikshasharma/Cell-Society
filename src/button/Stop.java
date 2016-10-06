package button;

import gui.Controller;

/** Creates Stop Button on the Scene
 * @author pratiksha sharma
 *  Assumption: Assumes ButtonClass has setButonSettings() method
 * Dependencies: ButtonClass (Super Class)
 */
public class Stop extends ButtonCreater{

	public Stop(){
		super("StopSimulationCommand");
		setButtonSettings(0.8 * Controller.SCENE_HEIGHT, 0.7 * Controller.SCENE_WIDTH, 14);
	}

}
