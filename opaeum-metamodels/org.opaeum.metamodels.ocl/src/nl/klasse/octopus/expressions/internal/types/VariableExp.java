/** Copyright Klasse Objecten
 */
package nl.klasse.octopus.expressions.internal.types;

import nl.klasse.octopus.expressions.IVariableDeclaration;
import nl.klasse.octopus.expressions.IVariableExp;
import nl.klasse.octopus.model.IClassifier;


/** @author  Jos Warmer
 * @version $Id: VariableExp.java,v 1.1 2006/03/01 19:13:25 jwarmer Exp $
 */
public class VariableExp extends OclExpression implements IVariableExp {

    private IVariableDeclaration referredVariable = null;
    
    /** Creates a new instance of VariableExp */
    public VariableExp(IVariableDeclaration referredVariable ) {
        this.referredVariable = referredVariable;
    }

    public IClassifier getNodeType() {
        return referredVariable.getType();
    }

    public String getName() {
        return referredVariable.getName();
    }

    /** Getter for property referredVariable.
     * @return Value of property referredVariable.
     */
    public IVariableDeclaration getReferredVariable() {
        return referredVariable;
    }
    
    /** Setter for property referredVariable.
     * @param referredVariable New value of property referredVariable.
     */
    public void setReferredVariable(IVariableDeclaration referredVariable) {
        this.referredVariable = referredVariable;
    }
    
    public String toString() {
        return referredVariable.getName();
    }

}
