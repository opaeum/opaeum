package org.opaeumbpm.util;

import org.opaeum.runtime.domain.IPersistentObject;
import org.opaeum.runtime.environment.JavaMetaInfoMap;

public class OpaeumBPMJavaMetaInfoMap extends JavaMetaInfoMap {
	static final public OpaeumBPMJavaMetaInfoMap INSTANCE = new OpaeumBPMJavaMetaInfoMap();

	/** Constructor for OpaeumBPMJavaMetaInfoMap
	 */
	public OpaeumBPMJavaMetaInfoMap() {
		this.importMetaInfo(org.opaeum.organization.util.OpaeumLibraryForBPMJavaMetaInfoMap.INSTANCE);
	}


}