/*
 * Created on Jan 21, 2004
 *
 * Copyright Klasse Objecten
 */
package nl.klasse.octopus.expressions;

import nl.klasse.octopus.expressions.internal.types.OclExpression;

/**
 * ILetExp : a representation of an expression that holds a local variable
 */
public interface ILetExp extends IOclExpression {
	/** Getter for property variable: the local variable defined in this expression.
	 * @return Value of property variable.
	 */
	public abstract IVariableDeclaration getVariable();
	/** Getter for property in: the expression in which the local variable amy be used..
	 * @return Value of property in.
	 */
	public abstract OclExpression getIn();
}