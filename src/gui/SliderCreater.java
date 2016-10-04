package gui;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;

/**
 * @author pratiksha sharma
 *
 */

public  class SliderCreater {
	protected Slider mySlider;
	protected Label mySliderCaption;
	protected Label mySliderValue;
	private GUIController myGUIController;

	public SliderCreater(int minValue, int maxValue, int defaultValue ){
		this.mySlider = new Slider(minValue,maxValue,defaultValue);
		myGUIController = new GUIController();
	}

	protected void createSlider(String myLabelText, boolean changeGridSize){
		mySliderCaption = new Label(myLabelText);
		mySliderValue = new Label(Double.toString(mySlider.getValue()));
		mySlider.setMajorTickUnit(2);
		
		mySlider.valueProperty().addListener(new ChangeListener<Number>() {
			public void changed(ObservableValue<? extends Number> ov, Number old_val, Number new_val) {
				mySlider.setValue((double) new_val);
				mySliderValue.textProperty().setValue(String.valueOf((int) mySlider.getValue()));
				if(changeGridSize){
					mySlider.setMajorTickUnit(5);
					myGUIController.initializeGrid((int) mySlider.getValue(), (int) mySlider.getValue());
				}
			}
		});
	}

	public Slider getMySlider(){
		return this.mySlider;
	}

}
