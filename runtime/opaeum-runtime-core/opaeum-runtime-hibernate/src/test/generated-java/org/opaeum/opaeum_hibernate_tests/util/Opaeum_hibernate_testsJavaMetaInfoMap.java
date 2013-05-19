package org.opaeum.opaeum_hibernate_tests.util;

import org.opaeum.runtime.environment.JavaMetaInfoMap;

public class Opaeum_hibernate_testsJavaMetaInfoMap extends JavaMetaInfoMap {
	static final public Opaeum_hibernate_testsJavaMetaInfoMap INSTANCE = new Opaeum_hibernate_testsJavaMetaInfoMap();

	/** Constructor for Opaeum_hibernate_testsJavaMetaInfoMap
	 */
	public Opaeum_hibernate_testsJavaMetaInfoMap() {
		this.importMetaInfo(bpmmodel.util.BpmmodelJavaMetaInfoMap.INSTANCE);
		this.importMetaInfo(org.opaeum.test.hibernate.util.ModelJavaMetaInfoMap.INSTANCE);
	}


}