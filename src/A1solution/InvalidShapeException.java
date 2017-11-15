package A1solution;

/**
 * This exception is thrown whenever an illegal shape is attempted.
 * 
 * @author andy
 *
 */
@SuppressWarnings("serial")
public class InvalidShapeException extends Exception {

	/**
	 * no message constructor
	 */
	public InvalidShapeException() {
		super();
	}

	/**
	 * detailed message constructor
	 * 
	 * @param message
	 *            detailed diagnostic message
	 */
	public InvalidShapeException(String message) {
		super(message);
	}

}