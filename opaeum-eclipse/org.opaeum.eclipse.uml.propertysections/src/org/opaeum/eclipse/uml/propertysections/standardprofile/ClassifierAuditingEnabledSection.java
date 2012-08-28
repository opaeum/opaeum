package org.opaeum.eclipse.uml.propertysections.standardprofile;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.uml2.uml.Element;
import org.opaeum.eclipse.uml.propertysections.base.AbstractBooleanOnStereotypeSection;
import org.opaeum.metamodel.core.internal.StereotypeNames;

public abstract class ClassifierAuditingEnabledSection extends AbstractBooleanOnStereotypeSection{
	abstract protected String getStereotypeName();
	@Override
	protected Element getElement(EObject e){
		return (Element) e;
	}
	@Override
	protected String getAttributeName(){
		return "auditingEnabled";
	}
	@Override
	protected String getProfileName(){
		return StereotypeNames.OPAEUM_STANDARD_PROFILE_TOPCASED;
	}
	@Override
	protected String getLabelText(){
		return "Auditing enabled";
	}
}
