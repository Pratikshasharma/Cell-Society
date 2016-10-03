package button;

import gui.GUIController;
/**
 * @author pratiksha sharma

 */
public class Stop extends ButtonClass{

	public Stop(){
		super("StopSimulationCommand");
		setButtonSettings(0.8 * GUIController.SCENE_HEIGHT, 0.7 * GUIController.SCENE_WIDTH, 14);
	}

}
