/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.nakeduml.uim;

import org.eclipse.uml2.uml.OpaqueAction;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Action Task Form</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.nakeduml.uim.ActionTaskForm#getAction <em>Action</em>}</li>
 *   <li>{@link org.nakeduml.uim.ActionTaskForm#getFolder <em>Folder</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.nakeduml.uim.UIMPackage#getActionTaskForm()
 * @model
 * @generated
 */
public interface ActionTaskForm extends UIMForm {
	/**
	 * Returns the value of the '<em><b>Action</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Action</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Action</em>' reference.
	 * @see #setAction(OpaqueAction)
	 * @see org.nakeduml.uim.UIMPackage#getActionTaskForm_Action()
	 * @model
	 * @generated
	 */
	OpaqueAction getAction();

	/**
	 * Sets the value of the '{@link org.nakeduml.uim.ActionTaskForm#getAction <em>Action</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Action</em>' reference.
	 * @see #getAction()
	 * @generated
	 */
	void setAction(OpaqueAction value);

	/**
	 * Returns the value of the '<em><b>Folder</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.nakeduml.uim.ActivityFolder#getActionTaskForms <em>Action Task Forms</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Folder</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Folder</em>' container reference.
	 * @see #setFolder(ActivityFolder)
	 * @see org.nakeduml.uim.UIMPackage#getActionTaskForm_Folder()
	 * @see org.nakeduml.uim.ActivityFolder#getActionTaskForms
	 * @model opposite="actionTaskForms" transient="false"
	 * @generated
	 */
	ActivityFolder getFolder();

	/**
	 * Sets the value of the '{@link org.nakeduml.uim.ActionTaskForm#getFolder <em>Folder</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Folder</em>' container reference.
	 * @see #getFolder()
	 * @generated
	 */
	void setFolder(ActivityFolder value);

} // ActionTaskForm
