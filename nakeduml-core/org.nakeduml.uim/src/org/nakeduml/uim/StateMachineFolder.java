/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.nakeduml.uim;

import org.eclipse.emf.common.util.EList;

import org.eclipse.uml2.uml.StateMachine;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>State Machine Folder</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.nakeduml.uim.StateMachineFolder#getStateForms <em>State Forms</em>}</li>
 *   <li>{@link org.nakeduml.uim.StateMachineFolder#getStateMachine <em>State Machine</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.nakeduml.uim.UIMPackage#getStateMachineFolder()
 * @model
 * @generated
 */
public interface StateMachineFolder extends OperationContainingFolder {
	/**
	 * Returns the value of the '<em><b>State Forms</b></em>' containment reference list.
	 * The list contents are of type {@link org.nakeduml.uim.StateForm}.
	 * It is bidirectional and its opposite is '{@link org.nakeduml.uim.StateForm#getFolder <em>Folder</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>State Forms</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>State Forms</em>' containment reference list.
	 * @see org.nakeduml.uim.UIMPackage#getStateMachineFolder_StateForms()
	 * @see org.nakeduml.uim.StateForm#getFolder
	 * @model opposite="folder" containment="true"
	 * @generated
	 */
	EList<StateForm> getStateForms();

	/**
	 * Returns the value of the '<em><b>State Machine</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>State Machine</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>State Machine</em>' reference.
	 * @see #setStateMachine(StateMachine)
	 * @see org.nakeduml.uim.UIMPackage#getStateMachineFolder_StateMachine()
	 * @model
	 * @generated
	 */
	StateMachine getStateMachine();

	/**
	 * Sets the value of the '{@link org.nakeduml.uim.StateMachineFolder#getStateMachine <em>State Machine</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>State Machine</em>' reference.
	 * @see #getStateMachine()
	 * @generated
	 */
	void setStateMachine(StateMachine value);

} // StateMachineFolder
