/*
 * Created on Jan 4, 2004
 *
 * Copyright Klasse Objecten
 */
package nl.klasse.octopus.model;

/** IPackageableElement : a ModelElement with visibility attribute
 * 
 * @version $Id: IPackageableElement.java,v 1.1 2006/03/01 19:13:26 jwarmer Exp $
 */
public interface IPackageableElement extends IModelElement{

	/**
	 * Returns the visibility.
	 * @return VisibilityKind
	 */
	public VisibilityKind getVisibility() ;
}
