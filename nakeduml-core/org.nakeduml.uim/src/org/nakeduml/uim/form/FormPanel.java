/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.nakeduml.uim.form;

import org.nakeduml.uim.UmlReference;

import org.nakeduml.uim.layout.LayoutContainer;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Panel</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.nakeduml.uim.form.FormPanel#getForm <em>Form</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.nakeduml.uim.form.FormPackage#getFormPanel()
 * @model
 * @generated
 */
public interface FormPanel extends UmlReference, LayoutContainer {
	/**
	 * Returns the value of the '<em><b>Form</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.nakeduml.uim.form.UimForm#getPanel <em>Panel</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Form</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Form</em>' container reference.
	 * @see #setForm(UimForm)
	 * @see org.nakeduml.uim.form.FormPackage#getFormPanel_Form()
	 * @see org.nakeduml.uim.form.UimForm#getPanel
	 * @model opposite="panel" transient="false"
	 * @generated
	 */
	UimForm getForm();

	/**
	 * Sets the value of the '{@link org.nakeduml.uim.form.FormPanel#getForm <em>Form</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Form</em>' container reference.
	 * @see #getForm()
	 * @generated
	 */
	void setForm(UimForm value);

} // FormPanel
