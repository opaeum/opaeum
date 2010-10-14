/*
 * Created on Jan 19, 2004
 *
 * (c) Copyright 2004, Klasse Obejcten
 */
package nl.klasse.octopus.expressionVisitors;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import nl.klasse.octopus.expressions.IAssociationClassCallExp;
import nl.klasse.octopus.expressions.IAssociationEndCallExp;
import nl.klasse.octopus.expressions.IAttributeCallExp;
import nl.klasse.octopus.expressions.IBooleanLiteralExp;
import nl.klasse.octopus.expressions.ICollectionItem;
import nl.klasse.octopus.expressions.ICollectionLiteralExp;
import nl.klasse.octopus.expressions.ICollectionLiteralPart;
import nl.klasse.octopus.expressions.ICollectionRange;
import nl.klasse.octopus.expressions.IEnumLiteralExp;
import nl.klasse.octopus.expressions.IIfExp;
import nl.klasse.octopus.expressions.IIntegerLiteralExp;
import nl.klasse.octopus.expressions.IIterateExp;
import nl.klasse.octopus.expressions.IIteratorExp;
import nl.klasse.octopus.expressions.ILetExp;
import nl.klasse.octopus.expressions.ILiteralExp;
import nl.klasse.octopus.expressions.ILoopExp;
import nl.klasse.octopus.expressions.IModelPropertyCallExp;
import nl.klasse.octopus.expressions.INavigationCallExp;
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


/** An AstWalker navigates the full OCL Abstract Syntax Tree. It implements 
 * the <code>accept</code> operation of the Visitor pattern for an IOclExpression
 * object. In this way the <code>accept</code> operation need not be part of the IOclExpression
 * interface, which can be as close a representation of the OclExpression concept 
 * from the OCL specification as can be.
 * <p>
 * OCL expressions can be navigated from the last applied property to the source, or the other way around.
 * For example, the expression <code>aap.noot.mies()</code> can be navigated in the order mies - noot - aap,
 * or aap - noot - mies. This class navigates the expression from source to last applied property, that is 
 * in the order aap - noot - mies. The class AstWalkerBackwards implements the other navigation route.
 * <p>
 * Users must implement IAstVisitor and call <code>walk(expression, visitor)</code>.
 * <p>
 * Example usage:
 * <p>
 * class MyVisitor implements IAstVisitor { .... }
 * <p>
 * 	MyVisitor i = new MyVisitor (); <br>
 *	AstWalker walker = new AstWalker(); <br>
 *	Object result = walker.walk(this, i); <br>
 *
 * @see AstWalkerBackwards
 * @see IAstVisitor
 * @author  Jos Warmer
 */
public class AstWalker {

	/** Navigates the complete Abstract Syntax Tree and ensures that 
	 * each node is visited by <code>visitor</code>.
	 * @param exp the expression to be visited
	 * @param visitor the visitor that should visit the expression
	 * @return
	 */
	public Object walk(IOclExpression exp, IAstVisitor visitor) {
		Object result = visitNode(exp, visitor);

		IOclExpression source = exp;
		IPropertyCallExp next = exp.getAppliedProperty();
		while( next != null ){
			Object nextResult = visitPropertyCallExp(next, visitor);
			result = visitor.oclExpression(source, result, nextResult);
			source = next;
			next   = next.getAppliedProperty();
		}
		return result;
	}

	private Object visitNode(IOclExpression exp, IAstVisitor visitor) {
		Object result = null;
		if( exp instanceof IIfExp ) {
			result = visitIfExp((IIfExp)exp, visitor);
		} else if( exp instanceof ILiteralExp ) {
			result = visitLiteralExp((ILiteralExp)exp, visitor);
		} else if( exp instanceof IPropertyCallExp) {
			result = visitPropertyCallExp((IPropertyCallExp)exp, visitor);
		} else if( exp instanceof ILetExp ) {
			result = visitLetExp((ILetExp )exp, visitor);
		} else if( exp instanceof IVariableExp ) {
			result = visitVariableExp((IVariableExp )exp, visitor);
		} else if( exp instanceof IUnspecifiedValueExp ) {
			return visitUnspecifiedValueExp ((IUnspecifiedValueExp )exp, visitor);
		} else if( exp instanceof IOclMessageExp ) {
			result = visitOclMessageExp((IOclMessageExp )exp, visitor);
		} else {
			System.err.println("AstWalker.visitNode unexpected expression type");
			System.err.println("OclExpression: " + exp + " of type " + exp.getClass());
		}
		return result;
	}

