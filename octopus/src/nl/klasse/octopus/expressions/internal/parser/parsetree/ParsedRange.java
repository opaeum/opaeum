/** Copyright Klasse Objecten
 */
package nl.klasse.octopus.expressions.internal.parser.parsetree;

import nl.klasse.octopus.expressions.internal.parser.javacc.Token;

/** @author  Jos Warmer
 * @version $Id: ParsedRange.java,v 1.1 2006/03/01 19:13:27 jwarmer Exp $
 */
public class ParsedRange extends ParsedOclExpression {

    /** Creates a new instance of ParsedRange
      */
    public ParsedRange(ParsedOclExpression lower, ParsedOclExpression upper) {
        this.lower = lower;
        this.upper = upper;
    }

    private ParsedOclExpression lower = null;
    private ParsedOclExpression upper = null;

    public ParsedOclExpression getLower()
    {
        return lower;
    }

    public ParsedOclExpression getUpper()
    {
        return upper;
    }

    public void setAppliedProperty(ParsedPropertyCallExp propCall){
    }

    /** Get the property that is applied to this expression.
     */
    public ParsedPropertyCallExp getAppliedProperty(){
      return null ;
    }

    /** Get the last appliedProperty at the end of the expression.
     */
    public ParsedPropertyCallExp getLastAppliedProperty(){
      return null;
    }

    /** Should never be called, since this is an abstract class.
     *  return something that shows in any way.
     */
    public String toString(){
      return lower.toString() + " .. " + upper.toString();
    }

    public String appendAppliedPropertyString(String base)
    {
        if( getAppliedProperty() != null ) {
            return base +  getAppliedProperty().toString();
        } else {
            return base;
        }
    }


    /** Re-arrange this expression such that all nested operators will be on the
     *  same level. During parsing they become all bnested.
     *  The next step (in appllyPriority() ) is to re-arrange the tree to reflect
     *   the different priorities.
     */
    public void arrangeOperators() {
        lower.arrangeOperators();
        upper.arrangeOperators();
        super.arrangeOperators();
    }

    /** find the first appliedProperty that is a binary operator
     *  and has a priority between 'minPrio' and 'maxPrio'
     */
    public ParsedPropertyCallExp findAppliedOperatorWithPrio(int minPrio, int maxPrio) {
      return null;
    }
    /** Find first applied binary operator
     **/
    public ParsedPropertyCallExp findAppliedOperator() {
      return null;
    }

    /** Re-arrange the parse tree to reflect the predefined priorities of the
     *  binary operators.
     */
    public void applyPriority(){
        lower.applyPriority();
        upper.applyPriority();
        super.arrangeOperators();
    }

    // ------------------  Token management -----------------
    public Token getStart()
    {
        return lower.getStart();
    }
    public Token getEnd()
    {
        return upper.getEnd();
    }
}
