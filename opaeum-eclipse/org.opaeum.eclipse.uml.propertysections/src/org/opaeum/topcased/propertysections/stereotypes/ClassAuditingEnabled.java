package org.opaeum.topcased.propertysections.stereotypes;

import org.opaeum.metamodel.core.internal.StereotypeNames;

public class ClassAuditingEnabled extends ClassifierAuditingEnabledSection{
	@Override
	protected String getStereotypeName(){
		return StereotypeNames.ENTITY;
	}
	@Override
	protected Boolean getDefaultValue(){
		return Boolean.TRUE;
	}
}
