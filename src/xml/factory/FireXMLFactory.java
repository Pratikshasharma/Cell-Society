// This entire file is part of my masterpiece.
// Ryan Anders

/*
 * The three classes I have marked as my masterpiece give an outline of the
 * part of the program that takes an xml file as an input and gives back
 * a SimModel object containing all the parameters of the simulation as an
 * output. The three classes selected follow this process for a Spreading
 * of Fire simulation.
 * 
 * I chose these files because I feel that they show a fairly large leap 
 * in my design abilities: My entire game consisted of just three classes.
 * Some of them were quite large, and there weren't clearly defined differences
 * between them.
 * 
 * In this project, however, and particularly in this xml reading feature,
 * I feel that I did a much better job of using my classes to create distinct
 * objects, each with a clearly defined purpose. My Factory parses the xml
 * document and creates a Model, which contains Strings such as title, author,
 * etc., but also contains the simulation's states, or GenStates. In turn,
 * these GenStates contain the state's name, color, and the percentage of
 * Cells that should be in that state upon initialization.
 * 
 * Compared to my Game project, which pretty much held all important information
 * within JavaFX rectangles or giant lists of instance variables within a
 * 400-line class, this is a much better way to keep track of and to pass
 * around values and parameters to different parts of the program.
 * 
 * I also do a better job of delegating work to other parts of the program:
 * The biggest "workhorse" methods are probably the getSimGenParams and
 * getSimGenStates methods, which are stored in the SimXMLFactory superclass
 * so that they can be accessed by all the Factory subclasses.
 * 
 */
package xml.factory;

import xml.model.FireModel;
import xml.model.GenState;
import org.w3c.dom.Element;


/**
 * Creates Fire object from an XML file.
 *
 * @author Ryan Anders
 */
public class FireXMLFactory extends SimXMLFactory {
    private static final String XML_TAG_NAME = "fire";


    /**
     * Create factory capable of generating Spreading of Fire objects.
     */
    public FireXMLFactory () {
        super(XML_TAG_NAME);
    }

    /**
     * read xml document detailing a Spreading of Fire simulation
     * 
     * @param root node
     * @return FireModel object containing general parameters, tree state, burning state, empty state, and probCatch
     */
    @Override
    public FireModel getSim (Element root) throws XMLFactoryException {  
    	if (! isValidFile(root)) {
            throw new XMLFactoryException("XML file does not represent a %s", getSimType());
        }
    	
    	String[] genParams = getSimGenParams(root);
    	GenState[] genStates = getSimGenStates(root);
    	String probCatch = getTextValue(root, "probcatch");
    	
    	checkPositiveNumberParameter(probCatch, "double", "Catch Probability", true);
    	double probCatchDouble = Double.parseDouble(probCatch);

        
        return new FireModel(genParams, probCatchDouble, genStates);
    }
}