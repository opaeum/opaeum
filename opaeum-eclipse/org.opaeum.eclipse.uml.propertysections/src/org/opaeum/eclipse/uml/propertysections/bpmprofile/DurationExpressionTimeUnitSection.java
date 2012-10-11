package org.opaeum.eclipse.uml.propertysections.bpmprofile;

import org.eclipse.uml2.uml.Element;
import org.opaeum.eclipse.uml.propertysections.base.AbstractEnumerationOnStereotypeSection;
import org.opaeum.metamodel.core.internal.StereotypeNames;
import org.opaeum.metamodel.core.internal.TagNames;

public class DurationExpressionTimeUnitSection extends AbstractEnumerationOnStereotypeSection{

	@Override
	protected String getAttributeName(){
		return TagNames.TIME_UNIT;
	}

	@Override
	protected String getProfileName(){
		return StereotypeNames.OPAEUM_BPM_PROFILE;
	}

	@Override
	protected String getStereotypeName(Element e){
		return StereotypeNames.DURATION_EXPRESSION;
	}

	@Override
	public String getLabelText(){
		return "Time unit:";
	}
}
