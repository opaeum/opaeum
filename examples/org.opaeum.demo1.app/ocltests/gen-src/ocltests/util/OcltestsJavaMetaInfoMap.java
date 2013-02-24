package ocltests.util;

import org.opaeum.runtime.domain.IPersistentObject;
import org.opaeum.runtime.environment.JavaMetaInfoMap;

public class OcltestsJavaMetaInfoMap extends JavaMetaInfoMap {
	static final public OcltestsJavaMetaInfoMap INSTANCE = new OcltestsJavaMetaInfoMap();

	/** Constructor for OcltestsJavaMetaInfoMap
	 */
	public OcltestsJavaMetaInfoMap() {
		putClass(ocltests.Boat.class,"ocltests.uml@_ECviIOEgEeGhxPe2keryuw");
		putMethod(ocltests.Boat.class,"ocltests.uml@_1HN10OFSEeGyXP1sbXN4GQ",1724583666510491049l);
		putClass(ocltests.BusinessRole1.class,"ocltests.uml@_b1zFwOIfEeGXVpXEt0fddw");
		putClass(ocltests.Sail.class,"ocltests.uml@_HYTIsOEgEeGhxPe2keryuw");
		putClass(ocltests.SailPosition.class,"ocltests.uml@_zLJVAOHyEeGQTZorD0E5LA");
	}


}