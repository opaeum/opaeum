/** Copyright Klasse Objecten
 */
package nl.klasse.octopus.expressions.internal.types;

import nl.klasse.octopus.expressions.IIfExp;
import nl.klasse.octopus.model.IClassifier;


/** @author  Jos Warmer
 * @version $Id: IfExp.java,v 1.1 2006/03/01 19:13:25 jwarmer Exp $
 */
public class IfExp extends OclExpression implements IIfExp {

    private OclExpression condition      = null;
    private OclExpression thenExpression = null;
    private OclExpression elseExpression = null;
    
    /** Creates a new instance of IfExp */
    public IfExp(OclExpression condition, 
                 OclExpression thenExpression, 
                 OclExpression elseExpression) {
        this.condition      = condition      ;
        this.thenExpression = thenExpression ;
        this.elseExpression = elseExpression ;
    }

    /** Getter for property condition.
     * @return Value of property condition.
     */
    public nl.klasse.octopus.expressions.internal.types.OclExpression getCondition() {
        return condition;
    }
    
    /** Setter for property condition.
     * @param condition New value of property condition.
     */
    public void setCondition(nl.klasse.octopus.expressions.internal.types.OclExpression condition) {
        this.condition = condition;
    }
    
    /** Getter for property thenExpression.
     * @return Value of property thenExpression.
     */
    public nl.klasse.octopus.expressions.internal.types.OclExpression getThenExpression() {
        return thenExpression;
    }
    
    /** Setter for property thenExpression.
     * @param thenExpression New value of property thenExpression.
     */
    public void setThenExpression(nl.klasse.octopus.expressions.internal.types.OclExpression thenExpression) {
        this.thenExpression = thenExpression;
    }
    
    /** Getter for property elseExpression.
     * @return Value of property elseExpression.
     */
    public nl.klasse.octopus.expressions.internal.types.OclExpression getElseExpression() {
        return elseExpression;
    }
    
    /** Setter for property elseExpression.
     * @param elseExpression New value of property elseExpression.
     */
    public void setElseExpression(nl.klasse.octopus.expressions.internal.types.OclExpression elseExpression) {
        this.elseExpression = elseExpression;
    }
    
    public String toString() {
        return "if "    + condition     .toString() +
               " else " + elseExpression.toString() +
               " then " + thenExpression.toString() +
               " endif\n";
    }
    
    public IClassifier getNodeType() {
        return thenExpression.getExpressionType();
    }

}
