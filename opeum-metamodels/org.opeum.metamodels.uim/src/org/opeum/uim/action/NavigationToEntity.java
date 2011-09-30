/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.opeum.uim.action;

import org.opeum.uim.UmlReference;
import org.opeum.uim.binding.NavigationBinding;
import org.opeum.uim.form.ClassForm;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Navigation To Entity</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.opeum.uim.action.NavigationToEntity#getToForm <em>To Form</em>}</li>
 *   <li>{@link org.opeum.uim.action.NavigationToEntity#getBinding <em>Binding</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.opeum.uim.action.ActionPackage#getNavigationToEntity()
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
	 * @see org.opeum.uim.action.ActionPackage#getNavigationToEntity_ToForm()
	 * @model
	 * @generated
	 */
	ClassForm getToForm();

	/**
	 * Sets the value of the '{@link org.opeum.uim.action.NavigationToEntity#getToForm <em>To Form</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>To Form</em>' reference.
	 * @see #getToForm()
	 * @generated
	 */
	void setToForm(ClassForm value);

	/**
	 * Returns the value of the '<em><b>Binding</b></em>' containment reference.
	 * It is bidirectional and its opposite is '{@link org.opeum.uim.binding.NavigationBinding#getNavigation <em>Navigation</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Binding</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Binding</em>' containment reference.
	 * @see #setBinding(NavigationBinding)
	 * @see org.opeum.uim.action.ActionPackage#getNavigationToEntity_Binding()
	 * @see org.opeum.uim.binding.NavigationBinding#getNavigation
	 * @model opposite="navigation" containment="true"
	 * @generated
	 */
	NavigationBinding getBinding();

	/**
	 * Sets the value of the '{@link org.opeum.uim.action.NavigationToEntity#getBinding <em>Binding</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Binding</em>' containment reference.
	 * @see #getBinding()
	 * @generated
	 */
	void setBinding(NavigationBinding value);

} // NavigationToEntity
