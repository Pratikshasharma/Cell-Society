package gui;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/** Purpose: Add Simulation Title Text Object in Scene
 * @author pratiksha sharma
 *
 */

public class SimulationTitle {
private Text myText;

public SimulationTitle(String mySimulationName){
	 myText = new Text(mySimulationName);
	 setTextSettings();
}

private void setTextSettings(){
	 myText.setFont(Font.font("Comic Sans", FontWeight.BOLD, 20));
	 myText.setFill(Color.CHOCOLATE);
}
/**
 * Returns the Text Object 
 */
public Text getMySimulationTitle(){
    return this.myText;
}
}
