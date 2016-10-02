package xml.model;

/**
 * Object for initialization parameters of a Segregation simulation
 * 
 * @author Ryan Anders
 */
public class SegregationModel extends SimModel {

	private double mySatisfaction;
	private GenState myRace1;
	private GenState myRace2;
	private GenState myEmptyState;
	
	public SegregationModel(String[] genParams,	String satisf, GenState[] genStates) {
		super(genParams);
		mySatisfaction = Double.parseDouble(satisf);
		myRace1 = genStates[0];
		myRace2 = genStates[1];
		myEmptyState = genStates[2];
	}

	/**
	 * @return mySatisfaction
	 */
	public double getMySatisfaction() {
		return mySatisfaction;
	}

	/**
	 * @return myRace1
	 */
	public GenState getMyRace1() {
		return myRace1;
	}

	/**
	 * @return myRace2
	 */
	public GenState getMyRace2() {
		return myRace2;
	}

	/**
	 * @return myEmptyState
	 */
	public GenState getMyEmptyState() {
		return myEmptyState;
	}
}
