/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.nakeduml.uim;

import org.eclipse.uml2.uml.Operation;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Operation Invocation Form</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.nakeduml.uim.OperationInvocationForm#getOperation <em>Operation</em>}</li>
 *   <li>{@link org.nakeduml.uim.OperationInvocationForm#getFolder <em>Folder</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.nakeduml.uim.UIMPackage#getOperationInvocationForm()
 * @model
 * @generated
 */
public interface OperationInvocationForm extends UIMForm, UmlReference {
	/**
	 * Returns the value of the '<em><b>Operation</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Operation</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Operation</em>' reference.
	 * @see #setOperation(Operation)
	 * @see org.nakeduml.uim.UIMPackage#getOperationInvocationForm_Operation()
	 * @model
	 * @generated
	 */
	Operation getOperation();

	/**
	 * Sets the value of the '{@link org.nakeduml.uim.OperationInvocationForm#getOperation <em>Operation</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Operation</em>' reference.
	 * @see #getOperation()
	 * @generated
	 */
	void setOperation(Operation value);

	/**
	 * Returns the value of the '<em><b>Folder</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.nakeduml.uim.OperationContainingFolder#getOperationInvocationForms <em>Operation Invocation Forms</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Folder</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Folder</em>' container reference.
	 * @see #setFolder(OperationContainingFolder)
	 * @see org.nakeduml.uim.UIMPackage#getOperationInvocationForm_Folder()
	 * @see org.nakeduml.uim.OperationContainingFolder#getOperationInvocationForms
	 * @model opposite="operationInvocationForms" required="true" transient="false"
	 * @generated
	 */
	OperationContainingFolder getFolder();

	/**
	 * Sets the value of the '{@link org.nakeduml.uim.OperationInvocationForm#getFolder <em>Folder</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Folder</em>' container reference.
	 * @see #getFolder()
	 * @generated
	 */
	void setFolder(OperationContainingFolder value);

} // OperationInvocationForm
