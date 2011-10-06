/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.opaeum.uim.action;

import org.opaeum.uim.UmlReference;
import org.opaeum.uim.binding.NavigationBinding;
import org.opaeum.uim.form.ClassForm;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Navigation To Entity</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.opaeum.uim.action.NavigationToEntity#getToForm <em>To Form</em>}</li>
 *   <li>{@link org.opaeum.uim.action.NavigationToEntity#getBinding <em>Binding</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.opaeum.uim.action.ActionPackage#getNavigationToEntity()
 * @model
 * @generated
 */
public interface NavigationToEntity extends UimNavigation, UmlReference {
	/**
	 * Returns the value of the '<em><b>To Form</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>To Form</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>To Form</em>' reference.
	 * @see #setToForm(ClassForm)
	 * @see org.opaeum.uim.action.ActionPackage#getNavigationToEntity_ToForm()
	 * @model
	 * @generated
	 */
	ClassForm getToForm();

	/**
	 * Sets the value of the '{@link org.opaeum.uim.action.NavigationToEntity#getToForm <em>To Form</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>To Form</em>' reference.
	 * @see #getToForm()
	 * @generated
	 */
	void setToForm(ClassForm value);

	/**
	 * Returns the value of the '<em><b>Binding</b></em>' containment reference.
	 * It is bidirectional and its opposite is '{@link org.opaeum.uim.binding.NavigationBinding#getNavigation <em>Navigation</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Binding</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Binding</em>' containment reference.
	 * @see #setBinding(NavigationBinding)
	 * @see org.opaeum.uim.action.ActionPackage#getNavigationToEntity_Binding()
	 * @see org.opaeum.uim.binding.NavigationBinding#getNavigation
	 * @model opposite="navigation" containment="true"
	 * @generated
	 */
	NavigationBinding getBinding();

	/**
	 * Sets the value of the '{@link org.opaeum.uim.action.NavigationToEntity#getBinding <em>Binding</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Binding</em>' containment reference.
	 * @see #getBinding()
	 * @generated
	 */
	void setBinding(NavigationBinding value);

} // NavigationToEntity
