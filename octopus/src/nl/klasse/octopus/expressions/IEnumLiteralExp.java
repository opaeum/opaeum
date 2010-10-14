/*
 * Created on Jan 21, 2004
 *
 * Copyright Klasse Objecten
 */
package nl.klasse.octopus.expressions;

import nl.klasse.octopus.model.IEnumLiteral;

/**
 * IEnumLiteralExp : a representation of a value of an enumeration type, like
 * 'gold'.
 */
public interface IEnumLiteralExp extends ILiteralExp {
	/**
	 * @return the attribute of the enumeration type that 
	 * this literal expression refers to.
	 */
	public abstract IEnumLiteral getReferredEnumLiteral();
}