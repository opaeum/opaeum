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
import nl.klasse.octopus.expressions.INumericLiteralExp;
import nl.klasse.octopus.expressions.IOclExpression;
import nl.klasse.octopus.expressions.IOclMessageExp;
import nl.klasse.octopus.expressions.IOclStateLiteralExp;
import nl.klasse.octopus.expressions.IOclTypeLiteralExp;
import nl.klasse.octopus.expressions.IOclUndefinedLiteralExp;
import nl.klasse.octopus.expressions.IOperationCallExp;
import nl.klasse.octopus.expressions.IPropertyCallExp;
import nl.klasse.octopus.expressions.IRealLiteralExp;
import nl.klasse.octopus.expressions.IStringLiteralExp;
import nl.klasse.octopus.expressions.ITupleLiteralExp;
import nl.klasse.octopus.expressions.IUnspecifiedValueExp;
import nl.klasse.octopus.expressions.IVariableDeclaration;
import nl.klasse.octopus.expressions.IVariableExp;


/** An AstVisitor visits each node in an OCL Abstract Syntax Tree. 
 * <p>
 * Note that the <code>accept</code> operation that is part of the Visitor pattern
 * is not part of the IOclExpression interface. It is externalised in class <ref>AstWalker</ref>. 
 * In this way IOclExpression can remain as close to the OCL specification as possible.
 * <p>
 * Example usage:
 * <p>
 * class MyVisitor implements IAstVisitor { .... }
 * <p>
 *  IOclExpression exp = ....;
 * 	MyVisitor i = new MyVisitor (); <br>
 *	AstWalker walker = new AstWalker(); <br>
 *	Object result = walker.walk(exp, i); <br>
 *
 * @see AstWalker
 * @see IOclExpression 
 * 
 * @author  Jos Warmer
 * @version $Id: IAstVisitor.java,v 1.1 2006/03/01 19:13:35 jwarmer Exp $
 */
public interface IAstVisitor {

