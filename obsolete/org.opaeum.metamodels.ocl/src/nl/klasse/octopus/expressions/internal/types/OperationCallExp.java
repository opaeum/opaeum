/** Copyright Klasse Objecten
 */
package nl.klasse.octopus.expressions.internal.types;

import java.util.ArrayList;
import java.util.List;

import nl.klasse.octopus.expressions.IOclExpression;
import nl.klasse.octopus.expressions.IOperationCallExp;
import nl.klasse.octopus.model.IClassifier;
import nl.klasse.octopus.model.IOperation;
import nl.klasse.tools.common.Check;
import nl.klasse.tools.octopus.OctopusUtil;


/** @author  Jos Warmer
 * @version $Id: OperationCallExp.java,v 1.2 2008/01/19 13:53:12 annekekleppe Exp $
 */
public class OperationCallExp extends ModelPropertyCallExp implements IOperationCallExp {

    private IOperation  				referredOperation = null;
    private List<IOclExpression> arguments         	= new ArrayList<IOclExpression>();
    private IClassifier 				returnType		  	= null;	// sometimes the returntype of an operation is determined 
    												// by its source expression or argument expressions, if so
    												// it is stored in this attribute
    
    /** Creates a new instance of OperationCallExp */
    public OperationCallExp() {
    }

    /** Creates a new instance of OperationCallExp */
    public OperationCallExp(IOperation referred, List<IOclExpression> args) {
    	referredOperation = referred;
    	arguments         = args;
    }

    /** Creates a new instance of OperationCallExp */
    public OperationCallExp(IOperation referredOperation) {
        this.setReferredOperation(referredOperation);
    }

    public IClassifier getNodeType() {
        IClassifier result = null;
        if (returnType != null) {
        	result = returnType;
        } else {
            result = referredOperation.getReturnType();
        }
        Check.post("OperationCallExp.getNodeType: result should not be null", result != null);
        return result;
    }

    /** Getter for property referredOperation.
     * @return Value of property referredOperation.
     */
    public IOperation getReferredOperation() {
        return referredOperation;
    }
    
    /** Setter for property referredOperation.
     * @param referredOperation New value of property referredOperation.
     */
    public void setReferredOperation(IOperation referredOperation) {
        this.referredOperation = referredOperation;
    }
    
    /** Getter for property arguments.
     * @return Value of property arguments.
     */
    public List<IOclExpression> getArguments() {
        return arguments;
    }
    
    /** Sets the argument list for this operation to <i>arguments</i>.
     * @param arguments List[OclExpression].
     */
    public void setArguments(List<IOclExpression> arguments) {
        if( arguments != null ){
            this.arguments = arguments;
        }
    }
    /** Setter for property arguments.
     * @param arguments New value of property arguments.
     */
    public void addArgument(OclExpression argument) {
        this.arguments.add(argument);
    }    

    public String toString(){
    	String coupleSign = ".";
    	if (referredOperation.hasClassScope()) coupleSign = "::";
    	
        return coupleSign + referredOperation.getName() + "(" + OctopusUtil.collectionAsOclString(arguments, ", ") + ")";
    }

	/**
	 * Sets the returnType.
	 * @param returnType The returnType to set
	 */
	public void setReturnType(IClassifier returnType) {
		this.returnType = returnType;
	}

}
