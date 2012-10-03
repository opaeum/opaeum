/**
 */
package org.opaeum.uim.control;

import org.opaeum.uim.editor.ObjectEditor;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Uim Link Control</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.opaeum.uim.control.UimLinkControl#getEditorToOpen <em>Editor To Open</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.opaeum.uim.control.ControlPackage#getUimLinkControl()
 * @model
 * @generated
 */
public interface UimLinkControl extends UimControl {
	/**
	 * Returns the value of the '<em><b>Editor To Open</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Editor To Open</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Editor To Open</em>' reference.
	 * @see #setEditorToOpen(ObjectEditor)
	 * @see org.opaeum.uim.control.ControlPackage#getUimLinkControl_EditorToOpen()
	 * @model
	 * @generated
	 */
	ObjectEditor getEditorToOpen();

	/**
	 * Sets the value of the '{@link org.opaeum.uim.control.UimLinkControl#getEditorToOpen <em>Editor To Open</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Editor To Open</em>' reference.
	 * @see #getEditorToOpen()
	 * @generated
	 */
	void setEditorToOpen(ObjectEditor value);

} // UimLinkControl
