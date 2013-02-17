package org.opeum.demo1.util;

import javax.persistence.spi.PersistenceUnitInfo;

import org.opaeum.runtime.domain.IPersistentObject;
import org.opaeum.runtime.jpa.AbstractJpaEnvironment;

public class Demo1Environment extends AbstractJpaEnvironment {
	static final public Demo1Environment INSTANCE = new Demo1Environment();

	/** Default constructor for Demo1Environment
	 */
	private Demo1Environment() {
	}

	public String getApplicationIdentifier() {
		String result = "demo1";
		
		return result;
	}
	
	public Demo1JavaMetaInfoMap getMetaInfoMap() {
		Demo1JavaMetaInfoMap result = Demo1JavaMetaInfoMap.INSTANCE;
		
		return result;
	}
	
	public PersistenceUnitInfo getPersistenceUnitInfo() {
		PersistenceUnitInfo result = new org.opeum.demo1.util.Demo1PersistenceUnitInfo(this);
		
		return result;
	}

}