	/** Visits <code>exp</code>.
	 * <p>
	 * Puts the glue between an expression and its applied property. For instance, the AstToString visitor
	 * simply puts in a '.' or '->' between the expression and its applied property.
	 * @param exp the expression to be visited
	 * @param expResult the expression to be visistedResult; the result of visiting this expression without visiting the applied property
	 * @param appliedProperty the result of visiting the applied property only
	 * @return whatever is appropriate for the implementor of this interface
	 */
	public Object oclExpression			(IOclExpression exp, Object expResult, Object appliedProperty);
	/** Visits <code>exp</code>.
	 * @param exp the expression to be visisted
	 * @return whatever is appropriate for the implementor of this interface
	 */
	public Object associationEndCallExp	(IAssociationEndCallExp exp);
	/** Visits <code>exp</code>.
	 * @param exp the expression to be visisted
	 * @return whatever is appropriate for the implementor of this interface
	 */
	public Object associationClassCallExp	(IAssociationClassCallExp exp);
	/** Visits <code>exp</code>.
	 * @param exp the expression to be visisted
	 * @return whatever is appropriate for the implementor of this interface
	 */
	public Object attributeCallExp     	(IAttributeCallExp exp);
	/** Visits <code>exp</code>.
	 * @param exp the expression to be visisted
	 * @return whatever is appropriate for the implementor of this interface
	 */
	public Object booleanLiteralExp		(IBooleanLiteralExp exp);
	/** Visits <code>exp</code>.
	 * @param exp the expression to be visisted
	 * @return whatever is appropriate for the implementor of this interface
	 */
	public Object oclUndefinedLiteralExp(IOclUndefinedLiteralExp exp);
	/** Visits <code>exp</code>.
	 * <p>
	 * Because the <code>accept</code> operation that is part of the Visitor pattern
	 * is externalised in class <ref>AstWalker</ref>, we need the parameter <code>item</code>.
	 * @param exp the expression to be visisted
	 * @param item holds the result of <code>walk(exp.getItem(), visitor)</code>.
	 * @return whatever is appropriate for the implementor of this interface
	 */
	public Object collectionItem 		(ICollectionItem exp, Object item);
	/** Visits <code>exp</code>.
	 * <p>
	 * Because the <code>accept</code> operation that is part of the Visitor pattern
	 * is externalised in class <ref>AstWalker</ref>, we need the parameter <code>parts</code>.
	 * @param exp the expression to be visisted: the expression to be visisted
	 * @param parts holds the result of <code>walk(part, visitor)</code> for each part of the collection literal.
	 * @return whatever is appropriate for the implementor of this interface
	 */
	public Object collectionLiteralExp 	(ICollectionLiteralExp exp, List parts);
//	public Object collectionLiteralPart	(ICollectionLiteralPart 	exp);
	/** Visits <code>exp</code>.
	 * <p>
	 * Because the <code>accept</code> operation that is part of the Visitor pattern
	 * is externalised in class <ref>AstWalker</ref>, we need the parameters <code>first</code> and <code>last</code>.
	 * @param exp the expression to be visisted
	 * @param first holds the result of <code>walk(exp.getFirst(), visitor)</code>.
	 * @param last holds the result of <code>walk(exp.getLast(), visitor)</code>.
	 * @return whatever is appropriate for the implementor of this interface
	 */
	public Object collectionRange		(ICollectionRange exp, Object first, Object last);
	/** Visits <code>exp</code>.
	 * @param exp the expression to be visisted
	 * @return whatever is appropriate for the implementor of this interface
	 */
	public Object enumLiteralExp		(IEnumLiteralExp exp);
	/** Visits <code>exp</code>.
	 * <p>
	 * Because the <code>accept</code> operation that is part of the Visitor pattern
	 * is externalised in class <ref>AstWalker</ref>, we need the parameters <code>condition</code>, 
	 * <code>thenPart</code>, and <code>elsePart</code>.
	 * @param exp the expression to be visisted
	 * @param condition holds the result of <code>walk(exp.getCondition(), visitor)</code>.
	 * @param thenPart holds the result of <code>walk(exp.getThenExpression(), visitor)</code>.
	 * @param elsePart holds the result of <code>walk(exp.getElseExpression(), visitor)</code>.
	 * @return whatever is appropriate for the implementor of this interface
	 */
	public Object ifExp					(IIfExp exp, Object condition, Object thenPart, Object elsePart);
	/** Visits <code>exp</code>.
	 * @param exp the expression to be visisted
	 * @return whatever is appropriate for the implementor of this interface
	 */
	public Object integerLiteralExp		(IIntegerLiteralExp exp);	
	/** Visits <code>exp</code>.
	 * <p>
	 * Because the <code>accept</code> operation that is part of the Visitor pattern
	 * is externalised in class <ref>AstWalker</ref>, we need the parameters <code>iterators</code>, 
	 * <code>result</code>, and <code>body</code>.
	 * @param exp the expression to be visisted
	 * @param iterators holds the result of <code>walk(iter, visitor)</code> for each iterator declaration.
	 * @param result holds the result of <code>walk(exp.getResult(), visitor)</code>.
	 * @param body holds the result of <code>walk(exp.getBody(), visitor)</code>.
	 * @return whatever is appropriate for the implementor of this interface
	 */
	public Object iterateExp(IIterateExp exp, List iterators, Object result, Object body);
	/** Visits <code>exp</code>.
	 * <p>
	 * Because the <code>accept</code> operation that is part of the Visitor pattern
	 * is externalised in class <ref>AstWalker</ref>, we need the parameters <code>iterators</code> 
	 * and <code>body</code>.
	 * @param exp the expression to be visisted
	 * @param iterators holds the result of <code>walk(iter, visitor)</code> for each iterator declaration.
	 * @param body holds the result of <code>walk(exp.getBody(), visitor)</code>.
	 * @return whatever is appropriate for the implementor of this interface
	 */
	public Object iteratorExp(IIteratorExp exp, List iterators, Object body);
	
