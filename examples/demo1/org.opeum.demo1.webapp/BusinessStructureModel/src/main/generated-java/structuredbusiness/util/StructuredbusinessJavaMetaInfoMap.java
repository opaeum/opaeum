package structuredbusiness.util;

import org.opaeum.runtime.domain.IPersistentObject;
import org.opaeum.runtime.environment.JavaMetaInfoMap;

public class StructuredbusinessJavaMetaInfoMap extends JavaMetaInfoMap {
	static final public StructuredbusinessJavaMetaInfoMap INSTANCE = new StructuredbusinessJavaMetaInfoMap();

	/** Constructor for StructuredbusinessJavaMetaInfoMap
	 */
	public StructuredbusinessJavaMetaInfoMap() {
		this.importMetaInfo(org.opaeum.runtime.bpm.util.OpaeumLibraryForBPMJavaMetaInfoMap.INSTANCE);
		putClass(structuredbusiness.Activity.class,"914890@_DlV9oJLCEeGnpuq6_ber_Q");
		putClass(structuredbusiness.ApplianceComponent.class,"914890@_x_4WgHJ6EeG5aYCQXxe9BQ");
		putClass(structuredbusiness.ApplianceComponentSale.class,"914890@_tGMQAJLBEeGnpuq6_ber_Q");
		putClass(structuredbusiness.ApplianceDoctor.class,"914890@_CQTWAGOeEeGwMNo027LgxA");
		putMethod(structuredbusiness.ApplianceDoctor.class,"914890@_FGOJ8H4bEeGW5bASaRr7SQ",4272470142100685737l);
		putClass(structuredbusiness.ApplianceModel.class,"914890@_nhV7IGCfEeG6xvYqJACneg");
		putClass(structuredbusiness.ApplianceType.class,"914890@_8mTZ0JK_EeGnpuq6_ber_Q");
		putClass(structuredbusiness.Branch.class,"914890@_0l-NAJJNEeGW4L5IejZxpA");
		putMethod(structuredbusiness.Branch.class,"914890@_dOodgMzBEeGKe7Qm4dvydQ",338091957186500850l);
		putClass(structuredbusiness.branch.BusinessStateMachine1.class,"914890@_HHdlgBYUEeKsDbmQL25eBw");
		putMethod(structuredbusiness.branch.BusinessStateMachine1.class,"914890@_SvOOoBYbEeKsDbmQL25eBw",60362420042796755l);
		putMethod(structuredbusiness.branch.BusinessStateMachine1.class,"914890@_Y9DC0BYbEeKsDbmQL25eBw",2827266857271290249l);
		putClass(structuredbusiness.branch.StandaloneSingleScreenTask1.class,"914890@_ylMisBYQEeKIFJAOfPz88A");
		putClass(structuredbusiness.City.class,"914890@_HSme4JKIEeGFkOm2e1MJNQ");
		putClass(structuredbusiness.CustomerAssistant.class,"914890@_bX-ooJJPEeGW4L5IejZxpA");
		putMethod(structuredbusiness.CustomerAssistant.class,"914890@_A1hu8JKiEeGiJMBDeZRymA",3915947986409510033l);
		putClass(structuredbusiness.IdBook.class,"914890@_oiVeEGCfEeG6xvYqJACneg");
		putClass(structuredbusiness.Job.class,"914890@_PgzU8JLAEeGnpuq6_ber_Q");
		putClass(structuredbusiness.Manager.class,"914890@_u7lZEGQWEeGbL9nlXe9lTQ");
		putClass(structuredbusiness.Online_Customer.class,"914890@_xQY8oGFKEeG2AvOqZt1NZQ");
		putClass(structuredbusiness.ProductAnnouncement.class,"914890@_o9aQgGCfEeG6xvYqJACneg");
		putClass(structuredbusiness.Province.class,"914890@_CkljUJJWEeGFkOm2e1MJNQ");
		putClass(structuredbusiness.Supplier.class,"914890@_-N6PwGK6EeGNuoaMwaBk1w");
		putClass(structuredbusiness.Technician.class,"914890@_loLrkJHrEeGtApeO0lzlHQ");
		putClass(structuredbusiness.Vendor.class,"914890@_z8IcwHsKEeGBGZr9IpIa3A");
		putEventHandler(structuredbusiness.branch.businessstatemachine1.Transition1TimeEventHandler.class,"914890@_eu1uUBb3EeKI68QaBu0uBA");
	}


}