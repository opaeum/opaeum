/*
 * Created on Jan 21, 2004
 *
 * Copyright Klasse Objecten
 */
package nl.klasse.octopus.expressions;


/**
 * IIterateExp : a representation of the generic loop operation called 'iterate'.
 */
public interface IIterateExp extends ILoopExp {
	/** Getter for property result.
	 * @return Value of property result.
	 */
	public abstract IVariableDeclaration getResult();
}