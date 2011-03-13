/** (c) Copyright 2003 by Klasse Objecten
 */
package nl.klasse.tools.common;

import java.util.StringTokenizer;

/**
 *
 * @author anneke
 * @version $Id: JavaStringHelpers.java,v 1.1 2006/03/01 19:06:41 jwarmer Exp $
 */
public class JavaStringHelpers {
	
	static public String[] convertValuesFromString(String value, String delimiter) {
		StringTokenizer tokenizer =
			new StringTokenizer(value, delimiter);
		int tokenCount = tokenizer.countTokens();
		String[] elements = new String[tokenCount];

		for (int i = 0; i < tokenCount; i++) {
			elements[i] = tokenizer.nextToken();
		}

		return elements;
	}

	static public String convertValuesToString(String[] elements, String delimiter) {
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < elements.length; i++) {
			buffer.append(elements[i]);
			buffer.append(delimiter);
		}
		return buffer.toString();
	}
}
