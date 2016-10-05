package gui;

import java.util.HashMap;
import java.util.Map;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

/** Creates Grid in the Scene
 * Dependencies: GridPane, int numCellsWidth, int numCellsHeight
 * @author pratiksha sharma
 */

public class Grid implements GridInterface {
	private Shape myShape;
	private GridPane myGrid;
	private int numberOfColumns;
	private int numberOfRows;

	/**
	 * Constructor: Creates an instance of Grid Object that contains Shape
	 * @param  noOfColumns: Number of Columns 
	 * @param noOfRows: number of Rows
	 */
	public Grid(int noOfColumns, int noOfRows) {
		myGrid = new GridPane();
		this.numberOfRows = noOfRows;
		this.numberOfColumns = noOfColumns;
	}

	/**
	 * Creates Grid based on the Color and the Map of State and Color provided 
	 * Also counts the population of different states initially
	 * @param myGridColor : Color[][] of the Cells to be represented
	 * @param myStateColorMap : State of Cells Mapped with the Color
	 * @return Map with State as Key and Population as Value when Cells are initialized in the beginning
	 */
	public Map<String,Integer> createGrid(Paint[][] myGridColor, Map<String,Paint> myStateColorMap) {
		Map<String,Integer> myStateNumberMap = new HashMap<String,Integer>();
		styleGrid();
		for(String key: myStateColorMap.keySet()){
			int counter = 0;
			for (int i = 0; i <= numberOfRows -1; i++) {
				for (int j = 0; j <= numberOfColumns -1; j++){
					myShape = new Rectangle(GRID_WIDTH / numberOfRows, GRID_HEIGHT / numberOfColumns);
					myShape.setFill(myGridColor[i][j]);
					myGrid.add(myShape, j, i);
					if(myGridColor[i][j].equals(myStateColorMap.get(key))){
						counter+=1;	
					}
				}
			}
			if(!myStateNumberMap.containsKey(key)){
				myStateNumberMap.put(key, counter);
			}
		}
		return myStateNumberMap;
	}

	private boolean checkNodeIDNull(Node tempNode) {
		return (GridPane.getColumnIndex(tempNode) != null || GridPane.getRowIndex(tempNode) != null);
	}

	/**
	 * Changes Color of the Shape in the index
	 * @param row row index of the Shape
	 * @param col Column index of the Shape
	 * @param color Color to be changed into
	 */
	public void changeMyGridColor(int row, int col, Paint color) {
		for (Node node : myGrid.getChildren()) {
			if (checkNodeIDNull(node)) {
				if (GridPane.getColumnIndex(node) == col && GridPane.getRowIndex(node) == row) {
					((Rectangle) node).setFill(color);
				}
			}
		}
	}

	/**
	 * 
	 * @return GridPane to be displayed in the Scene which represents the Cells[][] in back end
	 */
	public GridPane getGrid() {
		return this.myGrid;
	}

	private void styleGrid(){
		myGrid.setStyle("-fx-border-style: solid; -fx-border-width: 4px; -fx-padding: 10");
		myGrid.setGridLinesVisible(true);
		myGrid.setAlignment(Pos.BASELINE_LEFT);
	}

}