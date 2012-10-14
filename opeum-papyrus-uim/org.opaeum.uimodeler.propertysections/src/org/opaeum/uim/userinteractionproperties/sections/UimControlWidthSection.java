package org.opaeum.uim.userinteractionproperties.sections;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.opaeum.eclipse.uml.propertysections.base.AbstractStringPropertySection;
import org.opaeum.uim.control.ControlPackage;

/**
 * A section display a text field to edit/see String features
 *
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 *
 * @generated
 */
public class UimControlWidthSection extends AbstractStringPropertySection{
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @see org.topcased.tabbedproperties.sections.AbstractOpaeumPropertySection#getLabelText()
	 * @generated
	 */
	public String getLabelText(){
		return "Width:";
	}
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @see org.topcased.tabbedproperties.sections.AbstractOpaeumPropertySection#getFeature()
	 * @generated
	 */
	protected EStructuralFeature getFeature(){
		return ControlPackage.eINSTANCE.getUimControl_MimumWidth();
	}
}