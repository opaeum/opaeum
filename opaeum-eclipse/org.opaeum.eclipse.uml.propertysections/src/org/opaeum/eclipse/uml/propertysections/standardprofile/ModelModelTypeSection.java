package org.opaeum.eclipse.uml.propertysections.standardprofile;

import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Model;
import org.opaeum.eclipse.context.OpaeumEclipseContext;
import org.opaeum.eclipse.uml.propertysections.base.AbstractEnumerationOnStereotypeSection;
import org.opaeum.metamodel.core.internal.StereotypeNames;

public class ModelModelTypeSection extends AbstractEnumerationOnStereotypeSection{
	@Override
	protected String getLabelText(){
		return "Model Type";
	}
	@Override
	protected Element getElement(){
		return getModel();
	}
	private Model getModel(){
		return (Model) getEObject();
	}
	@Override
	protected String getAttributeName(){
		return "modelType";
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
}
