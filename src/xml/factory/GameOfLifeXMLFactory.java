package xml.factory;

import xml.model.SimModel;
import xml.model.GameOfLifeModel;
import xml.model.GenState;
import org.w3c.dom.Element;
import org.w3c.dom.Node;


/**
 * Creates Professor object from an XML file.
 *
 * @author Rhondu Smithwick
 * @author Robert Duvall
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
    public SimModel getSim (Element root) throws XMLFactoryException {
    //public SimModel getSim (Node root) throws XMLFactoryException {  
    	if (! isValidFile(root)) {
            throw new XMLFactoryException("XML file does not represent a %s", getSimType());
        }
        // BUGBUG: hard coding tagNames is a bad idea
//        Element genParam = (Element) root.getFirstChild();
//        Element fullStateElement = (Element) root.getFirstChild().getNextSibling().getFirstChild();
//        Element emptyStateElement = (Element) root.getFirstChild().getNextSibling().getLastChild();
    	Node genParam = root.getFirstChild();
        Node fullStateElement = root.getFirstChild().getNextSibling().getFirstChild();
        Node emptyStateElement = root.getFirstChild().getNextSibling().getLastChild();
        
//        String simName = getTextValue(genParam, "simname");
//        String simAuthor = getTextValue(genParam, "simauthor");
//        String simWidth = getTextValue(genParam, "simwidth");
//        String simHeight = getTextValue(genParam, "simheight");
        String simName = genParam.getFirstChild().getNodeValue();
        String simAuthor = genParam.getFirstChild().getNextSibling().getNodeValue();
        String simWidth = genParam.getLastChild().getPreviousSibling().getNodeValue();
        String simHeight = genParam.getLastChild().getNodeValue();
        
//        GenState fullState = new GenState(getTextValue(fullStateElement, "statename"), getTextValue(fullStateElement, "statecolor"),
//        								getTextValue(fullStateElement, "percentage"), getTextValue(fullStateElement, "stateID"));
//
//        GenState emptyState = new GenState(getTextValue(emptyStateElement, "statename"), getTextValue(emptyStateElement, "statecolor"),
//				getTextValue(emptyStateElement, "percentage"), getTextValue(emptyStateElement, "stateID"));
        
        GenState fullState = new GenState(fullStateElement.getFirstChild().getNodeValue(), fullStateElement.getFirstChild().getNextSibling().getNodeValue(),
    			fullStateElement.getLastChild().getPreviousSibling().getNodeValue(), fullStateElement.getLastChild().getNodeValue());
        GenState emptyState = new GenState(emptyStateElement.getFirstChild().getNodeValue(), emptyStateElement.getFirstChild().getNextSibling().getNodeValue(),
    			emptyStateElement.getLastChild().getPreviousSibling().getNodeValue(), emptyStateElement.getLastChild().getNodeValue());
    
        
        return new GameOfLifeModel(simName, simAuthor, simWidth, simHeight, fullState, emptyState);
    }
}