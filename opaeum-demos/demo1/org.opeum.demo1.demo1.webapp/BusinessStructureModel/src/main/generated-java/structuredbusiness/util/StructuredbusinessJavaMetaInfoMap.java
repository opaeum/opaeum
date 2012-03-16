package structuredbusiness.util;

import org.opaeum.runtime.domain.IPersistentObject;
import org.opaeum.runtime.environment.JavaMetaInfoMap;

public class StructuredbusinessJavaMetaInfoMap extends JavaMetaInfoMap {
	static final public StructuredbusinessJavaMetaInfoMap INSTANCE = new StructuredbusinessJavaMetaInfoMap();

	/** Constructor for StructuredbusinessJavaMetaInfoMap
	 */
	public StructuredbusinessJavaMetaInfoMap() {
		this.importMetaInfo(org.opaeum.runtime.bpm.util.OpaeumLibraryForBPMJavaMetaInfoMap.INSTANCE);
		putClass(structuredbusiness.Accountant.class,"914890@_mec6wGCfEeG6xvYqJACneg");
		putClass(structuredbusiness.DishWasher.class,"914890@_nhV7IGCfEeG6xvYqJACneg");
		putClass(structuredbusiness.DishwashersInc.class,"914890@_CQTWAGOeEeGwMNo027LgxA");
		putClass(structuredbusiness.DocumentVerifier.class,"914890@_tq_pUGK1EeGb14EjInbIAA");
		putMethod(structuredbusiness.DocumentVerifier.class,"914890@_BkEGYG33EeGRLMabaulNTg",7779246390203956904l);
		putMethod(structuredbusiness.DocumentVerifier.class,"914890@_vaiUUGK1EeGb14EjInbIAA",3618253756675873619l);
		putClass(structuredbusiness.IdBook.class,"914890@_oiVeEGCfEeG6xvYqJACneg");
		putClass(structuredbusiness.LulusRole.class,"914890@_urUa8GSDEeG8Es66O6-kpg");
		putClass(structuredbusiness.Manager.class,"914890@_u7lZEGQWEeGbL9nlXe9lTQ");
		putClass(structuredbusiness.Online_Customer.class,"914890@_xQY8oGFKEeG2AvOqZt1NZQ");
		putClass(structuredbusiness.ProductAnnouncement.class,"914890@_o9aQgGCfEeG6xvYqJACneg");
		putClass(structuredbusiness.Supplier.class,"914890@_-N6PwGK6EeGNuoaMwaBk1w");
		putClass(structuredbusiness.Structuredbusiness.class,"914890@_-VLbkE8VEeGA3PFuQY5w7QNakedBusinessCollaborationNakedBusinessCollaboration");
	}


}