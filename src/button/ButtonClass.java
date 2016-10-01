package button;
import java.util.ResourceBundle;
import javafx.scene.control.Button;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * @author pratiksha sharma
 *
 */

public  class ButtonClass {
	public final String BUTTON_RESOURCE_PACKAGE = "resources/Button";
	private ResourceBundle myResources;  
	
	protected Button myButton;

	public ButtonClass(String property) {
		this.myResources= ResourceBundle.getBundle(BUTTON_RESOURCE_PACKAGE);
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
