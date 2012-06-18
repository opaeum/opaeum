package model2.util;

import org.opaeum.runtime.domain.IPersistentObject;
import org.opaeum.runtime.environment.JavaMetaInfoMap;

public class Model2JavaMetaInfoMap extends JavaMetaInfoMap {
	static final public Model2JavaMetaInfoMap INSTANCE = new Model2JavaMetaInfoMap();

	/** Constructor for Model2JavaMetaInfoMap
	 */
	public Model2JavaMetaInfoMap() {
		putClass(model2.MyEntity.class,"model2.uml@_sLhTkLbIEeG62dzgiRb2WA");
		putClass(model2.MyOtherEntity.class,"model2.uml@_zveNMLbIEeG62dzgiRb2WA");
		putMethod(model2.MyOtherEntity.class,"model2.uml@_DcYK8LbJEeG62dzgiRb2WA",6369895055391538149l);
	}


}