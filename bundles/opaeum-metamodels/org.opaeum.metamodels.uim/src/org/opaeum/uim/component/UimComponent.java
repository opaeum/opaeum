/**
 */
package org.opaeum.uim.component;

import org.opaeum.uim.LabelContainer;
import org.opaeum.uim.LabeledElement;
import org.opaeum.uim.UserInteractionElement;
import org.opaeum.uim.constraint.ConstrainedObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Uim Component</b></em>'.
 * <!-- end-user-doc -->
 *
 *
 * @see org.opaeum.uim.component.ComponentPackage#getUimComponent()
 * @model abstract="true"
 * @generated
 */
public interface UimComponent extends UserInteractionElement, ConstrainedObject, LabelContainer {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 * @generated
	 */
	UimContainer getParent();

} // UimComponent
