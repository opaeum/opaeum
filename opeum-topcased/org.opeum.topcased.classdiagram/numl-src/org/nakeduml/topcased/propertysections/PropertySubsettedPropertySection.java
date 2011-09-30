package org.opeum.topcased.propertysections;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.UMLPackage;
import org.opeum.eclipse.EmfPropertyUtil;

public class PropertySubsettedPropertySection extends AbstractPropertyLookupSection{

	@Override
	protected Object getListValues(){
		return getProperty().getSubsettedProperties();
	}

	@Override
	protected EStructuralFeature getFeature(){
		return UMLPackage.eINSTANCE.getProperty_SubsettedProperty();
	}
	protected boolean isMultiplicityCompatible(Property thisProperty, Property potentialProperty){
		boolean isMany = EmfPropertyUtil.isMany(potentialProperty);
		return isMany?true:!EmfPropertyUtil.isMany(thisProperty);
	}


	@Override
	protected String getLabelText(){
		return "Subsetted properties";
	}
}
