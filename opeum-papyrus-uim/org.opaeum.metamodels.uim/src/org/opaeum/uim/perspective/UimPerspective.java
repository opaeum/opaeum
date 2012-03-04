/**
 */
package org.opaeum.uim.perspective;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Uim Perspective</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.opaeum.uim.perspective.UimPerspective#getViewAllocations <em>View Allocations</em>}</li>
 *   <li>{@link org.opaeum.uim.perspective.UimPerspective#getExplorerConfiguration <em>Explorer Configuration</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.opaeum.uim.perspective.PerspectivePackage#getUimPerspective()
 * @model
 * @generated
 */
public interface UimPerspective extends EObject {
	/**
	 * Returns the value of the '<em><b>View Allocations</b></em>' containment reference list.
	 * The list contents are of type {@link org.opaeum.uim.perspective.ViewAllocation}.
	 * It is bidirectional and its opposite is '{@link org.opaeum.uim.perspective.ViewAllocation#getPerspective <em>Perspective</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>View Allocations</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>View Allocations</em>' containment reference list.
	 * @see org.opaeum.uim.perspective.PerspectivePackage#getUimPerspective_ViewAllocations()
	 * @see org.opaeum.uim.perspective.ViewAllocation#getPerspective
	 * @model opposite="perspective" containment="true" lower="4" upper="4"
	 * @generated
	 */
	EList<ViewAllocation> getViewAllocations();

	/**
	 * Returns the value of the '<em><b>Explorer Configuration</b></em>' containment reference.
	 * It is bidirectional and its opposite is '{@link org.opaeum.uim.perspective.ExplorerConfiguration#getPerspective <em>Perspective</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Explorer Configuration</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Explorer Configuration</em>' containment reference.
	 * @see #setExplorerConfiguration(ExplorerConfiguration)
	 * @see org.opaeum.uim.perspective.PerspectivePackage#getUimPerspective_ExplorerConfiguration()
	 * @see org.opaeum.uim.perspective.ExplorerConfiguration#getPerspective
	 * @model opposite="perspective" containment="true"
	 * @generated
	 */
	ExplorerConfiguration getExplorerConfiguration();

	/**
	 * Sets the value of the '{@link org.opaeum.uim.perspective.UimPerspective#getExplorerConfiguration <em>Explorer Configuration</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Explorer Configuration</em>' containment reference.
	 * @see #getExplorerConfiguration()
	 * @generated
	 */
	void setExplorerConfiguration(ExplorerConfiguration value);

} // UimPerspective
