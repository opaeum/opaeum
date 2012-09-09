package org.opaeum.eclipse.uml.propertysections.standardprofile;

import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Model;
import org.opaeum.eclipse.uml.propertysections.base.AbstractEnumerationOnStereotypeSection;
import org.opaeum.metamodel.core.internal.StereotypeNames;
import org.opaeum.metamodel.core.internal.TagNames;

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
		return TagNames.MODEL_TYPE;
	}
	@Override
	protected String getProfileName(){
		return StereotypeNames.OPAEUM_STANDARD_PROFILE;
	}
	@Override
	protected String getStereotypeName(){
		return StereotypeNames.MODEL;
	}
}
