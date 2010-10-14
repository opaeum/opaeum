/** (c) Copyright 2002, Klasse Objecten
 */
package nl.klasse.octopus.expressionVisitors;

import java.util.List;

import nl.klasse.octopus.expressions.IAssociationClassCallExp;
import nl.klasse.octopus.expressions.IAssociationEndCallExp;
import nl.klasse.octopus.expressions.IAttributeCallExp;
import nl.klasse.octopus.expressions.IBooleanLiteralExp;
import nl.klasse.octopus.expressions.ICollectionItem;
import nl.klasse.octopus.expressions.ICollectionLiteralExp;
import nl.klasse.octopus.expressions.ICollectionRange;
import nl.klasse.octopus.expressions.IEnumLiteralExp;
import nl.klasse.octopus.expressions.IIfExp;
import nl.klasse.octopus.expressions.IIntegerLiteralExp;
import nl.klasse.octopus.expressions.IIterateExp;
import nl.klasse.octopus.expressions.IIteratorExp;
import nl.klasse.octopus.expressions.ILetExp;
import nl.klasse.octopus.expressions.IOclStateLiteralExp;
import nl.klasse.octopus.expressions.IOclTypeLiteralExp;
import nl.klasse.octopus.expressions.IOclUndefinedLiteralExp;
import nl.klasse.octopus.expressions.IOperationCallExp;
import nl.klasse.octopus.expressions.IRealLiteralExp;
import nl.klasse.octopus.expressions.IStringLiteralExp;
import nl.klasse.octopus.expressions.ITupleLiteralExp;
import nl.klasse.octopus.expressions.IUnspecifiedValueExp;
import nl.klasse.octopus.expressions.IVariableDeclaration;
import nl.klasse.octopus.expressions.IVariableExp;


/** An AstVisitorBackwards visits each node in an OCL Abstract Syntax Tree. 
 * <p>
 * Note that the <code>accept</code> operation that is part of the Visitor pattern
 * is not part of the IOclExpression interface. It is externalised in class <ref>AstWalker</ref>. 
 * In this way IOclExpression can remain as close to the OCL specification as possible.
 * <p>
 * Example usage:
 * <p>
 * class MyVisitor implements IAstVisitorBackwards { .... }
 * <p>
 *  IOclExpression exp = ....;
 * 	MyVisitor i = new MyVisitor (); <br>
 *	AstWalkerBackwards walker = new AstWalkerBackwards(); <br>
 *	Object result = walker.walk(exp, i); <br>

 * @see AstWalkerBackwards
 * @see IOclExpression 
 * 
 */
public interface IAstVisitorBackwards {

 	/** Visits <code>exp</code>.
	 * @param exp the expression to be visisted
	 * @param source holds the result of visiting exp.getSource()
	 * @return whatever is appropriate for the implementor of this interface
	 */
	public Object associationEndCallExp (IAssociationEndCallExp exp, Object source);
	/** Visits <code>exp</code>.
	 * @param exp the expression to be visited.
	 * @param source holds the result of visiting exp.getSource()
	 * @return whatever is appropriate for the implementor of this interface
	 */
	public Object attributeCallExp      (IAttributeCallExp exp, Object source);
	/** Visits <code>exp</code>.
	 * @param exp the expression to be visited.
	 * @return whatever is appropriate for the implementor of this interface
	 */
	/** Visits <code>exp</code>.
	 * @param exp the expression to be visited.
	 * @return whatever is appropriate for the implementor of this interface
	 */
	public Object booleanLiteralExp     (IBooleanLiteralExp exp);
	/** Visits <code>exp</code>.
	 * @param exp the expression to be visited.
	 * @return whatever is appropriate for the implementor of this interface
	 */
	public Object oclUndefinedLiteralExp(IOclUndefinedLiteralExp exp);
	
	/** Visits <code>exp</code>.
	 * @param exp the expression to be visited.
	 * @param parts holds the result of visiting all parts of this collection literal.
	 * @return whatever is appropriate for the implementor of this interface
	 */
	public Object collectionLiteralExp (ICollectionLiteralExp exp, List parts);
	/** Visits <code>exp</code>.
	 * @param exp the expression to be visited.
	 * @param item holds the result of <code>walk(exp.getItem(), visitor)</code>.
	 * @return whatever is appropriate for the implementor of this interface
	 */
	public Object collectionItem       (ICollectionItem exp, Object item);
	/** Visits <code>exp</code>.
	 * @param exp the expression to be visited.
	 * @param first holds the result of <code>walk(exp.getFirst(), visitor)</code>.
	 * @param last holds the result of <code>walk(exp.getLast(), visitor)</code>.
	 * @return whatever is appropriate for the implementor of this interface
	 */
	public Object collectionRange		 (ICollectionRange exp, Object first, Object last);
	
