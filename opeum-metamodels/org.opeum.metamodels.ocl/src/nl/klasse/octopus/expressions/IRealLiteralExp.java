/*
 * Created on Jan 21, 2004
 *
 * Copyright Klasse Objecten
 */
package nl.klasse.octopus.expressions;

/**
 * IRealLiteralExp : an expression that is a literal integer value, like '1.23'.
 */
public interface IRealLiteralExp extends INumericLiteralExp {
	/**
	 * @return the value of this expression
	 */
	public abstract float getSymbol();
}