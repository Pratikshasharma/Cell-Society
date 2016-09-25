package Simulations;

import java.util.List;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import CellPackage.Cell;
import CellPackage.State;

public class Tester {

	private Cell[][] myGrid;
	private State state1;
	private State state2;
	private State state3;

	private void makeState1(){
		state1 = new State("EMPTY", Paint.valueOf("YELLOW"), 0);
		//System.out.println(state1.getStateID());
	}


	private void makeState2(){
		state2 = new State("TREE", Paint.valueOf("GREEN"), 1);
		//System.out.println(state2.getStateID());
	}

	private void makeState3(){
		state3 = new State("BURNING", Paint.valueOf("RED"), 2);
	}

	private void makeGrid(int size){
		makeState1();
		makeState2();
		makeState3();
		myGrid = new Cell[size][size];
		for (int i = 0; i<size; i++){
			for(int j = 0; j<size; j++){
				//System.out.println(" row is " + i + " column is " + j);
				if (i%2 == 0 || j%2 ==0){
					//System.out.println("Setting fire");
					myGrid[i][j] = new Cell(new Rectangle(2, 2), state2);
				}
				else{
					//System.out.println("setting tree");
					myGrid[i][j] = new Cell(new Rectangle(2,2), state1);
				}
			}
		}

	}

	public Cell[][] getGrid(){
		return myGrid;
	}

	public static void main(String[] args){
		Tester t = new Tester();
		t.makeGrid(6);
		Cell[][] grid = t.getGrid();
		double pc = 1.0;
		GameOfLife sf = new GameOfLife(grid);
		System.out.println("Initial Grid");
		sf.printGrid();
		//for(int i = 0; i<3; i++){
			sf.updateSimulation();
		//}
		System.out.println("Final Grid after one step");
		sf.printGrid();

	}

}
