/**
 */
package org.opaeum.uim.binding;

import org.opaeum.uim.action.LinkToEntity;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Navigation Binding</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.opaeum.uim.binding.NavigationBinding#getNavigation <em>Navigation</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.opaeum.uim.binding.BindingPackage#getNavigationBinding()
 * @model
 * @generated
 */
public interface NavigationBinding extends UimBinding {
	/**
	 * Returns the value of the '<em><b>Navigation</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.opaeum.uim.action.LinkToEntity#getBinding <em>Binding</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Navigation</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Navigation</em>' container reference.
	 * @see #setNavigation(LinkToEntity)
	 * @see org.opaeum.uim.binding.BindingPackage#getNavigationBinding_Navigation()
	 * @see org.opaeum.uim.action.LinkToEntity#getBinding
	 * @model opposite="binding" required="true" transient="false"
	 * @generated
	 */
	LinkToEntity getNavigation();

	/**
	 * Sets the value of the '{@link org.opaeum.uim.binding.NavigationBinding#getNavigation <em>Navigation</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Navigation</em>' container reference.
	 * @see #getNavigation()
	 * @generated
	 */
	void setNavigation(LinkToEntity value);

} // NavigationBinding
