package org.opaeum.opaeum_hibernate_tests.util;

import java.util.ArrayList;
import java.util.List;

import org.opaeum.runtime.jpa.AbstractPersistenceUnitInfo;

public class Opaeum_hibernate_testsPersistenceUnitInfo extends AbstractPersistenceUnitInfo {


	/** Constructor for Opaeum_hibernate_testsPersistenceUnitInfo
	 * 
	 * @param env 
	 */
	public Opaeum_hibernate_testsPersistenceUnitInfo(Opaeum_hibernate_testsEnvironment env) {
	super(env);
	}

	public List<String> getManagedClassNames() {
		List<String> result = new ArrayList<String>();
		result.add("org.opaeum.hibernate.domain.EventOccurrence");
		result.add("org.opaeum.hibernate.domain.AbstractPersistentEnum");
		result.add("org.opaeum.hibernate.domain.AbstractPersistentOpaeumIdEnum");
		result.add("org.opaeum.opaeum_hibernate_tests.util");
		result.add("model.Hand");
		result.add("model.Finger");
		return result;
	}
	
	public String getPersistenceUnitName() {
		String result = "opaeum_hibernate_tests";
		
		return result;
	}

}