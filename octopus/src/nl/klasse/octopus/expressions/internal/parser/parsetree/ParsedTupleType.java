/** Copyright Klasse Objecten
 */
package nl.klasse.octopus.expressions.internal.parser.parsetree;

import java.util.Iterator;
import java.util.List;

import nl.klasse.tools.common.Util;


/** @author  Jos Warmer
 * @version $Id: ParsedTupleType.java,v 1.2 2008/01/19 13:48:05 annekekleppe Exp $
 */
public class ParsedTupleType extends ParsedType {

    private List<ParsedVariableDeclaration> tupleParts = null;

    /** Creates a new instance of ParsedTupleType
      */
    public ParsedTupleType(List<ParsedVariableDeclaration> parts) {
        tupleParts = parts;
    }

    public Iterator getParts()
    {
        return tupleParts.iterator();
    }

    public String toString()
    {
        String result = "ParsedTupleType(" + Util.collectionToString(tupleParts, ", ") + ")";
        return result;
    }
}
