/**
 */
package org.opaeum.uim.action;

import org.opaeum.uim.UmlReference;
import org.opaeum.uim.editor.QueryInvocationEditor;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Link To Operation</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * An operationNavigation is typically rendered as a menu item of some sort, and takes the user to an operationUserInteraction
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.opaeum.uim.action.LinkToOperation#getToForm <em>To Form</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.opaeum.uim.action.ActionPackage#getLinkToOperation()
 * @model
 * @generated
 */
public interface LinkToOperation extends UimLink, UmlReference {
	/**
	 * Returns the value of the '<em><b>To Form</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>To Form</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>To Form</em>' reference.
	 * @see #setToForm(QueryInvocationEditor)
	 * @see org.opaeum.uim.action.ActionPackage#getLinkToOperation_ToForm()
	 * @model
	 * @generated
	 */
	QueryInvocationEditor getToForm();

	/**
	 * Sets the value of the '{@link org.opaeum.uim.action.LinkToOperation#getToForm <em>To Form</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>To Form</em>' reference.
	 * @see #getToForm()
	 * @generated
	 */
	void setToForm(QueryInvocationEditor value);

} // LinkToOperation
