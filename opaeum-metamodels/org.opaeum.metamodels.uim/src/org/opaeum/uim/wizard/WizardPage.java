/**
 */
package org.opaeum.uim.wizard;

import org.opaeum.uim.Page;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Page</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.opaeum.uim.wizard.WizardPage#getWizard <em>Wizard</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.opaeum.uim.wizard.WizardPackage#getWizardPage()
 * @model
 * @generated
 */
public interface WizardPage extends Page {
	/**
	 * Returns the value of the '<em><b>Wizard</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.opaeum.uim.wizard.AbstractWizard#getPages <em>Pages</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Wizard</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Wizard</em>' container reference.
	 * @see #setWizard(AbstractWizard)
	 * @see org.opaeum.uim.wizard.WizardPackage#getWizardPage_Wizard()
	 * @see org.opaeum.uim.wizard.AbstractWizard#getPages
	 * @model opposite="pages" transient="false"
	 * @generated
	 */
	AbstractWizard getWizard();

	/**
	 * Sets the value of the '{@link org.opaeum.uim.wizard.WizardPage#getWizard <em>Wizard</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Wizard</em>' container reference.
	 * @see #getWizard()
	 * @generated
	 */
	void setWizard(AbstractWizard value);

} // WizardPage
