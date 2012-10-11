package org.opaeum.eclipse.uml.propertysections.standardprofile;

import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Model;
import org.opaeum.eclipse.uml.propertysections.base.AbstractEnumerationOnStereotypeSection;
import org.opaeum.metamodel.core.internal.StereotypeNames;
import org.opaeum.metamodel.core.internal.TagNames;

public class ModelModelTypeSection extends AbstractEnumerationOnStereotypeSection{
	@Override
	public String getLabelText(){
		return "Model Type";
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
	protected String getStereotypeName(Element e){
		return StereotypeNames.MODEL;
	}
}
