/**
 */
package org.opaeum.uim.editor;

import org.opaeum.uim.panel.AbstractPanel;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Action Bar</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.opaeum.uim.editor.ActionBar#getEditor <em>Editor</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.opaeum.uim.editor.EditorPackage#getActionBar()
 * @model
 * @generated
 */
public interface ActionBar extends AbstractPanel {
	/**
	 * Returns the value of the '<em><b>Editor</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.opaeum.uim.editor.InstanceEditor#getActionBar <em>Action Bar</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Editor</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Editor</em>' container reference.
	 * @see #setEditor(InstanceEditor)
	 * @see org.opaeum.uim.editor.EditorPackage#getActionBar_Editor()
	 * @see org.opaeum.uim.editor.InstanceEditor#getActionBar
	 * @model opposite="actionBar" transient="false"
	 * @generated
	 */
	InstanceEditor getEditor();

	/**
	 * Sets the value of the '{@link org.opaeum.uim.editor.ActionBar#getEditor <em>Editor</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Editor</em>' container reference.
	 * @see #getEditor()
	 * @generated
	 */
	void setEditor(InstanceEditor value);

} // ActionBar
