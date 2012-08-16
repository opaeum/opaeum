/** (c) Copyright 2003 by Klasse Objecten
 */
package nl.klasse.tools.common;

import org.opaeum.name.NameConverter;

/**
 *
 * @author anneke
 * @version $Id: StringHelpers.java,v 1.2 2008/01/19 15:02:35 annekekleppe Exp $
 */
public class StringHelpers {
	
	static public String newLine = System.getProperty("line.separator", "\n");
	static public char newLineChar = newLine.charAt(0);

	static public StringBuilder replaceAllSubstrings( StringBuilder orig, String origSub, String newSub ) {
		StringBuilder result = new StringBuilder();
		result.append(replaceAllSubstrings(orig.toString(), origSub, newSub));
		return result;
	}
			
    static public String replaceAllSubstrings( String orig, String origSub, String newSub ) {
    	if (origSub.length() == 0) return orig;
    	int first = orig.indexOf( origSub );
    	if (first == -1 ) {
    		return orig;
    	}
    	int last = first + origSub.length();
    	String result = orig.substring( 0, first );
    	String rest = orig.substring( last );
    	result = result + newSub + replaceAllSubstrings( rest, origSub, newSub );
    	return result;    	
    }   
     
	/**
	 * replaces 'origSub' with 'newSub' in the String 'orig'.
	 * @param orig
	 * @param origSub
	 * @param newSub
	 * @return String
	 */ 
    static public String replaceFirstSubstring( String orig, String origSub, String newSub ){
    	// TODO use String.replaceFirst()
    	int first = orig.indexOf( origSub );
    	if (first == -1 ) {
    		return orig;
    	}
    	int last = first + origSub.length();
    	String result = orig.substring( 0, first );
    	result = result + newSub + orig.substring( last );
    	return result;    		
    } 
  
    static public String indent(String in, int level) {
		StringBuilder result = new StringBuilder();
		String newIndent = "";
		for (int i=0; i<level; i++) {
			newIndent = newIndent + "\t";
		}
		String temp = in.toString();
		temp.trim(); // remove all whitespace from begin and end
		temp = newIndent + StringHelpers.replaceAllSubstrings(temp, "\n", "\n" + newIndent);
		result.append(temp);
		if (result.charAt(result.length()-1) == '\t'){
			result.deleteCharAt(result.length()-1);
		}
		return result.toString();		
	}
	
	static public StringBuilder indent(StringBuilder in, int level) {
		StringBuilder result = new StringBuilder();
		String newIndent = "";
		for (int i=0; i<level; i++) {
			newIndent = newIndent + "\t";
		}
		String temp = in.toString();
		temp.trim(); // remove all whitespace from begin and end
		temp = newIndent + StringHelpers.replaceAllSubstrings(temp, "\n", "\n" + newIndent);
		result.append(temp);
		if (result.charAt(result.length()-1) == '\t'){
			result.deleteCharAt(result.length()-1);
		}
		return result;		
	}

	public static String replaceLastSubstring(String orig, String origSub, String newSub ) {
		int first = orig.lastIndexOf( origSub );
			if (first == -1 ) {
				return orig;
			}
			int last = first + origSub.length();
			String result = orig.substring( 0, first );
			result = result + newSub + orig.substring( last );
			return result;    		
	}

	/**
	 * @param innerBody
	 */
	public static void trimTrailingWhiteSpace(StringBuilder innerBody) {
		while (Character.isWhitespace(innerBody.charAt(innerBody.length()-1))) {
			innerBody.deleteCharAt(innerBody.length()-1);
		}
		
	}

	/** Add brackets ( '(' and ')' ) around <code>source</code> when it contains any
	 * white space characters.
	 * @param source
	 * @return
	 */
	static public String addBrackets(String source) {
		String temp = source;
		temp.trim(); // remove all whitespace from begin and end
		if (temp.charAt(0) == '(' && temp.charAt(temp.length()-1) == ')') {
			return source;
		} else if (source.indexOf(' ') != -1) { // string contains a space character
			source = "(" + source + ")";
		}
		return source;
	}

	/**
	 * @param toBeCalled
	 * @return
	 */
	public static String removeSurroundingQuotes(String source) {
		if (source.charAt(0) == '\'' || source.charAt(0) == '\"') { // string starts with a quote character
			source = source.substring(1, source.length());
			if (source.charAt(source.length()-1) == '\'' 
				|| source.charAt(source.length()-1) == '\"') { // string ends with a quote character
				source = source.substring(0, source.length()-1);
			}
		}
		return source;
	}

}
