/*
 * Created on Jan 21, 2004
 *
 * Copyright Klasse Objecten
 */
package nl.klasse.octopus.expressions;

import nl.klasse.octopus.expressions.internal.types.OclExpression;
import nl.klasse.octopus.model.IClassifier;
import nl.klasse.octopus.model.IModelElement;


/**
 * IVariableDeclaration : an expression that defines a variable and binds 
 * it to a type. Optionally it can hold an initial value for the variable.
 */
public interface IVariableDeclaration extends IModelElement {
	/* (non-Javadoc)
	 * @see nl.klasse.octopus.model.IModelElement#getName()
	 */
	public abstract String getName();
	/**
	 * @return The type that the variable is bound to.
	 */
	public abstract IClassifier getType();
	/** 
	 * @return The expression that representsa the initial value of the variable.
	 */
	public abstract OclExpression getInitExpression();
	
	public boolean isIteratorVar();
	public abstract void setType(IClassifier expressionType);

}