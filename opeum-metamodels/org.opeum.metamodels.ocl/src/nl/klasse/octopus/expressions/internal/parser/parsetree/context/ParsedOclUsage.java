package nl.klasse.octopus.expressions.internal.parser.parsetree.context;

import nl.klasse.octopus.expressions.internal.parser.javacc.OclParserConstants;
import nl.klasse.octopus.expressions.internal.parser.javacc.Token;
import nl.klasse.octopus.expressions.internal.parser.parsetree.ParsedElement;
import nl.klasse.octopus.expressions.internal.parser.parsetree.ParsedOclExpression;
import nl.klasse.octopus.expressions.internal.parser.parsetree.ParsedOperDefinition;
import nl.klasse.octopus.expressions.internal.parser.parsetree.ParsedVariableDeclaration;
import nl.klasse.octopus.model.OclUsageType;


public class ParsedOclUsage extends ParsedElement implements OclParserConstants {
	private ParsedOclExpression  		expression = null;
	private ParsedVariableDeclaration   var = null;
	private ParsedOperDefinition        oper = null;
	private OclUsageType           		type;
	private String				 		name;
	private boolean 					isAnalyzed = false;
	
	public ParsedOclUsage() {
	}
	
	public void setExpression(ParsedOclExpression exp) {
		expression = exp;
	}
	
	public ParsedOclExpression getExpression() {
		return expression;
	}
	
	public void setVariable(ParsedVariableDeclaration exp) {
		var = exp;
	}
	
	public ParsedVariableDeclaration getVariable() {
		return var;
	}
	
	public void setOperation(ParsedOperDefinition exp) {
		oper = exp;
	}
	
	public ParsedOperDefinition getOperation() {
		return oper;
	}	
		
	public void setType(Token t) {
		switch ( t.kind ) {
			case INV:    { type = OclUsageType.INV; break; }
			case DEF:    { type = OclUsageType.DEF; break;}
			case PRE:    { type = OclUsageType.PRE; break;}
			case POST:   { type = OclUsageType.POST; break;}
			case BODY:   { type = OclUsageType.BODY; break;}
			case INIT:   { type = OclUsageType.INIT; break;}
			case DERIVE: { type = OclUsageType.DERIVE; break;}
		}
	} 
	
	public OclUsageType getType() {
		return type;
	}
	
	public void setName(String n) {
		name = n;
	}
	
	public String getName() {
		return name;
	}
	
	public String toString() {
		String result = type.toString();
		if ( name != "" ) result = result + " " + name;
		result = result + ": ";
		if ( var != null ) {
			return result + var.toString();
		}
		if ( oper != null ) return result + oper.toString();
		if ( expression != null ) result = result + expression.toString();
		return result;
	}
	/**
	 * Returns the isAnalyzed.
	 * @return boolean
	 */
	public boolean isAnalyzed() {
		return isAnalyzed;
	}

	/**
	 * Sets the isAnalyzed.
	 * @param isAnalyzed The isAnalyzed to set
	 */
	public void setIsAnalyzed(boolean isAnalyzed) {
		this.isAnalyzed = isAnalyzed;
	}

}
