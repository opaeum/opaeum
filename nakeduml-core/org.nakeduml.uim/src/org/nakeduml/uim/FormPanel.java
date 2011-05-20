/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.nakeduml.uim;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Form Panel</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.nakeduml.uim.FormPanel#getForm <em>Form</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.nakeduml.uim.UimPackage#getFormPanel()
 * @model
 * @generated
 */
public interface FormPanel extends UmlReference, LayoutContainer {
	/**
	 * Returns the value of the '<em><b>Form</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.nakeduml.uim.UimForm#getPanel <em>Panel</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Form</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Form</em>' container reference.
	 * @see #setForm(UimForm)
	 * @see org.nakeduml.uim.UimPackage#getFormPanel_Form()
	 * @see org.nakeduml.uim.UimForm#getPanel
	 * @model opposite="panel" transient="false"
	 * @generated
	 */
	UimForm getForm();

	/**
	 * Sets the value of the '{@link org.nakeduml.uim.FormPanel#getForm <em>Form</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Form</em>' container reference.
	 * @see #getForm()
	 * @generated
	 */
	void setForm(UimForm value);

} // FormPanel
