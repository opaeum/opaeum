package org.opaeum.structuretests.util;

import org.opaeum.runtime.domain.IPersistentObject;
import org.opaeum.runtime.environment.JavaMetaInfoMap;

public class StructuretestsJavaMetaInfoMap extends JavaMetaInfoMap {
	static final public StructuretestsJavaMetaInfoMap INSTANCE = new StructuretestsJavaMetaInfoMap();

	/** Constructor for StructuretestsJavaMetaInfoMap
	 */
	public StructuretestsJavaMetaInfoMap() {
		this.importMetaInfo(org.opaeum.test.util.ModelJavaMetaInfoMap.INSTANCE);
	}


}