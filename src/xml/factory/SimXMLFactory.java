package xml.factory;

import xml.model.GenState;
import xml.model.SimModel;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javafx.scene.paint.Color;

import java.util.Objects;


/**
 * An XMLFactory that gives back a Simulation object.
 *
 * @author Rhondu Smithwick
 * @author Robert Duvall
 * @author Ryan Anders
 */
public abstract class SimXMLFactory {
    private String mySimType;


    /**
     * Create a factory for making Simulation objects.  
     */
    protected SimXMLFactory (String simType) {
        mySimType = simType;
    }

    /**
     * @return the type of Simulation this file represents
     */
    public String getSimType () {
        return mySimType;
    }

    /**
     * @return the SimModel object that details the actual Simulation contained in this XML File.
     */
    public abstract SimModel getSim (Element root) throws XMLFactoryException;
    
    /**
     * @return true if the SimType in the xml file matches mySimType in the factory object
     */
    protected boolean isValidFile (Element root) {
        return Objects.equals(getAttribute(root, "SimType"), getSimType());
    }
    
    /**
     * extract the general parameters from an xml document - SimName, SimAuthor, SimWidth, SimHeight
     * 
     * @param root node
     * @return String[] containing general parameters
     * @throws XMLFactoryException
     */
    protected String[] getSimGenParams(Element root) throws XMLFactoryException {
    	NodeList genpList = root.getElementsByTagName("genparam");
    	
    	if (genpList.getLength() == 0) {
    		throw new XMLFactoryException("node 'genparam' not found--XML file does not contain name/author/width/height");
    	}
    	
    	Node genpNode = genpList.item(0);
    	
    	if (genpNode.getNodeType() == Node.ELEMENT_NODE) {
    		Element genpElement = (Element) genpNode; 
    		String simName = getTextValue(genpElement, "simname");
    		String simAuthor = getTextValue(genpElement, "simauthor");
    		String simWidth = getTextValue(genpElement, "simwidth");
    		String simHeight = getTextValue(genpElement, "simheight");
    		
    		String[] genParams = new String[]{simName,simAuthor,simWidth,simHeight};
    		checkGenParams(genParams);
    		return genParams;
    	}
    	return null;
    }
    
    /**
     * extract states from an xml document
     * @param root node
     * @return Array of GenStates containing the states required for the simulation
     * @throws XMLFactoryException
     */
    protected GenState[] getSimGenStates(Element root) throws XMLFactoryException {
    	NodeList nList = root.getElementsByTagName("state");
    	GenState[] states = new GenState[nList.getLength()];
    	double checkPercentages = 0;
    	
    	for (int temp = 0; temp < nList.getLength(); temp++) {
    		Node nNode = nList.item(temp);
    		if (nNode.getNodeType() == Node.ELEMENT_NODE) {
    			Element eElement = (Element) nNode;
    			valueCheck(eElement,temp);
    			states[temp] = new GenState(getTextValue(eElement, "statename"), getTextValue(eElement, "statecolor"),
    					getTextValue(eElement, "percentage"), eElement.getAttribute("id"));    			
    			
    			checkPercentages += Double.parseDouble(getTextValue(eElement, "percentage"));
    		}
    	}
    	
    	if (checkPercentages != 1) {
    		throw new XMLFactoryException("State Percentages add up to %.2f, must add up to 1", checkPercentages);
    	}
    	
    	return states;
    }
	
	/**
	 * Check to see if an extracted parameter is valid
	 * 
	 * @param param String - extracted parameter
	 * @param type String - type of extracted parameter ("int" or "double")
	 * @param description - description of extracted parameter (i.e. probCatch, satisfaction)
	 * @param checkPos - 'true' if the value being checked needs to be positive
	 * @throws XMLFactoryException
	 */
	protected void checkPositiveNumberParameter(String param, String type, String description, boolean checkPos) throws XMLFactoryException {
		
		if (param.equals("")) {
			throw new XMLFactoryException("%s value was not found", description);
		}
		
		int i = 0;
		double d = 0;
		
		try {
			if (type.equals("int")) {
				i = Integer.parseInt(param);
			} else if (type.equals("double")) {
				type = type + " less than 1";
				d = Double.parseDouble(param);
			} else {
				throw new XMLFactoryException("type \"%s\" not recognized for parameter check");
			}
		} catch (NumberFormatException e) {
			throw new XMLFactoryException("%s value, \"%s\" is not valid. Must be a positive %s", description, param, type);
		}
		
		if (checkPos) {
			if (i<0 || d<0 || d>1) {
				throw new XMLFactoryException("%s value, \"%s\" is not valid. Must be a positive %s", description, param, type);
			}
		}
	}
    
    /**
     * Get the value of an attribute.
     */
    protected String getAttribute (Element root, String attributeName) {
        return root.getAttribute(attributeName);
    }

    /**
     * Get the text value of a node.
     * <p>
     * Assumes you want the textValue of the first node with this tagName.
     * </p>
     * 
     */
    protected String getTextValue (Element root, String tagName) {
        NodeList nodeList = root.getElementsByTagName(tagName);
        if (nodeList != null && nodeList.getLength() > 0) {
            return nodeList.item(0).getTextContent();
        }
        else {
            // BUGBUG: return empty string or null, is it an error to not find the text value?
            return "";
        }
    }
    
    private void checkGenParams(String[] genParams) throws XMLFactoryException {
    	int track = 0;
    	String[] param = new String[]{"simname", "simauthor", "simwidth", "simheight"};
    	for (String s : genParams) {
    		if (s.equals("")) {
    			throw new XMLFactoryException("node \"%s\" was not found in XML, please check general parameters", param[track]);
    		}
    		track++;
    	}
    	
    	int width = 0;
    	int height = 0;
    	
    	try {
    		width = Integer.parseInt(genParams[2]);
    		height = Integer.parseInt(genParams[3]);
    	} catch (NumberFormatException e) {
    		if (e.getMessage().equals("For input string: \""+genParams[2]+"\"")) {
    			throw new XMLFactoryException("SimWidth value \"%s\" is invalid, must be a positive integer",genParams[2]);
    		} else if (e.getMessage().equals("For input string: \""+genParams[3]+"\"")) {
    			throw new XMLFactoryException("SimHeight value \"%s\" is invalid, must be a positive integer",genParams[3]);
    		}
    	}
    	
    	if (width<0) {
    		throw new XMLFactoryException("SimWidth must be a positive integer");
    	}
    	if (height<0) {
    		throw new XMLFactoryException("SimHeight must be a positive integer");    		
    	}
    }
    
	@SuppressWarnings("unused")
	private void valueCheck(Element e, int n) throws XMLFactoryException{
    	String name = getTextValue(e, "statename");
    	String color = getTextValue(e, "statecolor");
    	String percentage = getTextValue(e, "percentage");
    	String id = e.getAttribute("id");
    	
    	if(name.equals("")) {throw new XMLFactoryException("State %d: no name found", n+1);}
    	if(color.equals("")) {throw new XMLFactoryException("State %d: no color found", n+1);}
    	
    	try {
			Color c = Color.valueOf(color);
    	} catch (IllegalArgumentException exc) {
    			throw new XMLFactoryException("State %d: Color \"%s\" is not recognized", n+1, color);
    	}
    	
    	checkPositiveNumberParameter(percentage, "double", "Percentage", true);
    	checkPositiveNumberParameter(id, "int", "State ID", false);	
	}
}
