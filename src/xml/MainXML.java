package xml;

import java.io.File;
import org.w3c.dom.Element;
import xml.model.*;
import xml.XMLParser;
import xml.factory.*;

/**
 * Main Xml class -contains the most important method to the rest of the program, xmlRead
 * 
 * @author Rhondu Smithwick
 * @author Robert Duvall
 * @author Ryan Anders
 */
public class MainXML {

	/**
	 * Reads an xml file, returns a SimModel object containing parameters for the model described in the XMl file
	 * 
	 * @param file
	 * @return SimModel object containing states/parameters
	 */
    public SimModel xmlRead (File file) {
        XMLParser parser = new XMLParser();
        
        if (file.isFile()) {
        	try {
        		Element root = parser.getRootElement(file.getAbsolutePath());
        		String simType = root.getAttribute("SimType");
        		if (simType.equals("gameoflife")) {
        			SimXMLFactory factory = new GameOfLifeXMLFactory();
        			SimModel p = (GameOfLifeModel) factory.getSim(root);
        			return p;
        		} else if (simType.equals("fire")) {
        			SimXMLFactory factory = new FireXMLFactory();
        			SimModel p = (FireModel) factory.getSim(root);
        			return p;
        		} else if (simType.equals("segregation")) {
        			SimXMLFactory factory = new SegregationXMLFactory();
        			SimModel p = (SegregationModel) factory.getSim(root);
        			return p;
        		} else if (simType.equals("predprey")) {
        			SimXMLFactory factory = new PredPreyXMLFactory();
        			SimModel p = (PredPreyModel) factory.getSim(root);
        			return p;
        		} else if (simType.equals("slimemold")) {
        			SimXMLFactory factory = new SlimeMoldXMLFactory();
        			SimModel p = (SlimeMoldModel) factory.getSim(root);
        			return p;
        		} else if (simType.equals("")) {
        			String noSimType = "No simulation type specified";
        			throw new XMLFactoryException(noSimType);
        		} else {
        			String notRec = "Simulation type '%s' not recognized";
        			throw new XMLFactoryException(notRec, simType);
        		}
        	}
        	catch (XMLFactoryException e) {
        		System.err.println("Reading file " + file.getPath());
        		e.printStackTrace();
        	}
        }

		return null;	
    }
    
}