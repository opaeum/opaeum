package org.opaeum.uim.userinteractionproperties.sections;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.TypedElement;
import org.opaeum.eclipse.uml.propertysections.base.AbstractChooserPropertySection;
import org.opaeum.uim.UimPackage;
import org.opaeum.uim.UserInteractionElement;
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
public class UmlReferenceUmlElementUidSection extends AbstractChooserPropertySection{
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @see org.topcased.tabbedproperties.sections.AbstractOpaeumPropertySection#getLabelText()
	 * @generated
	 */
	public String getLabelText(){
		return "UmlElementUid:";
	}
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @see org.topcased.tabbedproperties.sections.AbstractOpaeumPropertySection#getFeature()
	 * @generated
	 */
	protected EStructuralFeature getFeature(){
		return UimPackage.eINSTANCE.getUmlReference_UmlElementUid();
	}
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.topcased.AbstractChooserPropertySection.sections.OpaeumChooserPropertySection#getComboFeatureValues()
	 * @generated NOT
	 */
	protected Object[] getComboFeatureValues(){
		PropertyRef pr = (PropertyRef) getEObject();
		UserInteractionElement grandParent = getGrandParent(pr);
		if(pr.getBinding() != null && UmlUimLinks.getCurrentUmlLinks(grandParent).getTypedElement(pr.getBinding()) != null){
			TypedElement typedElement = UmlUimLinks.getCurrentUmlLinks(grandParent).getTypedElement(pr.getBinding());
			Classifier classifier = (Classifier) typedElement.getType();
			EList<Property> attrs = classifier.getAllAttributes();
			return (Property[]) attrs.toArray(new Property[attrs.size()]);
		}else if(pr.getPrevious() != null && UmlUimLinks.getCurrentUmlLinks(grandParent).getProperty(pr.getPrevious()) != null){
			TypedElement typedElement = UmlUimLinks.getCurrentUmlLinks(grandParent).getProperty(pr.getPrevious());
			Classifier classifier = (Classifier) typedElement.getType();
			EList<Property> attrs = classifier.getAllAttributes();
			return (Property[]) attrs.toArray(new Property[attrs.size()]);
		}
		return new Property[0];
	}
	private UserInteractionElement getGrandParent(EObject pr){
		if(pr instanceof UserInteractionElement){
			return (UserInteractionElement) pr;
		}else{
			return getGrandParent(pr.eContainer());
		}
	}
	@Override
	protected Object getFeatureValue(){
		// TODO Auto-generated method stub
		return null;
	}

}