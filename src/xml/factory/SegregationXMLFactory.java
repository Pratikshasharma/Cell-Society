package xml.factory;

import xml.model.SegregationModel;
import xml.model.GenState;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


/**
 * Creates Segregation object from an XML file.
 *
 * @author Rhondu Smithwick
 * @author Robert Duvall
 * @author Ryan Anders
 */
public class SegregationXMLFactory extends SimXMLFactory {
    private static final String XML_TAG_NAME = "segregation";


    /**
     * Create factory capable of generating Professor objects.
     */
    public SegregationXMLFactory () {
        super(XML_TAG_NAME);
    }

    /**
     * @see PersonXMLFactory#getPerson()
     */
    @Override
    public SegregationModel getSim (Element root) throws XMLFactoryException {  
    	if (! isValidFile(root)) {
            throw new XMLFactoryException("XML file does not represent a %s", getSimType());
        }
    	
    	String simName = null;
    	String simAuthor = null;
    	String simWidth = null;
    	String simHeight = null;
    	
    	//get genparams
    	NodeList genpList = root.getElementsByTagName("genparam");
    	Node genpNode = genpList.item(0);
    	if (genpNode.getNodeType() == Node.ELEMENT_NODE) {
    		Element genpElement = (Element) genpNode; 
    		simName = getTextValue(genpElement, "simname");
    		simAuthor = getTextValue(genpElement, "simauthor");
    		simWidth = getTextValue(genpElement, "simwidth");
    		simHeight = getTextValue(genpElement, "simheight");
    	}
    	
    	//get satisfaction
    	String satisfaction = getTextValue(root, "satisfaction");
    	
    	//create GenState objects
    	NodeList nList = root.getElementsByTagName("state");
    	GenState[] states = new GenState[nList.getLength()];
    	
    	for (int temp = 0; temp < nList.getLength(); temp++) {
    		Node nNode = nList.item(temp);
    		if (nNode.getNodeType() == Node.ELEMENT_NODE) {
    			Element eElement = (Element) nNode;
    			states[temp] = new GenState(getTextValue(eElement, "statename"), getTextValue(eElement, "statecolor"),
    					getTextValue(eElement, "percentage"), eElement.getAttribute("id"));
    		}
    	}    
        
        return new SegregationModel(simName, simAuthor, simWidth, simHeight, satisfaction, states[0], states[1], states[2]);
    }
}