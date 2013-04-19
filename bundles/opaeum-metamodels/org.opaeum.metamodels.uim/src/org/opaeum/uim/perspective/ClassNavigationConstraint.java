/**
 */
package org.opaeum.uim.perspective;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Class Navigation Constraint</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.opaeum.uim.perspective.ClassNavigationConstraint#getExplorerConfiguration <em>Explorer Configuration</em>}</li>
 *   <li>{@link org.opaeum.uim.perspective.ClassNavigationConstraint#getProperties <em>Properties</em>}</li>
 *   <li>{@link org.opaeum.uim.perspective.ClassNavigationConstraint#getBehaviors <em>Behaviors</em>}</li>
 *   <li>{@link org.opaeum.uim.perspective.ClassNavigationConstraint#getOperations <em>Operations</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.opaeum.uim.perspective.PerspectivePackage#getClassNavigationConstraint()
 * @model
 * @generated
 */
public interface ClassNavigationConstraint extends NavigationConstraint {
	/**
	 * Returns the value of the '<em><b>Explorer Configuration</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.opaeum.uim.perspective.NavigatorConfiguration#getClasses <em>Classes</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Explorer Configuration</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Explorer Configuration</em>' container reference.
	 * @see #setExplorerConfiguration(NavigatorConfiguration)
	 * @see org.opaeum.uim.perspective.PerspectivePackage#getClassNavigationConstraint_ExplorerConfiguration()
	 * @see org.opaeum.uim.perspective.NavigatorConfiguration#getClasses
	 * @model opposite="classes" required="true" transient="false"
	 * @generated
	 */
	NavigatorConfiguration getExplorerConfiguration();

	/**
	 * Sets the value of the '{@link org.opaeum.uim.perspective.ClassNavigationConstraint#getExplorerConfiguration <em>Explorer Configuration</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Explorer Configuration</em>' container reference.
	 * @see #getExplorerConfiguration()
	 * @generated
	 */
	void setExplorerConfiguration(NavigatorConfiguration value);

	/**
	 * Returns the value of the '<em><b>Properties</b></em>' containment reference list.
	 * The list contents are of type {@link org.opaeum.uim.perspective.PropertyNavigationConstraint}.
	 * It is bidirectional and its opposite is '{@link org.opaeum.uim.perspective.PropertyNavigationConstraint#getOwner <em>Owner</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Properties</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Properties</em>' containment reference list.
	 * @see org.opaeum.uim.perspective.PerspectivePackage#getClassNavigationConstraint_Properties()
	 * @see org.opaeum.uim.perspective.PropertyNavigationConstraint#getOwner
	 * @model opposite="owner" containment="true"
	 * @generated
	 */
	EList<PropertyNavigationConstraint> getProperties();

	/**
	 * Returns the value of the '<em><b>Behaviors</b></em>' containment reference list.
	 * The list contents are of type {@link org.opaeum.uim.perspective.BehaviorNavigationConstraint}.
	 * It is bidirectional and its opposite is '{@link org.opaeum.uim.perspective.BehaviorNavigationConstraint#getOwner <em>Owner</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Behaviors</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Behaviors</em>' containment reference list.
	 * @see org.opaeum.uim.perspective.PerspectivePackage#getClassNavigationConstraint_Behaviors()
	 * @see org.opaeum.uim.perspective.BehaviorNavigationConstraint#getOwner
	 * @model opposite="owner" containment="true"
	 * @generated
	 */
	EList<BehaviorNavigationConstraint> getBehaviors();

	/**
	 * Returns the value of the '<em><b>Operations</b></em>' containment reference list.
	 * The list contents are of type {@link org.opaeum.uim.perspective.OperationNavigationConstraint}.
	 * It is bidirectional and its opposite is '{@link org.opaeum.uim.perspective.OperationNavigationConstraint#getOwner <em>Owner</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Operations</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Operations</em>' containment reference list.
	 * @see org.opaeum.uim.perspective.PerspectivePackage#getClassNavigationConstraint_Operations()
	 * @see org.opaeum.uim.perspective.OperationNavigationConstraint#getOwner
	 * @model opposite="owner" containment="true"
	 * @generated
	 */
	EList<OperationNavigationConstraint> getOperations();

} // ClassNavigationConstraint
