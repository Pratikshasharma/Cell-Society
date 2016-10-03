package xml.factory;

import xml.model.GenState;
import xml.model.SlimeMoldModel;

import org.w3c.dom.Element;

/**
 * Creates Slime Mold object from an XML file.
 * @author Ryan Anders
 *
 */
public class SlimeMoldXMLFactory extends SimXMLFactory {
	private static final String XML_TAG_NAME = "slimemold";
	
	/**
	 * Create factory capable of generating Slime Mold objects
	 */
	public SlimeMoldXMLFactory () {
		super(XML_TAG_NAME);
	}
	
	@Override
	public SlimeMoldModel getSim(Element root) throws XMLFactoryException {
		if (! isValidFile(root)) {
            throw new XMLFactoryException("XML file does not represent a %s", getSimType());
        }
    	
    	String[] genParams = getSimGenParams(root);
    	GenState[] genStates = getSimGenStates(root); 
        
        return new SlimeMoldModel(genParams, genStates);
	}

}
