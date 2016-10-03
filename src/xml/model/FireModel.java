package xml.model;

/**
 * Object for initialization parameters of a Spreading of Fire simulation
 * 
 * @author Ryan Anders
 */
public class FireModel extends SimModel {

	private double myProbCatch;
	private GenState myTree;
	private GenState myBurning;
	private GenState myEmptyState;
	
	public FireModel(String[] genParams, double probCatch, GenState[] genStates) {
		super(genParams);
		myProbCatch = probCatch;
		myTree = genStates[0];
		myBurning = genStates[1];
		myEmptyState = genStates[2];
	}

	 public FireModel() {
	}

	/**
	  * @return myProbCatch
	  */
	public double getMyProbCatch() {
		return myProbCatch;
	}
	
	/**
	 * @return myTree
	 */
	public GenState getMyTree() {
		return myTree;
	}

	/**
	 * @return myBurning
	 */
	public GenState getMyBurning() {
		return myBurning;
	}

	/**
	 * @return myEmptyState
	 */
	public GenState getMyEmptyState() {
		return myEmptyState;
	}

}
