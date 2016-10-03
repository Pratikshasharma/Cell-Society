package gui;

import java.util.HashMap;
import java.util.Map;

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

	public Map<String,Integer> createGrid(Paint[][] myGridColor, Map<String,Paint> myStateColorMap) {
		Map<String,Integer> myStateNumberMap = new HashMap<String,Integer>();
		myGrid.setStyle("-fx-border: 15px solid; -fx-border-color: black; -fx-padding: 10");
		myGrid.setGridLinesVisible(true);
		myGrid.setAlignment(Pos.BASELINE_LEFT);
		
		for(String key: myStateColorMap.keySet()){
			int counter = 0;
			for (int i = 0; i <= numCellsWidth -1 ; i++) {
				for (int j = 0; j <= numCellsHeight -1; j++){
					myShape = new Rectangle(GRID_WIDTH / numCellsWidth, GRID_HEIGHT / numCellsHeight);
					myShape.setFill(myGridColor[i][j]);
					myGrid.add(myShape, i, j);
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
