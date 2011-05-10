package org.nakeduml.workinghours;
import org.nakeduml.runtime.domain.IInvariantError;
public class InvariantError implements IInvariantError {
	private Object instance = null;
	private String message = "";
	/**
	 * Constructor for InvariantError
	 * 
	 * @param instance
	 * @param message
	 */
	public InvariantError(Object instance, String message) {
		this.message = message;
		this.instance = instance;
	}
	/**
	 * Returns the reason why this error has occurred.
	 */
	public String getMessage() {
		return this.message;
	}
	/**
	 * Set the reason why this error has occurred.
	 * 
	 * @param string
	 */
	public void setMessage(String string) {
		this.message = string;
	}
	/**
	 * Returns the instance in which this error has occurred.
	 */
	public Object getInstance() {
		return this.instance;
	}
	/**
	 * Sets the instance in which this error has occurred.
	 * 
	 * @param object
	 */
	public void setInstance(Object object) {
		this.instance = object;
	}
	/**
	 * Returns a printable version of this error.
	 */
	@Override
	public String toString() {
		return getMessage();
	}
}