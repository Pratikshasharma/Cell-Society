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
	
	public FireModel(String simName, String simAuthor, String simWidth, String simHeight, String probCatch,
			GenState tree, GenState burning, GenState emptyState) {
		super(simName, simAuthor, simWidth, simHeight);
		myProbCatch = Double.parseDouble(probCatch);
		myTree = tree;
		myBurning = burning;
		myEmptyState = emptyState;
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
