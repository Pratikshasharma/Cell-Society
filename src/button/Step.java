package button;

import gui.GUIController;
/**
 * @author pratiksha sharma
 */
public class Step extends ButtonClass{

	public Step(){
		super("StepSimulationCommand");
		setButtonSettings(0.8 * GUIController.SCENE_HEIGHT, 0.8 * GUIController.SCENE_WIDTH, 14);
	}
}
