package structuredbusiness.util;

import org.opaeum.runtime.domain.IPersistentObject;
import org.opaeum.runtime.environment.JavaMetaInfoMap;

public class StructuredbusinessJavaMetaInfoMap extends JavaMetaInfoMap {
	static final public StructuredbusinessJavaMetaInfoMap INSTANCE = new StructuredbusinessJavaMetaInfoMap();

	/** Constructor for StructuredbusinessJavaMetaInfoMap
	 */
	public StructuredbusinessJavaMetaInfoMap() {
		this.importMetaInfo(org.opaeum.runtime.bpm.util.OpaeumLibraryForBPMJavaMetaInfoMap.INSTANCE);
		putClass(structuredbusiness.BusinessDocument1.class,"914890@_oiVeEGCfEeG6xvYqJACneg");
		putClass(structuredbusiness.BusinessRole1.class,"914890@_mec6wGCfEeG6xvYqJACneg");
		putClass(structuredbusiness.BusinessService1.class,"914890@_oBOVEGCfEeG6xvYqJACneg");
		putClass(structuredbusiness.Class1.class,"914890@_nhV7IGCfEeG6xvYqJACneg");
		putClass(structuredbusiness.Notification1.class,"914890@_o9aQgGCfEeG6xvYqJACneg");
		putClass(structuredbusiness.Online_Customer.class,"914890@_xQY8oGFKEeG2AvOqZt1NZQ");
		putClass(structuredbusiness.Structuredbusiness.class,"914890@_-VLbkE8VEeGA3PFuQY5w7QNakedBusinessCollaborationNakedBusinessCollaboration");
	}


}