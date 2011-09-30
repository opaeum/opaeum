/** Copyright Klasse Objecten
 */
package nl.klasse.octopus.expressions.internal.parser.parsetree;

import java.util.List;

/** @author  Jos Warmer
 * @version $Id: ParsedDefinition.java,v 1.2 2008/01/19 13:48:05 annekekleppe Exp $
 */
public class ParsedDefinition extends ParsedElement {
	//TODO find out whether we need this class

    @SuppressWarnings("unused")
	private List attributeDefinitions = null; // contains ParsedVariableDeclarations
    @SuppressWarnings("unused")
	private List operationDefinitions = null; // contains ParsedOperDefinitions

    /** Creates a new instance of ParsedDefinitions
      */
    public ParsedDefinition(List attributes, List operations) {
        attributeDefinitions = attributes;
        operationDefinitions = operations;
    }

}
