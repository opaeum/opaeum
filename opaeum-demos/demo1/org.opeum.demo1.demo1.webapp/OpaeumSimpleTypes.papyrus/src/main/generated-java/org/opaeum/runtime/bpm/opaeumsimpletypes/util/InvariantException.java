package org.opaeum.runtime.bpm.opaeumsimpletypes.util;

/** Class ...InvariantException: instances are thrown when an invariant is broken.
This class cannot be implemented without the compiler warning about 'static final serialVersionUID'
The best option is to ignore this warning.
 */
@SuppressWarnings("serial")
public class InvariantException extends Exception {
	private Object instance = null;

	/** Constructor for InvariantException
	 * 
	 * @param instance 
	 * @param message 
	 */
	public InvariantException(Object instance, String message) {
		super(message);
		this.instance = instance;
	}

	public Object getInstance() {
		return instance;
	}

}