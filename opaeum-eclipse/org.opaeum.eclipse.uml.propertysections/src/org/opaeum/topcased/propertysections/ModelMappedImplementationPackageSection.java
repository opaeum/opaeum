package org.opaeum.topcased.propertysections;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.uml2.uml.Element;
import org.opaeum.eclipse.context.OpaeumEclipseContext;
import org.opaeum.metamodel.core.internal.StereotypeNames;
import org.opaeum.topcased.propertysections.base.AbstractStringOnStereotypeSection;

public class ModelMappedImplementationPackageSection extends AbstractStringOnStereotypeSection{
	@Override
	protected Element getElement(EObject e){
		return (Element) e;
	}
	@Override
	protected String getAttributeName(){
		return "mappedImplementationPackage";
	}
	@Override
	protected String getProfileName(){
		if(OpaeumEclipseContext.shouldBeCm1Compatible()){
			return StereotypeNames.OPAEUM_STANDARD_PROFILE_TOPCASED;
		}else{
			return StereotypeNames.OPAEUM_STANDARD_PROFILE_PAPYRUS;
		}
	}
	@Override
	protected String getStereotypeName(){
		return StereotypeNames.MODEL;
	}
	@Override
	protected String getLabelText(){
		return "Implement as:";
	}
}
