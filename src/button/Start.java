package button;

import gui.GUIController;
/**
 * @author pratiksha sharma
 */
public class Start extends ButtonClass{

	public Start(){
		super("StartSimulationCommand");	
		setButtonSettings(0.8 * GUIController.SCENE_HEIGHT, 0.75 * GUIController.SCENE_WIDTH, 14);
	}	
}
