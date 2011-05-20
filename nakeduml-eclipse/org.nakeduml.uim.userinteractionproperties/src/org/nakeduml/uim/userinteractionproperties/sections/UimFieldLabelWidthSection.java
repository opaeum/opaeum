package org.nakeduml.uim.userinteractionproperties.sections;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.nakeduml.uim.UimPackage;
import org.topcased.tabbedproperties.sections.AbstractIntegerPropertySection;

/**
 * A section display a text field to edit/see Integer Features
 *
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 *
 * @generated
 */
public class UimFieldLabelWidthSection extends AbstractIntegerPropertySection{
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @see org.topcased.tabbedproperties.sections.AbstractTabbedPropertySection#getLabelText()
	 * @generated
	 */
	protected String getLabelText(){
		return "LabelWidth:";
	}
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @see org.topcased.tabbedproperties.sections.AbstractTabbedPropertySection#getFeature()
	 * @generated
	 */
	protected EStructuralFeature getFeature(){
		return UimPackage.eINSTANCE.getUimField_LabelWidth();
	}
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @see org.topcased.tabbedproperties.sections.AbstractIntegerPropertySection#getFeatureInteger()
	 * @generated
	 */
	protected Integer getFeatureInteger(){
		Object Int = getEObject().eGet(getFeature());
		if(Int == null){
			return new Integer(0);
		}
		return (Integer) Int;
	}
}