/**
 */
package org.opaeum.uim.editor;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Instance Editor</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A ClassForm gives the user access to all the attributes and operations of ANY of the following types of classes:
 * 1. OrgUNit
 * 2. Role
 * 3. Normal Entity
 * 4. Process
 * 5. StateMachine
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.opaeum.uim.editor.InstanceEditor#getActionBar <em>Action Bar</em>}</li>
 *   <li>{@link org.opaeum.uim.editor.InstanceEditor#getMenuConfiguration <em>Menu Configuration</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.opaeum.uim.editor.EditorPackage#getInstanceEditor()
 * @model abstract="true"
 *        extendedMetaData="kind='mixed'"
 * @generated
 */
public interface InstanceEditor extends AbstractEditor {
	/**
	 * Returns the value of the '<em><b>Action Bar</b></em>' containment reference.
	 * It is bidirectional and its opposite is '{@link org.opaeum.uim.editor.ActionBar#getEditor <em>Editor</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Action Bar</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Action Bar</em>' containment reference.
	 * @see #setActionBar(ActionBar)
	 * @see org.opaeum.uim.editor.EditorPackage#getInstanceEditor_ActionBar()
	 * @see org.opaeum.uim.editor.ActionBar#getEditor
	 * @model opposite="editor" containment="true"
	 * @generated
	 */
	ActionBar getActionBar();

	/**
	 * Sets the value of the '{@link org.opaeum.uim.editor.InstanceEditor#getActionBar <em>Action Bar</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Action Bar</em>' containment reference.
	 * @see #getActionBar()
	 * @generated
	 */
	void setActionBar(ActionBar value);

	/**
	 * Returns the value of the '<em><b>Menu Configuration</b></em>' containment reference.
	 * It is bidirectional and its opposite is '{@link org.opaeum.uim.editor.MenuConfiguration#getEditor <em>Editor</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Menu Configuration</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Menu Configuration</em>' containment reference.
	 * @see #setMenuConfiguration(MenuConfiguration)
	 * @see org.opaeum.uim.editor.EditorPackage#getInstanceEditor_MenuConfiguration()
	 * @see org.opaeum.uim.editor.MenuConfiguration#getEditor
	 * @model opposite="editor" containment="true"
	 * @generated
	 */
	MenuConfiguration getMenuConfiguration();

	/**
	 * Sets the value of the '{@link org.opaeum.uim.editor.InstanceEditor#getMenuConfiguration <em>Menu Configuration</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Menu Configuration</em>' containment reference.
	 * @see #getMenuConfiguration()
	 * @generated
	 */
	void setMenuConfiguration(MenuConfiguration value);

} // InstanceEditor
