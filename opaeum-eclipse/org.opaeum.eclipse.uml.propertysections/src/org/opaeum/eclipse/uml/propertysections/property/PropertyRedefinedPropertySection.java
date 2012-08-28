package org.opaeum.eclipse.uml.propertysections.property;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.UMLPackage;
import org.opaeum.eclipse.EmfPropertyUtil;
import org.opaeum.eclipse.uml.propertysections.base.AbstractPropertyLookupSection;

public class PropertyRedefinedPropertySection extends AbstractPropertyLookupSection{
	protected boolean isMultiplicityCompatible(Property thisProperty,Property potentialProperty){
		return EmfPropertyUtil.isMany(potentialProperty) == EmfPropertyUtil.isMany(thisProperty);
	}
	@Override
	protected EStructuralFeature getFeature(){
		return UMLPackage.eINSTANCE.getProperty_RedefinedProperty();
	}
	@Override
	protected String getLabelText(){
		return "Redefined types";
	}
	@Override
	protected EObject getFeatureOwner(EObject e){
		return e;
	}
}
