package org.opaeum.eclipse.uml.propertysections.bpmprofile;

import org.eclipse.uml2.uml.Element;
import org.opaeum.eclipse.uml.propertysections.base.AbstractEnumerationOnStereotypeSection;
import org.opaeum.metamodel.core.internal.StereotypeNames;

public class TimeEventTimeUnitSection extends AbstractEnumerationOnStereotypeSection{
	@Override
	protected String getAttributeName(){
		return "timeUnit";
	}
	@Override
	protected String getProfileName(){
		return StereotypeNames.OPAEUM_BPM_PROFILE;
	}
	@Override
	protected String getStereotypeName(Element e){
		return StereotypeNames.CONTEXTUAL_BUSINESS_TIME_EVENT;
	}
	@Override
	public String getLabelText(){
		return "Time Unit";
	}
}
