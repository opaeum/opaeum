package opaeumbpmcommon.util;

import org.opaeum.runtime.domain.IPersistentObject;
import org.opaeum.runtime.environment.JavaMetaInfoMap;

public class OpaeumBPMCommonJavaMetaInfoMap extends JavaMetaInfoMap {
	static final public OpaeumBPMCommonJavaMetaInfoMap INSTANCE = new OpaeumBPMCommonJavaMetaInfoMap();

	/** Constructor for OpaeumBPMCommonJavaMetaInfoMap
	 */
	public OpaeumBPMCommonJavaMetaInfoMap() {
		putClass(org.opaeum.runtime.domain.BusinessTimeUnit.class,"OpaeumBPMCommon.library.uml@_7zLcgNb-EeCJ0dmaHEVVnw");
		putClass(org.opaeum.runtime.domain.DocumentType.class,"OpaeumBPMCommon.library.uml@_Ckeg4F9ZEeG3X_yvufTVmw");
		putClass(org.opaeum.runtime.domain.TaskDelegation.class,"OpaeumBPMCommon.library.uml@_5lxp4LRZEeCilvbXE8KmHA");
	}


}