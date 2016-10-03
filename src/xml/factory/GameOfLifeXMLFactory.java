package xml.factory;

import xml.model.GameOfLifeModel;
import xml.model.GenState;
import org.w3c.dom.Element;


/**
 * Creates Game of Life object from an XML file.
 *
 * @author Rhondu Smithwick
 * @author Robert Duvall
 * @author Ryan Anders
 */
public class GameOfLifeXMLFactory extends SimXMLFactory {
    private static final String XML_TAG_NAME = "gameoflife";


    /**
     * Create factory capable of generating Professor objects.
     */
    public GameOfLifeXMLFactory () {
        super(XML_TAG_NAME);
    }

    /**
     * @see PersonXMLFactory#getPerson()
     */
    @Override
    public GameOfLifeModel getSim (Element root) throws XMLFactoryException {  
    	if (! isValidFile(root)) {
            throw new XMLFactoryException("XML file does not represent a %s", getSimType());
        }
    	
    	String[] genParams = getSimGenParams(root);
    	GenState[] genStates = getSimGenStates(root); 
        
        return new GameOfLifeModel(genParams, genStates);
    }
}