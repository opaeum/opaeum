package model.util;

import org.opaeum.runtime.domain.IPersistentObject;
import org.opaeum.runtime.environment.JavaMetaInfoMap;

public class ModelJavaMetaInfoMap extends JavaMetaInfoMap {
	static final public ModelJavaMetaInfoMap INSTANCE = new ModelJavaMetaInfoMap();

	/** Constructor for ModelJavaMetaInfoMap
	 */
	public ModelJavaMetaInfoMap() {
		putClass(model.Finger.class,"model.uml@_5bDEAHdnEeK1avyEuWoMVw");
		putClass(model.Hand.class,"model.uml@_2V87UHdnEeK1avyEuWoMVw");
	}


}