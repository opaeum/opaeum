/**
 */
package org.opaeum.uim.action;

import org.eclipse.emf.common.util.EList;
import org.opaeum.uim.PageContainer;
import org.opaeum.uim.UmlReference;
import org.opaeum.uim.editor.AbstractEditor;
import org.opaeum.uim.UserInterface;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Operation Action Popup</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.opaeum.uim.action.OperationActionPopup#getOperationAction <em>Operation Action</em>}</li>
 *   <li>{@link org.opaeum.uim.action.OperationActionPopup#getPages <em>Pages</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.opaeum.uim.action.ActionPackage#getOperationActionPopup()
 * @model
 * @generated
 */
public interface OperationActionPopup extends PageContainer, UmlReference {
	/**
	 * Returns the value of the '<em><b>Operation Action</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.opaeum.uim.action.OperationAction#getPopup <em>Popup</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Operation Action</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Operation Action</em>' container reference.
	 * @see #setOperationAction(OperationAction)
	 * @see org.opaeum.uim.action.ActionPackage#getOperationActionPopup_OperationAction()
	 * @see org.opaeum.uim.action.OperationAction#getPopup
	 * @model opposite="popup" transient="false"
	 * @generated
	 */
	OperationAction getOperationAction();

	/**
	 * Sets the value of the '{@link org.opaeum.uim.action.OperationActionPopup#getOperationAction <em>Operation Action</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Operation Action</em>' container reference.
	 * @see #getOperationAction()
	 * @generated
	 */
	void setOperationAction(OperationAction value);

	/**
	 * Returns the value of the '<em><b>Pages</b></em>' containment reference list.
	 * The list contents are of type {@link org.opaeum.uim.action.OperationPopupPage}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Pages</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Pages</em>' containment reference list.
	 * @see org.opaeum.uim.action.ActionPackage#getOperationActionPopup_Pages()
	 * @model containment="true"
	 * @generated
	 */
	EList<OperationPopupPage> getPages();

} // OperationActionPopup
