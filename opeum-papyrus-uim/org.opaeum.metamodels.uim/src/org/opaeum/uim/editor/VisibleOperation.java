/**
 */
package org.opaeum.uim.editor;

import org.opaeum.uim.UmlReference;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Visible Operation</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.opaeum.uim.editor.VisibleOperation#getMenuConfiguration <em>Menu Configuration</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.opaeum.uim.editor.EditorPackage#getVisibleOperation()
 * @model
 * @generated
 */
public interface VisibleOperation extends UmlReference {
	/**
	 * Returns the value of the '<em><b>Menu Configuration</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.opaeum.uim.editor.MenuConfiguration#getVisibleOperations <em>Visible Operations</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Menu Configuration</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Menu Configuration</em>' container reference.
	 * @see #setMenuConfiguration(MenuConfiguration)
	 * @see org.opaeum.uim.editor.EditorPackage#getVisibleOperation_MenuConfiguration()
	 * @see org.opaeum.uim.editor.MenuConfiguration#getVisibleOperations
	 * @model opposite="visibleOperations" required="true" transient="false"
	 * @generated
	 */
	MenuConfiguration getMenuConfiguration();

	/**
	 * Sets the value of the '{@link org.opaeum.uim.editor.VisibleOperation#getMenuConfiguration <em>Menu Configuration</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Menu Configuration</em>' container reference.
	 * @see #getMenuConfiguration()
	 * @generated
	 */
	void setMenuConfiguration(MenuConfiguration value);

} // VisibleOperation
