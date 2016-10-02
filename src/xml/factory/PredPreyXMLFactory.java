package xml.factory;

import xml.model.PredPreyModel;
import xml.model.GenState;
import org.w3c.dom.Element;


/**
 * Creates PredPrey object from an XML file.
 *
 * @author Rhondu Smithwick
 * @author Robert Duvall
 * @author Ryan Anders
 */
public class PredPreyXMLFactory extends SimXMLFactory {
    private static final String XML_TAG_NAME = "predprey";


    /**
     * Create factory capable of generating Professor objects.
     */
    public PredPreyXMLFactory () {
        super(XML_TAG_NAME);
    }

    /**
     * @see PersonXMLFactory#getPerson()
     */
    @Override
    public PredPreyModel getSim (Element root) throws XMLFactoryException {  
    	if (! isValidFile(root)) {
            throw new XMLFactoryException("XML file does not represent a %s", getSimType());
        }
    	
    	String[] genParams = getSimGenParams(root);
    	GenState[] genStates = getSimGenStates(root);
    	String fishBreed = getTextValue(root, "fishturnstobreed");
    	String sharkBreed = getTextValue(root, "sharkturnstobreed");
    	String sharkStarve = getTextValue(root, "sharkturnstostarve");
        
        return new PredPreyModel(genParams, fishBreed, sharkBreed, sharkStarve, genStates);
    }
}