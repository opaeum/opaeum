package org.opaeum.uim.action;


public enum BuiltInLinkKind {
	AUDIT_TRAIL,
	BUSINESS_INTELLIGENCE,
	EDIT,
	VIEW;


	static public BuiltInLinkKind getByName(String name) {
		BuiltInLinkKind result = null;
		if ( "auditTrail".equals(name) ) {
			return AUDIT_TRAIL;
		}
		if ( "businessIntelligence".equals(name) ) {
			return BUSINESS_INTELLIGENCE;
		}
		if ( "edit".equals(name) ) {
			return EDIT;
		}
		if ( "view".equals(name) ) {
			return VIEW;
		}
		return result;
	}

}