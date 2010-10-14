/*
 * Created on Jan 21, 2004
 *
 * Copyright Klasse Objecten
 */
package nl.klasse.octopus.expressions;

import nl.klasse.octopus.stdlib.IOclIterator;

/**
 * IIteratorExp : a representation of one of the loop expressions in the standard
 * library, e.g. 'forAll', 'exists'.
 */
public interface IIteratorExp extends ILoopExp {
	/** Getter for property referredIterator.
	 * NB: This is different from the OCL 2.0 Standard
	 * @return Value of property referredIterator.
	 */
	public abstract IOclIterator getReferredIterator();
}