/*
 * Created on Jan 21, 2004
 *
 * Copyright Klasse Objecten
 */
package nl.klasse.octopus.expressions;

/**
 * IBooleanLiteralExp : an expression that is a literal boolean value, like 'true'.
 */
public interface IBooleanLiteralExp extends IPrimitiveLiteralExp {
	/** Returns the value of this expression
	 * @return 
	 */
	public abstract Boolean getSymbol();
}