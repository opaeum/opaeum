/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.opaeum.uim.form;

import org.opaeum.uim.UmlReference;
import org.opaeum.uim.layout.LayoutContainer;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Panel</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.opaeum.uim.form.FormPanel#getForm <em>Form</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.opaeum.uim.form.FormPackage#getFormPanel()
 * @model
 * @generated
 */
public interface FormPanel extends UmlReference, LayoutContainer {
	/**
	 * Returns the value of the '<em><b>Form</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.opaeum.uim.form.UimForm#getPanel <em>Panel</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Form</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Form</em>' container reference.
	 * @see #setForm(UimForm)
	 * @see org.opaeum.uim.form.FormPackage#getFormPanel_Form()
	 * @see org.opaeum.uim.form.UimForm#getPanel
	 * @model opposite="panel" transient="false"
	 * @generated
	 */
	UimForm getForm();

	/**
	 * Sets the value of the '{@link org.opaeum.uim.form.FormPanel#getForm <em>Form</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Form</em>' container reference.
	 * @see #getForm()
	 * @generated
	 */
	void setForm(UimForm value);

} // FormPanel
