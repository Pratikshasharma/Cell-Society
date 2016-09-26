package Simulations;
import CellPackage.Cell;



public abstract class SimulationSuperClass {

	//protected genState myGenState; -- This holds the State and the percentages
//	public SimulationSuperClass(Cell[][] myGrid){
//		this.myGrid = myGrid;
//	}
	public SimulationSuperClass() {
		
	}
	
//	/**
//	 * Abstract Class for Calling Simulation
//	 */
//
	public abstract Cell[][] getGrid();
	
	public abstract  void updateSimulation();
	
	public abstract void printGrid();
}


	
