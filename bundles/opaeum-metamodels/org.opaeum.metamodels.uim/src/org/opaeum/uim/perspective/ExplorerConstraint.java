/**
 */
package org.opaeum.uim.perspective;

import org.opaeum.uim.LabeledElement;
import org.opaeum.uim.constraint.UserInteractionConstraint;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Explorer Constraint</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.opaeum.uim.perspective.ExplorerConstraint#isHidden <em>Hidden</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.opaeum.uim.perspective.PerspectivePackage#getExplorerConstraint()
 * @model abstract="true"
 * @generated
 */
public interface ExplorerConstraint extends UserInteractionConstraint, LabeledElement {
	/**
	 * Returns the value of the '<em><b>Hidden</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Hidden</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Hidden</em>' attribute.
	 * @see #setHidden(boolean)
	 * @see org.opaeum.uim.perspective.PerspectivePackage#getExplorerConstraint_Hidden()
	 * @model
	 * @generated
	 */
	boolean isHidden();

	/**
	 * Sets the value of the '{@link org.opaeum.uim.perspective.ExplorerConstraint#isHidden <em>Hidden</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Hidden</em>' attribute.
	 * @see #isHidden()
	 * @generated
	 */
	void setHidden(boolean value);

} // ExplorerConstraint
