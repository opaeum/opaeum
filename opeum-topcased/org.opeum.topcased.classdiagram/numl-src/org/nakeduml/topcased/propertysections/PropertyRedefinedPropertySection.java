package org.opeum.topcased.propertysections;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.UMLPackage;
import org.opeum.eclipse.EmfPropertyUtil;

public class PropertyRedefinedPropertySection extends AbstractPropertyLookupSection{
	@Override
	protected Object getListValues(){
		return getProperty().getRedefinedProperties();
	}
	protected boolean isMultiplicityCompatible(Property thisProperty,Property potentialProperty){
		return EmfPropertyUtil.isMany(potentialProperty) == EmfPropertyUtil.isMany(thisProperty);
	}
	@Override
	protected EStructuralFeature getFeature(){
		return UMLPackage.eINSTANCE.getProperty_RedefinedProperty();
	}
	@Override
	protected String getLabelText(){
		return "Redefined properties";
	}
}
