package xml.model;

/**
 * Object for initialization parameters of a Game of Life simulation
 * 
 * @author Ryan Anders
 */
public class GameOfLifeModel extends SimModel {
	private GenState myFullState;
	private GenState myEmptyState;
	
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
}
