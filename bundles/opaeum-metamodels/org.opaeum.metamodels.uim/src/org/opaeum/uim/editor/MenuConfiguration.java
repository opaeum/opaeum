/**
 */
package org.opaeum.uim.editor;

import org.eclipse.emf.common.util.EList;
import org.opaeum.uim.UserInteractionElement;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Menu Configuration</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.opaeum.uim.editor.MenuConfiguration#getEditor <em>Editor</em>}</li>
 *   <li>{@link org.opaeum.uim.editor.MenuConfiguration#getOperations <em>Operations</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.opaeum.uim.editor.EditorPackage#getMenuConfiguration()
 * @model
 * @generated
 */
public interface MenuConfiguration extends UserInteractionElement {
	/**
	 * Returns the value of the '<em><b>Editor</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.opaeum.uim.editor.InstanceEditor#getMenuConfiguration <em>Menu Configuration</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Editor</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Editor</em>' container reference.
	 * @see #setEditor(InstanceEditor)
	 * @see org.opaeum.uim.editor.EditorPackage#getMenuConfiguration_Editor()
	 * @see org.opaeum.uim.editor.InstanceEditor#getMenuConfiguration
	 * @model opposite="menuConfiguration" required="true" transient="false"
	 * @generated
	 */
	InstanceEditor getEditor();

	/**
	 * Sets the value of the '{@link org.opaeum.uim.editor.MenuConfiguration#getEditor <em>Editor</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Editor</em>' container reference.
	 * @see #getEditor()
	 * @generated
	 */
	void setEditor(InstanceEditor value);

	/**
	 * Returns the value of the '<em><b>Operations</b></em>' containment reference list.
	 * The list contents are of type {@link org.opaeum.uim.editor.OperationMenuItem}.
	 * It is bidirectional and its opposite is '{@link org.opaeum.uim.editor.OperationMenuItem#getMenuConfiguration <em>Menu Configuration</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Operations</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Operations</em>' containment reference list.
	 * @see org.opaeum.uim.editor.EditorPackage#getMenuConfiguration_Operations()
	 * @see org.opaeum.uim.editor.OperationMenuItem#getMenuConfiguration
	 * @model opposite="menuConfiguration" containment="true"
	 * @generated
	 */
	EList<OperationMenuItem> getOperations();

} // MenuConfiguration
