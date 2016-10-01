package button;

import gui.GUIController;

public class OpenFile extends ButtonClass{

	public OpenFile(){
		super("ChooseFileCommand");
		setButtonSettings( 0.4*GUIController.SCENE_WIDTH, GUIController.SCENE_HEIGHT /2, 20);
	}	
}