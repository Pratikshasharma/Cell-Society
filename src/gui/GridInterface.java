package gui;

import javafx.scene.paint.Paint;

public interface GridInterface {
	public static final int GRID_WIDTH = 400;
	public static final int GRID_HEIGHT = 400;

	public default void createGrid(Paint[][] myGridColor) {

	}

	public default void updateGrid(int rowIndex, int colIndex, Paint color) {

	}
}
