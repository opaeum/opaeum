/*
 */
package nl.klasse.octopus.expressions.internal.analysis.context;

import java.util.ArrayList;
import java.util.List;

import nl.klasse.octopus.expressionVisitors.IAstVisitor;
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
import nl.klasse.octopus.oclengine.IOclError;
import nl.klasse.octopus.oclengine.internal.OclError;


/** The PostconditionChecker check whether the current AST contains things that are
 *  allowed only in postconditions (like @pre, OclMEssage's, etc.). If this is the case
 *  an error is added to the error list.
 * 
 * @author  Jos Warmer
 * @version $Id: PostconditionChecker.java,v 1.2 2008/01/19 13:16:05 annekekleppe Exp $
 */
public class PostconditionChecker implements IAstVisitor {
	List<IOclError> errors = null;

	/**
	 * 
	 */
	public PostconditionChecker() {
		super();
		this.errors = new ArrayList<IOclError>();
	}

	/* (non-Javadoc)
	 * @see nl.klasse.octopus.expressionVisitors.IAstVisitor#oclExpression(nl.klasse.octopus.expressions.IOclExpression, java.lang.Object, java.lang.Object)
	 */
	public Object oclExpression(IOclExpression exp, Object expResult, Object appliedProperty) {
		return null;
	}

	/* (non-Javadoc)
	 * @see nl.klasse.octopus.expressionVisitors.IAstVisitor#associationEndCallExp(nl.klasse.octopus.expressions.IAssociationEndCallExp)
	 */
	public Object associationEndCallExp(IAssociationEndCallExp exp) {
		checkAtpre(exp);
		return null;
	}

	/* (non-Javadoc)
	 * @see nl.klasse.octopus.expressionVisitors.IAstVisitor#associationEndCallExp(nl.klasse.octopus.expressions.IAssociationEndCallExp)
	 */
	public Object associationClassCallExp(IAssociationClassCallExp exp) {
		checkAtpre(exp);
		return null;
	}

	/* (non-Javadoc)
	 * @see nl.klasse.octopus.expressionVisitors.IAstVisitor#attributeCallExp(nl.klasse.octopus.expressions.IAttributeCallExp)
	 */
	public Object attributeCallExp(IAttributeCallExp exp) {
		checkAtpre(exp);
		return null;
	}

	/* (non-Javadoc)
	 * @see nl.klasse.octopus.expressionVisitors.IAstVisitor#booleanLiteralExp(nl.klasse.octopus.expressions.IBooleanLiteralExp)
	 */
	public Object booleanLiteralExp(IBooleanLiteralExp exp) {
		return null;
	}

	/* (non-Javadoc)
	 * @see nl.klasse.octopus.expressionVisitors.IAstVisitor#collectionItem(nl.klasse.octopus.expressions.ICollectionItem, java.lang.Object)
	 */
	public Object collectionItem(ICollectionItem exp, Object item) {
		return null;
	}

	/* (non-Javadoc)
	 * @see nl.klasse.octopus.expressionVisitors.IAstVisitor#collectionLiteralExp(nl.klasse.octopus.expressions.ICollectionLiteralExp, java.util.List)
	 */
	public Object collectionLiteralExp(ICollectionLiteralExp exp, List parts) {
		return null;
	}

	/* (non-Javadoc)
	 * @see nl.klasse.octopus.expressionVisitors.IAstVisitor#collectionRange(nl.klasse.octopus.expressions.ICollectionRange, java.lang.Object, java.lang.Object)
	 */
	public Object collectionRange(ICollectionRange exp, Object first, Object last) {
		return null;
	}

	/* (non-Javadoc)
	 * @see nl.klasse.octopus.expressionVisitors.IAstVisitor#enumLiteralExp(nl.klasse.octopus.expressions.IEnumLiteralExp)
	 */
	public Object enumLiteralExp(IEnumLiteralExp exp) {
		return null;
	}

	/* (non-Javadoc)
	 * @see nl.klasse.octopus.expressionVisitors.IAstVisitor#ifExp(nl.klasse.octopus.expressions.IIfExp, java.lang.Object, java.lang.Object, java.lang.Object)
	 */
	public Object ifExp(IIfExp exp, Object condition, Object thenPart, Object elsePart) {
		return null;
	}

	/* (non-Javadoc)
	 * @see nl.klasse.octopus.expressionVisitors.IAstVisitor#integerLiteralExp(nl.klasse.octopus.expressions.IIntegerLiteralExp)
	 */
	public Object integerLiteralExp(IIntegerLiteralExp exp) {
		return null;
	}

	/* (non-Javadoc)
	 * @see nl.klasse.octopus.expressionVisitors.IAstVisitor#iterateExp(nl.klasse.octopus.expressions.IIterateExp, java.util.List, java.lang.Object, java.lang.Object)
	 */
	public Object iterateExp(IIterateExp exp, List iterators, Object result, Object body) {
		return null;
	}

	/* (non-Javadoc)
	 * @see nl.klasse.octopus.expressionVisitors.IAstVisitor#iteratorExp(nl.klasse.octopus.expressions.IIteratorExp, java.util.List, java.lang.Object)
	 */
	public Object iteratorExp(IIteratorExp exp, List iterators, Object body) {
		return null;
	}

