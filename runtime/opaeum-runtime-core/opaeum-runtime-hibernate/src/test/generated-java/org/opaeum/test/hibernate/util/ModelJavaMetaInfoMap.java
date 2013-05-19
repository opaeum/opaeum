package org.opaeum.test.hibernate.util;

import org.opaeum.runtime.environment.JavaMetaInfoMap;

public class ModelJavaMetaInfoMap extends JavaMetaInfoMap {
	static final public ModelJavaMetaInfoMap INSTANCE = new ModelJavaMetaInfoMap();

	/** Constructor for ModelJavaMetaInfoMap
	 */
	public ModelJavaMetaInfoMap() {
		this.importMetaInfo(org.opaeum.test.util.ModelJavaMetaInfoMap.INSTANCE);
		putClass(bpmmodel.mybusiness.PrepareQuote.class,"bpm.uml@_D4xCQI_hEeK855GX2Z3x4Q@MT");
		putClass(org.opaeum.test.hibernate.Finger.class,"model.uml@_5bDEAHdnEeK1avyEuWoMVw");
		putClass(org.opaeum.test.hibernate.Hand.class,"model.uml@_2V87UHdnEeK1avyEuWoMVw");
	}


}