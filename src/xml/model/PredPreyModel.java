package xml.model;

/**
 * Object for initialization parameters of a Predator-Prey simulation
 * 
 * @author Ryan Anders
 */
public class PredPreyModel extends SimModel {

	private int myFishTurnsToBreed;
	private int mySharkTurnsToBreed;
	private int mySharkTurnsToStarve;
	private GenState myFish;
	private GenState myShark;
	private GenState myEmptyState;
	
	public PredPreyModel(String[] genParams,
			String fishTurnsToBreed, String sharkTurnsToBreed, String sharkTurnsToStarve,
			GenState[] genStates) {
		super(genParams);
		myFishTurnsToBreed = Integer.parseInt(fishTurnsToBreed);
		mySharkTurnsToBreed = Integer.parseInt(sharkTurnsToBreed);
		mySharkTurnsToStarve = Integer.parseInt(sharkTurnsToStarve);
		myFish = genStates[0];
		myShark = genStates[1];
		myEmptyState = genStates[2];
	}

	/**
	 * @return myFishTurnsToBreed
	 */
	public int getMyFishTurnsToBreed() {
		return myFishTurnsToBreed;
	}

	/**
	 * @return mySharkTurnsToBreed
	 */
	public int getMySharkTurnsToBreed() {
		return mySharkTurnsToBreed;
	}

	/**
	 * @return mySharkTurnsToStarve
	 */
	public int getMySharkTurnsToStarve() {
		return mySharkTurnsToStarve;
	}
	
	/**
	 * @return myFish
	 */
	public GenState getMyFish() {
		return myFish;
	}

	/**
	 * @return myShark
	 */
	public GenState getMyShark() {
		return myShark;
	}

	/**
	 * @return myEmptyState
	 */
	public GenState getMyEmptyState() {
		return myEmptyState;
	}

}
