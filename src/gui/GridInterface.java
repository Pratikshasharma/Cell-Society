package gui;

/**
 * Interface for creation of Grid 
 * @author pratiksha sharma

 */

import javafx.scene.paint.Paint;

public interface GridInterface {
	public static final int GRID_WIDTH = 400;
	public static final int GRID_HEIGHT = 400;

	/**
	 * Creates Grid based on the Color Array provided 
	 * @param myGridColor
	 */
	public default void createGrid(Paint[][] myGridColor) {
	}
	
/**
 * Changes Color of a cell in the specific location of Grid
 * @param rowIndex
 * @param colIndex
 * @param color
 */
	public default void updateGrid(int rowIndex, int colIndex, Paint color) {

	}
}
