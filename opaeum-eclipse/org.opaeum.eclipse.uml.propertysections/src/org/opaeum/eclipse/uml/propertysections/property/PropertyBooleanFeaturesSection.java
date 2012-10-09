package org.opaeum.eclipse.uml.propertysections.property;

import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Profile;
import org.eclipse.uml2.uml.UMLPackage;
import org.opaeum.eclipse.EmfElementFinder;
import org.opaeum.eclipse.uml.propertysections.base.AbstractMultiFeaturePropertySection;
import org.opaeum.eclipse.uml.propertysections.base.BooleanSubSection;

public class PropertyBooleanFeaturesSection extends AbstractMultiFeaturePropertySection{
	private BooleanSubSection isReadOnly;
	private BooleanSubSection isStatic;
	private BooleanSubSection isDerivedUnion;
	private BooleanSubSection isDerived;
	private BooleanSubSection isComposition;
	public PropertyBooleanFeaturesSection(){
		isReadOnly = createBoolean(UMLPackage.eINSTANCE.getStructuralFeature_IsReadOnly(), "Is Read Only", 110);
		isStatic = createBoolean(UMLPackage.eINSTANCE.getFeature_IsStatic(), "Is Static", 110);
		isDerivedUnion = createBoolean(UMLPackage.eINSTANCE.getProperty_IsDerivedUnion(), "Is Derived Union", 110);
		isDerived = createBoolean(UMLPackage.eINSTANCE.getProperty_IsDerived(), "Is Derived", 110);
		isComposition = createBoolean(UMLPackage.eINSTANCE.getProperty_IsComposite(), "Is Composite", 110);
	}

	@Override
	public void refresh(){
		super.refresh();
		if(isInProfile()){
			isComposition.setVisible(false);
		}
	}
	private boolean isInProfile(){
		if(getEObject()!=null &&  getFeatureOwner(getEObject())!=null){
			return EmfElementFinder.getRootObject((Element) getFeatureOwner(getEObject())) instanceof Profile;
		}else{
			return false;
		}
	}
}