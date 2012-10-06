/**
 */
package org.opaeum.uim.perspective;

import org.eclipse.emf.common.util.EList;
import org.opaeum.uim.UmlReference;
import org.opaeum.uim.constraint.UserInteractionConstraint;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Explorer Class Constraint</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.opaeum.uim.perspective.ExplorerClassConstraint#getExplorerConfiguration <em>Explorer Configuration</em>}</li>
 *   <li>{@link org.opaeum.uim.perspective.ExplorerClassConstraint#getProperties <em>Properties</em>}</li>
 *   <li>{@link org.opaeum.uim.perspective.ExplorerClassConstraint#getNewObjectConstraint <em>New Object Constraint</em>}</li>
 *   <li>{@link org.opaeum.uim.perspective.ExplorerClassConstraint#getBehaviors <em>Behaviors</em>}</li>
 *   <li>{@link org.opaeum.uim.perspective.ExplorerClassConstraint#getOperations <em>Operations</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.opaeum.uim.perspective.PerspectivePackage#getExplorerClassConstraint()
 * @model
 * @generated
 */
public interface ExplorerClassConstraint extends ExplorerConstraint {
	/**
	 * Returns the value of the '<em><b>Explorer Configuration</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.opaeum.uim.perspective.ExplorerConfiguration#getClasses <em>Classes</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Explorer Configuration</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Explorer Configuration</em>' container reference.
	 * @see #setExplorerConfiguration(ExplorerConfiguration)
	 * @see org.opaeum.uim.perspective.PerspectivePackage#getExplorerClassConstraint_ExplorerConfiguration()
	 * @see org.opaeum.uim.perspective.ExplorerConfiguration#getClasses
	 * @model opposite="classes" required="true" transient="false"
	 * @generated
	 */
	ExplorerConfiguration getExplorerConfiguration();

	/**
	 * Sets the value of the '{@link org.opaeum.uim.perspective.ExplorerClassConstraint#getExplorerConfiguration <em>Explorer Configuration</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Explorer Configuration</em>' container reference.
	 * @see #getExplorerConfiguration()
	 * @generated
	 */
	void setExplorerConfiguration(ExplorerConfiguration value);

	/**
	 * Returns the value of the '<em><b>Properties</b></em>' containment reference list.
	 * The list contents are of type {@link org.opaeum.uim.perspective.ExplorerPropertyConstraint}.
	 * It is bidirectional and its opposite is '{@link org.opaeum.uim.perspective.ExplorerPropertyConstraint#getOwner <em>Owner</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Properties</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Properties</em>' containment reference list.
	 * @see org.opaeum.uim.perspective.PerspectivePackage#getExplorerClassConstraint_Properties()
	 * @see org.opaeum.uim.perspective.ExplorerPropertyConstraint#getOwner
	 * @model opposite="owner" containment="true"
	 * @generated
	 */
	EList<ExplorerPropertyConstraint> getProperties();

	/**
	 * Returns the value of the '<em><b>New Object Constraint</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>New Object Constraint</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>New Object Constraint</em>' containment reference.
	 * @see #setNewObjectConstraint(UserInteractionConstraint)
	 * @see org.opaeum.uim.perspective.PerspectivePackage#getExplorerClassConstraint_NewObjectConstraint()
	 * @model containment="true"
	 * @generated
	 */
	UserInteractionConstraint getNewObjectConstraint();

	/**
	 * Sets the value of the '{@link org.opaeum.uim.perspective.ExplorerClassConstraint#getNewObjectConstraint <em>New Object Constraint</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>New Object Constraint</em>' containment reference.
	 * @see #getNewObjectConstraint()
	 * @generated
	 */
	void setNewObjectConstraint(UserInteractionConstraint value);

	/**
	 * Returns the value of the '<em><b>Behaviors</b></em>' containment reference list.
	 * The list contents are of type {@link org.opaeum.uim.perspective.ExplorerBehaviorConstraint}.
	 * It is bidirectional and its opposite is '{@link org.opaeum.uim.perspective.ExplorerBehaviorConstraint#getOwner <em>Owner</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Behaviors</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Behaviors</em>' containment reference list.
	 * @see org.opaeum.uim.perspective.PerspectivePackage#getExplorerClassConstraint_Behaviors()
	 * @see org.opaeum.uim.perspective.ExplorerBehaviorConstraint#getOwner
	 * @model opposite="owner" containment="true"
	 * @generated
	 */
	EList<ExplorerBehaviorConstraint> getBehaviors();

	/**
	 * Returns the value of the '<em><b>Operations</b></em>' containment reference list.
	 * The list contents are of type {@link org.opaeum.uim.perspective.ExplorerOperationConstraint}.
	 * It is bidirectional and its opposite is '{@link org.opaeum.uim.perspective.ExplorerOperationConstraint#getOwner <em>Owner</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Operations</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Operations</em>' containment reference list.
	 * @see org.opaeum.uim.perspective.PerspectivePackage#getExplorerClassConstraint_Operations()
	 * @see org.opaeum.uim.perspective.ExplorerOperationConstraint#getOwner
	 * @model opposite="owner" containment="true"
	 * @generated
	 */
	EList<ExplorerOperationConstraint> getOperations();

} // ExplorerClassConstraint
