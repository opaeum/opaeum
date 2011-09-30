/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.opeum.uim.binding;

import org.opeum.uim.action.NavigationToEntity;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Navigation Binding</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.opeum.uim.binding.NavigationBinding#getNavigation <em>Navigation</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.opeum.uim.binding.BindingPackage#getNavigationBinding()
 * @model
 * @generated
 */
public interface NavigationBinding extends UimBinding {
	/**
	 * Returns the value of the '<em><b>Navigation</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.opeum.uim.action.NavigationToEntity#getBinding <em>Binding</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Navigation</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Navigation</em>' container reference.
	 * @see #setNavigation(NavigationToEntity)
	 * @see org.opeum.uim.binding.BindingPackage#getNavigationBinding_Navigation()
	 * @see org.opeum.uim.action.NavigationToEntity#getBinding
	 * @model opposite="binding" required="true" transient="false"
	 * @generated
	 */
	NavigationToEntity getNavigation();

	/**
	 * Sets the value of the '{@link org.opeum.uim.binding.NavigationBinding#getNavigation <em>Navigation</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Navigation</em>' container reference.
	 * @see #getNavigation()
	 * @generated
	 */
	void setNavigation(NavigationToEntity value);

} // NavigationBinding
