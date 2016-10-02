package xml.factory;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;


/**
 * Base class for all XMLFactories, regardless of what kind of object they actually create.
 * <p>
 * Most this class factors out common methods from more specific XML factories.
 * </p>
 *
 * @author Rhondu Smithwick
 * @author Robert Duvall
 */
public abstract class XMLFactory {
	
    /**
     * @return if this is a valid XML file for this specific XML object type
     */
    protected abstract boolean isValidFile (Element root);
    
    /**
     * Get the value of an attribute.
     * 
     * Why might it not be good design to include this and getTextValue in this class?
     * What happens when you need more transformation methods?
     */
    protected String getAttribute (Element root, String attributeName) {
        return root.getAttribute(attributeName);
    }

    /**
     * Get the text value of a node.
     * <p>
     * Assumes you want the textValue of the first node with this tagName.
     * </p>
     * 
     * Why might it not be good design to include this and getAttribute in this class?
     * What happens when I need more transformation methods?
     */
    protected String getTextValue (Element root, String tagName) {
        NodeList nodeList = root.getElementsByTagName(tagName);
        if (nodeList != null && nodeList.getLength() > 0) {
            return nodeList.item(0).getTextContent();
        }
        else {
            // BUGBUG: return empty string or null, is it an error to not find the text value?
            return "";
        }
    }
}
