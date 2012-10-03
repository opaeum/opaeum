/**
 */
package org.opaeum.uim.perspective;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Explorer Configuration</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.opaeum.uim.perspective.ExplorerConfiguration#getClasses <em>Classes</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.opaeum.uim.perspective.PerspectivePackage#getExplorerConfiguration()
 * @model
 * @generated
 */
public interface ExplorerConfiguration extends ViewAllocation {
	/**
	 * Returns the value of the '<em><b>Classes</b></em>' containment reference list.
	 * The list contents are of type {@link org.opaeum.uim.perspective.ExplorerClassConstraint}.
	 * It is bidirectional and its opposite is '{@link org.opaeum.uim.perspective.ExplorerClassConstraint#getExplorerConfiguration <em>Explorer Configuration</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Classes</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Classes</em>' containment reference list.
	 * @see org.opaeum.uim.perspective.PerspectivePackage#getExplorerConfiguration_Classes()
	 * @see org.opaeum.uim.perspective.ExplorerClassConstraint#getExplorerConfiguration
	 * @model opposite="explorerConfiguration" containment="true"
	 * @generated
	 */
	EList<ExplorerClassConstraint> getClasses();

} // ExplorerConfiguration
