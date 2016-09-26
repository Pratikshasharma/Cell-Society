package GUIPackage;

import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;


public class ButtonCreater {
	//public final String DEFAULT_RESOURCE_PACKAGE = "resources/Button.properties";
	//private ResourceBundle myResources;  
	private Button myButton;

	public ButtonCreater(String property, EventHandler<ActionEvent> handler) {
	//	myResources= ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE);
	//	String label = myResources.getString(property);
		
		this.myButton = new Button(property);
		if (handler != null){ myButton.setOnAction(handler);};
		//myButton.setText(label);
	}

	public Button getButton(){
		return this.myButton;
	}

	public void setButtonSettings(double width, double height, double font_size){
		myButton.setLayoutX(width);
		myButton.setLayoutY(height);
		myButton.setFont(Font.font("Comic Sans", FontWeight.BOLD,font_size));
	}
}
