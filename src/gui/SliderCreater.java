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

	public SliderCreater(int minValue, int maxValue, int defaultValue ){
		this.mySlider = new Slider(minValue,maxValue,defaultValue);
	}

	protected void createSlider(String myLabelText){
		//mySlider.setMajorTickUnit(10);
		mySliderCaption = new Label(myLabelText);
		mySliderValue = new Label(Double.toString(mySlider.getValue()));
		
		mySlider.valueProperty().addListener(new ChangeListener<Number>() {
			public void changed(ObservableValue<? extends Number> ov, Number old_val, Number new_val) {
				mySlider.setValue((double) new_val);
				mySliderValue.textProperty().setValue(String.valueOf((int) mySlider.getValue()));
			}
		});
	}

}
