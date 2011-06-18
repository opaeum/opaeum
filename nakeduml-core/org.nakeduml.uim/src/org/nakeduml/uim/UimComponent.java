/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.nakeduml.uim;

import org.nakeduml.uim.security.SecureObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Component</b></em>'.
 * <!-- end-user-doc -->
 *
 *
 * @see org.nakeduml.uim.UimPackage#getUimComponent()
 * @model abstract="true"
 * @generated
 */
public interface UimComponent extends UserInteractionElement, SecureObject {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 * @generated
	 */
	UimContainer getParent();

} // UimComponent
