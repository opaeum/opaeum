/*
 * Created on Jan 21, 2004
 *
 * Copyright Klasse Objecten
 */
package nl.klasse.octopus.expressions;

import nl.klasse.octopus.model.IState;

/**
 * IOclStateLiteralExp : a representation of an expression that refers to a
 * state of a Classifier.
 * <p>
 * Note that this metaclass is not present in the OCL 2.0 specification.
 */
public interface IOclStateLiteralExp extends ILiteralExp {
	
	/**
	 * Returns the state that this literal expression refers to.
	 * @return
	 */
	public abstract IState getReferredState();
}