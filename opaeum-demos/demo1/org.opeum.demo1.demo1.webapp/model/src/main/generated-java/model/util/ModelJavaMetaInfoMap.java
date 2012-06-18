package model.util;

import org.opaeum.runtime.domain.IPersistentObject;
import org.opaeum.runtime.environment.JavaMetaInfoMap;

public class ModelJavaMetaInfoMap extends JavaMetaInfoMap {
	static final public ModelJavaMetaInfoMap INSTANCE = new ModelJavaMetaInfoMap();

	/** Constructor for ModelJavaMetaInfoMap
	 */
	public ModelJavaMetaInfoMap() {
		this.importMetaInfo(org.opaeum.runtime.bpm.util.OpaeumLibraryForBPMJavaMetaInfoMap.INSTANCE);
		putClass(model.Class1lk.class,"model.uml@_Gqq44LPJEeGjH_kSoa4Y3A");
		putClass(model.Class2.class,"model.uml@_LUyeoLPJEeGjH_kSoa4Y3A");
		putMethod(model.Class2.class,"model.uml@_FJO3sLawEeGoaOQgUyDAWw",3618094225901378004l);
		putMethod(model.Class2.class,"model.uml@_H4RWYLboEeGZM4YYtBhImQ",5502045197953410104l);
		putClass(model.Class3.class,"model.uml@_tWXP4LblEeGZM4YYtBhImQ");
		putClass(model.MyBusiness.class,"model.uml@_FWdk8La8EeGKjIJH2lzvoQ");
	}


}