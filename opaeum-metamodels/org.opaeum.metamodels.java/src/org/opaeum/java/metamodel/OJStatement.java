package org.opaeum.java.metamodel;

import java.util.Map;

import org.opaeum.java.metamodel.generated.OJStatementGEN;


public class OJStatement extends OJStatementGEN {

	public OJStatement() {
		super();
	}
	
	public OJStatement getDeepCopy() {
		OJStatement copy = new OJStatement();
		copy.copyDeepInfoInto(copy);
		return copy;
	}
	
	public void copyDeepInfoInto(OJStatement copy) {
	}
	
	public void renameAll(Map<String, OJPathName> renamePathNames, String newName) {
	}

}