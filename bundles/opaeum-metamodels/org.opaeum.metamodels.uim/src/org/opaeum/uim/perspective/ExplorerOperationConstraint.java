/**
 */
package org.opaeum.uim.perspective;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Explorer Operation Constraint</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.opaeum.uim.perspective.ExplorerOperationConstraint#getOwner <em>Owner</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.opaeum.uim.perspective.PerspectivePackage#getExplorerOperationConstraint()
 * @model
 * @generated
 */
public interface ExplorerOperationConstraint extends ExplorerConstraint {
	/**
	 * Returns the value of the '<em><b>Owner</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.opaeum.uim.perspective.ExplorerClassConstraint#getOperations <em>Operations</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owner</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Owner</em>' container reference.
	 * @see #setOwner(ExplorerClassConstraint)
	 * @see org.opaeum.uim.perspective.PerspectivePackage#getExplorerOperationConstraint_Owner()
	 * @see org.opaeum.uim.perspective.ExplorerClassConstraint#getOperations
	 * @model opposite="operations" transient="false"
	 * @generated
	 */
	ExplorerClassConstraint getOwner();

	/**
	 * Sets the value of the '{@link org.opaeum.uim.perspective.ExplorerOperationConstraint#getOwner <em>Owner</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Owner</em>' container reference.
	 * @see #getOwner()
	 * @generated
	 */
	void setOwner(ExplorerClassConstraint value);

} // ExplorerOperationConstraint
