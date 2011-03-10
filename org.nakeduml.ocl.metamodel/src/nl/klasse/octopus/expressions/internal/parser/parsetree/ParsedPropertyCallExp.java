package nl.klasse.octopus.expressions.internal.parser.parsetree;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import nl.klasse.tools.common.Util;


/** This class represents:
 *<UL> <LI> OclPropertyCallExp
 *     <LI> OclModelPropertyCallExp
 *     <LI> OclIteratorExp
 *     <LI> OclIterateExp
 *     <LI> OclAttributeCallExp
 *     <LI> OclNavigationCallExp
 *     <LI> OclVariableExp
 *     <LI> OclNavigationCallExp
 *     <LI> OclMessageExp (both ^ and ^^
 *     <LI> ... etc. ...
 *</UL>
 * @version $Id: ParsedPropertyCallExp.java,v 1.2 2008/01/19 13:48:05 annekekleppe Exp $
 */
public class ParsedPropertyCallExp extends ParsedOclExpression {

    public static final ParsedPropertyCallExp NULL_PARSED_PROPERTY_CALL_EXP =
                        new ParsedPropertyCallExp(null, false, null, null);
    public static final int UNKNOWN         	= 0;
    public static final int BINARY_OPERATOR = 1;
    public static final int UNARY_OPERATOR  = 2;
    public static final int ARROW_CALL      	= 3;
    public static final int DOT_CALL        		= 4;
    public static final int NO_SOURCE       	= 7;
    public static final String[] opText 			= { "?", "+/-/*", "-/not", "->", ".", "^", "^^", ":" };

    private int                       					propertyKind = UNKNOWN;
    private ParsedName                			propertyName = null;
    private List<ParsedOclExpression> 		arguments    = new ArrayList<ParsedOclExpression>();
    private boolean                   				isMarkedPre  = false;
    private boolean                   				hasBrackets = false;
    private List<ParsedVariableDeclaration>	iterators; 
    private ParsedVariableDeclaration 			result;
    private ParsedOclExpression       			source;


    /** Creates new ParsedPropertyCallExp */
    public ParsedPropertyCallExp(ParsedName name, boolean pre, ParsedIterators its,
                                 List<ParsedOclExpression> args)
    {
      propertyName = name;
      isMarkedPre  = pre;
      arguments    = args;
      if( its != null ){
        iterators    = its.getIterators();
        result       = its.getResult();
      }
    }

    public void setSource(ParsedOclExpression  src){
      source = src;
    }

    public ParsedOclExpression  getSource(){
      return source;
    }

    public void setPropertyKind(int t){
      propertyKind = t;
    }
    public int getPropertyKind(){
      return propertyKind;
    }

    public void addArgument(ParsedOclExpression exp){
        arguments.add(exp);
    }

    public List<ParsedOclExpression> getArguments(){
      if( arguments == null ) {
        arguments = new ArrayList<ParsedOclExpression>();
      }
        return arguments;
    }

    public ParsedOclExpression getArgument(int index){
      if( arguments.size() <= index ) {
        return null;
      } else {
        return (ParsedOclExpression ) arguments.get(index);
      }
    }

    public ParsedName getPropertyName() {
        return propertyName;
    }

    /** Getter for property markedPre.
     * @return Value of property markedPre.
     */
    public boolean isMarkedPre() {
        return isMarkedPre;
    }

    public void setMarkedPre(boolean markedPre) {
        this.isMarkedPre = markedPre;
    }

    public boolean hasBrackets() {
        return hasBrackets;
    }

    public void setBrackets(boolean hasThem) {
        hasBrackets = hasThem;
    }

    /** Getter for property iterator.
     * @return Value of property iterator.

    public ParsedVariableDeclaration getIterator() {
        return iterator;
    }*/

    public Iterator getIterators()
    {
        if( iterators == null ){
            return null;
        } else {
          return iterators.iterator();
        }
    }

    /** Setter for property iterator.
     * @param iterator New value of property iterator.

    public void setIterator(ParsedVariableDeclaration iterator) {
        this.iterator = iterator;
    }
    */

    /** Getter for property result.
     * @return Value of property result.
     */
    public ParsedVariableDeclaration getResult() {
        return result;
    }

    /** Setter for property result.
     * @param result New value of property result.
     */
    public void setResult(ParsedVariableDeclaration result) {
        this.result = result;
    }

    /** Get the priority of the propertycall if it is a binary operator.
     *  Otherwise return Priorities.MAX_PRIORITY.
     */
    public int getPriority(){
      if( getPropertyKind() == BINARY_OPERATOR ){
        return Priorities.getPriority( this.getPropertyName().getBaseToken().kind);
      } else {
        return Priorities.MAX_PRIORITY;
      }
    }

    public void arrangeOperators() {
      super.arrangeOperators();

      Iterator i = getIterators();
      while( i != null && i.hasNext() ){
        ( (ParsedVariableDeclaration) i.next() ).arrangeOperators();
      }

      if( result   != null ) { result.arrangeOperators(); }

      i = getArguments().iterator();
      while( i != null && i.hasNext() ){
        ( (ParsedOclExpression) i.next() ).arrangeOperators();
      }
    }

    public void applyPriority() {
      super.applyPriority();

      Iterator i = getIterators();
      while( i != null && i.hasNext() ){
        ( (ParsedVariableDeclaration) i.next() ).applyPriority();
      }

      if( result   != null ) { result.applyPriority(); }

      i = getArguments().iterator();
      while( i != null && i.hasNext() ){
        ( (ParsedOclExpression) i.next() ).applyPriority();
      }
    }

    public String toString() {
      String s = "";
      if( propertyKind == BINARY_OPERATOR ){
        s = " " + propertyName.toString() + " ";
      } else if( propertyKind == ARROW_CALL || propertyKind == DOT_CALL) {
        s = opText[propertyKind] + propertyName.toString();
//      } else if( propertyKind == DAKJE | propertyKind == DOUBLE_DAKJE) {
//        s = opText[propertyKind] + propertyName.toString();
      } else if( propertyKind == UNARY_OPERATOR ){
        s = " " + propertyName.toString() +  " ";
      } else {
        s = s +  propertyName.toString();
      }

      if( isMarkedPre ) { s = s + "@pre"; }

      if( hasBrackets() ) { s = s + "("; }
      if( (iterators != null) || (result != null) || (arguments != null) ){
        // if(  propertyKind != BINARY_OPERATOR )
        s = s + Util.collectionToString(iterators, ", ");
        if( result != null ){
          s = s + "; " + result.toString() ;
        }
        if( iterators != null ){
          s = s + " | ";
        }
        s = s + Util.collectionToString(arguments, ", ");
        //if(  propertyKind != BINARY_OPERATOR )
      }
      if( hasBrackets() ) {
        s = s + ")";
      }
      return this.appendAppliedPropertyString( s);
    }

    /* If operator prio == operator parent ==> argument up as appliedProperty
     * If operator prio  > operator.parent ==> Ok
     * If Operator prio <  operator.parent ==> argument up as appiedProperty
     */


}
