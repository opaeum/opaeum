/** Copyright Klasse Objecten
 */
package nl.klasse.octopus.expressions.internal.types;

import nl.klasse.octopus.expressions.ILetExp;
import nl.klasse.octopus.expressions.IVariableDeclaration;
import nl.klasse.octopus.model.IClassifier;


/** @author  Jos Warmer
 * @version $Id: LetExp.java,v 1.1 2006/03/01 19:13:25 jwarmer Exp $
 */
public class LetExp extends OclExpression implements ILetExp {

    private IVariableDeclaration variable = null;
    private OclExpression       in       = null;
    
    /** Creates a new instance of LetExp */
    public LetExp() {
    }

    public IClassifier getNodeType() {
        return in.getExpressionType();
    }
    
    /** Getter for property variable.
     * @return Value of property variable.
     */
    public IVariableDeclaration getVariable() {
        return variable;
    }
    
    /** Setter for property variable.
     * @param variable New value of property variable.
     */
    public void setVariable(IVariableDeclaration variable) {
        this.variable = variable;
    }
    
    /** Getter for property in.
     * @return Value of property in.
     */
    public OclExpression getIn() {
        return in;
    }
    
    /** Setter for property in.
     * @param in New value of property in.
     */
    public void setIn(OclExpression in) {
        this.in = in;
    }
    
    public String toString() {
        return "let " + variable.toString() + " in " + in.asOclString();
    }
    
}
