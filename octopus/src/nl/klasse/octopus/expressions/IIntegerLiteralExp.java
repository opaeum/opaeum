/*
 * Created on Jan 21, 2004
 *
 * Copyright Klasse Objecten
 */
package nl.klasse.octopus.expressions;

/**
 * IIntegerLiteralExp : an expression that is a literal integer value, like '100'.
 */
public interface IIntegerLiteralExp extends INumericLiteralExp {
	/**
	 * @return the value of this expression
	 */
	public abstract int getSymbol();
}