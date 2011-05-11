/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.nakeduml.uim;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Operation Containing Folder</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.nakeduml.uim.OperationContainingFolder#getOperationInvocationForms <em>Operation Invocation Forms</em>}</li>
 *   <li>{@link org.nakeduml.uim.OperationContainingFolder#getClassForm <em>Class Form</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.nakeduml.uim.UIMPackage#getOperationContainingFolder()
 * @model abstract="true"
 * @generated
 */
public interface OperationContainingFolder extends AbstractFormFolder {
	/**
	 * Returns the value of the '<em><b>Operation Invocation Forms</b></em>' containment reference list.
	 * The list contents are of type {@link org.nakeduml.uim.OperationInvocationForm}.
	 * It is bidirectional and its opposite is '{@link org.nakeduml.uim.OperationInvocationForm#getFolder <em>Folder</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Operation Invocation Forms</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Operation Invocation Forms</em>' containment reference list.
	 * @see org.nakeduml.uim.UIMPackage#getOperationContainingFolder_OperationInvocationForms()
	 * @see org.nakeduml.uim.OperationInvocationForm#getFolder
	 * @model opposite="folder" containment="true"
	 * @generated
	 */
	EList<OperationInvocationForm> getOperationInvocationForms();

	/**
	 * Returns the value of the '<em><b>Class Form</b></em>' containment reference list.
	 * The list contents are of type {@link org.nakeduml.uim.ClassForm}.
	 * It is bidirectional and its opposite is '{@link org.nakeduml.uim.ClassForm#getFolder <em>Folder</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Class Form</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Class Form</em>' containment reference list.
	 * @see org.nakeduml.uim.UIMPackage#getOperationContainingFolder_ClassForm()
	 * @see org.nakeduml.uim.ClassForm#getFolder
	 * @model opposite="folder" containment="true"
	 * @generated
	 */
	EList<ClassForm> getClassForm();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation" required="true"
	 * @generated
	 */
	org.eclipse.uml2.uml.Class getRepresentedClass();

} // OperationContainingFolder
