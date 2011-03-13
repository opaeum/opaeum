/*
 * Created on Jan 21, 2004
 *
 * Copyright Klasse Objecten
 */
package nl.klasse.octopus.expressions;

/**
 * IStringLiteralExp : an expression that is a literal string value, like 'aString'.
 */
public interface IStringLiteralExp extends IPrimitiveLiteralExp {
	/**
	 * @return the value of this literal
	 */
	public abstract String getSymbol();
}