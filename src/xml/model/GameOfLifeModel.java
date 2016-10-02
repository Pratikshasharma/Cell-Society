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
	
	public GameOfLifeModel(String[] genParams, GenState[] genStates) {
		super(genParams);
		myFullState = genStates[0];
		myEmptyState = genStates[1];
	}

	/**
	 * @return myFullState
	 */
	@Override
	public GenState getMyFullState() {
		return myFullState;
	}

	/**
	 * @return myEmptyState
	 */
	@Override
	public GenState getMyEmptyState() {
		return myEmptyState;
	}
}
