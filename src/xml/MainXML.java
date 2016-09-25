package xml;

import java.io.File;
import xml.model.SimModel;
import xml.XMLParser;
import xml.factory.SimXMLFactory;
import xml.factory.GameOfLifeXMLFactory;
import xml.factory.XMLFactoryException;


/**
 * @author Rhondu Smithwick
 * @author Robert Duvall
 */
public class MainXML {
    private static final String XML_FILES_LOCATION = "data/GameOfLife.xml";
    private static final String XML_SUFFIX = ".xml";


    public SimModel xmlRead () {
        XMLParser parser = new XMLParser();
        SimXMLFactory factory = new GameOfLifeXMLFactory();
        File folder = new File(XML_FILES_LOCATION);
        SimModel p = null;
        if (folder.isFile()) {
        	try {
        		
        		p = factory.getSim(parser.getRootElement(folder.getAbsolutePath()));
        	}
        	catch (XMLFactoryException e) {
        		System.err.println("Reading file " + folder.getPath());
        		e.printStackTrace();
        	}
        }
        System.out.println(p.getMySimAuthor());
        return p;
//        for (File f : folder.listFiles()) {
//            if (f.isFile() && f.getName().endsWith(XML_SUFFIX)) {
//                try {
//                    SimModel p = factory.getPerson(parser.getRootElement(f.getAbsolutePath()));
//                    System.out.println(p);
//                }
//                catch (XMLFactoryException e) {
//                    System.err.println("Reading file " + f.getPath());
//                    e.printStackTrace();
//                }
//            }
//        }
    }
}