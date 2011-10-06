/*
 * Created on Jan 21, 2004
 *
 * Copyright Klasse Objecten
 */
package nl.klasse.octopus.expressions;

import nl.klasse.octopus.model.IAttribute;

/**
 * IAttributeCallExp : a representation of a reference to an attribute.
 */
public interface IAttributeCallExp extends IModelPropertyCallExp {
	/** Returns the attribute this expression refers to.
	 * @return Value of property referredAttribute.
	 */
	public abstract IAttribute getReferredAttribute();
}