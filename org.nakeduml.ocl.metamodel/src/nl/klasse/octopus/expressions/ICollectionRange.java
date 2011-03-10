/*
 * Created on Jan 21, 2004
 *
 * Copyright Klasse Objecten
 */
package nl.klasse.octopus.expressions;

import nl.klasse.octopus.expressions.internal.types.OclExpression;

/**
 * ICollectionRange : a range that represents a number of elements in a literal 
 * collection expression, like '2..5' in 'Bag{ 2..5 }'.
 */
public interface ICollectionRange extends ICollectionLiteralPart {
	/** Getter for property first (for instance, 2 in '2..5).
	 * @return Value of property first.
	 */
	public abstract OclExpression getFirst();
	/** Getter for property last (for instance, 5 in '2..5).
	 * @return Value of property last.
	 */
	public abstract OclExpression getLast();
}