	/** Visits <code>exp</code>.
	 * @param exp the expression to be visited.
	 * @return whatever is appropriate for the implementor of this interface
	 */
	public Object realLiteralExp       (IRealLiteralExp exp);
	/** Visits <code>exp</code>.
	 * @param exp the expression to be visited.
	 * @return whatever is appropriate for the implementor of this interface
	 */
	public Object stringLiteralExp     (IStringLiteralExp exp);
	/** Visits <code>exp</code>.
	 * @param exp the expression to be visited.
	 * @return whatever is appropriate for the implementor of this interface
	 */
	public Object integerLiteralExp    (IIntegerLiteralExp exp);
	/** Visits <code>exp</code>.
	 * @param exp the expression to be visited.
	 * @return whatever is appropriate for the implementor of this interface
	 */
	public Object oclTypeLiteralExp    (IOclTypeLiteralExp exp);
	/** Visits <code>exp</code>.
	 * @param exp the expression to be visited.
	 * @return whatever is appropriate for the implementor of this interface
	 */
	public Object oclStateLiteralExp   (IOclStateLiteralExp exp);
	
	/** Visits <code>exp</code>.
	 * @param exp the expression to be visited.
	 * @param condition holds the result of <code>walk(exp.getCondition(), visitor)</code>.
	 * @param thenPart holds the result of <code>walk(exp.getThenExpression(), visitor)</code>.
	 * @param elsePart holds the result of <code>walk(exp.getElseExpression(), visitor)</code>.
	 * @return whatever is appropriate for the implementor of this interface
	 */
	public Object ifExp(IIfExp exp, Object condition, Object thenPart, Object elsePart);
	
	/** Visits <code>exp</code>.
	 * @param exp the expression to be visited.
	 * @param source holds the result of visiting exp.getSource()
	 * @param resultVar holds the result of <code>walk(exp.getResult(), visitor)</code>.
	 * @param iterators holds the result of <code>walk(iter, visitor)</code> for each iterator declaration.
	 * @param body holds the result of <code>walk(exp.getBody(), visitor)</code>.
	 * @return whatever is appropriate for the implementor of this interface
	 */
	public Object iterateExp(IIterateExp exp, Object source, Object resultVar, List iterators, Object body);
	
	/** Visits <code>exp</code>.
	 * @param exp the expression to be visited.
	 * @param source holds the result of visiting exp.getSource()
	 * @param iterators holds the result of <code>walk(iter, visitor)</code> for each iterator declaration.
	 * @param body holds the result of <code>walk(exp.getBody(), visitor)</code>.
	 * @return whatever is appropriate for the implementor of this interface
	 */
	public Object iteratorExp(IIteratorExp exp, Object source, List iterators, Object body);
	
	/** Visits <code>exp</code>.
	 * @param exp the expression to be visited.
	 * @param varDecl  holds the result of <code>walk(exp.getVariable(), visitor))</code> for each iterator declaration.
	 * @param in  holds the result of <code>walk(exp.getIn(), visitor)</code>.
	 * @return whatever is appropriate for the implementor of this interface
	 */
	public Object letExp(ILetExp exp, Object varDecl, Object in);
	
	/** Visits <code>exp</code>.
	 * @param exp the expression to be visited.
	 * @param source holds the result of visiting exp.getSource()
	 * @param args  holds the result of <code>walk(arg, visitor))</code> for each actual parameter to the operation call.
	 * @return whatever is appropriate for the implementor of this interface
	 */
	public Object operationCallExp(IOperationCallExp exp, Object source, List args);
	  
	/** Visits <code>exp</code>.
	 * @param exp the expression to be visited.
	 * @param parts holds the result of visiting all parts of this tuple literal.
	 * @return whatever is appropriate for the implementor of this interface
	 */
	public Object tupleLiteralExp(ITupleLiteralExp exp, List parts);
	  
	/** Visits <code>exp</code>.
	 * @param exp the expression to be visited.
	 * @param initExpression holds the result of <code>walk(exp.getInitExpression(), visitor))</code>
	 * @return whatever is appropriate for the implementor of this interface
	 */
	public Object variableDeclaration(IVariableDeclaration exp, Object initExpression);
	
	/** Visits <code>exp</code>.
	 * @param exp the expression to be visited.
	 * @return whatever is appropriate for the implementor of this interface
	 */
	public Object variableExp(IVariableExp exp);
	/** Visits <code>exp</code>.
	 * @param exp the expression to be visited.
	 * @return whatever is appropriate for the implementor of this interface
	 */
	public Object enumLiteralExp(IEnumLiteralExp exp);
	/** Visits <code>exp</code>.
	 * @param exp the expression to be visited.
	 * @return whatever is appropriate for the implementor of this interface
	 */
	public Object unspecifiedValueExp(IUnspecifiedValueExp exp);
	/** Visits <code>exp</code>.
	 * @param exp the expression to be visited.
	 * @param source holds the result of visiting exp.getSource()
	 * @return whatever is appropriate for the implementor of this interface
	 */
	public Object associationClassCallExp(IAssociationClassCallExp exp, Object source);
}
