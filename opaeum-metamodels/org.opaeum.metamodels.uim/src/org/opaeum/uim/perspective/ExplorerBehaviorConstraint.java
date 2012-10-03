/**
 */
package org.opaeum.uim.perspective;

import org.opaeum.uim.constraint.UserInteractionConstraint;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Explorer Behavior Constraint</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.opaeum.uim.perspective.ExplorerBehaviorConstraint#getInvocationConstraint <em>Invocation Constraint</em>}</li>
 *   <li>{@link org.opaeum.uim.perspective.ExplorerBehaviorConstraint#getOwner <em>Owner</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.opaeum.uim.perspective.PerspectivePackage#getExplorerBehaviorConstraint()
 * @model
 * @generated
 */
public interface ExplorerBehaviorConstraint extends ExplorerConstraint {
	/**
	 * Returns the value of the '<em><b>Invocation Constraint</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Invocation Constraint</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Invocation Constraint</em>' containment reference.
	 * @see #setInvocationConstraint(UserInteractionConstraint)
	 * @see org.opaeum.uim.perspective.PerspectivePackage#getExplorerBehaviorConstraint_InvocationConstraint()
	 * @model containment="true"
	 * @generated
	 */
	UserInteractionConstraint getInvocationConstraint();

	/**
	 * Sets the value of the '{@link org.opaeum.uim.perspective.ExplorerBehaviorConstraint#getInvocationConstraint <em>Invocation Constraint</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Invocation Constraint</em>' containment reference.
	 * @see #getInvocationConstraint()
	 * @generated
	 */
	void setInvocationConstraint(UserInteractionConstraint value);

	/**
	 * Returns the value of the '<em><b>Owner</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.opaeum.uim.perspective.ExplorerClassConstraint#getBehaviors <em>Behaviors</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owner</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Owner</em>' container reference.
	 * @see #setOwner(ExplorerClassConstraint)
	 * @see org.opaeum.uim.perspective.PerspectivePackage#getExplorerBehaviorConstraint_Owner()
	 * @see org.opaeum.uim.perspective.ExplorerClassConstraint#getBehaviors
	 * @model opposite="behaviors" transient="false"
	 * @generated
	 */
	ExplorerClassConstraint getOwner();

	/**
	 * Sets the value of the '{@link org.opaeum.uim.perspective.ExplorerBehaviorConstraint#getOwner <em>Owner</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Owner</em>' container reference.
	 * @see #getOwner()
	 * @generated
	 */
	void setOwner(ExplorerClassConstraint value);

} // ExplorerBehaviorConstraint
