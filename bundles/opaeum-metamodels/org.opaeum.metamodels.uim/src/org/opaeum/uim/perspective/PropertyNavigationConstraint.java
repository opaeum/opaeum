/**
 */
package org.opaeum.uim.perspective;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Property Navigation Constraint</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.opaeum.uim.perspective.PropertyNavigationConstraint#getOwner <em>Owner</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.opaeum.uim.perspective.PerspectivePackage#getPropertyNavigationConstraint()
 * @model
 * @generated
 */
public interface PropertyNavigationConstraint extends NavigationConstraint, MultiplicityElementNavigationConstraint {
	/**
	 * Returns the value of the '<em><b>Owner</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.opaeum.uim.perspective.ClassNavigationConstraint#getProperties <em>Properties</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owner</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Owner</em>' container reference.
	 * @see #setOwner(ClassNavigationConstraint)
	 * @see org.opaeum.uim.perspective.PerspectivePackage#getPropertyNavigationConstraint_Owner()
	 * @see org.opaeum.uim.perspective.ClassNavigationConstraint#getProperties
	 * @model opposite="properties" transient="false"
	 * @generated
	 */
	ClassNavigationConstraint getOwner();

	/**
	 * Sets the value of the '{@link org.opaeum.uim.perspective.PropertyNavigationConstraint#getOwner <em>Owner</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Owner</em>' container reference.
	 * @see #getOwner()
	 * @generated
	 */
	void setOwner(ClassNavigationConstraint value);

} // PropertyNavigationConstraint
