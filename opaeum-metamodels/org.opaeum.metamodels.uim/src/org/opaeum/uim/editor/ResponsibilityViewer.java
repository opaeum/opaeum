/**
 */
package org.opaeum.uim.editor;

import org.opaeum.uim.model.ResponsibilityUserInteractionModel;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Responsibility Viewer</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.opaeum.uim.editor.ResponsibilityViewer#getModel <em>Model</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.opaeum.uim.editor.EditorPackage#getResponsibilityViewer()
 * @model
 * @generated
 */
public interface ResponsibilityViewer extends AbstractEditor {
	/**
	 * Returns the value of the '<em><b>Model</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.opaeum.uim.model.ResponsibilityUserInteractionModel#getViewer <em>Viewer</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Model</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Model</em>' container reference.
	 * @see #setModel(ResponsibilityUserInteractionModel)
	 * @see org.opaeum.uim.editor.EditorPackage#getResponsibilityViewer_Model()
	 * @see org.opaeum.uim.model.ResponsibilityUserInteractionModel#getViewer
	 * @model opposite="viewer" transient="false"
	 * @generated
	 */
	ResponsibilityUserInteractionModel getModel();

	/**
	 * Sets the value of the '{@link org.opaeum.uim.editor.ResponsibilityViewer#getModel <em>Model</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Model</em>' container reference.
	 * @see #getModel()
	 * @generated
	 */
	void setModel(ResponsibilityUserInteractionModel value);

} // ResponsibilityViewer
