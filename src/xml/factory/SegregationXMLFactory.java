package xml.factory;

import xml.model.SegregationModel;
import xml.model.GenState;
import org.w3c.dom.Element;


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
    	
    	String[] genParams = getSimGenParams(root);
    	GenState[] genStates = getSimGenStates(root);
    	String satisfaction = getTextValue(root, "satisfaction");
        
    	checkPositiveNumberParameter(satisfaction, "double", "Satisfaction", true);
    	double satisfactionDouble = Double.parseDouble(satisfaction);
    	
        return new SegregationModel(genParams, satisfactionDouble, genStates);
    }
}