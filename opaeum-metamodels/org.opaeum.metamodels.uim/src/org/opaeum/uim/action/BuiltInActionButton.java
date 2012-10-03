/**
 */
package org.opaeum.uim.action;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Built In Action Button</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.opaeum.uim.action.BuiltInActionButton#getKind <em>Kind</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.opaeum.uim.action.ActionPackage#getBuiltInActionButton()
 * @model
 * @generated
 */
public interface BuiltInActionButton extends AbstractActionButton {
	/**
	 * Returns the value of the '<em><b>Kind</b></em>' attribute.
	 * The literals are from the enumeration {@link org.opaeum.uim.action.ActionKind}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Kind</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Kind</em>' attribute.
	 * @see org.opaeum.uim.action.ActionKind
	 * @see #setKind(ActionKind)
	 * @see org.opaeum.uim.action.ActionPackage#getBuiltInActionButton_Kind()
	 * @model
	 * @generated
	 */
	ActionKind getKind();

	/**
	 * Sets the value of the '{@link org.opaeum.uim.action.BuiltInActionButton#getKind <em>Kind</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Kind</em>' attribute.
	 * @see org.opaeum.uim.action.ActionKind
	 * @see #getKind()
	 * @generated
	 */
	void setKind(ActionKind value);

} // BuiltInActionButton
