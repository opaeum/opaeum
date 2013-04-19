package org.opaeum.opaeum_hibernate_tests.util;

import javax.persistence.spi.PersistenceUnitInfo;

import org.opaeum.runtime.domain.IPersistentObject;
import org.opaeum.runtime.jpa.AbstractJpaEnvironment;

public class Opaeum_hibernate_testsEnvironment extends AbstractJpaEnvironment {
	static public Opaeum_hibernate_testsEnvironment INSTANCE = new Opaeum_hibernate_testsEnvironment();
	private Opaeum_hibernate_testsJavaMetaInfoMap metaInfoMap;


	public String getApplicationIdentifier() {
		String result = "opaeum_hibernate_tests";
		
		return result;
	}
	
	public Opaeum_hibernate_testsJavaMetaInfoMap getMetaInfoMap() {
		Opaeum_hibernate_testsJavaMetaInfoMap result = metaInfoMap;
		if ( metaInfoMap==null ) {
			result=metaInfoMap=new Opaeum_hibernate_testsJavaMetaInfoMap();
		}
		return result;
	}
	
	public PersistenceUnitInfo getPersistenceUnitInfo() {
		PersistenceUnitInfo result = new org.opaeum.opaeum_hibernate_tests.util.Opaeum_hibernate_testsPersistenceUnitInfo(this);
		
		return result;
	}
	
	public void register() {
		super.register();
		INSTANCE=new Opaeum_hibernate_testsEnvironment();
	}
	
	public void unregister() {
		super.unregister();
		INSTANCE=null;
	}

}