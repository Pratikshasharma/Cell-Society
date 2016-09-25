package xml;

import java.io.File;
import org.w3c.dom.Element;
import xml.model.*;
import xml.XMLParser;
import xml.factory.*;

/**
 * @author Rhondu Smithwick
 * @author Robert Duvall
 * @author Ryan Anders
 */
public class MainXML {
private FireModel fireModel;
private GameOfLifeModel gameOfLifeModel;

    public SimModel xmlRead (File file) {
        XMLParser parser = new XMLParser();
//        SimModel p = myModelManager();
        if (file.isFile()) {
        	try {
        		Element root = parser.getRootElement(file.getAbsolutePath());
        		if (root.getAttribute("SimType").equals("gameoflife")) {
        			SimXMLFactory factory = new GameOfLifeXMLFactory();
        			SimModel p = (GameOfLifeModel) factory.getSim(root);
        			return p;
        		}
        		else if (root.getAttribute("SimType").equals("fire")) {
        			SimXMLFactory factory = new FireXMLFactory();
        			SimModel p = (FireModel) factory.getSim(root);
        			return p;
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