/**
 */
package org.opaeum.uim.wizard;

import org.eclipse.emf.common.util.EList;
import org.opaeum.uim.UmlReference;
import org.opaeum.uim.UserInterfaceEntryPoint;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Abstract Wizard</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.opaeum.uim.wizard.AbstractWizard#getPages <em>Pages</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.opaeum.uim.wizard.WizardPackage#getAbstractWizard()
 * @model abstract="true"
 * @generated
 */
public interface AbstractWizard extends UserInterfaceEntryPoint, UmlReference {
	/**
	 * Returns the value of the '<em><b>Pages</b></em>' containment reference list.
	 * The list contents are of type {@link org.opaeum.uim.wizard.WizardPage}.
	 * It is bidirectional and its opposite is '{@link org.opaeum.uim.wizard.WizardPage#getWizard <em>Wizard</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Pages</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Pages</em>' containment reference list.
	 * @see org.opaeum.uim.wizard.WizardPackage#getAbstractWizard_Pages()
	 * @see org.opaeum.uim.wizard.WizardPage#getWizard
	 * @model opposite="wizard" containment="true"
	 * @generated
	 */
	EList<WizardPage> getPages();

} // AbstractWizard
