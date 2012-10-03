/**
 */
package org.opaeum.uim.component;

import org.eclipse.emf.common.util.EList;
import org.opaeum.uim.constraint.EditableConstrainedObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Uim Container</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.opaeum.uim.component.UimContainer#getChildren <em>Children</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.opaeum.uim.component.ComponentPackage#getUimContainer()
 * @model abstract="true"
 * @generated
 */
public interface UimContainer extends UimComponent, EditableConstrainedObject {
	/**
	 * Returns the value of the '<em><b>Children</b></em>' containment reference list.
	 * The list contents are of type {@link org.opaeum.uim.component.UimComponent}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Children</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Children</em>' containment reference list.
	 * @see org.opaeum.uim.component.ComponentPackage#getUimContainer_Children()
	 * @model containment="true"
	 * @generated
	 */
	EList<UimComponent> getChildren();

} // UimContainer
