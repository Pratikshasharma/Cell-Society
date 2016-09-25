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
    
    public SimModel (String simName, String simAuthor, String simWidth, String simHeight) {
        mySimName = simName;
        mySimAuthor = simAuthor;
        mySimWidth = Integer.parseInt(simWidth);
        mySimHeight = Integer.parseInt(simHeight);
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
	
	//fire
	public abstract double getMyProbCatch();
	
	public abstract GenState getMyTree();
	
	public abstract GenState getMyBurning();
	
	public abstract GenState getMyEmptyState();

	//predprey
	public abstract int getMyFishTurnsToBreed();
	
	public abstract int getMySharkTurnsToBreed();

	public abstract int getMySharkTurnsToStarve();
	
	public abstract GenState getMyFish();

	public abstract GenState getMyShark();
	
	//Segregation
	public abstract double getMySatisfaction();
	
	public abstract GenState getMyRace1();
	
	public abstract GenState getMyRace2();

}
