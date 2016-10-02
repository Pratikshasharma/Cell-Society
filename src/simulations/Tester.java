package Simulations;

import java.util.Random;

import CellPackage.Cell;
import CellPackage.State;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;


public class Tester {

	private Cell[][] myGrid;
	private State state1;
	private State state2;
	private State state3;

	private void makeState1(){
		state1 = new State("NOAMEOBE", Paint.valueOf("BLUE"), 0);
		//System.out.println(state1.getStateID());
	}


	private void makeState2(){
		state2 = new State("AMEOBE", Paint.valueOf("GREEN"), 1);
		//System.out.println(state2.getStateID());
	}

	private void makeState3(){
		state3 = new State("SHARK", Paint.valueOf("YELLOW"), 2);
	}

	private void makeGrid(int size){
		makeState1();
		makeState2();
		//makeState3();
		Random rand = new Random();
		myGrid = new Cell[size][size];
		for (int i = 0; i<size; i++){
			for(int j = 0; j<size; j++){

				int luck = rand.nextInt(10);
				if (luck<=5){
					myGrid[i][j] = new Cell(new Rectangle(2, 2), state2);
				}
				/*
				else if (luck >= 5 && luck <8) {
					//System.out.println("setting tree");
					myGrid[i][j] = new Cell(new Rectangle(2,2), state3);
				}
				 */
				else {
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
		t.makeGrid(3);
		Cell[][] grid = t.getGrid();
		SlimeMold sf = new SlimeMold(grid, t.state1, t.state2);
		System.out.println("Initial Grid");
		sf.printGrid();
		sf.updateSimulation();
		System.out.println("");
		System.out.println("Grid after 1 step");
		sf.printGrid();
	}

}