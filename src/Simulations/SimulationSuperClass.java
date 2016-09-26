package Simulations;
import CellPackage.Cell;



public abstract class SimulationSuperClass {
	protected Cell[][] myGrid;

	//protected genState myGenState; -- This holds the State and the percentages
	public SimulationSuperClass(Cell[][] myGrid){
		this.myGrid = myGrid;
	}

	/**
	 * Abstract Class for Calling Simulation
	 */

	public abstract  void updateSimulation();
}


	
