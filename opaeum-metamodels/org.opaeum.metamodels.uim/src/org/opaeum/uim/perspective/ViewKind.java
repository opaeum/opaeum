/**
 */
package org.opaeum.uim.perspective;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>View Kind</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.opaeum.uim.perspective.PerspectivePackage#getViewKind()
 * @model
 * @generated
 */
public enum ViewKind implements Enumerator {
	/**
	 * The '<em><b>Explorer</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #EXPLORER_VALUE
	 * @generated
	 * @ordered
	 */
	EXPLORER(0, "explorer", "explorer"),

	/**
	 * The '<em><b>Editor</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #EDITOR_VALUE
	 * @generated
	 * @ordered
	 */
	EDITOR(1, "editor", "editor"),

	/**
	 * The '<em><b>Inbox</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #INBOX_VALUE
	 * @generated
	 * @ordered
	 */
	INBOX(2, "inbox", "inbox"),

	/**
	 * The '<em><b>Outbox</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #OUTBOX_VALUE
	 * @generated
	 * @ordered
	 */
	OUTBOX(3, "outbox", "outbox");

	/**
	 * The '<em><b>Explorer</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Explorer</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #EXPLORER
	 * @model name="explorer"
	 * @generated
	 * @ordered
	 */
	public static final int EXPLORER_VALUE = 0;

	/**
	 * The '<em><b>Editor</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Editor</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #EDITOR
	 * @model name="editor"
	 * @generated
	 * @ordered
	 */
	public static final int EDITOR_VALUE = 1;

	/**
	 * The '<em><b>Inbox</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Inbox</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #INBOX
	 * @model name="inbox"
	 * @generated
	 * @ordered
	 */
	public static final int INBOX_VALUE = 2;

	/**
	 * The '<em><b>Outbox</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Outbox</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #OUTBOX
	 * @model name="outbox"
	 * @generated
	 * @ordered
	 */
	public static final int OUTBOX_VALUE = 3;

	/**
	 * An array of all the '<em><b>View Kind</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final ViewKind[] VALUES_ARRAY =
		new ViewKind[] {
			EXPLORER,
			EDITOR,
			INBOX,
			OUTBOX,
		};

	/**
	 * A public read-only list of all the '<em><b>View Kind</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<ViewKind> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>View Kind</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ViewKind get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			ViewKind result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>View Kind</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ViewKind getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			ViewKind result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>View Kind</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ViewKind get(int value) {
		switch (value) {
			case EXPLORER_VALUE: return EXPLORER;
			case EDITOR_VALUE: return EDITOR;
			case INBOX_VALUE: return INBOX;
			case OUTBOX_VALUE: return OUTBOX;
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
	private ViewKind(int value, String name, String literal) {
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
	
} //ViewKind
