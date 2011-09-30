/** Copyright Klasse Objecten
 */
package nl.klasse.octopus.expressions.internal.parser.parsetree.context;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import nl.klasse.octopus.expressions.internal.parser.parsetree.ParsedName;
import nl.klasse.octopus.expressions.internal.parser.parsetree.ParsedType;
import nl.klasse.tools.common.StringHelpers;


/** @author  Jos Warmer
 * @version $Id: ParsedOclAttributeContext.java,v 1.2 2008/01/19 13:48:05 annekekleppe Exp $
 */
public class ParsedOclAttributeContext extends ParsedOclContext {

    private ParsedName 					referredAttribute 	= null;
    private ParsedType 					type				 	= null;
    private List<ParsedOclUsage> expressions       	= new ArrayList<ParsedOclUsage>();


    /** Creates a new instance of ParsedOclClassContext
      */
    public ParsedOclAttributeContext(ParsedName path) {
        referredAttribute = path;
    }

    public ParsedName getReferredElem() {
        return referredAttribute;
    }

	public boolean referredElemIsClass() {
		return false;
	}
	
    public void addExpression(ParsedOclUsage inv){
        expressions.add(inv);
    }

    public Iterator getExpressions() {
        return expressions.iterator();
    }

    public String toString() {
        String result = "context: " + referredAttribute.toString() + StringHelpers.newLine;
        result = result + expressions.toString() + StringHelpers.newLine;
        return result;
    }

	/**
	 * Returns the type.
	 * @return ParsedType
	 */
	public ParsedType getType() {
		return type;
	}

	/**
	 * Sets the type.
	 * @param type The type to set
	 */
	public void setType(ParsedType type) {
		this.type = type;
	}

}
