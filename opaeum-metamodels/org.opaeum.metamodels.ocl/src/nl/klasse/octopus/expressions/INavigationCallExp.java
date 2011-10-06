/*
 * Created on Jan 21, 2004
 *
 * Copyright Klasse Objecten
 */
package nl.klasse.octopus.expressions;

import nl.klasse.octopus.model.IAssociationEnd;

/**
 * INavigationCallExp : a representation of an expression that either references 
 * an association end or an association class.
 */
public interface INavigationCallExp extends IModelPropertyCallExp {
	
	/** Returns the association end at the side of the object that 
	 * calls this navigation.
	 * @return
	 */
	public IAssociationEnd getNavigationSource();
}