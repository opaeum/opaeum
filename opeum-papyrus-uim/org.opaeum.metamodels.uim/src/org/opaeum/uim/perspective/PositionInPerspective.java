/**
 */
package org.opaeum.uim.perspective;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Position In Perspective</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.opaeum.uim.perspective.PerspectivePackage#getPositionInPerspective()
 * @model
 * @generated
 */
public enum PositionInPerspective implements Enumerator {
	/**
	 * The '<em><b>Left</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #LEFT_VALUE
	 * @generated
	 * @ordered
	 */
	LEFT(0, "left", "left"),

	/**
	 * The '<em><b>Right</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #RIGHT_VALUE
	 * @generated
	 * @ordered
	 */
	RIGHT(1, "right", "right"),

	/**
	 * The '<em><b>Top</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #TOP_VALUE
	 * @generated
	 * @ordered
	 */
	TOP(2, "top", "top"),

	/**
	 * The '<em><b>Bottom</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #BOTTOM_VALUE
	 * @generated
	 * @ordered
	 */
	BOTTOM(3, "bottom", "bottom"),

	/**
	 * The '<em><b>Right Top</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #RIGHT_TOP_VALUE
	 * @generated
	 * @ordered
	 */
	RIGHT_TOP(4, "rightTop", "rightTop"),

	/**
	 * The '<em><b>Right Bottom</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #RIGHT_BOTTOM_VALUE
	 * @generated
	 * @ordered
	 */
	RIGHT_BOTTOM(5, "rightBottom", "rightBottom"),

	/**
	 * The '<em><b>Left Top</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #LEFT_TOP_VALUE
	 * @generated
	 * @ordered
	 */
	LEFT_TOP(6, "leftTop", "leftTop"),

	/**
	 * The '<em><b>Left Bottom</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #LEFT_BOTTOM_VALUE
	 * @generated
	 * @ordered
	 */
	LEFT_BOTTOM(7, "leftBottom", "leftBottom"),

	/**
	 * The '<em><b>Top Right</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #TOP_RIGHT_VALUE
	 * @generated
	 * @ordered
	 */
	TOP_RIGHT(8, "topRight", "topRight"),

	/**
	 * The '<em><b>Top Left</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #TOP_LEFT_VALUE
	 * @generated
	 * @ordered
	 */
	TOP_LEFT(9, "topLeft", "topLeft"),

	/**
	 * The '<em><b>Bottom Right</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #BOTTOM_RIGHT_VALUE
	 * @generated
	 * @ordered
	 */
	BOTTOM_RIGHT(10, "bottomRight", "bottomRight"),

	/**
	 * The '<em><b>Bottom Left</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #BOTTOM_LEFT_VALUE
	 * @generated
	 * @ordered
	 */
	BOTTOM_LEFT(11, "bottomLeft", "bottomLeft");

	/**
	 * The '<em><b>Left</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Left</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #LEFT
	 * @model name="left"
	 * @generated
	 * @ordered
	 */
	public static final int LEFT_VALUE = 0;

	/**
	 * The '<em><b>Right</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Right</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #RIGHT
	 * @model name="right"
	 * @generated
	 * @ordered
	 */
	public static final int RIGHT_VALUE = 1;

	/**
	 * The '<em><b>Top</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Top</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #TOP
	 * @model name="top"
	 * @generated
	 * @ordered
	 */
	public static final int TOP_VALUE = 2;

	/**
	 * The '<em><b>Bottom</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Bottom</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #BOTTOM
	 * @model name="bottom"
	 * @generated
	 * @ordered
	 */
	public static final int BOTTOM_VALUE = 3;

	/**
	 * The '<em><b>Right Top</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Right Top</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #RIGHT_TOP
	 * @model name="rightTop"
	 * @generated
	 * @ordered
	 */
	public static final int RIGHT_TOP_VALUE = 4;

	/**
	 * The '<em><b>Right Bottom</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Right Bottom</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #RIGHT_BOTTOM
	 * @model name="rightBottom"
	 * @generated
	 * @ordered
	 */
	public static final int RIGHT_BOTTOM_VALUE = 5;

	/**
	 * The '<em><b>Left Top</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Left Top</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #LEFT_TOP
	 * @model name="leftTop"
	 * @generated
	 * @ordered
	 */
	public static final int LEFT_TOP_VALUE = 6;

	/**
	 * The '<em><b>Left Bottom</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Left Bottom</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #LEFT_BOTTOM
	 * @model name="leftBottom"
	 * @generated
	 * @ordered
	 */
	public static final int LEFT_BOTTOM_VALUE = 7;

	/**
	 * The '<em><b>Top Right</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Top Right</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #TOP_RIGHT
	 * @model name="topRight"
	 * @generated
	 * @ordered
	 */
	public static final int TOP_RIGHT_VALUE = 8;

	/**
	 * The '<em><b>Top Left</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Top Left</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #TOP_LEFT
	 * @model name="topLeft"
	 * @generated
	 * @ordered
	 */
	public static final int TOP_LEFT_VALUE = 9;

	/**
	 * The '<em><b>Bottom Right</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Bottom Right</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #BOTTOM_RIGHT
	 * @model name="bottomRight"
	 * @generated
	 * @ordered
	 */
	public static final int BOTTOM_RIGHT_VALUE = 10;

	/**
	 * The '<em><b>Bottom Left</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Bottom Left</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #BOTTOM_LEFT
	 * @model name="bottomLeft"
	 * @generated
	 * @ordered
	 */
	public static final int BOTTOM_LEFT_VALUE = 11;

	/**
	 * An array of all the '<em><b>Position In Perspective</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final PositionInPerspective[] VALUES_ARRAY =
		new PositionInPerspective[] {
			LEFT,
			RIGHT,
			TOP,
			BOTTOM,
			RIGHT_TOP,
			RIGHT_BOTTOM,
			LEFT_TOP,
			LEFT_BOTTOM,
			TOP_RIGHT,
			TOP_LEFT,
			BOTTOM_RIGHT,
			BOTTOM_LEFT,
		};

	/**
	 * A public read-only list of all the '<em><b>Position In Perspective</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<PositionInPerspective> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Position In Perspective</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static PositionInPerspective get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			PositionInPerspective result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Position In Perspective</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static PositionInPerspective getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			PositionInPerspective result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Position In Perspective</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static PositionInPerspective get(int value) {
		switch (value) {
			case LEFT_VALUE: return LEFT;
			case RIGHT_VALUE: return RIGHT;
			case TOP_VALUE: return TOP;
			case BOTTOM_VALUE: return BOTTOM;
			case RIGHT_TOP_VALUE: return RIGHT_TOP;
			case RIGHT_BOTTOM_VALUE: return RIGHT_BOTTOM;
			case LEFT_TOP_VALUE: return LEFT_TOP;
			case LEFT_BOTTOM_VALUE: return LEFT_BOTTOM;
			case TOP_RIGHT_VALUE: return TOP_RIGHT;
			case TOP_LEFT_VALUE: return TOP_LEFT;
			case BOTTOM_RIGHT_VALUE: return BOTTOM_RIGHT;
			case BOTTOM_LEFT_VALUE: return BOTTOM_LEFT;
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
	private PositionInPerspective(int value, String name, String literal) {
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
	
} //PositionInPerspective
