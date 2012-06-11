package org.opaeum.topcased.propertysections.stereotypes;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.uml2.uml.Element;
import org.opaeum.eclipse.context.OpaeumEclipseContext;
import org.opaeum.metamodel.core.internal.StereotypeNames;
import org.opaeum.topcased.propertysections.base.AbstractBooleanOnStereotypeSection;

public abstract class ClassifierAuditingEnabledSection extends AbstractBooleanOnStereotypeSection{
	abstract protected String getStereotypeName();
	@Override
	protected Element getElement(EObject e ){
		return (Element) e;
	}
	@Override
	protected String getAttributeName(){
		return "auditingEnabled";
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
	protected String getLabelText(){
		return "Auditing enabled";
	}
}
