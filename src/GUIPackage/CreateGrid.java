package GUIPackage;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import CellPackage.Cell;
import CellPackage.State;
import javafx.scene.Group;
import javafx.scene.shape.Rectangle;

public class CreateGrid {
	State myCellState;
	private List<Cell> myCellList = new ArrayList<Cell>();
	private int CELL_WIDTH = 10;
	private int CELL_HEIGHT = 10;
	private Rectangle rectangle; 
	
	CreateGrid(){	
	}

	public Group createCellsList(){
		 State myCellState0 = new State("unhappy", Color.red);
		 State myCellState1 = new State("Satisfied", Color.blue);
		 State myCellState2 = new State("Empty", Color.white);
		 
		 Group root = new Group();
		
		 for ( int i = 1; i<=5;i++ ){
			 rectangle = new Rectangle(CELL_WIDTH,CELL_HEIGHT);
			 Cell myCell = new Cell(rectangle,myCellState0);
			 myCellList.add(myCell);
			 root.getChildren().add(myCell.getShape());
		 }
		 
		 for ( int i = 1; i<=5;i++ ){
			 rectangle = new Rectangle(CELL_WIDTH,CELL_HEIGHT);
			 Cell myCell = new Cell(rectangle,myCellState1);
			 myCellList.add(myCell);
			 System.out.println(myCellList.size());
			 root.getChildren().add(myCell.getShape());
		 }
		 
		 for ( int i = 1; i<=5;i++ ){
			 rectangle = new Rectangle(CELL_WIDTH,CELL_HEIGHT);
			 Cell myCell = new Cell(rectangle,myCellState2);
			 myCellList.add(myCell);
			 root.getChildren().add(myCell.getShape());
		 } 
		 
		 return root;
	}
	
}
