/** Copyright 2002, Klasse Objecten
 */
package nl.klasse.octopus.expressions.internal.parser.parsetree;

import java.util.Iterator;
import java.util.List;

import nl.klasse.tools.common.Util;


/** @author  Jos Warmer
 * @version $Id: ParsedCollectionLiteral.java,v 1.2 2008/01/19 13:48:05 annekekleppe Exp $
 */
public class ParsedCollectionLiteral extends ParsedLiteral {
    private List<ParsedOclExpression> parts = null;

    /** Creates a new instance of ParsedCollectionLiteral
      */
    public ParsedCollectionLiteral(String s, List<ParsedOclExpression> parts) {
        super(s);
        this.parts = parts;
    }

	public List<ParsedOclExpression> getParts() {
		return parts;
	}
	
    public String toString()
    {
        String result = symbol + "{" + Util.collectionToString(parts, ", ") + " }";
        return appendAppliedPropertyString( result);
    }

    /** Re-arrange this expression such that all nested operators will be on the
     *  same level. During parsing they become all nested.
     *  The next step (in applyPriority() ) is to re-arrange the tree to reflect
     *  the different priorities.
     */
    public void arrangeOperators() {
        Iterator iter = parts.iterator();
        while( iter.hasNext() ) {
            ((ParsedOclExpression)iter.next()).arrangeOperators();
        }
        super.arrangeOperators();
    }
    
    /** Re-arrange the parse tree to reflect the predefined priorities of the
     *  binary operators. DONE-04/09/2002.
     */
    public void applyPriority(){
        Iterator iter = parts.iterator();
        while( iter.hasNext() ) {
            ((ParsedOclExpression)iter.next()).applyPriority();
        }
        super.applyPriority();
    }

}
