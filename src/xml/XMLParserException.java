package xml;

/**
 * This class represents what might go wrong when using XML files.
 * 
 * @author Robert Duvall
 */
public class XMLParserException extends RuntimeException {
    // for serialization
    private static final long serialVersionUID = 1L;


    /**
     * Create an exception based on an issue in our code.
     */
    public XMLParserException (String message, Object ... values) {
        super(String.format(message, values));
    }

    /**
     * Create an exception based on a caught exception with a different message.
     */
    public XMLParserException (Throwable cause, String message, Object ... values) {
        super(String.format(message, values), cause);
    }

    /**
     * Create an exception based on a caught exception, with no additional message.
     */
    public XMLParserException (Throwable cause) {
        super(cause);
    }
}
