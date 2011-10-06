package nl.klasse.octopus.expressions.internal.parser.parsetree;

import java.util.Iterator;
import java.util.ListIterator;

import nl.klasse.tools.common.Check;


/* @version $Id: ParsedOclExpression.java,v 1.1 2006/03/01 19:13:27 jwarmer Exp $
 */
public abstract class ParsedOclExpression extends ParsedElement {

    /** Creates new ParsedOclExpression */
    public ParsedOclExpression() {
    }

    private ParsedPropertyCallExp appliedProperty = null;

    public void setAppliedProperty(ParsedPropertyCallExp propCall){
      appliedProperty = propCall;
      if( appliedProperty != null ){ appliedProperty.setSource(this) ;};
    }

    /** Get the property that is applied to this expression.
     */
    public ParsedPropertyCallExp getAppliedProperty(){
      return appliedProperty ;
    }

    /** Get the last appliedProperty at the end of the expression.
     */
    public ParsedPropertyCallExp getLastAppliedProperty(){
      ParsedPropertyCallExp last      = this.getAppliedProperty() ;
      ParsedPropertyCallExp tmpPlusOne = this.getAppliedProperty() ;

      while( tmpPlusOne != null ){
        last = tmpPlusOne;
        tmpPlusOne = last.getAppliedProperty();
      }
      return last;
    }

    /** Should never be called, since this is an abstract class.
     *  return something that shows in any way.
     */
    public String toString(){
      return "ABSTRACT ParsedOclExpression";
    }

