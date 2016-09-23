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
	
	public PredPreyModel(String simName, String simAuthor, String simWidth, String simHeight,
			String fishTurnsToBreed, String sharkTurnsToBreed, String sharkTurnsToStarve,
			GenState fish, GenState shark, GenState emptyState) {
		super(simName, simAuthor, simWidth, simHeight);
		myFishTurnsToBreed = Integer.parseInt(fishTurnsToBreed);
		mySharkTurnsToBreed = Integer.parseInt(sharkTurnsToBreed);
		mySharkTurnsToStarve = Integer.parseInt(sharkTurnsToStarve);
		myFish = fish;
		myShark = shark;
		myEmptyState = emptyState;
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
