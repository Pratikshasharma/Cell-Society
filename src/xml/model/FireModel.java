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

	@Override
	public int getMyFishTurnsToBreed() {
		return 0;
	}

	@Override
	public int getMySharkTurnsToBreed() {
		return 0;
	}

	@Override
	public int getMySharkTurnsToStarve() {
		return 0;
	}

	@Override
	public GenState getMyFish() {
		return null;
	}

	@Override
	public GenState getMyShark() {
		return null;
	}

	@Override
	public double getMySatisfaction() {
		return 0;
	}

	@Override
	public GenState getMyRace1() {
		return null;
	}

	@Override
	public GenState getMyRace2() {
		return null;
	}
}
