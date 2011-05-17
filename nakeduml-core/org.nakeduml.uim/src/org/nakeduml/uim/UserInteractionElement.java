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
 * A representation of the model object '<em><b>User Interaction Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.nakeduml.uim.UserInteractionElement#getName <em>Name</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.nakeduml.uim.UimPackage#getUserInteractionElement()
 * @model abstract="true"
 * @generated
 */
public interface UserInteractionElement extends EObject {
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see org.nakeduml.uim.UimPackage#getUserInteractionElement_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link org.nakeduml.uim.UserInteractionElement#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

} // UserInteractionElement
