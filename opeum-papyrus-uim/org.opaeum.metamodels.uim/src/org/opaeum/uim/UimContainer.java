/**
 */
package org.opaeum.uim;

import org.eclipse.emf.common.util.EList;
import org.opaeum.uim.constraint.EditableConstrainedObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Container</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.opaeum.uim.UimContainer#getChildren <em>Children</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.opaeum.uim.UimPackage#getUimContainer()
 * @model abstract="true"
 * @generated
 */
public interface UimContainer extends UimComponent, EditableConstrainedObject {

	/**
	 * Returns the value of the '<em><b>Children</b></em>' containment reference list.
	 * The list contents are of type {@link org.opaeum.uim.UimComponent}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Children</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Children</em>' containment reference list.
	 * @see org.opaeum.uim.UimPackage#getUimContainer_Children()
	 * @model containment="true"
	 * @generated
	 */
	EList<UimComponent> getChildren();
} // UimContainer
