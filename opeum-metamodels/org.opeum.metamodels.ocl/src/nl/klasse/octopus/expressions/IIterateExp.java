/*
 * Created on Jan 21, 2004
 *
 * Copyright Klasse Objecten
 */
package nl.klasse.octopus.expressions;

import nl.klasse.octopus.expressions.internal.types.VariableDeclaration;

/**
 * IIterateExp : a representation of the generic loop operation called 'iterate'.
 */
public interface IIterateExp extends ILoopExp {
	/** Getter for property result.
	 * @return Value of property result.
	 */
	public abstract VariableDeclaration getResult();
}