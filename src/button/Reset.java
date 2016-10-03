package button;

import gui.GUIController;
/**
 * @author pratiksha sharma
 */
public class Reset extends ButtonClass {
	public Reset(){
		super("ResetSimulationCommand");
		setButtonSettings(0.8 * GUIController.SCENE_HEIGHT, 0.85 * GUIController.SCENE_WIDTH, 14);
	}
}

