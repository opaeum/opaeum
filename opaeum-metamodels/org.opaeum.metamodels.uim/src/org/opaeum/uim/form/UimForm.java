/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.opeum.uim.form;

import org.opeum.uim.UserInteractionElement;
import org.opeum.uim.folder.AbstractFormFolder;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Uim Form</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.opeum.uim.form.UimForm#getPanel <em>Panel</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.opeum.uim.form.FormPackage#getUimForm()
 * @model
 * @generated
 */
public interface UimForm extends UserInteractionElement {
	/**
	 * Returns the value of the '<em><b>Panel</b></em>' containment reference.
	 * It is bidirectional and its opposite is '{@link org.opeum.uim.form.FormPanel#getForm <em>Form</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Panel</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Panel</em>' containment reference.
	 * @see #setPanel(FormPanel)
	 * @see org.opeum.uim.form.FormPackage#getUimForm_Panel()
	 * @see org.opeum.uim.form.FormPanel#getForm
	 * @model opposite="form" containment="true"
	 * @generated
	 */
	FormPanel getPanel();

	/**
	 * Sets the value of the '{@link org.opeum.uim.form.UimForm#getPanel <em>Panel</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Panel</em>' containment reference.
	 * @see #getPanel()
	 * @generated
	 */
	void setPanel(FormPanel value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation" required="true"
	 * @generated
	 */
	AbstractFormFolder getFolder();

} // UimForm
