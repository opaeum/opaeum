/**
 */
package org.opaeum.uim.editor;

import org.opaeum.uim.Page;
import org.opaeum.uim.panel.AbstractPanel;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Page</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.opaeum.uim.editor.EditorPage#getEditor <em>Editor</em>}</li>
 *   <li>{@link org.opaeum.uim.editor.EditorPage#getPanel <em>Panel</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.opaeum.uim.editor.EditorPackage#getEditorPage()
 * @model
 * @generated
 */
public interface EditorPage extends Page {
	/**
	 * Returns the value of the '<em><b>Editor</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Editor</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Editor</em>' reference.
	 * @see #setEditor(AbstractEditor)
	 * @see org.opaeum.uim.editor.EditorPackage#getEditorPage_Editor()
	 * @model required="true"
	 * @generated
	 */
	AbstractEditor getEditor();

	/**
	 * Sets the value of the '{@link org.opaeum.uim.editor.EditorPage#getEditor <em>Editor</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Editor</em>' reference.
	 * @see #getEditor()
	 * @generated
	 */
	void setEditor(AbstractEditor value);

	/**
	 * Returns the value of the '<em><b>Panel</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Panel</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Panel</em>' containment reference.
	 * @see #setPanel(AbstractPanel)
	 * @see org.opaeum.uim.editor.EditorPackage#getEditorPage_Panel()
	 * @model containment="true" required="true"
	 * @generated
	 */
	AbstractPanel getPanel();

	/**
	 * Sets the value of the '{@link org.opaeum.uim.editor.EditorPage#getPanel <em>Panel</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Panel</em>' containment reference.
	 * @see #getPanel()
	 * @generated
	 */
	void setPanel(AbstractPanel value);

} // EditorPage