	/**
	 * @param exp
	 * @param visitor
	 * @return
	 */
	private Object visitPropertyCallExp(IPropertyCallExp exp, IAstVisitor visitor) {
		if( exp instanceof IModelPropertyCallExp ) {
			return visitModelPropertyCallExp ((IModelPropertyCallExp )exp, visitor);
		} else if( exp instanceof ILoopExp ) {
			return visitLoopExp  ((ILoopExp )exp, visitor);
		} else {
			System.err.println("INTERNAL OCTOPUS ERROR: AstWalker.visitPropertyCallExp");
		}
		return null;
	}

	/**
	 * @param exp
	 * @param visitor
	 * @return
	 */
	private Object visitLoopExp(ILoopExp exp, IAstVisitor visitor) {
		if( exp instanceof IIterateExp ) {
			return visitIterateExp ((IIterateExp )exp, visitor);
		} else if( exp instanceof IIteratorExp ) {
			return visitIteratorExp  ((IIteratorExp )exp, visitor);
		} else {
			System.out.println("INTERNAL OCTOPUS ERROR: AstWalker.visitLoopExp");
		}
		return null;
	}

	/**
	 * @param exp
	 * @param visitor
	 * @return
	 */
	private Object visitIteratorExp(IIteratorExp exp, IAstVisitor visitor) {
		List<Object> its = new ArrayList<Object>();
		Iterator it = exp.getIterators().iterator();
		while( it.hasNext() ){
			IVariableDeclaration var = (IVariableDeclaration ) it.next();
			its.add( visitVariableDeclaration(var, visitor) );
		}
		Object bodyResult = null;
		if( exp.getBody() != null ){
			bodyResult = walk( exp.getBody(), visitor);
		}
		return visitor.iteratorExp(exp, its, bodyResult);
	}

	/**
	 * @param exp
	 * @param visitor
	 * @return
	 */
	private Object visitIterateExp(IIterateExp exp, IAstVisitor visitor) {
		List<Object> its = new ArrayList<Object>();
		Iterator it = exp.getIterators().iterator();
		while( it.hasNext() ){
			IVariableDeclaration var = (IVariableDeclaration ) it.next();
			its.add(visitVariableDeclaration(var, visitor));
		}
		Object resultVar = null;
		if( exp.getResult() != null ) 
			resultVar = visitVariableDeclaration( exp.getResult(), visitor);
		Object bodyResult = null;
		if( exp.getBody() != null ){
			bodyResult = walk( exp.getBody(), visitor);
		}
		return visitor.iterateExp(exp, its, resultVar, bodyResult);
	}

	/**
	 * @param arg
	 * @param visitor
	 * @return
	 */
	private Object visitUnspecifiedValueExp(IUnspecifiedValueExp arg, IAstVisitor visitor) {
		return visitor.unspecifiedValueExp(arg);
	}

	/**
	 * @param exp
	 * @param visitor
	 * @return
	 */
	private Object visitLetExp(ILetExp exp, IAstVisitor visitor) {
		Object var = visitVariableDeclaration(exp.getVariable(), visitor);
		Object in  = walk(exp.getIn (), visitor);
		return visitor.letExp(exp, var, in);
	}

	/**
	 * @param declaration
	 * @param visitor
	 * @return
	 */
	private Object visitVariableDeclaration(IVariableDeclaration declaration, IAstVisitor visitor) {
		Object init ;
		if( declaration.getInitExpression() != null ){
			init = walk(declaration.getInitExpression(), visitor);
		} else {
			init = null;
		}
		return visitor.variableDeclaration(declaration, init);
	}

	/**
	 * @param declaration
	 * @param visitor
	 * @return
	 */
	private Object visitVariableExp(IVariableExp exp, IAstVisitor visitor) {
		return visitor.variableExp(exp);
	}

