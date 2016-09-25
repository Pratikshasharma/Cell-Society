package xml.model;

/**
 * Object for initialization parameters of a Game of Life simulation
 * 
 * @author Ryan Anders
 */
public class GameOfLifeModel extends SimModel {
	private GenState myFullState;
	private GenState myEmptyState;
	
	public GameOfLifeModel() {
		
	}
	
	public GameOfLifeModel(String simName, String simAuthor, String simWidth, String simHeight,
						GenState fullState, GenState emptyState) {
		super(simName, simAuthor, simWidth, simHeight);
		myFullState = fullState;
		myEmptyState = emptyState;
	}

	/**
	 * @return myFullState
	 */
	public GenState getMyFullState() {
		return myFullState;
	}

	/**
	 * @return myEmptyState
	 */
	public GenState getMyEmptyState() {
		return myEmptyState;
	}

	@Override
	public double getMyProbCatch() {
		return 0;
	}

	@Override
	public GenState getMyTree() {
		return null;
	}

	@Override
	public GenState getMyBurning() {
		return null;
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
