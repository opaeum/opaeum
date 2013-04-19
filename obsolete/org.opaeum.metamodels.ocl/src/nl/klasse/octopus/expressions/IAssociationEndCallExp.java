/*
 * Created on Jan 21, 2004
 *
 * Copyright Klasse Objecten
 */
package nl.klasse.octopus.expressions;

import nl.klasse.octopus.model.IAssociationEnd;

/**
 * IAssociationEndCallExp : a representation of a reference to an association end.
 */
public interface IAssociationEndCallExp extends INavigationCallExp {
	/** Returns the association end that this expression refers to.
	 * @return Value of property referredAssociationEnd.
	 */
	public abstract IAssociationEnd getReferredAssociationEnd();
}