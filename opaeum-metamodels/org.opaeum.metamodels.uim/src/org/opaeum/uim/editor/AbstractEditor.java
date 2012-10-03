/**
 */
package org.opaeum.uim.editor;

import org.eclipse.emf.common.util.EList;
import org.opaeum.uim.UserInterfaceRoot;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Abstract Editor</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.opaeum.uim.editor.AbstractEditor#getPages <em>Pages</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.opaeum.uim.editor.EditorPackage#getAbstractEditor()
 * @model
 * @generated
 */
public interface AbstractEditor extends UserInterfaceRoot {
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
