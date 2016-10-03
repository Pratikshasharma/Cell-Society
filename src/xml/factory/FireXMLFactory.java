package xml.factory;

import xml.model.FireModel;
import xml.model.GenState;
import org.w3c.dom.Element;


/**
 * Creates Fire object from an XML file.
 *
 * @author Ryan Anders
 */
public class FireXMLFactory extends SimXMLFactory {
    private static final String XML_TAG_NAME = "fire";


    /**
     * Create factory capable of generating Spreading of Fire objects.
     */
    public FireXMLFactory () {
        super(XML_TAG_NAME);
    }

    @Override
    public FireModel getSim (Element root) throws XMLFactoryException {  
    	if (! isValidFile(root)) {
            throw new XMLFactoryException("XML file does not represent a %s", getSimType());
        }
    	
    	String[] genParams = getSimGenParams(root);
    	GenState[] genStates = getSimGenStates(root);
    	String probCatch = getTextValue(root, "probcatch");
    	
    	checkPositiveNumberParameter(probCatch, "double", "Catch Probability", true);
    	double probCatchDouble = Double.parseDouble(probCatch);

        
        return new FireModel(genParams, probCatchDouble, genStates);
    }
}