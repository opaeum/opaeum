/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.nakeduml.uim;


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
 *   <li>{@link org.nakeduml.uim.ClassForm#getFolder <em>Folder</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.nakeduml.uim.UIMPackage#getClassForm()
 * @model extendedMetaData="kind='mixed'"
 * @generated
 */
public interface ClassForm extends UIMForm, UmlReference {
	/**
	 * Returns the value of the '<em><b>Folder</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.nakeduml.uim.OperationContainingFolder#getClassForm <em>Class Form</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Folder</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Folder</em>' container reference.
	 * @see #setFolder(OperationContainingFolder)
	 * @see org.nakeduml.uim.UIMPackage#getClassForm_Folder()
	 * @see org.nakeduml.uim.OperationContainingFolder#getClassForm
	 * @model opposite="classForm" required="true" transient="false"
	 * @generated
	 */
	OperationContainingFolder getFolder();

	/**
	 * Sets the value of the '{@link org.nakeduml.uim.ClassForm#getFolder <em>Folder</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Folder</em>' container reference.
	 * @see #getFolder()
	 * @generated
	 */
	void setFolder(OperationContainingFolder value);

} // ClassForm
