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
 * </ul>
 * </p>
 *
 * @see org.opaeum.uim.editor.EditorPackage#getEditorPage()
 * @model
 * @generated
 */
public interface EditorPage extends Page {
	/**
	 * Returns the value of the '<em><b>Editor</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.opaeum.uim.editor.AbstractEditor#getPages <em>Pages</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Editor</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Editor</em>' container reference.
	 * @see #setEditor(AbstractEditor)
	 * @see org.opaeum.uim.editor.EditorPackage#getEditorPage_Editor()
	 * @see org.opaeum.uim.editor.AbstractEditor#getPages
	 * @model opposite="pages" required="true" transient="false"
	 * @generated
	 */
	AbstractEditor getEditor();

	/**
	 * Sets the value of the '{@link org.opaeum.uim.editor.EditorPage#getEditor <em>Editor</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Editor</em>' container reference.
	 * @see #getEditor()
	 * @generated
	 */
	void setEditor(AbstractEditor value);

} // EditorPage
