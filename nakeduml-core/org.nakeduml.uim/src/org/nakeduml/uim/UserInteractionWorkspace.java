/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.nakeduml.uim;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>User Interaction Workspace</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.nakeduml.uim.UserInteractionWorkspace#getVisibility <em>Visibility</em>}</li>
 *   <li>{@link org.nakeduml.uim.UserInteractionWorkspace#getEditability <em>Editability</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.nakeduml.uim.UimPackage#getUserInteractionWorkspace()
 * @model
 * @generated
 */
public interface UserInteractionWorkspace extends AbstractFolder {
	/**
	 * Returns the value of the '<em><b>Visibility</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Visibility</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Visibility</em>' containment reference.
	 * @see #setVisibility(WorkspaceSecurityConstraint)
	 * @see org.nakeduml.uim.UimPackage#getUserInteractionWorkspace_Visibility()
	 * @model containment="true"
	 * @generated
	 */
	WorkspaceSecurityConstraint getVisibility();

	/**
	 * Sets the value of the '{@link org.nakeduml.uim.UserInteractionWorkspace#getVisibility <em>Visibility</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Visibility</em>' containment reference.
	 * @see #getVisibility()
	 * @generated
	 */
	void setVisibility(WorkspaceSecurityConstraint value);

	/**
	 * Returns the value of the '<em><b>Editability</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Editability</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Editability</em>' containment reference.
	 * @see #setEditability(WorkspaceSecurityConstraint)
	 * @see org.nakeduml.uim.UimPackage#getUserInteractionWorkspace_Editability()
	 * @model containment="true"
	 * @generated
	 */
	WorkspaceSecurityConstraint getEditability();

	/**
	 * Sets the value of the '{@link org.nakeduml.uim.UserInteractionWorkspace#getEditability <em>Editability</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Editability</em>' containment reference.
	 * @see #getEditability()
	 * @generated
	 */
	void setEditability(WorkspaceSecurityConstraint value);

} // UserInteractionWorkspace
