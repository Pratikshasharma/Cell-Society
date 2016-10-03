package button;
import java.io.File;
import java.util.ResourceBundle;

import gui.MainGUI;
import javafx.scene.control.Button;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * @author pratiksha sharma
 *
 */

public  class ButtonClass {
	private ResourceBundle myResources;  
	protected Button myButton;

	public ButtonClass(String property) {
		this.myResources= ResourceBundle.getBundle(MainGUI.DEFAULT_RESOURCE_PACKAGE + File.separator + "Button");
		String label = myResources.getString(property);
		this.myButton = new Button(label);
	}

	protected void setButtonSettings(double width, double height, double font_size){
		myButton.setLayoutY(height);
		myButton.setLayoutX(width);
		myButton.setFont(Font.font("Comic Sans", FontWeight.BOLD,font_size));
	}
	
	public Button getButton(){
		return this.myButton;
	}
	

}
