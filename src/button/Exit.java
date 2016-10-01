package button;

import gui.GUIController;

public class Exit extends ButtonClass {
	public Exit(){
		super("ExitCommand");
		setButtonSettings(0.7 * GUIController.SCENE_WIDTH, GUIController.SCENE_HEIGHT / 2, 20);
	}

}
