package org.nakeduml.uim.userinteractionproperties.sections;


import org.eclipse.emf.ecore.EStructuralFeature;
import org.nakeduml.uim.UIMPackage;
import org.topcased.tabbedproperties.sections.AbstractIntegerPropertySection;

/**
 * A section display a text field to edit/see Integer Features
 *
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 *
 * @generated
 */
public class UIMTextAreaRowsSection extends AbstractIntegerPropertySection {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @see org.topcased.tabbedproperties.sections.AbstractTabbedPropertySection#getLabelText()
	 * @generated
	 */
	protected String getLabelText() {
		return "Rows:";
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @see org.topcased.tabbedproperties.sections.AbstractTabbedPropertySection#getFeature()
	 * @generated
	 */
	protected EStructuralFeature getFeature() {
		return UIMPackage.eINSTANCE.getUIMTextArea_Rows();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @see org.topcased.tabbedproperties.sections.AbstractIntegerPropertySection#getFeatureInteger()
	 * @generated
	 */
	protected Integer getFeatureInteger() {
		Object Int = getEObject().eGet(getFeature());
		if (Int == null) {
			return new Integer(0);
		}

		return (Integer) Int;
	}
}