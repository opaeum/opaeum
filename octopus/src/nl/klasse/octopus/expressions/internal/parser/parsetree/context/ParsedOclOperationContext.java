/** Copyright Klasse Objecten
 */
package nl.klasse.octopus.expressions.internal.parser.parsetree.context;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import nl.klasse.octopus.expressions.internal.parser.parsetree.ParsedName;
import nl.klasse.octopus.expressions.internal.parser.parsetree.ParsedType;
import nl.klasse.octopus.expressions.internal.parser.parsetree.ParsedVariableDeclaration;
import nl.klasse.tools.common.StringHelpers;


/** @author  Jos Warmer
 * @version $Id: ParsedOclOperationContext.java,v 1.2 2008/01/19 13:48:05 annekekleppe Exp $
 */
public class ParsedOclOperationContext extends ParsedOclContext {

    private ParsedName 							referredOperation	= null;
    private ParsedType 							returnType		 	= null;
    private List<ParsedVariableDeclaration> 	parameters        	= new ArrayList<ParsedVariableDeclaration>();
    private List<ParsedOclUsage>       		expressions       	= new ArrayList<ParsedOclUsage>();


    /** Creates a new instance of ParsedOclClassContext
      */
    public ParsedOclOperationContext(ParsedName path) {
        referredOperation = path;
    }

    public ParsedName getReferredElem() {
        return referredOperation;
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
    
    public void setPars(List<ParsedVariableDeclaration> p){
    	parameters = p;
    }
    
    public List<ParsedVariableDeclaration> getPars() {
    	return parameters;
    }

    public String toString() {
        String result = "context: " + referredOperation.toString() + StringHelpers.newLine;
        result = result + expressions.toString() + StringHelpers.newLine;
        return result;
    }

	/**
	 * Returns the returnType.
	 * @return ParsedType
	 */
	public ParsedType getReturnType() {
		return returnType;
	}

	/**
	 * Sets the returnType.
	 * @param returnType The returnType to set
	 */
	public void setReturnType(ParsedType returnType) {
		this.returnType = returnType;
	}

}
