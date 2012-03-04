/**
 */
package org.opaeum.uim.editor;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Menu Configuration</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.opaeum.uim.editor.MenuConfiguration#getEditor <em>Editor</em>}</li>
 *   <li>{@link org.opaeum.uim.editor.MenuConfiguration#getVisibleOperations <em>Visible Operations</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.opaeum.uim.editor.EditorPackage#getMenuConfiguration()
 * @model
 * @generated
 */
public interface MenuConfiguration extends EObject {
	/**
	 * Returns the value of the '<em><b>Editor</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.opaeum.uim.editor.AbstractEditor#getMenuConfiguration <em>Menu Configuration</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Editor</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Editor</em>' container reference.
	 * @see #setEditor(AbstractEditor)
	 * @see org.opaeum.uim.editor.EditorPackage#getMenuConfiguration_Editor()
	 * @see org.opaeum.uim.editor.AbstractEditor#getMenuConfiguration
	 * @model opposite="menuConfiguration" required="true" transient="false"
	 * @generated
	 */
	AbstractEditor getEditor();

	/**
	 * Sets the value of the '{@link org.opaeum.uim.editor.MenuConfiguration#getEditor <em>Editor</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Editor</em>' container reference.
	 * @see #getEditor()
	 * @generated
	 */
	void setEditor(AbstractEditor value);

	/**
	 * Returns the value of the '<em><b>Visible Operations</b></em>' containment reference list.
	 * The list contents are of type {@link org.opaeum.uim.editor.VisibleOperation}.
	 * It is bidirectional and its opposite is '{@link org.opaeum.uim.editor.VisibleOperation#getMenuConfiguration <em>Menu Configuration</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Visible Operations</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Visible Operations</em>' containment reference list.
	 * @see org.opaeum.uim.editor.EditorPackage#getMenuConfiguration_VisibleOperations()
	 * @see org.opaeum.uim.editor.VisibleOperation#getMenuConfiguration
	 * @model opposite="menuConfiguration" containment="true"
	 * @generated
	 */
	EList<VisibleOperation> getVisibleOperations();

} // MenuConfiguration
