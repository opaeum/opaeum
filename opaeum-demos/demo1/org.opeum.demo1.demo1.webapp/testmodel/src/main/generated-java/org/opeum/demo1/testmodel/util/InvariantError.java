package org.opeum.demo1.testmodel.util;

/** Class ...
 */
public class InvariantError {
	private Object instance = null;
	private String message = "";

	/** Constructor for InvariantError
	 * 
	 * @param instance 
	 * @param message 
	 */
	public InvariantError(Object instance, String message) {
		this.message = message;
		this.instance = instance;
	}

	/** Returns the instance in which this error has occurred.
	 */
	public Object getInstance() {
		return instance;
	}
	
	/** Returns the reason why this error has occurred.
	 */
	public String getMessage() {
		return message;
	}
	
	/** Sets the instance in which this error has occurred.
	 * 
	 * @param object 
	 */
	public void setInstance(Object object) {
		instance = object;
	}
	
	/** Set the reason why this error has occurred.
	 * 
	 * @param string 
	 */
	public void setMessage(String string) {
		message = string;
	}
	
	/** Returns a printable version of this error.
	 */
	public String toString() {
		return getMessage();
	}

}