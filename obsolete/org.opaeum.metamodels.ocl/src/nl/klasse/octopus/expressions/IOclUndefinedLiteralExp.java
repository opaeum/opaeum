/*
 * Created on Jan 21, 2004
 *
 * Copyright Klasse Objecten
 */
package nl.klasse.octopus.expressions;

/**
 * IOclUndefinedLiteralExp : an expression that is a literal OCL undefined value, the only value is 'oclUndefined'.
 */
public interface IOclUndefinedLiteralExp extends IPrimitiveLiteralExp {
	/**
	 * @return the value of this expression
	 * 
	 */
	public abstract String getSymbol();
}