/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.opaeum.uim.form;

import org.opaeum.uim.folder.OperationContainingFolder;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Class Form</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A ClassForm gives the user access to all the attributes and operations of ANY of the following types of classes:
 * 1. OrgUNit
 * 2. Role
 * 3. Normal Entity
 * 4. Process
 * 5. StateMachine
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.opaeum.uim.form.ClassForm#getFolder <em>Folder</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.opaeum.uim.form.FormPackage#getClassForm()
 * @model extendedMetaData="kind='mixed'"
 * @generated
 */
public interface ClassForm extends FormPanel {
	/**
	 * Returns the value of the '<em><b>Folder</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Folder</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Folder</em>' reference.
	 * @see #setFolder(OperationContainingFolder)
	 * @see org.opaeum.uim.form.FormPackage#getClassForm_Folder()
	 * @model required="true"
	 * @generated
	 */
	OperationContainingFolder getFolder();

	/**
	 * Sets the value of the '{@link org.opaeum.uim.form.ClassForm#getFolder <em>Folder</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Folder</em>' reference.
	 * @see #getFolder()
	 * @generated
	 */
	void setFolder(OperationContainingFolder value);

} // ClassForm
