package xml.factory;

import xml.model.FireModel;
import xml.model.GenState;
import org.w3c.dom.Element;


/**
 * Creates Fire object from an XML file.
 *
 * @author Rhondu Smithwick
 * @author Robert Duvall
 * @author Ryan Anders
 */
public class FireXMLFactory extends SimXMLFactory {
    private static final String XML_TAG_NAME = "fire";


    /**
     * Create factory capable of generating Professor objects.
     */
    public FireXMLFactory () {
        super(XML_TAG_NAME);
    }

    /**
     * @see PersonXMLFactory#getPerson()
     */
    @Override
    public FireModel getSim (Element root) throws XMLFactoryException {  
    	if (! isValidFile(root)) {
            throw new XMLFactoryException("XML file does not represent a %s", getSimType());
        }
    	
    	String[] genParams = getSimGenParams(root);
    	GenState[] genStates = getSimGenStates(root);
    	String probCatch = getTextValue(root, "probcatch");
        
        return new FireModel(genParams, probCatch, genStates);
    }
}