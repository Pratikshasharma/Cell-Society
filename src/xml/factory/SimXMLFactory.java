package xml.factory;

import xml.model.GenState;
import xml.model.SimModel;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.Objects;


/**
 * An XMLFactory that gives back a Simulation object.
 *
 * @author Rhondu Smithwick
 * @author Robert Duvall
 * @author Ryan Anders
 */
public abstract class SimXMLFactory extends XMLFactory {
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
     * Get the actual Simulation contained in this XML File.
     */
    public abstract SimModel getSim (Element root) throws XMLFactoryException;
    
    /**
     * @see XMLFactory#isValidFile()
     */
    @Override
    protected boolean isValidFile (Element root) {
        return Objects.equals(getAttribute(root, "SimType"), getSimType());
    }
    
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
    		return genParams;
    	}
    	return null;
    }
    
    protected GenState[] getSimGenStates(Element root) throws XMLFactoryException {
    	NodeList nList = root.getElementsByTagName("state");
    	GenState[] states = new GenState[nList.getLength()];
    	double checkPercentages = 0;
    	
    	for (int temp = 0; temp < nList.getLength(); temp++) {
    		Node nNode = nList.item(temp);
    		if (nNode.getNodeType() == Node.ELEMENT_NODE) {
    			Element eElement = (Element) nNode;
    			states[temp] = new GenState(getTextValue(eElement, "statename"), getTextValue(eElement, "statecolor"),
    					getTextValue(eElement, "percentage"), eElement.getAttribute("id"));
    			checkPercentages += Double.parseDouble(getTextValue(eElement, "percentage"));
    		}
    	}
    	
    	if (checkPercentages != 1) {
    		throw new XMLFactoryException("State Percentages add up to %.2f, not 1", checkPercentages);
    	}
    	
    	return states;
    }

}
