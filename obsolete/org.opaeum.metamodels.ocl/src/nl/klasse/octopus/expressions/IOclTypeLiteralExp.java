/*
 * Created on Jan 21, 2004
 *
 * Copyright Klasse Objecten
 */
package nl.klasse.octopus.expressions;

import nl.klasse.octopus.model.IClassifier;

/**
 * IOclTypeLiteralExp : a representation of an expression that refers to a
 * Classifier itself, i.e used as type in for instance the 'oclAsType' operation.
 * <p>
 * Note that this metaclass is not present in the OCL 2.0 specification.
 */
public interface IOclTypeLiteralExp extends ILiteralExp{
	
	/**
	 * @return the classifier (type) that this literal expression refers to
	 */
	public abstract IClassifier getReferredClassifier();
}