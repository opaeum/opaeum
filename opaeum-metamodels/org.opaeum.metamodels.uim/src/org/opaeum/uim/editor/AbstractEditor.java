/**
 */
package org.opaeum.uim.editor;

import org.eclipse.emf.common.util.EList;
import org.opaeum.uim.UmlReference;
import org.opaeum.uim.UserInterfaceEntryPoint;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Abstract Editor</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.opaeum.uim.editor.AbstractEditor#getActionBar <em>Action Bar</em>}</li>
 *   <li>{@link org.opaeum.uim.editor.AbstractEditor#getMenuConfiguration <em>Menu Configuration</em>}</li>
 *   <li>{@link org.opaeum.uim.editor.AbstractEditor#getPages <em>Pages</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.opaeum.uim.editor.EditorPackage#getAbstractEditor()
 * @model abstract="true"
 * @generated
 */
public interface AbstractEditor extends UmlReference, UserInterfaceEntryPoint {
	/**
	 * Returns the value of the '<em><b>Action Bar</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Action Bar</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Action Bar</em>' containment reference.
	 * @see #setActionBar(EditorActionBar)
	 * @see org.opaeum.uim.editor.EditorPackage#getAbstractEditor_ActionBar()
	 * @model containment="true"
	 * @generated
	 */
	EditorActionBar getActionBar();

	/**
	 * Sets the value of the '{@link org.opaeum.uim.editor.AbstractEditor#getActionBar <em>Action Bar</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Action Bar</em>' containment reference.
	 * @see #getActionBar()
	 * @generated
	 */
	void setActionBar(EditorActionBar value);

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
	 * @see org.opaeum.uim.editor.EditorPackage#getAbstractEditor_MenuConfiguration()
	 * @see org.opaeum.uim.editor.MenuConfiguration#getEditor
	 * @model opposite="editor" containment="true"
	 * @generated
	 */
	MenuConfiguration getMenuConfiguration();

	/**
	 * Sets the value of the '{@link org.opaeum.uim.editor.AbstractEditor#getMenuConfiguration <em>Menu Configuration</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Menu Configuration</em>' containment reference.
	 * @see #getMenuConfiguration()
	 * @generated
	 */
	void setMenuConfiguration(MenuConfiguration value);

	/**
	 * Returns the value of the '<em><b>Pages</b></em>' containment reference list.
	 * The list contents are of type {@link org.opaeum.uim.editor.EditorPage}.
	 * It is bidirectional and its opposite is '{@link org.opaeum.uim.editor.EditorPage#getEditor <em>Editor</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Pages</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Pages</em>' containment reference list.
	 * @see org.opaeum.uim.editor.EditorPackage#getAbstractEditor_Pages()
	 * @see org.opaeum.uim.editor.EditorPage#getEditor
	 * @model opposite="editor" containment="true"
	 * @generated
	 */
	EList<EditorPage> getPages();

} // AbstractEditor
