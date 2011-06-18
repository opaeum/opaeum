/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.nakeduml.uim.action;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Kind</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.nakeduml.uim.action.ActionPackage#getActionKind()
 * @model
 * @generated
 */
public enum ActionKind implements Enumerator {
	/**
	 * The '<em><b>Update</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #UPDATE_VALUE
	 * @generated
	 * @ordered
	 */
	UPDATE(0, "update", "update"),

	/**
	 * The '<em><b>Delete</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #DELETE_VALUE
	 * @generated
	 * @ordered
	 */
	DELETE(1, "delete", "delete"),

	/**
	 * The '<em><b>Back</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #BACK_VALUE
	 * @generated
	 * @ordered
	 */
	BACK(2, "back", "back"),

	/**
	 * The '<em><b>Execute Operation</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #EXECUTE_OPERATION_VALUE
	 * @generated
	 * @ordered
	 */
	EXECUTE_OPERATION(3, "executeOperation", "executeOperation"),

	/**
	 * The '<em><b>Delegate Task</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #DELEGATE_TASK_VALUE
	 * @generated
	 * @ordered
	 */
	DELEGATE_TASK(4, "delegateTask", "delegateTask"),

	/**
	 * The '<em><b>Complete Task</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #COMPLETE_TASK_VALUE
	 * @generated
	 * @ordered
	 */
	COMPLETE_TASK(5, "completeTask", "completeTask"),

	/**
	 * The '<em><b>Suspend Task</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SUSPEND_TASK_VALUE
	 * @generated
	 * @ordered
	 */
	SUSPEND_TASK(6, "suspendTask", "suspendTask"),

	/**
	 * The '<em><b>Create</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #CREATE_VALUE
	 * @generated
	 * @ordered
	 */
	CREATE(7, "create", "create"),

	/**
	 * The '<em><b>Forward Task</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #FORWARD_TASK_VALUE
	 * @generated
	 * @ordered
	 */
	FORWARD_TASK(8, "forwardTask", "forwardTask"),

	/**
	 * The '<em><b>Claim Task</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #CLAIM_TASK_VALUE
	 * @generated
	 * @ordered
	 */
	CLAIM_TASK(9, "claimTask", "claimTask");

	/**
	 * The '<em><b>Update</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Update</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #UPDATE
	 * @model name="update"
	 * @generated
	 * @ordered
	 */
	public static final int UPDATE_VALUE = 0;

	/**
	 * The '<em><b>Delete</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Delete</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #DELETE
	 * @model name="delete"
	 * @generated
	 * @ordered
	 */
	public static final int DELETE_VALUE = 1;

	/**
	 * The '<em><b>Back</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Back</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #BACK
	 * @model name="back"
	 * @generated
	 * @ordered
	 */
	public static final int BACK_VALUE = 2;

	/**
	 * The '<em><b>Execute Operation</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Execute Operation</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #EXECUTE_OPERATION
	 * @model name="executeOperation"
	 * @generated
	 * @ordered
	 */
	public static final int EXECUTE_OPERATION_VALUE = 3;

	/**
	 * The '<em><b>Delegate Task</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Delegate Task</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #DELEGATE_TASK
	 * @model name="delegateTask"
	 * @generated
	 * @ordered
	 */
	public static final int DELEGATE_TASK_VALUE = 4;

	/**
	 * The '<em><b>Complete Task</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Complete Task</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #COMPLETE_TASK
	 * @model name="completeTask"
	 * @generated
	 * @ordered
	 */
	public static final int COMPLETE_TASK_VALUE = 5;

	/**
	 * The '<em><b>Suspend Task</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Suspend Task</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #SUSPEND_TASK
	 * @model name="suspendTask"
	 * @generated
	 * @ordered
	 */
	public static final int SUSPEND_TASK_VALUE = 6;

	/**
	 * The '<em><b>Create</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Create</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #CREATE
	 * @model name="create"
	 * @generated
	 * @ordered
	 */
	public static final int CREATE_VALUE = 7;

	/**
	 * The '<em><b>Forward Task</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Forward Task</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #FORWARD_TASK
	 * @model name="forwardTask"
	 * @generated
	 * @ordered
	 */
	public static final int FORWARD_TASK_VALUE = 8;

	/**
	 * The '<em><b>Claim Task</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Claim Task</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #CLAIM_TASK
	 * @model name="claimTask"
	 * @generated
	 * @ordered
	 */
	public static final int CLAIM_TASK_VALUE = 9;

	/**
	 * An array of all the '<em><b>Kind</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final ActionKind[] VALUES_ARRAY =
		new ActionKind[] {
			UPDATE,
			DELETE,
			BACK,
			EXECUTE_OPERATION,
			DELEGATE_TASK,
			COMPLETE_TASK,
			SUSPEND_TASK,
			CREATE,
			FORWARD_TASK,
			CLAIM_TASK,
		};

	/**
	 * A public read-only list of all the '<em><b>Kind</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<ActionKind> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Kind</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ActionKind get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			ActionKind result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Kind</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ActionKind getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			ActionKind result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Kind</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ActionKind get(int value) {
		switch (value) {
			case UPDATE_VALUE: return UPDATE;
			case DELETE_VALUE: return DELETE;
			case BACK_VALUE: return BACK;
			case EXECUTE_OPERATION_VALUE: return EXECUTE_OPERATION;
			case DELEGATE_TASK_VALUE: return DELEGATE_TASK;
			case COMPLETE_TASK_VALUE: return COMPLETE_TASK;
			case SUSPEND_TASK_VALUE: return SUSPEND_TASK;
			case CREATE_VALUE: return CREATE;
			case FORWARD_TASK_VALUE: return FORWARD_TASK;
			case CLAIM_TASK_VALUE: return CLAIM_TASK;
		}
		return null;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final int value;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final String name;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final String literal;

	/**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private ActionKind(int value, String name, String literal) {
		this.value = value;
		this.name = name;
		this.literal = literal;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getValue() {
	  return value;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
	  return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getLiteral() {
	  return literal;
	}

	/**
	 * Returns the literal value of the enumerator, which is its string representation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		return literal;
	}
	
} //ActionKind
