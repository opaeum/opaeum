/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.nakeduml.uim;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Navigation To Entity</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.nakeduml.uim.NavigationToEntity#getToForm <em>To Form</em>}</li>
 *   <li>{@link org.nakeduml.uim.NavigationToEntity#getBinding <em>Binding</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.nakeduml.uim.UIMPackage#getNavigationToEntity()
 * @model
 * @generated
 */
public interface NavigationToEntity extends UIMNavigation {
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
	 * @see org.nakeduml.uim.UIMPackage#getNavigationToEntity_ToForm()
	 * @model
	 * @generated
	 */
	ClassForm getToForm();

	/**
	 * Sets the value of the '{@link org.nakeduml.uim.NavigationToEntity#getToForm <em>To Form</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>To Form</em>' reference.
	 * @see #getToForm()
	 * @generated
	 */
	void setToForm(ClassForm value);

	/**
	 * Returns the value of the '<em><b>Binding</b></em>' containment reference.
	 * It is bidirectional and its opposite is '{@link org.nakeduml.uim.NavigationBinding#getNavigation <em>Navigation</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>UIMBinding</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Binding</em>' containment reference.
	 * @see #setBinding(NavigationBinding)
	 * @see org.nakeduml.uim.UIMPackage#getNavigationToEntity_Binding()
	 * @see org.nakeduml.uim.NavigationBinding#getNavigation
	 * @model opposite="navigation" containment="true"
	 * @generated
	 */
	NavigationBinding getBinding();

	/**
	 * Sets the value of the '{@link org.nakeduml.uim.NavigationToEntity#getBinding <em>Binding</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Binding</em>' containment reference.
	 * @see #getBinding()
	 * @generated
	 */
	void setBinding(NavigationBinding value);

} // NavigationToEntity
