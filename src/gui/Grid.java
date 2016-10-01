package gui;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class Grid implements GridInterface {
	private Shape myShape;
	private GridPane myGrid;
	private int numCellsWidth;
	private int numCellsHeight;

	public Grid(int numCellsWidth, int numCellsHeight) {
		myGrid = new GridPane();
		this.numCellsHeight = numCellsHeight;
		this.numCellsWidth = numCellsWidth;
	}

	public void createGrid(Paint[][] myGridColor) {
		myGrid.setGridLinesVisible(true);
		myGrid.setAlignment(Pos.BASELINE_LEFT);

		for (int i = 0; i <= myGridColor[0].length - 1; i++) {
			for (int j = 0; j <= myGridColor.length - 1; j++) {
				myShape = new Rectangle(GRID_WIDTH / numCellsWidth, GRID_HEIGHT / numCellsHeight);
				myShape.setFill(myGridColor[i][j]);
				myGrid.add(myShape, i, j);
			}
		}
	}

	private boolean checkNodeIDNull(Node tempNode) {
		return (GridPane.getColumnIndex(tempNode) != null || GridPane.getRowIndex(tempNode) != null);
	}

	public void changeMyGridColor(int row, int col, Paint color) {
		for (Node node : myGrid.getChildren()) {
			if (checkNodeIDNull(node)) {
				if (GridPane.getColumnIndex(node) == col && GridPane.getRowIndex(node) == row) {
					((Rectangle) node).setFill(color);
				}
			}
		}
	}

	public GridPane getGrid() {
		return this.myGrid;
	}

}
