package button;
import java.io.File;
import java.util.ResourceBundle;
import gui.MainGUI;
import javafx.scene.control.Button;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

//This entire file is part of my masterpiece.
//Pratiksha Sharma

/**
 * This is a Super class that creates buttons based on the String property passed in.
 * This implements inheritance principles learnt in the class. 
 * I have also used the Button.properties file to create the Labels for the buttons
 * I should have used CSS file to style the buttons instead of hard coding the styles.
 * 
 * @author pratiksha sharma
 * Creates Buttons on the GUI
 * Dependencies: Button.properties file , Controller, MainGUI.java 
 * Assumption: Assumes Button.properties file exists
 */

public class ButtonCreater {
	private ResourceBundle myResources;  
	protected Button myButton;

	/**
	 * Creates a Button with the label 'property' 
	 * @param property : tag to be read in Button.properties file
	 */
	public ButtonCreater(String property) {
		myResources= ResourceBundle.getBundle(MainGUI.DEFAULT_RESOURCE_PACKAGE + File.separator + "Button");
		String label = myResources.getString(property);
		myButton = new Button(label);
	}

	protected void setButtonSettings(double width, double height, double font_size){
		myButton.setLayoutY(height);
		myButton.setLayoutX(width);
		myButton.setFont(Font.font("Comic Sans", FontWeight.BOLD,font_size));
	}
	
	/**
	 * Accessed from MainGUI to add buttons on the Scene
	 * @return myButton: Button that is created 
	 */
	public Button getButton(){
		return myButton;
	}
}
