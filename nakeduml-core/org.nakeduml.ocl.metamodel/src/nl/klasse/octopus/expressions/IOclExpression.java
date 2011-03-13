/*
 * Created on Jan 21, 2004
 *
 * Copyright Klasse Objecten
 */
package nl.klasse.octopus.expressions;

import nl.klasse.octopus.expressions.internal.types.PropertyCallExp;
import nl.klasse.octopus.model.IClassifier;
import nl.klasse.octopus.model.IModelElement;


/**
 * IOclExpression : a representation of an expression written in OCL that must be evaluated 
 * in a certain environment (its context). Evaluation always results in a value, for instance
 * a simpel string or integer value, or an object, or datatype instance.
 */
public interface IOclExpression extends IModelElement {
	/* (non-Javadoc)
	 * @see nl.klasse.octopus.model.IModelElement#getName()
	 */
	public abstract String getName();
	/**
	 * Because evaluating an OclExpression results in a value, this value can 
	 * in turn be used as part of another OclExpression. The 'appliedProperty'
	 * represents the operation or loopexpression that is used to build the larger 
	 * expression.
	 * 
	 * @return the expression that is applied to this one to obtain a more complex
	 * expression
	 */
	public abstract PropertyCallExp getAppliedProperty();
	/** 
	 * returns the last property call. E.g in a.b->c(..).b()
	 * it will return the property call <i>b</i>. If the OCL
	 * expression has no applied property it returns null.
	 *
	 * @return the expression that is the last part of this complex expression
	 */
	public abstract PropertyCallExp getLastAppliedProperty();
	/**
	 * Method getNodeType returns the type of this node in the
	 * abstract syntax tree. E.g. when this object holds the information 
	 * on the 'if-then-else' part of the expression: 
	 * <code>(if true then a else b).someProperty.attr<\code>
	 * , then this method will return the type of the 'if-then-else' part 
	 * (which is equal to the type of 'a' and 'b'; both should 
	 * have the same type). The method getExpressionType on the other hand 
	 * would return the type of 'attr'.
	 * 
	 * @return IClassifier The returned type
	 */
	public abstract IClassifier getNodeType();
	/**
	 * Method getExpressionType returns the type of the complete expression
	 * taking into account all applied properties. E.g. when this object 
	 * holds the information on the 'if-then-else' part of the expression: 
	 * (if true then a else b).someProperty.attr
	 * , then this method will return the type of 'attr'.
	 * The method getNodeType on the other hand would return the type
	 * of the 'if-then-else' part.
	 * 
	 * @return IClassifier The returned type
	 */
	public abstract IClassifier getExpressionType();
	/**
	 * Return a Business Modeling Syntax representation of this expression.
	 * @return
	 */
	public abstract String asBMString();
	/**
	 * Return a string representation of this expression, using standard syntax.
	 * @return
	 */
	public abstract String asOclString();
	/**
	 * Returns true if this ast-node was inserted by the OclEngine to complete
	 * the AST. It was not present in the user-edited input to the OclEngine.
	 * For instance, when the user has given the text
	 * <code>context ClassA inv: anAttribute = true<\code>
	 * the OclEngine will add a node to represent the 'self' keyword that was omitted
	 * before 'anAttribute'. This node will be marked implicit.
	 * @return
	 */
	public abstract boolean isImplicit();
}