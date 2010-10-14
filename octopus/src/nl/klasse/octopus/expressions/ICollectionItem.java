/*
 * Created on Jan 21, 2004
 *
 * Copyright Klasse Objecten
 */
package nl.klasse.octopus.expressions;

import nl.klasse.octopus.expressions.internal.types.OclExpression;

/**
 * ICollectionItem : an element in a literal collection expression, like
 * '4' in 'Set{4,10,5}', or 'a.b' in 'Bag{ a.b }'.
 */
public interface ICollectionItem extends ICollectionLiteralPart {
	/** Getter for property item.
	 * @return Value of property item.
	 */
	public abstract OclExpression getItem();
}