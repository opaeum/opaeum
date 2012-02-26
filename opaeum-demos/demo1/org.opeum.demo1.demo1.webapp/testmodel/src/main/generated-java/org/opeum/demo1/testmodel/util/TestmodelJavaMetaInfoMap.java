package org.opeum.demo1.testmodel.util;

import org.opaeum.runtime.domain.IPersistentObject;
import org.opaeum.runtime.environment.JavaMetaInfoMap;

public class TestmodelJavaMetaInfoMap extends JavaMetaInfoMap {
	static final public TestmodelJavaMetaInfoMap INSTANCE = new TestmodelJavaMetaInfoMap();

	/** Constructor for TestmodelJavaMetaInfoMap
	 */
	public TestmodelJavaMetaInfoMap() {
		this.importMetaInfo(org.opaeum.runtime.bpm.util.OpaeumLibraryForBPMJavaMetaInfoMap.INSTANCE);
		putClass(org.opeum.demo1.testmodel.Component1.class,"988915@_HFuSQFuaEeG1eMG0Z5qnHg");
	}


}