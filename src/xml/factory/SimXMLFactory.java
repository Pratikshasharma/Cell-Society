package xml.factory;

import xml.model.SimModel;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import java.util.Objects;


/**
 * An XMLFactory that gives back a Person object.
 *
 * @author Rhondu Smithwick
 * @author Robert Duvall
 */
public abstract class SimXMLFactory extends XMLFactory {
    private String mySimType;


    /**
     * Create a factory for making Person objects.  
     */
    protected SimXMLFactory (String simType) {
        mySimType = simType;
    }

    /**
     * @return the type of person this file represents
     */
    public String getSimType () {
        return mySimType;
    }

    /**
     * Get the actual person contained in this XML File.
     */
    public abstract SimModel getSim (Element root) throws XMLFactoryException;
    //public abstract SimModel getSim (Node root) throws XMLFactoryException;

    /**
     * @see XMLFactory#isValidFile()
     */
    @Override
    protected boolean isValidFile (Element root) {
    //protected boolean isValidFile (Node root) {
        return Objects.equals(getAttribute(root, "SimType"), getSimType());
    	//return true;
    }
}
