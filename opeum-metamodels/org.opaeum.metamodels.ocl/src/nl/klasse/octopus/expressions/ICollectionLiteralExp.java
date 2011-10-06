/*
 * Created on Jan 21, 2004
 *
 * Copyright Klasse Objecten
 */
package nl.klasse.octopus.expressions;

import java.util.List;

/**
 * ICollectionLiteralExp : a representation of a literal collection expression,
 * like 'Set{2,4,7}'
 */
public interface ICollectionLiteralExp extends ILiteralExp {
	/**
	 * @return the expressions that represent the elements in the collection
	 */
	public abstract List<ICollectionLiteralPart> getParts();
}