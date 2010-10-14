/** (c) Copyright 2003 by Klasse Objecten
 */
package nl.klasse.octopus.model.internal.analysis;

import nl.klasse.octopus.oclengine.internal.OclError;

/**
 *
 * @author anneke
 * @version $Id: ModelError.java,v 1.1 2006/03/01 19:13:35 jwarmer Exp $
 */
public class ModelError extends OclError {
	private String filename = "";
	
	public ModelError(String filename, int line, int column, String message){
		super(filename, line, column, message);
		this.filename = filename;
    }

	/**
	 * Returns the filename.
	 * @return String
	 */
	public String getFilename() {
		return filename;
	}

	/**
	 * Sets the filename.
	 * @param filename The filename to set
	 */
	public void setFilename(String filename) {
		this.filename = filename;
	}

}
