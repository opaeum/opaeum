package org.opaeum.eclipse.uml.propertysections.standardprofile;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.uml2.uml.Element;
import org.opaeum.eclipse.uml.propertysections.base.AbstractBooleanOnStereotypeSection;
import org.opaeum.metamodel.core.internal.StereotypeNames;
import org.opaeum.metamodel.core.internal.TagNames;

public class AuditingEnabledSection extends AbstractBooleanOnStereotypeSection{
	@Override
	protected String getStereotypeName(Element e){
		if(e instanceof org.eclipse.uml2.uml.Class){
			return StereotypeNames.ENTITY;
		}else{
			return e.eClass().getName();
		}
	}
	@Override
	protected Element getElement(EObject e){
		return (Element) e;
	}
	@Override
	protected String getAttributeName(){
		return TagNames.AUDITING_ENABLED;
	}
	@Override
	protected String getProfileName(){
		return StereotypeNames.OPAEUM_STANDARD_PROFILE;
	}
	@Override
	protected String getLabelText(){
		return "Auditing enabled";
	}
	@Override
	protected Boolean getDefaultValue(){
		return Boolean.TRUE;
	}
}
