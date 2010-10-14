package net.sf.nakeduml.library.workinghours;
public class InvariantException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3194382984208687904L;
	private Object instance = null;
	/**
	 * Constructor for InvariantException
	 * 
	 * @param instance
	 * @param message
	 */
	public InvariantException(Object instance, String message) {
		super(message);
		this.instance = instance;
	}
	public Object getInstance() {
		return this.instance;
	}
}