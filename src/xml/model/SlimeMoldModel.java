package xml.model;
 /**
  * Object for initialization parameters of a Slime Mold simulation
  * 
  * @author Ryan Anders
  */
public class SlimeMoldModel extends SimModel {
	private GenState myMoldState;
	private GenState myEmptyState;
	
	public SlimeMoldModel() {
	}
	
	public SlimeMoldModel(String[] genParams, GenState[] genStates) {
		super(genParams);
		myMoldState = genStates[0];
		myEmptyState = genStates[1];
	}
	
	/**
	 * @return myMoldState
	 */
	@Override
	public GenState getMyMoldState() {
		return myMoldState;
	}

	/**
	 * @return myEmptyState
	 */
	@Override
	public GenState getMyEmptyState() {
		return myEmptyState;
	}
	
}
