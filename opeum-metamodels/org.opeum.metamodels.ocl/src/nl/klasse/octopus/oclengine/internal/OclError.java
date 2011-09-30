/** (c) Copyright 2002, Klasse Objecten
 */
package nl.klasse.octopus.oclengine.internal;

import nl.klasse.octopus.expressions.internal.parser.javacc.ParseException;
import nl.klasse.octopus.oclengine.IOclError;
import nl.klasse.tools.common.StringHelpers;


/**
 * @author  Jos Warmer
 * @version $Id: OclError.java,v 1.1 2006/03/01 19:13:33 jwarmer Exp $
 */
public class OclError implements IOclError {
    private String filename = "";
    private int    lineNumber;
    private int    columnNumber;
    private String errorMessage;

    public OclError(String filename, int line, int column, String message){
        lineNumber   = line;
        columnNumber = column;
        errorMessage = transformMess(message);
        this.filename = filename;
    }
    
    public OclError() {
    	lineNumber = -1;
    }
    
    public OclError(String filename, ParseException p) {
    	if (p.currentToken.beginLine > 0 ) {
	    	lineNumber = p.currentToken.beginLine -1;
		} else {
			lineNumber = p.currentToken.beginLine;
    	}
		columnNumber = p.currentToken.beginColumn;
		errorMessage = transformMess(p.getMessage());
        this.filename = filename;
    }

	/**
	 * Method transformMess: transforms the message from the parser into an OclError message, 
	 * removing line number references and newlines.
	 * Removes everything between '"  at line' and '.'
	 * @param string
	 * @return String
	 */
	private String transformMess(String orig) {
		String result = "";
		String newLine = StringHelpers.newLine;
    	int first = orig.indexOf( "\" at line " );
    	int last = orig.indexOf( "." + newLine );
    	if (first == -1 || last == -1 ) {
    		result = orig;
    	} else {
    		result = orig.substring( 0, first ) + "\"" + orig.substring( last, orig.length() );
    	}
		result = StringHelpers.replaceAllSubstrings(result, newLine, " ");
		result = StringHelpers.replaceAllSubstrings(result, "     ", " ");
		result = StringHelpers.replaceAllSubstrings(result, " ...", ", ");
		// replace the last ', ' by '.'
		result = result.trim();
		if (result.charAt(result.length()-1) == ',') {
			result = result.substring(0, result.length()-1) + '.';
		}
		return result;
	}
        
	public void setLineNumber(int lineNumber) {
		this.lineNumber = lineNumber;
	}

	public int getLineNumber() {
		return lineNumber;
	}

	public void setColumnNumber(int columnNumber) {
		this.columnNumber = columnNumber;
	}

	public int getColumnNumber() {
		return columnNumber;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

    public String getErrorMessage() {
        return errorMessage;
    }
    
    public String toString() {
    	return "Ocl error in file " + filename + " on line " + lineNumber + " on column " + columnNumber + ": " + errorMessage;
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
