package xml.model;

/**
 * Object for Simulation to be created by XML
 * 
 * @author Ryan Anders
 */
public class SimModel {
    private String mySimName;
    private String mySimAuthor;
    private String mySimWidth;
    private String mySimHeight;

    public SimModel (String simName, String simAuthor, String simWidth, String simHeight) {
        mySimName = simName;
        mySimAuthor = simAuthor;
        mySimWidth = simWidth;
        mySimHeight = simHeight;
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
	public String getMySimWidth() {
		return mySimWidth;
	}

	/**
	 * @return mySimHeight
	 */
	public String getMySimHeight() {
		return mySimHeight;
	}
}
