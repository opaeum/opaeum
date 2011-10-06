/*
 * ParsedUnspecifiedValue.java
 *
 * Created on June 4, 2002, 4:28 PM
 */
package nl.klasse.octopus.expressions.internal.parser.parsetree;

import nl.klasse.octopus.expressions.internal.parser.javacc.Token;

/** @author  Jos Warmer
 * @version $Id: ParsedUnspecifiedValue.java,v 1.1 2006/03/01 19:13:27 jwarmer Exp $
 */
public class ParsedUnspecifiedValue extends ParsedOclExpression {

    /** Creates a new instance of ParsedUnspecifiedValue
      */
    public ParsedUnspecifiedValue(ParsedName typeName) {
        this.typeName = typeName;
    }

    private ParsedName typeName = null;

    public ParsedName getTypeName()
    {
        return typeName;
    }

    public String toString()
    {
        if( typeName == null ){
            return "?";
        } else {
            return "? : " + typeName.toString();
        }
    }

    public void setAppliedProperty(ParsedPropertyCallExp propCall){
        // do nothing
    }

    /** Get the property that is applied to this expression.
     */
    public ParsedPropertyCallExp getAppliedProperty(){
      return null;
    }

    /** Get the last appliedProperty at the end of the expression.
     */
    public ParsedPropertyCallExp getLastAppliedProperty(){
      return null;
    }

   /** Re-arrange this expression such that all nested operators will be on the
     *  same level. During parsing they become all bnested.
     *  The next step (in appllyPriority() ) is to re-arrange the tree to reflect
     *   the different priorities.
     */
    public void arrangeOperators() {
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
    }

    // ---------------------- Token management ---------------------
    public Token getEnd()
    {
        if( typeName != null ){
            return typeName.getEnd();
        } else {
            return getStart();
        }
    }

}
