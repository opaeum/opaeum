/*
 * Created on Jan 21, 2004
 *
 * Copyright Klasse Objecten
 */
package nl.klasse.octopus.expressions;

/**
 * IVariableExp : an expression that is a reference to a variable.
 */
public interface IVariableExp extends IOclExpression {
	/** Getter for property referredVariable.
	 * @return Value of property referredVariable.
	 */
	public abstract IVariableDeclaration getReferredVariable();
}