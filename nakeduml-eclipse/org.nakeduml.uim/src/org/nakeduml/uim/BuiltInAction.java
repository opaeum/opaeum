/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.nakeduml.uim;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Built In Action</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.nakeduml.uim.BuiltInAction#getKind <em>Kind</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.nakeduml.uim.UIMPackage#getBuiltInAction()
 * @model
 * @generated
 */
public interface BuiltInAction extends UIMAction {
	/**
	 * Returns the value of the '<em><b>Kind</b></em>' attribute.
	 * The literals are from the enumeration {@link org.nakeduml.uim.ActionKind}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Kind</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Kind</em>' attribute.
	 * @see org.nakeduml.uim.ActionKind
	 * @see #setKind(ActionKind)
	 * @see org.nakeduml.uim.UIMPackage#getBuiltInAction_Kind()
	 * @model
	 * @generated
	 */
	ActionKind getKind();

	/**
	 * Sets the value of the '{@link org.nakeduml.uim.BuiltInAction#getKind <em>Kind</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Kind</em>' attribute.
	 * @see org.nakeduml.uim.ActionKind
	 * @see #getKind()
	 * @generated
	 */
	void setKind(ActionKind value);

} // BuiltInAction
