package xml.factory;

/**
 * This class represents what might go wrong when using XML files.
 * 
 * @author Robert Duvall
 */
public class XMLFactoryException extends Exception {
    // for serialization
    private static final long serialVersionUID = 1L;


    /**
     * Create an exception based on an issue in our code.
     */
    public XMLFactoryException (String message, Object ... values) {
        super(String.format(message, values));
    }
    
    /**
     * Create an exception based on a caught exception with a different message.
     */
    public XMLFactoryException (Throwable cause, String message, Object ... values) {
        super(String.format(message, values), cause);
    }

    /**
     * Create an exception based on a caught exception, with no additional message.
     */
    public XMLFactoryException (Throwable cause) {
        super(cause);
    }
}