	/** Visits <code>exp</code>.
	 * <p>
	 * Because the <code>accept</code> operation that is part of the Visitor pattern
	 * is externalised in class <ref>AstWalker</ref>, we need the parameters <code>varDecl</code> 
	 * and <code>in</code>.
	 * @param exp the expression to be visisted
	 * @param varDecl  holds the result of <code>walk(exp.getVariable(), visitor))</code> for each iterator declaration.
	 * @param in  holds the result of <code>walk(exp.getIn(), visitor)</code>.
	 * @return whatever is appropriate for the implementor of this interface
	 */
	public Object letExp				(ILetExp exp, Object varDecl, Object in);
	
//	public Object literalExp			(ILiteralExp 			exp);
//	public Object loopExp				(ILoopExp 				exp);
//	public Object modelPropCallExp		(IModelPropertyCallExp 	exp);
//	public Object navigationCallExp		(INavigationCallExp 		exp);
	
	/** Visits <code>exp</code>.
	 * @param exp the expression to be visisted
	 * @return whatever is appropriate for the implementor of this interface
	 */
	public Object numericLiteralExp		(INumericLiteralExp exp);
	/** Visits <code>exp</code>.
	 * @param exp the expression to be visisted
	 * @return whatever is appropriate for the implementor of this interface
	 */
	public Object unspecifiedValueExp	(IUnspecifiedValueExp	exp);
	/** Visits <code>exp</code>.
	 * @param exp the expression to be visisted
	 * @return whatever is appropriate for the implementor of this interface
	 */
	public Object oclStateLiteralExp 	(IOclStateLiteralExp exp);
	/** Visits <code>exp</code>.
	 * @param exp the expression to be visisted
	 * @return whatever is appropriate for the implementor of this interface
	 */
	public Object oclTypeLiteralExp		(IOclTypeLiteralExp exp);
	/** Visits <code>exp</code>.
	 * <p>
	 * Because the <code>accept</code> operation that is part of the Visitor pattern
	 * is externalised in class <ref>AstWalker</ref>, we need the parameter <code>args</code>.
	 * @param exp the expression to be visisted
	 * @param args  holds the result of <code>walk(arg, visitor))</code> for each actual parameter to the operation call.
	 * @return whatever is appropriate for the implementor of this interface
	 */
	public Object operationCallExp		(IOperationCallExp exp, List args);
//	public Object primitiveLiteralExp	(IPrimitiveLiteralExp exp);

	/** Visits <code>exp</code>.
	 * @param exp the expression to be visisted
	 * @return whatever is appropriate for the implementor of this interface
	 */
	public Object propertyCallExp		(IPropertyCallExp exp);
	/** Visits <code>exp</code>.
	 * @param exp the expression to be visisted
	 * @return whatever is appropriate for the implementor of this interface
	 */
	public Object realLiteralExp 		(IRealLiteralExp exp);
	/** Visits <code>exp</code>.
	 * @param exp the expression to be visisted
	 * @return whatever is appropriate for the implementor of this interface
	 */
	public Object stringLiteralExp 		(IStringLiteralExp exp);
	/** Visits <code>exp</code>.
	 * <p>
	 * Because the <code>accept</code> operation that is part of the Visitor pattern
	 * is externalised in class <ref>AstWalker</ref>, we need the parameter <code>parts</code>.
	 * @param exp the expression to be visisted
	 * @param parts holds the result of <code>walk(exp.getVariable(), visitor))</code> for each tuple part.
	 * @return whatever is appropriate for the implementor of this interface
	 */
	public Object tupleLiteralExp		(ITupleLiteralExp exp, List parts);
	/** Visits <code>exp</code>.
	 * <p>
	 * Because the <code>accept</code> operation that is part of the Visitor pattern
	 * is externalised in class <ref>AstWalker</ref>, we need the parameter <code>initExpression</code>.
	 * @param exp the expression to be visisted
	 * @param initExpression holds the result of <code>walk(exp.getInitExpression(), visitor))</code>.
	 * @return whatever is appropriate for the implementor of this interface
	 */
	public Object variableDeclaration	(IVariableDeclaration exp, Object initExpression);
	/** Visits <code>exp</code>.
	 * @param exp the expression to be visisted
	 * @return whatever is appropriate for the implementor of this interface
	 */
	public Object variableExp			(IVariableExp exp);
	
	/**
	 * @param exp
	 * @param target
	 * @param args
	 * @return
	 */
	public Object oclMessageExp(IOclMessageExp exp, Object target, List args);

}
