/** Copyright Klasse Objecten
 */
package nl.klasse.octopus.expressions.internal.types;

import java.util.ArrayList;
import java.util.List;

import nl.klasse.octopus.expressions.IOclExpression;
import nl.klasse.octopus.expressions.IOclMessageExp;
import nl.klasse.octopus.model.IOperation;


/** @author  Jos Warmer
 * @version $Id: OclMessageExp.java,v 1.2 2008/01/19 13:53:12 annekekleppe Exp $
 */
public class OclMessageExp extends OclExpression implements IOclMessageExp {

    private IOclExpression 			target          		= null;
    private List<IOclExpression> arguments       	= new ArrayList<IOclExpression>(); 
    private IOperation    			calledOperation 	= null;
    
    /** Creates a new instance of OclMessageExp */
    public OclMessageExp() {
    }

    public String toString() {
        return calledOperation.getName();
    }

	/**
	 * @return
	 */
	public List<IOclExpression> getArguments() {
		return arguments;
	}

	/**
	 * @return
	 */
	public IOperation getCalledOperation() {
		return calledOperation;
	}

	/**
	 * @return
	 */
	public IOclExpression getTarget() {
		return target;
	}

	/**
	 * @param list
	 */
	public void setArguments(List<IOclExpression> list) {
		arguments = list;
	}

	/**
	 * @param operation
	 */
	public void setCalledOperation(IOperation operation) {
		calledOperation = operation;
		this.setName(calledOperation.getName());
	}

	/**
	 * @param expression
	 */
	public void setTarget(IOclExpression expression) {
		target = expression;
	}

}
