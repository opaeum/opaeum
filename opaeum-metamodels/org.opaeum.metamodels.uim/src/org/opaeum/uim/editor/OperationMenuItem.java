/**
 */
package org.opaeum.uim.editor;

import org.opaeum.uim.LabeledElement;
import org.opaeum.uim.constraint.UserInteractionConstraint;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Operation Menu Item</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.opaeum.uim.editor.OperationMenuItem#getMenuConfiguration <em>Menu Configuration</em>}</li>
 *   <li>{@link org.opaeum.uim.editor.OperationMenuItem#isHidden <em>Hidden</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.opaeum.uim.editor.EditorPackage#getOperationMenuItem()
 * @model
 * @generated
 */
public interface OperationMenuItem extends UserInteractionConstraint, LabeledElement {
	/**
	 * Returns the value of the '<em><b>Menu Configuration</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.opaeum.uim.editor.MenuConfiguration#getOperations <em>Operations</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Menu Configuration</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Menu Configuration</em>' container reference.
	 * @see #setMenuConfiguration(MenuConfiguration)
	 * @see org.opaeum.uim.editor.EditorPackage#getOperationMenuItem_MenuConfiguration()
	 * @see org.opaeum.uim.editor.MenuConfiguration#getOperations
	 * @model opposite="operations" required="true" transient="false"
	 * @generated
	 */
	MenuConfiguration getMenuConfiguration();

	/**
	 * Sets the value of the '{@link org.opaeum.uim.editor.OperationMenuItem#getMenuConfiguration <em>Menu Configuration</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Menu Configuration</em>' container reference.
	 * @see #getMenuConfiguration()
	 * @generated
	 */
	void setMenuConfiguration(MenuConfiguration value);

	/**
	 * Returns the value of the '<em><b>Hidden</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Hidden</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Hidden</em>' attribute.
	 * @see #setHidden(boolean)
	 * @see org.opaeum.uim.editor.EditorPackage#getOperationMenuItem_Hidden()
	 * @model
	 * @generated
	 */
	boolean isHidden();

	/**
	 * Sets the value of the '{@link org.opaeum.uim.editor.OperationMenuItem#isHidden <em>Hidden</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Hidden</em>' attribute.
	 * @see #isHidden()
	 * @generated
	 */
	void setHidden(boolean value);

} // OperationMenuItem
