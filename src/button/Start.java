package button;

import gui.Controller;

/** Creates Start Simulation Button on the Scene
 * @author pratiksha sharma
 * Assumption: Assumes ButtonClass has setButonSettings() method
 * Dependencies: ButtonClass (Super Class)
 */
public class Start extends ButtonCreater{

	public Start(){
		super("StartSimulationCommand");	
		setButtonSettings(0.8 * Controller.SCENE_HEIGHT, 0.75 * Controller.SCENE_WIDTH, 14);
	}	
}
