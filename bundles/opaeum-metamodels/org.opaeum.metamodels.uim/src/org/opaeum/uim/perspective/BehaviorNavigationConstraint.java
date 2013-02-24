/**
 */
package org.opaeum.uim.perspective;

import org.eclipse.emf.common.util.EList;

import org.opaeum.uim.constraint.UserInteractionConstraint;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Behavior Navigation Constraint</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.opaeum.uim.perspective.BehaviorNavigationConstraint#getInvocationConstraint <em>Invocation Constraint</em>}</li>
 *   <li>{@link org.opaeum.uim.perspective.BehaviorNavigationConstraint#getOwner <em>Owner</em>}</li>
 *   <li>{@link org.opaeum.uim.perspective.BehaviorNavigationConstraint#getParameters <em>Parameters</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.opaeum.uim.perspective.PerspectivePackage#getBehaviorNavigationConstraint()
 * @model
 * @generated
 */
public interface BehaviorNavigationConstraint extends NavigationConstraint {
	/**
	 * Returns the value of the '<em><b>Invocation Constraint</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Invocation Constraint</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Invocation Constraint</em>' containment reference.
	 * @see #setInvocationConstraint(NavigationConstraint)
	 * @see org.opaeum.uim.perspective.PerspectivePackage#getBehaviorNavigationConstraint_InvocationConstraint()
	 * @model containment="true"
	 * @generated
	 */
	NavigationConstraint getInvocationConstraint();

	/**
	 * Sets the value of the '{@link org.opaeum.uim.perspective.BehaviorNavigationConstraint#getInvocationConstraint <em>Invocation Constraint</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Invocation Constraint</em>' containment reference.
	 * @see #getInvocationConstraint()
	 * @generated
	 */
	void setInvocationConstraint(NavigationConstraint value);

	/**
	 * Returns the value of the '<em><b>Owner</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.opaeum.uim.perspective.ClassNavigationConstraint#getBehaviors <em>Behaviors</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owner</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Owner</em>' container reference.
	 * @see #setOwner(ClassNavigationConstraint)
	 * @see org.opaeum.uim.perspective.PerspectivePackage#getBehaviorNavigationConstraint_Owner()
	 * @see org.opaeum.uim.perspective.ClassNavigationConstraint#getBehaviors
	 * @model opposite="behaviors" transient="false"
	 * @generated
	 */
	ClassNavigationConstraint getOwner();

	/**
	 * Sets the value of the '{@link org.opaeum.uim.perspective.BehaviorNavigationConstraint#getOwner <em>Owner</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Owner</em>' container reference.
	 * @see #getOwner()
	 * @generated
	 */
	void setOwner(ClassNavigationConstraint value);

	/**
	 * Returns the value of the '<em><b>Parameters</b></em>' containment reference list.
	 * The list contents are of type {@link org.opaeum.uim.perspective.ParameterNavigationConstraint}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Parameters</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Parameters</em>' containment reference list.
	 * @see org.opaeum.uim.perspective.PerspectivePackage#getBehaviorNavigationConstraint_Parameters()
	 * @model containment="true"
	 * @generated
	 */
	EList<ParameterNavigationConstraint> getParameters();

} // BehaviorNavigationConstraint
