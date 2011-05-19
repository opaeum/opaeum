/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.nakeduml.uim;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Uml Reference</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.nakeduml.uim.UmlReference#getUmlElementUid <em>Uml Element Uid</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.nakeduml.uim.UIMPackage#getUmlReference()
 * @model
 * @generated
 */
public interface UmlReference extends EObject {
	/**
	 * Returns the value of the '<em><b>Uml Element Uid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Uml Element Uid</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Uml Element Uid</em>' attribute.
	 * @see #setUmlElementUid(String)
	 * @see org.nakeduml.uim.UIMPackage#getUmlReference_UmlElementUid()
	 * @model
	 * @generated
	 */
	String getUmlElementUid();

	/**
	 * Sets the value of the '{@link org.nakeduml.uim.UmlReference#getUmlElementUid <em>Uml Element Uid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Uml Element Uid</em>' attribute.
	 * @see #getUmlElementUid()
	 * @generated
	 */
	void setUmlElementUid(String value);

} // UmlReference
