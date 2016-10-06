package gui;

/** Purpose: Add slider to adjust simulation speed in the Scene
 * @author pratiksha sharma
 * Dependencies: SliderCreater Super Class
 *
 */

public class SimulationSpeedSlider extends SliderCreater{
	public final String SPEED_SLIDER_TEXT = "Simulation Speed : ";
	
	public SimulationSpeedSlider(){
		super(Controller.MIN_FRAMES_PER_SECOND,Controller.MAX_FRAMES_PER_SECOND,Controller.DEFAULT_FRAMES_PER_SECOND);
		createSlider(SPEED_SLIDER_TEXT);
	}
}
