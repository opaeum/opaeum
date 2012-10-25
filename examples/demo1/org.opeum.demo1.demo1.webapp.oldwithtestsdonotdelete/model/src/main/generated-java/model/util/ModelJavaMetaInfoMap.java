package model.util;

import org.opaeum.runtime.domain.IPersistentObject;
import org.opaeum.runtime.environment.JavaMetaInfoMap;

public class ModelJavaMetaInfoMap extends JavaMetaInfoMap {
	static final public ModelJavaMetaInfoMap INSTANCE = new ModelJavaMetaInfoMap();

	/** Constructor for ModelJavaMetaInfoMap
	 */
	public ModelJavaMetaInfoMap() {
		this.importMetaInfo(org.opaeum.runtime.bpm.util.OpaeumLibraryForBPMJavaMetaInfoMap.INSTANCE);
		putClass(model.BusinessDocument1.class,"model.uml@_F8xYMLlHEeG-Ou4fV0X62w");
		putClass(model.Class1lk.class,"model.uml@_Gqq44LPJEeGjH_kSoa4Y3A");
		putClass(model.Class2.class,"model.uml@_LUyeoLPJEeGjH_kSoa4Y3A");
		putMethod(model.Class2.class,"model.uml@_FJO3sLawEeGoaOQgUyDAWw",3618094225901378004l);
		putMethod(model.Class2.class,"model.uml@_H4RWYLboEeGZM4YYtBhImQ",5502045197953410104l);
		putClass(model.Class3.class,"model.uml@_tWXP4LblEeGZM4YYtBhImQ");
		putClass(model.Class4.class,"model.uml@_iEtO4LkpEeGPU4u_bLx6fA");
		putMethod(model.Class4.class,"model.uml@_ke1H8LkpEeGPU4u_bLx6fA",3897512208819415614l);
		putClass(model.Class5.class,"model.uml@_z86RoLlMEeG4Bcw-uZ2BZg");
		putClass(model.DataType1.class,"model.uml@_ejhnILkpEeGPU4u_bLx6fA");
		putMethod(model.DataType1.class,"model.uml@_KNkFsLlMEeG-Ou4fV0X62w",58478475360438044l);
		putClass(model.Enumeration1.class,"model.uml@_Il3dALkpEeGPU4u_bLx6fA");
		putClass(model.Interface12.class,"model.uml@_fvMwELkpEeGPU4u_bLx6fA");
		putMethod(model.Interface12.class,"model.uml@_pl7T4LlFEeG-Ou4fV0X62w",448801394253005110l);
		putClass(model.MyBusiness.class,"model.uml@_FWdk8La8EeGKjIJH2lzvoQ");
		putClass(model.Signal1.class,"model.uml@_67TgwLlEEeG-Ou4fV0X62w");
	}


}