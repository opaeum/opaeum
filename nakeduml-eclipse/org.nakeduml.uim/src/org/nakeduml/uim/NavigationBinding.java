/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.nakeduml.uim;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Navigation UIMBinding</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.nakeduml.uim.NavigationBinding#getNavigation <em>Navigation</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.nakeduml.uim.UIMPackage#getNavigationBinding()
 * @model
 * @generated
 */
public interface NavigationBinding extends UIMBinding {
	/**
	 * Returns the value of the '<em><b>Navigation</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.nakeduml.uim.NavigationToEntity#getBinding <em>Binding</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Navigation</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Navigation</em>' container reference.
	 * @see #setNavigation(NavigationToEntity)
	 * @see org.nakeduml.uim.UIMPackage#getNavigationBinding_Navigation()
	 * @see org.nakeduml.uim.NavigationToEntity#getBinding
	 * @model opposite="binding" required="true" transient="false"
	 * @generated
	 */
	NavigationToEntity getNavigation();

	/**
	 * Sets the value of the '{@link org.nakeduml.uim.NavigationBinding#getNavigation <em>Navigation</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Navigation</em>' container reference.
	 * @see #getNavigation()
	 * @generated
	 */
	void setNavigation(NavigationToEntity value);

} // NavigationBinding
