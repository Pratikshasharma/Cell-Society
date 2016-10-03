package xml.model;

/**
 * Object for Simulation to be created by XML
 * 
 * @author Ryan Anders
 */
public abstract class SimModel {
    private String mySimName;
    private String mySimAuthor;
    private int mySimWidth;
    private int mySimHeight;

    public SimModel (){
    	
    }
    
    public SimModel (String[] genParams) {
        mySimName = genParams[0];
        mySimAuthor = genParams[1];
        mySimWidth = Integer.parseInt(genParams[2]);
        mySimHeight = Integer.parseInt(genParams[3]);
    }

	/**
	 * @return mySimName
	 */
	public String getMySimName() {
		return mySimName;
	}

	/**
	 * @return mySimAuthor
	 */
	public String getMySimAuthor() {
		return mySimAuthor;
	}

	/**
	 * @return mySimWidth
	 */
	public int getMySimWidth() {
		return mySimWidth;
	}

	/**
	 * @return mySimHeight
	 */
	public int getMySimHeight() {
		return mySimHeight;
	}
	
	//gameoflife
	
	/**
	 * @return Full State (game of life)
	 */
	public GenState getMyFullState() {
		return null;
	}
	
	/**
	 * @return Empty State
	 */
	public GenState getMyEmptyState() {
		return null;
	}
	
	//fire
	
	/**
	 * @return Probability of Catching Fire (Spreading of Fire)
	 */
	public double getMyProbCatch() {
		return 0;
	}
	
	/**
	 * @return Tree State (Spreading of Fire)
	 */
	public GenState getMyTree() {
		return null;
	}
	
	/**
	 * @return Burning State (Spreading of Fire)
	 */
	public GenState getMyBurning() {
		return null;
	}

	//predprey
	
	/**
	 * @return Turns it takes for Fish to breed (Predator-Prey)
	 */
	public int getMyFishTurnsToBreed() {
		return 0;
	}
	
	/**
	 * @return Turns it takes for Sharks to breed (Predator-Prey)
	 */
	public int getMySharkTurnsToBreed() {
		return 0;
	}
	
	/**
	 * @return Turns it takes for Sharks to starve (Predator-Prey)
	 */
	public int getMySharkTurnsToStarve() {
		return 0;
	}
	
	/**
	 * @return Fish State (Predator-Prey)
	 */
	public GenState getMyFish() {
		return null;
	}

	/**
	 * @return Shark State (Predator-Prey)
	 */
	public GenState getMyShark() {
		return null;
	}
	
	//Segregation
	
	/**
	 * @return Satisfaction requirement (Segregation)
	 */
	public double getMySatisfaction() {
		return 0;
	}
	
	/**
	 * @return Race1 State (Segregation)
	 */
	public GenState getMyRace1() {
		return null;
	}
	
	/**
	 * @return Race2 State (Segregation)
	 */
	public GenState getMyRace2() {
		return null;
	}

}
