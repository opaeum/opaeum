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
		putClass(structuredbusiness.DishWasherComponent.class,"914890@_x_4WgHJ6EeG5aYCQXxe9BQ");
		putClass(structuredbusiness.DishWasherModel.class,"914890@_nhV7IGCfEeG6xvYqJACneg");
		putClass(structuredbusiness.DishwashersInc.class,"914890@_CQTWAGOeEeGwMNo027LgxA");
		putMethod(structuredbusiness.DishwashersInc.class,"914890@_FGOJ8H4bEeGW5bASaRr7SQ",4272470142100685737l);
		putClass(structuredbusiness.IdBook.class,"914890@_oiVeEGCfEeG6xvYqJACneg");
		putClass(structuredbusiness.Manager.class,"914890@_u7lZEGQWEeGbL9nlXe9lTQ");
		putClass(structuredbusiness.Online_Customer.class,"914890@_xQY8oGFKEeG2AvOqZt1NZQ");
		putClass(structuredbusiness.Order.class,"914890@_gTMrEH47EeGarqqEaoJFHg");
		putMethod(structuredbusiness.Order.class,"914890@_2Wwo4H47EeGarqqEaoJFHg",3724332385847356446l);
		putClass(structuredbusiness.ProductAnnouncement.class,"914890@_o9aQgGCfEeG6xvYqJACneg");
		putClass(structuredbusiness.Supplier.class,"914890@_-N6PwGK6EeGNuoaMwaBk1w");
		putClass(structuredbusiness.Vendor.class,"914890@_z8IcwHsKEeGBGZr9IpIa3A");
		putClass(structuredbusiness.Structuredbusiness.class,"914890@_-VLbkE8VEeGA3PFuQY5w7QNakedBusinessCollaborationNakedBusinessCollaboration");
	}


}