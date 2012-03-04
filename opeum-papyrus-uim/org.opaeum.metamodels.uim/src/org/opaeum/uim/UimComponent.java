/**
 */
package org.opaeum.uim;

import org.opaeum.uim.constraint.ConstrainedObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Component</b></em>'.
 * <!-- end-user-doc -->
 *
 *
 * @see org.opaeum.uim.UimPackage#getUimComponent()
 * @model abstract="true"
 * @generated
 */
public interface UimComponent extends UserInteractionElement, ConstrainedObject {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 * @generated
	 */
	UimContainer getParent();

} // UimComponent
