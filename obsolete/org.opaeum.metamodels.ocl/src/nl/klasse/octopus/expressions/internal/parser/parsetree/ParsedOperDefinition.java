/** Copyright Klasse Objecten
 */
package nl.klasse.octopus.expressions.internal.parser.parsetree;

import java.util.List;

import nl.klasse.octopus.expressions.IVariableDeclaration;

/** @author  Jos Warmer
 * @version $Id: ParsedOperDefinition.java,v 1.2 2008/01/19 13:48:05 annekekleppe Exp $
 */
public class ParsedOperDefinition extends ParsedElement {

    private String               				name       = null;
    private List<IVariableDeclaration>  parameters = null; 
    private ParsedOclExpression  		resultExp  = null;
    private ParsedType          	 		returnType = null;

    /** Creates a new instance of ParsedOperDefinition
      */
    public ParsedOperDefinition(String name, List<IVariableDeclaration> pars,
                                ParsedType returnType, ParsedOclExpression exp) {
        this.name       = name;
        parameters      = pars;
        this.returnType = returnType;
        resultExp       = exp;
    }
    
    public ParsedOclExpression getExpression() {
    	return resultExp;
    }
    
    public String toString() {
    	String result = "";
    	result = name + "(";
    	if ( parameters != null && !parameters.isEmpty() ) result = result + parameters.toString();
    	result = result + ")";
    	if ( returnType != null ) result = result + " : " + returnType.toString();
    	if ( resultExp != null ) result = result + " = " + resultExp.toString();
    	return result;
    }
	/**
	 * Returns the name.
	 * @return String
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns the parameters.
	 * @return List
	 */
	public List<IVariableDeclaration> getParameters() {
		return parameters;
	}

	/**
	 * Returns the resultExp.
	 * @return ParsedOclExpression
	 */
	public ParsedOclExpression getResultExp() {
		return resultExp;
	}

	/**
	 * Returns the returnType.
	 * @return ParsedType
	 */
	public ParsedType getReturnType() {
		return returnType;
	}

}
