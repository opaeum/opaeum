/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.opaeum.uim.form;

import org.opaeum.uim.folder.StateMachineFolder;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>State Form</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.opaeum.uim.form.StateForm#getFolder <em>Folder</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.opaeum.uim.form.FormPackage#getStateForm()
 * @model
 * @generated
 */
public interface StateForm extends FormPanel {
	/**
	 * Returns the value of the '<em><b>Folder</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Folder</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Folder</em>' reference.
	 * @see #setFolder(StateMachineFolder)
	 * @see org.opaeum.uim.form.FormPackage#getStateForm_Folder()
	 * @model required="true"
	 * @generated
	 */
	StateMachineFolder getFolder();

	/**
	 * Sets the value of the '{@link org.opaeum.uim.form.StateForm#getFolder <em>Folder</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Folder</em>' reference.
	 * @see #getFolder()
	 * @generated
	 */
	void setFolder(StateMachineFolder value);

} // StateForm
