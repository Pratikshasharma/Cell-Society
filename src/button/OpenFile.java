package button;

import gui.Controller;

/** Creates openFile Button 
 * @author pratiksha sharma
 * Assumption: Assumes ButtonClass has setButonSettings() method
 * Dependencies: ButtonClass (Super Class)
 */
public class OpenFile extends ButtonCreater{

	public OpenFile(){
		super("ChooseFileCommand");
		setButtonSettings( 0.4*Controller.SCENE_WIDTH, Controller.SCENE_HEIGHT /2, 20);
	}	
}
