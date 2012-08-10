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
		putMethod(structuredbusiness.ApplianceDoctor.class,"914890@_-VLbkE8VEeGA3PFuQY5w7Q",5138080424939164536l);
		putClass(structuredbusiness.ApplianceModel.class,"914890@_nhV7IGCfEeG6xvYqJACneg");
		putClass(structuredbusiness.ApplianceType.class,"914890@_8mTZ0JK_EeGnpuq6_ber_Q");
		putClass(structuredbusiness.Branch.class,"914890@_0l-NAJJNEeGW4L5IejZxpA");
		putMethod(structuredbusiness.Branch.class,"914890@_-VLbkE8VEeGA3PFuQY5w7Q",5138080424939164536l);
		putClass(structuredbusiness.City.class,"914890@_HSme4JKIEeGFkOm2e1MJNQ");
		putClass(structuredbusiness.CustomerAssistant.class,"914890@_bX-ooJJPEeGW4L5IejZxpA");
		putMethod(structuredbusiness.CustomerAssistant.class,"914890@_-VLbkE8VEeGA3PFuQY5w7Q",5138080424939164536l);
		putClass(structuredbusiness.IdBook.class,"914890@_oiVeEGCfEeG6xvYqJACneg");
		putClass(structuredbusiness.Job.class,"914890@_PgzU8JLAEeGnpuq6_ber_Q");
		putClass(structuredbusiness.Manager.class,"914890@_u7lZEGQWEeGbL9nlXe9lTQ");
		putClass(structuredbusiness.Online_Customer.class,"914890@_xQY8oGFKEeG2AvOqZt1NZQ");
		putClass(structuredbusiness.ProductAnnouncement.class,"914890@_o9aQgGCfEeG6xvYqJACneg");
		putClass(structuredbusiness.Province.class,"914890@_CkljUJJWEeGFkOm2e1MJNQ");
		putClass(structuredbusiness.Supplier.class,"914890@_-N6PwGK6EeGNuoaMwaBk1w");
		putClass(structuredbusiness.Technician.class,"914890@_loLrkJHrEeGtApeO0lzlHQ");
		putClass(structuredbusiness.Vendor.class,"914890@_z8IcwHsKEeGBGZr9IpIa3A");
	}


}