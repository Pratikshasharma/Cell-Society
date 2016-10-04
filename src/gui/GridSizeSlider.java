package gui;

/**
 * 
 * @author Pratiksha Sharma
 *
 */
public class GridSizeSlider extends SliderCreater {
	public final String NUMBER_CELLS_SLIDER_TEXT = "Number Of Cells : ";
	
	public GridSizeSlider(int minValue, int maxValue, int defaultValue) {
		super(minValue, maxValue, defaultValue);
		createSlider(NUMBER_CELLS_SLIDER_TEXT,true);
	}

}
