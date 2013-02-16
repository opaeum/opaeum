package org.opaeum.opaeum_hibernate_tests.util;

import javax.persistence.spi.PersistenceUnitInfo;

import org.opaeum.runtime.domain.IPersistentObject;
import org.opaeum.runtime.jpa.AbstractJpaEnvironment;

public class Opaeum_hibernate_testsEnvironment extends AbstractJpaEnvironment {
	static final public Opaeum_hibernate_testsEnvironment INSTANCE = new Opaeum_hibernate_testsEnvironment();

	/** Default constructor for Opaeum_hibernate_testsEnvironment
	 */
	private Opaeum_hibernate_testsEnvironment() {
	}

	public String getApplicationIdentifier() {
		String result = "opaeum_hibernate_tests";
		
		return result;
	}
	
	public Opaeum_hibernate_testsJavaMetaInfoMap getMetaInfoMap() {
		Opaeum_hibernate_testsJavaMetaInfoMap result = Opaeum_hibernate_testsJavaMetaInfoMap.INSTANCE;
		
		return result;
	}
	
	public PersistenceUnitInfo getPersistenceUnitInfo() {
		PersistenceUnitInfo result = new org.opaeum.opaeum_hibernate_tests.util.Opaeum_hibernate_testsPersistenceUnitInfo(this);
		
		return result;
	}

}