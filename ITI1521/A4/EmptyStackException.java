/**
 * Error to throw when empty stack detected.
 *
 * @author Oliver Benning, 7798804, University of Ottawa
 *
 */

class EmptyStackException extends RuntimeException {
	/**
	 * Initialise object as RuntimeException
	 */
	public EmptyStackException () {
		super();
	}
	/**
	 * Initialise object as RuntimeException
	 *
	 * @param s
	 *				String to display
	 */
	public EmptyStackException (String s) {
		super(s);
	}
}
