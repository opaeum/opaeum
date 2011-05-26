package org.nakeduml.topcased.propertysections;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.UMLPackage;

public class PropertyRedefinedPropertySection extends AbstractPropertyLookupSection{

	@Override
	protected Object getListValues(){
		return ((Property)getEObject()).getSubsettedProperties();
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
