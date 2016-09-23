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
	
	public SegregationModel(String simName, String simAuthor, String simWidth, String simHeight,
						String satisf, GenState race1, GenState race2, GenState emptyState) {
		super(simName, simAuthor, simWidth, simHeight);
		mySatisfaction = Double.parseDouble(satisf);
		myRace1 = race1;
		myRace2 = race2;
		myEmptyState = emptyState;
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
