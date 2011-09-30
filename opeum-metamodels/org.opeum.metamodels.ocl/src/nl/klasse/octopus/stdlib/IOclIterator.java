/*
 * Created on Jan 21, 2004
 *
 * Copyright Klasse Objecten
 */
package nl.klasse.octopus.stdlib;

import nl.klasse.octopus.model.IClassifier;
import nl.klasse.octopus.model.ICollectionType;

/**
 * IOclIterator : 
 */
public interface IOclIterator {
	public abstract String getName();
	/** Check whether the type of the body is correct.
	 */
	public abstract boolean checkBodyType(IClassifier bodyType);
	/** Get the return type for this iterator, given that 'source'
	 *  is the source expression and 'body' the body.
	 */
	public abstract IClassifier getReturnType(
		ICollectionType sourceType,
		IClassifier bodyType);
}