	/* (non-Javadoc)
	 * @see nl.klasse.octopus.expressionVisitors.IAstVisitor#letExp(nl.klasse.octopus.expressions.ILetExp, java.lang.Object, java.lang.Object)
	 */
	public Object letExp(ILetExp exp, Object varDecl, Object in) {
		return null;
	}

	/* (non-Javadoc)
	 * @see nl.klasse.octopus.expressionVisitors.IAstVisitor#numericLiteralExp(nl.klasse.octopus.expressions.INumericLiteralExp)
	 */
	public Object numericLiteralExp(INumericLiteralExp exp) {
		return null;
	}

	/* (non-Javadoc)
	 * @see nl.klasse.octopus.expressionVisitors.IAstVisitor#oclMessageArg(nl.klasse.octopus.expressions.IOclMessageArg)
	 */
	public Object unspecifiedValueExp(IUnspecifiedValueExp exp) {
		return null;
	}

	/* (non-Javadoc)
	 * @see nl.klasse.octopus.expressionVisitors.IAstVisitor#oclStateLiteralExp(nl.klasse.octopus.expressions.IOclStateLiteralExp)
	 */
	public Object oclStateLiteralExp(IOclStateLiteralExp exp) {
		return null;
	}

	/* (non-Javadoc)
	 * @see nl.klasse.octopus.expressionVisitors.IAstVisitor#oclTypeLiteralExp(nl.klasse.octopus.expressions.IOclTypeLiteralExp)
	 */
	public Object oclTypeLiteralExp(IOclTypeLiteralExp exp) {
		return null;
	}

	/* (non-Javadoc)
	 * @see nl.klasse.octopus.expressionVisitors.IAstVisitor#operationCallExp(nl.klasse.octopus.expressions.IOperationCallExp, java.util.List)
	 */
	public Object operationCallExp(IOperationCallExp exp, List args) {
		checkAtpre(exp);
		if (exp.getReferredOperation().getName().equals("oclIsNew")){
			OclError err = new OclError();
			err.setErrorMessage("Operation 'oclIsNew' may be used in postconditions only.");
			errors.add(err);	
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see nl.klasse.octopus.expressionVisitors.IAstVisitor#propertyCallExp(nl.klasse.octopus.expressions.IPropertyCallExp)
	 */
	public Object propertyCallExp(IPropertyCallExp exp) {
		return null;
	}

	/* (non-Javadoc)
	 * @see nl.klasse.octopus.expressionVisitors.IAstVisitor#realLiteralExp(nl.klasse.octopus.expressions.IRealLiteralExp)
	 */
	public Object realLiteralExp(IRealLiteralExp exp) {
		return null;
	}

	/* (non-Javadoc)
	 * @see nl.klasse.octopus.expressionVisitors.IAstVisitor#stringLiteralExp(nl.klasse.octopus.expressions.IStringLiteralExp)
	 */
	public Object stringLiteralExp(IStringLiteralExp exp) {
		return null;
	}

	/* (non-Javadoc)
	 * @see nl.klasse.octopus.expressionVisitors.IAstVisitor#tupleLiteralExp(nl.klasse.octopus.expressions.ITupleLiteralExp, java.util.List)
	 */
	public Object tupleLiteralExp(ITupleLiteralExp exp, List parts) {
		return null;
	}

	/* (non-Javadoc)
	 * @see nl.klasse.octopus.expressionVisitors.IAstVisitor#variableDeclaration(nl.klasse.octopus.expressions.IVariableDeclaration, java.lang.Object)
	 */
	public Object variableDeclaration(IVariableDeclaration exp, Object initExpression) {
		return null;
	}

	/* (non-Javadoc)
	 * @see nl.klasse.octopus.expressionVisitors.IAstVisitor#variableExp(nl.klasse.octopus.expressions.IVariableExp)
	 */
	public Object variableExp(IVariableExp exp) {
		return null;
	}

	/* (non-Javadoc)
	 * @see nl.klasse.octopus.expressionVisitors.IAstVisitor#oclUndefinedLiteralExp(nl.klasse.octopus.expressions.IOclUndefinedLiteralExp)
	 */
	public Object oclUndefinedLiteralExp(IOclUndefinedLiteralExp exp) {
		return null;
	}

	/* (non-Javadoc)
	 * @see nl.klasse.octopus.expressionVisitors.IAstVisitor#oclMessageExp(nl.klasse.octopus.expressions.IOclMessageExp, java.lang.Object, java.util.List)
	 */
	public Object oclMessageExp(IOclMessageExp exp, Object target, List args) {
		OclError err = new OclError();
		err.setErrorMessage("Ocl Messages '^'  or '^^' may be used in postconditions only.");
		errors.add(err);	
		return null;
	}
	
	private void checkAtpre(IPropertyCallExp exp) {
		if (exp.isMarkedPre()){
			OclError err = new OclError();
			err.setErrorMessage("Marker '@pre' may be used in postconditions only.");
			errors.add(err);	
		}
	}
	
	/**
	 * @return
	 */
	public List getErrors() {
		return errors;
	}

}
