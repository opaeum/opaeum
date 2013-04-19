/** (c) Copyright 2003 by Klasse Objecten
 */
package nl.klasse.octopus.expressions.internal.analysis.expressions;

import nl.klasse.octopus.oclengine.internal.OclError;

/** This exception is thrown whenever an error has occurred during analysis
 *  of a single OCL expression or Variable Declaration.
 *
 * @author anneke
 * @version $Id: AnalysisException.java,v 1.2 2008/01/19 13:16:05 annekekleppe Exp $
 */
public class AnalysisException extends Exception {

	private static final long serialVersionUID = 3164170407330630649L;
	private OclError theError = null;
	
	public AnalysisException(OclError e) {
		super(e.getErrorMessage());
		theError = e;
	}
	
	public AnalysisException(String filename, int line, int column, String msg) {
		super(msg);
		theError = new OclError(filename, line, column, msg);
	}
	
	public OclError getError() {
		return theError;
	}
}

