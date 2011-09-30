/** Copyright Klasse Objecten
 */
package nl.klasse.octopus.expressions.internal.parser.parsetree.context;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import nl.klasse.octopus.expressions.internal.parser.parsetree.ParsedName;
import nl.klasse.tools.common.StringHelpers;


/** @author  Jos Warmer
 * @version $Id: ParsedOclClassContext.java,v 1.2 2008/01/19 13:48:05 annekekleppe Exp $
 */
public class ParsedOclClassContext extends ParsedOclContext {

    private ParsedName 				referredClass 	= null;
    private List<ParsedOclUsage>	invariants    	= new ArrayList<ParsedOclUsage>();
    private List<ParsedOclUsage>	definitions   		= new ArrayList<ParsedOclUsage>();

    /** Creates a new instance of ParsedOclClassContext
      */
    public ParsedOclClassContext(ParsedName path) {
        referredClass = path;
    }

    public ParsedName getReferredElem() {
        return referredClass;
    }
    
	public boolean referredElemIsClass() {
		return true;
	}
	
    public void addInvariant(ParsedOclUsage inv){
        invariants.add(inv);
    }

    public Iterator getInvariants() {
        return invariants.iterator();
    }

    public void addDefinition(ParsedOclUsage def){
        definitions.add(def);
    }

    public Iterator getDefinitions() {
        return definitions.iterator();
    }
    
    public void removeDefinitions(List toBeRemoved) {
    	definitions.removeAll(toBeRemoved);
    }
    
    public String toString() {
        String result = "context: " + referredClass.toString() + StringHelpers.newLine;
        result = result + invariants.toString() + StringHelpers.newLine;
        result = result + definitions.toString() + StringHelpers.newLine;
        return result;
    }

}
