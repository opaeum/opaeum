/*
 * Created on Mar 28, 2004
 *
 * Copyright Klasse Objecten
 */
package nl.klasse.octopus.expressions;

import nl.klasse.octopus.model.IAssociationClass;

/**
 * IAssociationClassCallExp represents an expression that is a call to 
 * an AssociationClass.
 * <p>
 * For instance, if class A and class B are associated through association class AB,
 * the following expression is an instance of IAssociationClassCallExp.
 * context A
 * inv: AB
 * Note that this is not a correct invariant! 
 *  
 */
public interface IAssociationClassCallExp extends INavigationCallExp {

	/** Returns the association class that this expression refers to.
	 * 
	 * @return Value of property referredAssociationClass.
	 */
	public abstract IAssociationClass getReferredAssociationClass();
}
