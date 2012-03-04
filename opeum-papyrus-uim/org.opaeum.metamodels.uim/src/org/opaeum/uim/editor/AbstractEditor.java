/**
 */
package org.opaeum.uim.editor;

import org.eclipse.emf.common.util.EList;
import org.opaeum.uim.UmlReference;
import org.opaeum.uim.UserInterfaceEntryPoint;
import org.opaeum.uim.constraint.RootUserInteractionConstraint;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Abstract Editor</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.opaeum.uim.editor.AbstractEditor#getEditability <em>Editability</em>}</li>
 *   <li>{@link org.opaeum.uim.editor.AbstractEditor#getVisibility <em>Visibility</em>}</li>
 *   <li>{@link org.opaeum.uim.editor.AbstractEditor#getActionBar <em>Action Bar</em>}</li>
 *   <li>{@link org.opaeum.uim.editor.AbstractEditor#getMenuConfiguration <em>Menu Configuration</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.opaeum.uim.editor.EditorPackage#getAbstractEditor()
 * @model
 * @generated
 */
public interface AbstractEditor extends UmlReference, UserInterfaceEntryPoint {
	/**
	 * Returns the value of the '<em><b>Editability</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Editability</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Editability</em>' containment reference.
	 * @see #setEditability(RootUserInteractionConstraint)
	 * @see org.opaeum.uim.editor.EditorPackage#getAbstractEditor_Editability()
	 * @model containment="true"
	 * @generated
	 */
	RootUserInteractionConstraint getEditability();

	/**
	 * Sets the value of the '{@link org.opaeum.uim.editor.AbstractEditor#getEditability <em>Editability</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Editability</em>' containment reference.
	 * @see #getEditability()
	 * @generated
	 */
	void setEditability(RootUserInteractionConstraint value);

	/**
	 * Returns the value of the '<em><b>Visibility</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Visibility</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Visibility</em>' containment reference.
	 * @see #setVisibility(RootUserInteractionConstraint)
	 * @see org.opaeum.uim.editor.EditorPackage#getAbstractEditor_Visibility()
	 * @model containment="true"
	 * @generated
	 */
	RootUserInteractionConstraint getVisibility();

	/**
	 * Sets the value of the '{@link org.opaeum.uim.editor.AbstractEditor#getVisibility <em>Visibility</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Visibility</em>' containment reference.
	 * @see #getVisibility()
	 * @generated
	 */
	void setVisibility(RootUserInteractionConstraint value);

	/**
	 * Returns the value of the '<em><b>Action Bar</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Action Bar</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Action Bar</em>' containment reference.
	 * @see #setActionBar(ActionBar)
	 * @see org.opaeum.uim.editor.EditorPackage#getAbstractEditor_ActionBar()
	 * @model containment="true"
	 * @generated
	 */
	ActionBar getActionBar();

	/**
	 * Sets the value of the '{@link org.opaeum.uim.editor.AbstractEditor#getActionBar <em>Action Bar</em>}' containment reference.
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

} // AbstractEditor
