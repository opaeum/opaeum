/*
 * Created on Dec 13, 2003
 *
 * Copyright Klasse Objecten
 */
package nl.klasse.octopus.expressions.internal.analysis;

import java.util.List;

import nl.klasse.octopus.oclengine.IOclError;
import nl.klasse.octopus.oclengine.internal.OclError;


/**
 * Analyzer : 
 */
public class Analyzer {
	
	protected List<IOclError> errors = null;
	protected String currentFile = "";
       
	protected void error(int line, int column, String message){
		OclError err = new OclError(currentFile, line, column, message);
		errors.add(err);
	}
	/**
	 * 
	 */
	public Analyzer(String filename, List<IOclError> errors) {
		this.currentFile = filename;
		this.errors = errors;
	}

}
