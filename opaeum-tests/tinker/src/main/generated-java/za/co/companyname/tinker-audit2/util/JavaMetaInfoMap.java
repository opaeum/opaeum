package za.co.companyname.tinker-audit2.util;

import org.opaeum.runtime.domain.IPersistentObject;
import org.opaeum.runtime.environment.JavaMetaInfoMap;

public class JavaMetaInfoMap extends JavaMetaInfoMap {
	static final public JavaMetaInfoMap INSTANCE = new JavaMetaInfoMap();

	/** Constructor for JavaMetaInfoMap
	 */
	public JavaMetaInfoMap() {
		this.importMetaInfo(org.util.OrgJavaMetaInfoMap.INSTANCE);
	}


}