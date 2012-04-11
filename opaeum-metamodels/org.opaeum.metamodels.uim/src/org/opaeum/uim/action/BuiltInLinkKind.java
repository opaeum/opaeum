/**
 */
package org.opaeum.uim.action;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Built In Link Kind</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.opaeum.uim.action.ActionPackage#getBuiltInLinkKind()
 * @model
 * @generated
 */
public enum BuiltInLinkKind implements Enumerator {
	/**
	 * The '<em><b>Audit Trail</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #AUDIT_TRAIL_VALUE
	 * @generated
	 * @ordered
	 */
	AUDIT_TRAIL(0, "auditTrail", "auditTrail"),

	/**
	 * The '<em><b>Business Intelligence</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #BUSINESS_INTELLIGENCE_VALUE
	 * @generated
	 * @ordered
	 */
	BUSINESS_INTELLIGENCE(1, "businessIntelligence", "businessIntelligence"), /**
	 * The '<em><b>Edit</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #EDIT_VALUE
	 * @generated
	 * @ordered
	 */
	EDIT(2, "edit", "edit"), /**
	 * The '<em><b>View</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #VIEW_VALUE
	 * @generated
	 * @ordered
	 */
	VIEW(3, "view", "view");

	/**
	 * The '<em><b>Audit Trail</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Audit Trail</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #AUDIT_TRAIL
	 * @model name="auditTrail"
	 * @generated
	 * @ordered
	 */
	public static final int AUDIT_TRAIL_VALUE = 0;

	/**
	 * The '<em><b>Business Intelligence</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Business Intelligence</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #BUSINESS_INTELLIGENCE
	 * @model name="businessIntelligence"
	 * @generated
	 * @ordered
	 */
	public static final int BUSINESS_INTELLIGENCE_VALUE = 1;

	/**
	 * The '<em><b>Edit</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Edit</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #EDIT
	 * @model name="edit"
	 * @generated
	 * @ordered
	 */
	public static final int EDIT_VALUE = 2;

	/**
	 * The '<em><b>View</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>View</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #VIEW
	 * @model name="view"
	 * @generated
	 * @ordered
	 */
	public static final int VIEW_VALUE = 3;

	/**
	 * An array of all the '<em><b>Built In Link Kind</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final BuiltInLinkKind[] VALUES_ARRAY =
		new BuiltInLinkKind[] {
			AUDIT_TRAIL,
			BUSINESS_INTELLIGENCE,
			EDIT,
			VIEW,
		};

	/**
	 * A public read-only list of all the '<em><b>Built In Link Kind</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<BuiltInLinkKind> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Built In Link Kind</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static BuiltInLinkKind get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			BuiltInLinkKind result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Built In Link Kind</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static BuiltInLinkKind getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			BuiltInLinkKind result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Built In Link Kind</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static BuiltInLinkKind get(int value) {
		switch (value) {
			case AUDIT_TRAIL_VALUE: return AUDIT_TRAIL;
			case BUSINESS_INTELLIGENCE_VALUE: return BUSINESS_INTELLIGENCE;
			case EDIT_VALUE: return EDIT;
			case VIEW_VALUE: return VIEW;
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
	private BuiltInLinkKind(int value, String name, String literal) {
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
	
} //BuiltInLinkKind
