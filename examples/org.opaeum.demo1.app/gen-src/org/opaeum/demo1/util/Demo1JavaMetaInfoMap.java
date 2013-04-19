package org.opaeum.demo1.util;

import org.opaeum.runtime.domain.IPersistentObject;
import org.opaeum.runtime.environment.JavaMetaInfoMap;

public class Demo1JavaMetaInfoMap extends JavaMetaInfoMap {
	static final public Demo1JavaMetaInfoMap INSTANCE = new Demo1JavaMetaInfoMap();

	/** Constructor for Demo1JavaMetaInfoMap
	 */
	public Demo1JavaMetaInfoMap() {
		this.importMetaInfo(org.opaeum.demo1.structuredbusiness.util.StructuredbusinessJavaMetaInfoMap.INSTANCE);
		this.importMetaInfo(ocltests.util.OcltestsJavaMetaInfoMap.INSTANCE);
	}


}