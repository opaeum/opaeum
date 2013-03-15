package org.opaeum.test.hibernate.util;

import org.opaeum.runtime.domain.IPersistentObject;
import org.opaeum.runtime.environment.JavaMetaInfoMap;

public class ModelJavaMetaInfoMap extends JavaMetaInfoMap {
	static final public ModelJavaMetaInfoMap INSTANCE = new ModelJavaMetaInfoMap();

	/** Constructor for ModelJavaMetaInfoMap
	 */
	public ModelJavaMetaInfoMap() {
		this.importMetaInfo(org.opaeum.test.util.ModelJavaMetaInfoMap.INSTANCE);
		putClass(org.opaeum.test.hibernate.Finger.class,"model.uml@_5bDEAHdnEeK1avyEuWoMVw");
		putClass(org.opaeum.test.hibernate.Hand.class,"model.uml@_2V87UHdnEeK1avyEuWoMVw");
	}


}