package Simulations;
import CellPackage.Cell;



public abstract class SimulationSuperClass {
	protected Cell[][] myCells;

	//protected genState myGenState; -- This holds the State and the percentages
	public SimulationSuperClass(Cell[][] myCells){
		this.myCells = myCells;
	}

	/**
	 * Abstract Class for Calling Simulation
	 */

	public abstract  void updateSimulation();
}


	
