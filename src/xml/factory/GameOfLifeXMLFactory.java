package xml.factory;

import xml.model.GameOfLifeModel;
import xml.model.GenState;
import org.w3c.dom.Element;


/**
 * Creates Game of Life object from an XML file.
 *
 * @author Ryan Anders
 */
public class GameOfLifeXMLFactory extends SimXMLFactory {
    private static final String XML_TAG_NAME = "gameoflife";


    /**
     * Create factory capable of generating Game Of Life objects.
     */
    public GameOfLifeXMLFactory () {
        super(XML_TAG_NAME);
    }

    /**
     * read xml document detailing a Game of Life simulation
     * 
     * @param root node
     * @return GameOfLifeModel object containing general parameters, alive state and empty state
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