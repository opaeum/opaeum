/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.nakeduml.uim.form;

import org.nakeduml.uim.folder.OperationContainingFolder;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Operation Invocation Form</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.nakeduml.uim.form.OperationInvocationForm#getFolder <em>Folder</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.nakeduml.uim.form.FormPackage#getOperationInvocationForm()
 * @model
 * @generated
 */
public interface OperationInvocationForm extends FormPanel {
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
	 * @see org.nakeduml.uim.form.FormPackage#getOperationInvocationForm_Folder()
	 * @model required="true"
	 * @generated
	 */
	OperationContainingFolder getFolder();

	/**
	 * Sets the value of the '{@link org.nakeduml.uim.form.OperationInvocationForm#getFolder <em>Folder</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Folder</em>' reference.
	 * @see #getFolder()
	 * @generated
	 */
	void setFolder(OperationContainingFolder value);

} // OperationInvocationForm
