package ocltests.util;

import org.opaeum.runtime.environment.JavaMetaInfoMap;

public class OcltestsJavaMetaInfoMap extends JavaMetaInfoMap {
	static final public OcltestsJavaMetaInfoMap INSTANCE = new OcltestsJavaMetaInfoMap();

	/** Constructor for OcltestsJavaMetaInfoMap
	 */
	public OcltestsJavaMetaInfoMap() {
		putClass(ocltests.Boat.class,"ocltests.uml@_ECviIOEgEeGhxPe2keryuw");
		putMethod(ocltests.Boat.class,"ocltests.uml@_5xPdIOEfEeGhxPe2keryuw",1495757841246172578l);
		putClass(ocltests.BusinessRole1.class,"ocltests.uml@_b1zFwOIfEeGXVpXEt0fddw");
		putClass(ocltests.Sail.class,"ocltests.uml@_HYTIsOEgEeGhxPe2keryuw");
		putClass(ocltests.SailPosition.class,"ocltests.uml@_zLJVAOHyEeGQTZorD0E5LA");
	}


}