    /** A helper operation that is reused in all subclasses
     */
    protected String appendAppliedPropertyString(String base)
    {
    	ParsedPropertyCallExp myProp = getAppliedProperty();
        if( myProp != null ) {
        	if (myProp.getPropertyKind() == ParsedPropertyCallExp.UNARY_OPERATOR){
				return myProp.toString() + base;
        	} else {
	            return base +  myProp.toString();
			}
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
      ParsedPropertyCallExp index = null;
      index = this.getAppliedProperty();

      // Loop through all appliedProperties until the end of the expression
      while( index != null ) {
        // For binary operators find whether the arguments have operators inside
        if( index.getPropertyKind() == ParsedPropertyCallExp.BINARY_OPERATOR ){
          // find each argument that has an operator and pull the operator up
          ListIterator args = index.getArguments().listIterator();
          while( args.hasNext() ){
            ParsedOclExpression    arg = (ParsedOclExpression ) args.next();
            ParsedPropertyCallExp operator = arg.findAppliedOperator();
            while( operator != null ){
              ParsedPropertyCallExp tmp = index.getAppliedProperty();
              ParsedOclExpression src = operator.getSource();
              index.setAppliedProperty(operator);
              if( operator.getLastAppliedProperty() != null ){
                 operator.getLastAppliedProperty().setAppliedProperty(tmp);
              }
              src.setAppliedProperty(null);
              // operator = null ;
              operator = arg.findAppliedOperator();
              if( operator != null ) System.out.println("ERROR IN ARRANGEOPERATOR");
            }
            // All operators in arg are moved behind 'index'.
          }
        }
        // Recursively arrange the same for each argument expression
        ListIterator args = index.getArguments().listIterator();
        if( args != null ){
          while( args.hasNext() ){
            ParsedOclExpression arg = (ParsedOclExpression ) args.next();
            arg.arrangeOperators();
          }
        }
// TBD multiple        if( index.getIterator() != null ) { index.getIterator().arrangeOperators(); }
        Iterator iter = index.getIterators();
        if( iter != null ){
          while( iter.hasNext() ){
            ParsedVariableDeclaration varDecl = (ParsedVariableDeclaration) iter.next();
            varDecl.arrangeOperators();
          }
        }


        if( index.getResult  () != null ) { index.getResult  ().arrangeOperators(); }
        index = index.getAppliedProperty();
      }
    }

    /** find the first appliedProperty that is a binary operator
     *  and has a priority between 'minPrio' and 'maxPrio'
     */
    public ParsedPropertyCallExp findAppliedOperatorWithPrio(int minPrio, int maxPrio) {
      ParsedPropertyCallExp index = this.getAppliedProperty();
      int prio;
      while( index != null ) {
        if( index.getPropertyKind() == ParsedPropertyCallExp.BINARY_OPERATOR ){
          prio = Priorities.getPriority( index.getPropertyName().getBaseToken().kind);
          if( (prio > minPrio) && (prio < maxPrio) ){
            return index;
          }
        }
        index = index.getAppliedProperty();
      }
      return null;
    }
    /** Find first applied binary operator
     **/
    public ParsedPropertyCallExp findAppliedOperator() {
      return findAppliedOperatorWithPrio(Priorities.MIN_PRIORITY, Priorities.MAX_PRIORITY);
    }

    /** Re-arrange the parse tree to reflect the predefined priorities of the
     *  binary operators.
     */
    public void applyPriority(){
        ParsedPropertyCallExp first  = this.findAppliedOperator();
        ParsedPropertyCallExp second = null;
        ParsedPropertyCallExp third  = null;

        while( first != null ) {
            second = first.findAppliedOperator();
            // if next operator with higher priority
            if( second != null && second.getPriority() > first.getPriority() ){
                // find third operator with priority less or equal to first
                third = second.findAppliedOperatorWithPrio(Priorities.MIN_PRIORITY, first.getPriority()+1  );
      
                ParsedOclExpression arg = first.getArgument(0);
                if( arg.getAppliedProperty() == null ){
                    arg.setAppliedProperty(second);
                } else {
                    arg.getLastAppliedProperty().setAppliedProperty(second);
                }
                if( third != null ){
                    third.getSource().setAppliedProperty(null);
                }
                first.setAppliedProperty(third);
            }
            first = first.findAppliedOperator();
        }

        first = getAppliedProperty();
        if( first != null ){
            first.applyPriority();
        }
        // Now make sure the unary operators are donme correctly.
         applyUnaryPriorities();
      
    }


    /** The assumption is that the unary boperator have a priority that is always
     *  higher than the binaru operators.  Otherwise this algorithm does not work !
     * Also, all unary operators must have equal priority.
     */
    public void applyUnaryPriorities() {
        ParsedPropertyCallExp unaryCall = this.findAppliedUnary();
        if( unaryCall == null ) return;
//        System.out.println("Parser tree before unary: '" + this.toString() + "'");
        // now move it until you find the end
        ParsedPropertyCallExp next           = unaryCall.getAppliedProperty();
        if( next == null ) { return; } // nothing to do, its at the end already.

        ParsedPropertyCallExp justBeforeNext = unaryCall;

        int kind = next.getPropertyKind() ;
        if( !( (kind == ParsedPropertyCallExp.ARROW_CALL)
//                  ||
//                  (kind == ParsedPropertyCallExp.DAKJE)
                  ||
                  (kind == ParsedPropertyCallExp.DOT_CALL)
                  ||
                  (kind == ParsedPropertyCallExp.UNARY_OPERATOR)
//                  ||
//                  (kind == ParsedPropertyCallExp.DOUBLE_DAKJE)
           ) ) 
        {
            return;
        }


        while( (next != null) && 
               (  (kind == ParsedPropertyCallExp.ARROW_CALL)
//                  ||
//                  (kind == ParsedPropertyCallExp.DAKJE)
                  ||
                  (kind == ParsedPropertyCallExp.DOT_CALL)
                  ||
                  (kind == ParsedPropertyCallExp.UNARY_OPERATOR)
//                  ||
//                  (kind == ParsedPropertyCallExp.DOUBLE_DAKJE) 
               ) )
        {
             justBeforeNext = next;
             next = next.getAppliedProperty();   
             if( next != null ) {
                kind = next.getPropertyKind() ;
             }
        }
        ParsedOclExpression source = unaryCall.getSource();
        Check.isTrue("unary operator must have a source", source != null);
        
//        System.out.println("Moving " + unaryCall.getPropertyName().toString() + 
//                           "\nfrom '" + source.toString() + "' " +
//                           "\nto " + justBeforeNext.getPropertyName().toString());
        source.setAppliedProperty(unaryCall.getAppliedProperty());
        next = justBeforeNext.getAppliedProperty(); // tmp save
        justBeforeNext.setAppliedProperty(unaryCall);
        unaryCall.setAppliedProperty(next);
                
//        System.out.println("Parser tree after unary: '" + this.toString() + "'");
    }

    /** find the first appliedProperty that is a unary operator
     */
    public ParsedPropertyCallExp findAppliedUnary() {
        ParsedPropertyCallExp index = this.getAppliedProperty();
        while( index != null ) {
            if( index.getPropertyKind() == ParsedPropertyCallExp.UNARY_OPERATOR ){
                return index;
            }
            index = index.getAppliedProperty();
        }
        return null;
    }

}
