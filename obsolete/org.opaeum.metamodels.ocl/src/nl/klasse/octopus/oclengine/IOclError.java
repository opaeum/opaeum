/*
 * Created on Jan 21, 2004
 *
 * Copyright Klasse Objecten
 */
package nl.klasse.octopus.oclengine;

/**
 * IOclError : 
 */
public interface IOclError {
	/** Returns the number of the line where this error was found.
	 * @return
	 */
	public abstract int getLineNumber();
	/** Returns the startcolumn where this error was found.
	 * @return
	 */
	public abstract int getColumnNumber();
	/** Returns the message that tells what went wrong.
	 * @return
	 */
	public abstract String getErrorMessage();
	/**
	 * Returns the filename where this error was found.
	 * @return String
	 */
	public abstract String getFilename();
}