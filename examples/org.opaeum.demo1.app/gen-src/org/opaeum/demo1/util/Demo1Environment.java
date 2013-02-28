package org.opaeum.demo1.util;

import javax.persistence.spi.PersistenceUnitInfo;

import org.opaeum.runtime.domain.IPersistentObject;
import org.opaeum.runtime.jpa.AbstractJpaEnvironment;

public class Demo1Environment extends AbstractJpaEnvironment {
	static public Demo1Environment INSTANCE = new Demo1Environment();
	private Demo1JavaMetaInfoMap metaInfoMap;


	public String getApplicationIdentifier() {
		String result = "demo1";
		
		return result;
	}
	
	public Demo1JavaMetaInfoMap getMetaInfoMap() {
		Demo1JavaMetaInfoMap result = metaInfoMap;
		if ( metaInfoMap==null ) {
			result=metaInfoMap=new Demo1JavaMetaInfoMap();
		}
		return result;
	}
	
	public PersistenceUnitInfo getPersistenceUnitInfo() {
		PersistenceUnitInfo result = new org.opaeum.demo1.util.Demo1PersistenceUnitInfo(this);
		
		return result;
	}
	
	public void register() {
		super.register();
		INSTANCE=new Demo1Environment();
	}
	
	public void unregister() {
		super.unregister();
		INSTANCE=null;
	}

}