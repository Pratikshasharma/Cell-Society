package xml.factory;

import xml.model.PredPreyModel;
import xml.model.GenState;
import org.w3c.dom.Element;


/**
 * Creates PredPrey object from an XML file.
 *
 * @author Ryan Anders
 */
public class PredPreyXMLFactory extends SimXMLFactory {
    private static final String XML_TAG_NAME = "predprey";


    /**
     * Create factory capable of generating Predator-Prey objects.
     */
    public PredPreyXMLFactory () {
        super(XML_TAG_NAME);
    }

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
    	
    	checkPositiveNumberParameter(fishBreed, "int", "Turns until fish breed", true);
    	checkPositiveNumberParameter(sharkBreed, "int", "Turns until sharks breed", true);
    	checkPositiveNumberParameter(sharkStarve, "int", "Turns until sharks starve", true);
    	
    	int fishBreedInt = Integer.parseInt(fishBreed);
    	int sharkBreedInt = Integer.parseInt(sharkBreed);
    	int sharkStarveInt = Integer.parseInt(sharkStarve);
    	        
        return new PredPreyModel(genParams, fishBreedInt, sharkBreedInt, sharkStarveInt, genStates);
    }
}