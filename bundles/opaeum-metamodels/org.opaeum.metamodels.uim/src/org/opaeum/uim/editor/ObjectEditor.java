/**
 */
package org.opaeum.uim.editor;

import org.opaeum.uim.model.ClassUserInteractionModel;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Object Editor</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.opaeum.uim.editor.ObjectEditor#getModel <em>Model</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.opaeum.uim.editor.EditorPackage#getObjectEditor()
 * @model
 * @generated
 */
public interface ObjectEditor extends InstanceEditor {
	/**
	 * Returns the value of the '<em><b>Model</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.opaeum.uim.model.ClassUserInteractionModel#getPrimaryEditor <em>Primary Editor</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Model</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Model</em>' container reference.
	 * @see #setModel(ClassUserInteractionModel)
	 * @see org.opaeum.uim.editor.EditorPackage#getObjectEditor_Model()
	 * @see org.opaeum.uim.model.ClassUserInteractionModel#getPrimaryEditor
	 * @model opposite="primaryEditor" transient="false"
	 * @generated
	 */
	ClassUserInteractionModel getModel();

	/**
	 * Sets the value of the '{@link org.opaeum.uim.editor.ObjectEditor#getModel <em>Model</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Model</em>' container reference.
	 * @see #getModel()
	 * @generated
	 */
	void setModel(ClassUserInteractionModel value);

} // ObjectEditor
