/** Copyright Klasse Objecten
 */
package nl.klasse.octopus.expressions.internal.parser.parsetree.context;

import nl.klasse.octopus.expressions.internal.parser.parsetree.ParsedElement;
import nl.klasse.octopus.expressions.internal.parser.parsetree.ParsedName;

/** @author  Jos Warmer
 * @version $Id: ParsedOclContext.java,v 1.1 2006/03/01 19:13:27 jwarmer Exp $
 */
public class ParsedOclContext extends ParsedElement  {

    /** Creates a new instance of ParsedContext
      */
    public ParsedOclContext() {
    }

	public ParsedName getReferredElem() {
		System.out.println("ParsedOclContext::getReferredElem() : operation should not be called. Use one the subclass implementations instead.");
		return null;
	}
	
	public boolean referredElemIsClass() {
		System.out.println("ParsedOclContext::referredElemIsClass() : operation should not be called. Use one the subclass implementations instead.");
		return false;
	}
}