	private Object visitModelPropertyCallExp(IModelPropertyCallExp exp, IAstVisitor visitor){
		if( exp instanceof INavigationCallExp ) {
			return visitNavigationCallExp  ((INavigationCallExp )exp, visitor);
		} else if( exp instanceof IAttributeCallExp ) {
			return visitAttributeCallExp ((IAttributeCallExp )exp, visitor);
		} else if( exp instanceof IOperationCallExp ) {
			return visitOperationCallExp ((IOperationCallExp )exp, visitor);
		} else {
			System.out.println("ERRROORRRR AstWALKER visitModelPropertyCallExp");
		}
		return null;
	}

	/**
	 * @param exp
	 * @param visitor
	 * @return
	 */
	private Object visitNavigationCallExp(INavigationCallExp exp, IAstVisitor visitor) {
		if( exp instanceof IAssociationEndCallExp ) {
			return visitAssociationEndCallExp( (IAssociationEndCallExp) exp, visitor);
		} else if( exp instanceof IAssociationClassCallExp ) {
			return visitAssociationClassCallExp( (IAssociationClassCallExp) exp, visitor);
		} else {
			System.out.println("ERRROORRRR AstWALKER visitNavigationCallExp");
		}

		return null;
	}

	/**
	 * @param exp
	 * @param visitor
	 * @return
	 */
	private Object visitOperationCallExp(IOperationCallExp exp, IAstVisitor visitor) {
		List<Object> args = new ArrayList<Object>();
		Iterator it = exp.getArguments().iterator();
		while( it.hasNext() ){
			IOclExpression arg = (IOclExpression) it.next();
			if( arg != null ){
			  args.add( walk(arg, visitor) );
			}
		}
		return visitor.operationCallExp(exp, args);
	}

	/**
	 * @param exp
	 * @param visitor
	 * @return
	 */
	private Object visitOclMessageExp(IOclMessageExp exp, IAstVisitor visitor) {
		List<Object> args = new ArrayList<Object>();
		Iterator it = exp.getArguments().iterator();
		while( it.hasNext() ){
			IOclExpression arg = (IOclExpression) it.next();
			if( arg != null ){
			  args.add( walk(arg, visitor) );
			}
		}
		Object target = walk( exp.getTarget(), visitor );
		return visitor.oclMessageExp(exp, target, args);
	}

	private Object visitLiteralExp(ILiteralExp exp, IAstVisitor visitor){
		if( exp instanceof IIntegerLiteralExp ) {
			return visitIntegerLiteralExp((IIntegerLiteralExp)exp, visitor);
		} else if( exp instanceof IStringLiteralExp ) {
			return visitStringLiteralExp ((IStringLiteralExp )exp, visitor);
		} else if( exp instanceof IRealLiteralExp ) {
			return visitRealLiteralExp ((IRealLiteralExp )exp, visitor);
		} else if( exp instanceof IBooleanLiteralExp ) {
			return visitBooleanLiteralExp ((IBooleanLiteralExp )exp, visitor);
		} else if( exp instanceof IOclUndefinedLiteralExp ) {
			return visitOclUndefinedLiteralExp ((IOclUndefinedLiteralExp )exp, visitor);
		} else if( exp instanceof ICollectionLiteralExp ) {
			return visitCollectionLiteralExp ((ICollectionLiteralExp)exp, visitor);
		} else if( exp instanceof IEnumLiteralExp) {
			return visitEnumLiteralExp((IEnumLiteralExp)exp, visitor);
		} else if( exp instanceof IOclStateLiteralExp) {
			return visitOclStateLiteralExp((IOclStateLiteralExp)exp, visitor);
		} else if( exp instanceof IOclTypeLiteralExp) {
			return visitOclTypeLiteralExp((IOclTypeLiteralExp)exp, visitor);
		} else if( exp instanceof ITupleLiteralExp) {
			return visitTupleLiteralExp((ITupleLiteralExp)exp, visitor);
		} else {
			System.out.println("ERRROORRRR AstWALKER visitLiteralExp");
		}
		return null;
	}

	/**
	 * @param exp
	 * @param visitor
	 * @return
	 */
	private Object visitTupleLiteralExp(ITupleLiteralExp exp, IAstVisitor visitor) {
		List<Object> items = new ArrayList<Object>();
		Iterator it = exp.getTupleParts().iterator();
		while( it.hasNext() ){
			IVariableDeclaration var = (IVariableDeclaration) it.next();
			if( var != null ){
			  items.add( visitVariableDeclaration(var, visitor) );
			}
		}
		return visitor.tupleLiteralExp(exp, items);
	}

