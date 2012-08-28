package org.opaeum.eclipse.uml.propertysections.standardprofile;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.uml2.uml.Element;
import org.opaeum.eclipse.uml.propertysections.base.AbstractStringOnStereotypeSection;
import org.opaeum.metamodel.core.internal.StereotypeNames;

public class EntityMappedImplementationTypeSection extends AbstractStringOnStereotypeSection{
	@Override
	protected Element getElement(EObject e){
		return (Element) e;
	}
	@Override
	protected String getAttributeName(){
		return "mappedImplementationType";
	}
	@Override
	protected String getProfileName(){
		return StereotypeNames.OPAEUM_STANDARD_PROFILE_TOPCASED;
	}
	@Override
	protected String getStereotypeName(){
		return StereotypeNames.ENTITY;
	}
	@Override
	protected String getLabelText(){
		return "Existing Implementation:";
	}
}
