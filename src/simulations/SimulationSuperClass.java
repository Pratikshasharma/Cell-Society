package simulations;
import cellpackage.Cell;



public abstract class SimulationSuperClass {

	//protected genState myGenState; -- This holds the State and the percentages
	//	public SimulationSuperClass(Cell[][] myGrid){
	//		this.myGrid = myGrid;
	//	}

	private Cell[][] myGrid;

	public SimulationSuperClass() {
	}

	public SimulationSuperClass(Cell[][] grid){
		myGrid = grid;
	}

	//	/**
	//	 * Abstract Class for Calling Simulation
	//	 */
	//
	public Cell[][] getGrid(){
		return myGrid;
	}

	public abstract void updateSimulation();

	public void printGrid(){
		for (int i = 0; i < myGrid.length;i++){
			System.out.println();
			for(int j = 0 ; j<myGrid[i].length; j++){
				System.out.print(myGrid[i][j].getCellCurrentState().getStateID());
			}
		}
	}
}