	/**
	 * @param exp
	 * @param visitor
	 * @return
	 */
	private Object visitOclTypeLiteralExp(IOclTypeLiteralExp exp, IAstVisitor visitor) {
		return visitor.oclTypeLiteralExp(exp);
	}

	/**
	 * @param exp
	 * @param visitor
	 * @return
	 */
	private Object visitOclStateLiteralExp(IOclStateLiteralExp exp, IAstVisitor visitor) {
		return visitor.oclStateLiteralExp(exp);
	}

	/**
	 * @param exp
	 * @param visitor
	 * @return
	 */
	private Object visitEnumLiteralExp(IEnumLiteralExp exp, IAstVisitor visitor) {
		return visitor.enumLiteralExp(exp);
	}

	/**
	 * Will go sequentially through all parts of the collection literal.
	 * 
	 * @param exp
	 * @param visitor
	 * @return
	 */
	private Object visitCollectionLiteralExp(ICollectionLiteralExp exp, IAstVisitor visitor) {
		List<Object> myList = new ArrayList<Object>();
		Object partResult = null;
		Iterator it = exp.getParts().iterator();
		while (it.hasNext()) {
			ICollectionLiteralPart part = (ICollectionLiteralPart) it.next();
			if (part instanceof ICollectionItem) {
				IOclExpression item = ((ICollectionItem)part).getItem();
				partResult = visitor.collectionItem((ICollectionItem)part, walk(item, visitor));
			}
			if (part instanceof ICollectionRange) {
				IOclExpression first = ((ICollectionRange)part).getFirst();
				IOclExpression last = ((ICollectionRange)part).getLast();
				partResult = visitor.collectionRange((ICollectionRange)part , walk(first, visitor), walk(last, visitor) );    			
			}
			myList.add(partResult);
		}
		return visitor.collectionLiteralExp(exp, myList);
	}

	/**
	 * @param exp
	 * @param visitor
	 * @return
	 */
	private Object visitAttributeCallExp(IAttributeCallExp exp, IAstVisitor visitor) {
		return visitor.attributeCallExp(exp);
	}

	/**
	 * @param exp
	 * @param visitor
	 * @return
	 */
	private Object visitAssociationEndCallExp(IAssociationEndCallExp exp, IAstVisitor visitor) {
		return visitor.associationEndCallExp(exp);
	}

	/**
	 * @param exp
	 * @param visitor
	 * @return
	 */
	private Object visitAssociationClassCallExp(IAssociationClassCallExp exp, IAstVisitor visitor) {
		return visitor.associationClassCallExp(exp);
	}

	/**
	 * @param exp
	 * @param visitor
	 * @return
	 */
	private Object visitBooleanLiteralExp(IBooleanLiteralExp exp, IAstVisitor visitor) {
		return visitor.booleanLiteralExp(exp);
	}

	/**
	 * @param exp
	 * @param visitor
	 * @return
	 */
	private Object visitOclUndefinedLiteralExp(IOclUndefinedLiteralExp exp, IAstVisitor visitor) {
		return visitor.oclUndefinedLiteralExp(exp);
	}

	/**
	 * @param exp
	 * @param visitor
	 * @return
	 */
	private Object visitRealLiteralExp(IRealLiteralExp exp, IAstVisitor visitor) {
		return visitor.realLiteralExp(exp);
	}

	/**
	 * @param exp
	 * @param visitor
	 * @return
	 */
	private Object visitStringLiteralExp(IStringLiteralExp exp, IAstVisitor visitor) {
		return visitor.stringLiteralExp(exp);
	}

	/**
	 * @param exp
	 * @param visitor
	 * @return
	 */
	private Object visitIntegerLiteralExp(IIntegerLiteralExp exp, IAstVisitor visitor) {
		return visitor.integerLiteralExp(exp);
	}

	/**
	 * @param exp
	 * @return
	 */
	private Object visitIfExp(IIfExp exp, IAstVisitor visitor) {
		  Object condition = walk(exp.getCondition(), visitor);
		  Object thenPart  = walk(exp.getThenExpression(), visitor);
		  Object elsePart  = walk(exp.getElseExpression(), visitor);
		  return visitor.ifExp(exp, condition, thenPart, elsePart);
	}

}