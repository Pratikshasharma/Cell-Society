package simulations;

import java.util.ArrayList;
import java.util.Random;

import cellpackage.Cell;
import cellpackage.State;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

import java.util.Random;

public class Tester {

	private Cell[][] myGrid;
	private State state1;
	private State state2;
	private State state3;

	private void makeState1(){
		state1 = new State("EMPTY", Paint.valueOf("WHITE"), 0);
		//System.out.println(state1.getStateID());
	}


	private void makeState2(){
		state2 = new State("FISH", Paint.valueOf("GREEN"), 1);
		//System.out.println(state2.getStateID());
	}

	private void makeState3(){
		state3 = new State("SHARK", Paint.valueOf("YELLOW"), 2);
	}

	private void makeGrid(int size){
		makeState1();
		makeState2();
		makeState3();
		Random rand = new Random();
		myGrid = new Cell[size][size];
		for (int i = 0; i<size; i++){
			for(int j = 0; j<size; j++){
				
				int luck = rand.nextInt(10);
				if (luck<5){
					myGrid[i][j] = new Cell(new Rectangle(2, 2), state2);
				}
				else if (luck >= 5 && luck <8) {
					//System.out.println("setting tree");
					myGrid[i][j] = new Cell(new Rectangle(2,2), state3);
				}
				else {
					myGrid[i][j] = new Cell(new Rectangle(2,2), state1);
				}
			}
		}

	}

	public Cell[][] getGrid(){
		return myGrid;
	}

//	public static void main(String[] args){
//		Tester t = new Tester();
//		t.makeGrid(3);
//		Cell[][] grid = t.getGrid();
//		int fishbreed = 5;
//		int sharkbreed = 7;
//		int sharkdie = 9;
//		PredPrey sf = new PredPrey(grid, fishbreed,sharkbreed,sharkdie);
//		System.out.println("Initial Grid");
//		sf.printGrid();
//		for(int i = 0; i<4; i++){
//			sf.updateSimulation();
//			System.out.println("");
//			sf.printGrid();
//			System.out.println("");
//			System.out.printf("grid after step %d", i+1);
//		}
//
//	}

}