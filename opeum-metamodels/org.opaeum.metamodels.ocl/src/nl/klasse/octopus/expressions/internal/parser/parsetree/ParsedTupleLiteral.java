/** Copyright Klasse Objecten
 */
package nl.klasse.octopus.expressions.internal.parser.parsetree;

import java.util.Iterator;
import java.util.List;

import nl.klasse.tools.common.Util;


/** @author  Jos Warmer
 * @version $Id: ParsedTupleLiteral.java,v 1.2 2008/01/19 13:48:05 annekekleppe Exp $
 */
public class ParsedTupleLiteral extends ParsedLiteral {
    private List<ParsedVariableDeclaration> variableDeclarations = null;


    /** Creates a new instance of ParsedTupleLiteral
      */
    public ParsedTupleLiteral(List<ParsedVariableDeclaration> vars)
    {
        variableDeclarations = vars;
    }


    public Iterator getVariables()
    {
        return variableDeclarations.iterator();
    }

    public String toString()
    {
        String result = "TupleLiteral("
                        + Util.collectionToString(variableDeclarations, ", ")
                        + ")";
        return appendAppliedPropertyString(result);
    }
}
