/**
 */
package org.opaeum.uim.perspective;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Multiplicity Element Navigation Constraint</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.opaeum.uim.perspective.MultiplicityElementNavigationConstraint#getRemoveConstraint <em>Remove Constraint</em>}</li>
 *   <li>{@link org.opaeum.uim.perspective.MultiplicityElementNavigationConstraint#getAddConstraint <em>Add Constraint</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.opaeum.uim.perspective.PerspectivePackage#getMultiplicityElementNavigationConstraint()
 * @model
 * @generated
 */
public interface MultiplicityElementNavigationConstraint extends NavigationConstraint {
	/**
	 * Returns the value of the '<em><b>Remove Constraint</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Remove Constraint</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Remove Constraint</em>' containment reference.
	 * @see #setRemoveConstraint(NavigationConstraint)
	 * @see org.opaeum.uim.perspective.PerspectivePackage#getMultiplicityElementNavigationConstraint_RemoveConstraint()
	 * @model containment="true"
	 * @generated
	 */
	NavigationConstraint getRemoveConstraint();

	/**
	 * Sets the value of the '{@link org.opaeum.uim.perspective.MultiplicityElementNavigationConstraint#getRemoveConstraint <em>Remove Constraint</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Remove Constraint</em>' containment reference.
	 * @see #getRemoveConstraint()
	 * @generated
	 */
	void setRemoveConstraint(NavigationConstraint value);

	/**
	 * Returns the value of the '<em><b>Add Constraint</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Add Constraint</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Add Constraint</em>' containment reference.
	 * @see #setAddConstraint(NavigationConstraint)
	 * @see org.opaeum.uim.perspective.PerspectivePackage#getMultiplicityElementNavigationConstraint_AddConstraint()
	 * @model containment="true"
	 * @generated
	 */
	NavigationConstraint getAddConstraint();

	/**
	 * Sets the value of the '{@link org.opaeum.uim.perspective.MultiplicityElementNavigationConstraint#getAddConstraint <em>Add Constraint</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Add Constraint</em>' containment reference.
	 * @see #getAddConstraint()
	 * @generated
	 */
	void setAddConstraint(NavigationConstraint value);

} // MultiplicityElementNavigationConstraint
