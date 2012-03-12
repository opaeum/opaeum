/**
 */
package org.opaeum.uim.action;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Kind</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.opaeum.uim.action.ActionPackage#getActionKind()
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
	CLAIM_TASK(9, "claimTask", "claimTask"), /**
	 * The '<em><b>Add</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #ADD_VALUE
	 * @generated
	 * @ordered
	 */
	ADD(2, "add", "add"), /**
	 * The '<em><b>Refresh</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #REFRESH_VALUE
	 * @generated
	 * @ordered
	 */
	REFRESH(7, "refresh", "refresh"), /**
	 * The '<em><b>Revert</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #REVERT_VALUE
	 * @generated
	 * @ordered
	 */
	REVERT(10, "revert", "revert");

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
	 * The '<em><b>Add</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Add</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #ADD
	 * @model name="add"
	 * @generated
	 * @ordered
	 */
	public static final int ADD_VALUE = 2;

	/**
	 * The '<em><b>Refresh</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Refresh</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #REFRESH
	 * @model name="refresh"
	 * @generated
	 * @ordered
	 */
	public static final int REFRESH_VALUE = 7;

	/**
	 * The '<em><b>Revert</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Revert</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #REVERT
	 * @model name="revert"
	 * @generated
	 * @ordered
	 */
	public static final int REVERT_VALUE = 10;

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
			EXECUTE_OPERATION,
			DELEGATE_TASK,
			COMPLETE_TASK,
			SUSPEND_TASK,
			FORWARD_TASK,
			CLAIM_TASK,
			ADD,
			REFRESH,
			REVERT,
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
			case EXECUTE_OPERATION_VALUE: return EXECUTE_OPERATION;
			case DELEGATE_TASK_VALUE: return DELEGATE_TASK;
			case COMPLETE_TASK_VALUE: return COMPLETE_TASK;
			case SUSPEND_TASK_VALUE: return SUSPEND_TASK;
			case FORWARD_TASK_VALUE: return FORWARD_TASK;
			case CLAIM_TASK_VALUE: return CLAIM_TASK;
			case ADD_VALUE: return ADD;
			case REFRESH_VALUE: return REFRESH;
			case REVERT_VALUE: return REVERT;
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
