package gui;

/**
 * @author pratiksha sharma
 *
 */

public class SimulationSpeedSlider extends SliderCreater{
	public final String SPEED_SLIDER_TEXT = "Simulation Speed : ";
	
	public SimulationSpeedSlider(){
		super(GUIController.MIN_FRAMES_PER_SECOND,GUIController.MAX_FRAMES_PER_SECOND,GUIController.DEFAULT_FRAMES_PER_SECOND);
		createSlider(SPEED_SLIDER_TEXT,false);
	}

}
