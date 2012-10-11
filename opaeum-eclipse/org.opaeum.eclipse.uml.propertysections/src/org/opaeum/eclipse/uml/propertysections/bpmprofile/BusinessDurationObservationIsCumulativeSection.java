package org.opaeum.eclipse.uml.propertysections.bpmprofile;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.uml2.uml.Element;
import org.opaeum.eclipse.uml.propertysections.base.AbstractBooleanOnStereotypeSection;
import org.opaeum.metamodel.core.internal.StereotypeNames;
import org.opaeum.metamodel.core.internal.TagNames;

public class BusinessDurationObservationIsCumulativeSection extends AbstractBooleanOnStereotypeSection{
	@Override
	protected Boolean getDefaultValue(){
		return Boolean.FALSE;
	}
	@Override
	protected Element getElement(EObject eObject){
		return (Element) eObject;
	}
	@Override
	protected String getAttributeName(){
		return TagNames.IS_CUMULATIVE;
	}
	@Override
	protected String getProfileName(){
		return StereotypeNames.OPAEUM_BPM_PROFILE;
	}
	@Override
	protected String getStereotypeName(Element e){
		return StereotypeNames.BUSINESS_DURATION_OBSERVATION;
	}
	@Override
	public String getLabelText(){
		return "Is Cumulative:";
	}
}
