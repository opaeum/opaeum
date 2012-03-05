package org.opaeum.uim.userinteractionproperties.sections;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.TypedElement;
import org.opaeum.uim.UimPackage;
import org.opaeum.uim.binding.PropertyRef;
import org.opaeum.uim.util.UmlUimLinks;

/**
 * A section display a text field to edit/see String features
 *
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 *
 * @generated NOT
 */
public class UmlReferenceUmlElementUidSection extends OpaeumChooserPropertySection{
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @see org.topcased.tabbedproperties.sections.AbstractTabbedPropertySection#getLabelText()
	 * @generated
	 */
	protected String getLabelText(){
		return "UmlElementUid:";
	}
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @see org.topcased.tabbedproperties.sections.AbstractTabbedPropertySection#getFeature()
	 * @generated
	 */
	protected EStructuralFeature getFeature(){
		return UimPackage.eINSTANCE.getUmlReference_UmlElementUid();
	}
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.topcased.tabbedproperties.sections.OpaeumChooserPropertySection#getComboFeatureValues()
	 * @generated NOT
	 */
	protected Object[] getComboFeatureValues(){
		PropertyRef pr = (PropertyRef) getEObject();
		if(pr.getBinding() != null && UmlUimLinks.getCurrentUmlLinks().getTypedElement(pr.getBinding()) != null){
			TypedElement typedElement = UmlUimLinks.getCurrentUmlLinks().getTypedElement(pr.getBinding());
			Classifier classifier = (Classifier) typedElement.getType();
			EList<Property> attrs = classifier.getAllAttributes();
			return (Property[]) attrs.toArray(new Property[attrs.size()]);
		}else if(pr.getPrevious() != null && UmlUimLinks.getCurrentUmlLinks().getProperty(pr.getPrevious()) != null){
			TypedElement typedElement = UmlUimLinks.getCurrentUmlLinks().getProperty(pr.getPrevious());
			Classifier classifier = (Classifier) typedElement.getType();
			EList<Property> attrs = classifier.getAllAttributes();
			return (Property[]) attrs.toArray(new Property[attrs.size()]);
		}
		return new Property[0];
	}
	@Override
	protected Object getFeatureValue(){
		// TODO Auto-generated method stub
		return null;
	}

}