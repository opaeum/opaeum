/**
 */
package org.opaeum.uim.perspective;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Navigator Configuration</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.opaeum.uim.perspective.NavigatorConfiguration#getClasses <em>Classes</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.opaeum.uim.perspective.PerspectivePackage#getNavigatorConfiguration()
 * @model
 * @generated
 */
public interface NavigatorConfiguration extends ViewAllocation {
	/**
	 * Returns the value of the '<em><b>Classes</b></em>' containment reference list.
	 * The list contents are of type {@link org.opaeum.uim.perspective.ClassNavigationConstraint}.
	 * It is bidirectional and its opposite is '{@link org.opaeum.uim.perspective.ClassNavigationConstraint#getExplorerConfiguration <em>Explorer Configuration</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Classes</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Classes</em>' containment reference list.
	 * @see org.opaeum.uim.perspective.PerspectivePackage#getNavigatorConfiguration_Classes()
	 * @see org.opaeum.uim.perspective.ClassNavigationConstraint#getExplorerConfiguration
	 * @model opposite="explorerConfiguration" containment="true"
	 * @generated
	 */
	EList<ClassNavigationConstraint> getClasses();

} // NavigatorConfiguration
