package org.opaeum.demo1.structuredbusiness.util;

import org.opaeum.runtime.domain.IPersistentObject;
import org.opaeum.runtime.environment.JavaMetaInfoMap;

public class StructuredbusinessJavaMetaInfoMap extends JavaMetaInfoMap {
	static final public StructuredbusinessJavaMetaInfoMap INSTANCE = new StructuredbusinessJavaMetaInfoMap();

	/** Constructor for StructuredbusinessJavaMetaInfoMap
	 */
	public StructuredbusinessJavaMetaInfoMap() {
		this.importMetaInfo(org.opaeum.runtime.bpm.util.OpaeumLibraryForBPMJavaMetaInfoMap.INSTANCE);
		putClass(org.opaeum.demo1.structuredbusiness.ApplianceCollaboration.class,"914890@_J3MS8HkAEeKsL8ZaFiY2TQ");
		putClass(org.opaeum.demo1.structuredbusiness.ApplianceDoctor.class,"914890@_CQTWAGOeEeGwMNo027LgxA");
		putMethod(org.opaeum.demo1.structuredbusiness.ApplianceDoctor.class,"914890@_FGOJ8H4bEeGW5bASaRr7SQ",4272470142100685737l);
		putClass(org.opaeum.demo1.structuredbusiness.OnlineCustomer.class,"914890@_xQY8oGFKEeG2AvOqZt1NZQ");
		putClass(org.opaeum.demo1.structuredbusiness.Supplier.class,"914890@_-N6PwGK6EeGNuoaMwaBk1w");
		putClass(org.opaeum.demo1.structuredbusiness.appliance.ApplianceComponent.class,"914890@_x_4WgHJ6EeG5aYCQXxe9BQ");
		putClass(org.opaeum.demo1.structuredbusiness.appliance.ApplianceModel.class,"914890@_nhV7IGCfEeG6xvYqJACneg");
		putClass(org.opaeum.demo1.structuredbusiness.appliance.ApplianceType.class,"914890@_8mTZ0JK_EeGnpuq6_ber_Q");
		putClass(org.opaeum.demo1.structuredbusiness.appliance.ProductAnnouncement.class,"914890@_o9aQgGCfEeG6xvYqJACneg");
		putClass(org.opaeum.demo1.structuredbusiness.appliance.Vendor.class,"914890@_z8IcwHsKEeGBGZr9IpIa3A");
		putClass(org.opaeum.demo1.structuredbusiness.branch.Branch.class,"914890@_0l-NAJJNEeGW4L5IejZxpA");
		putMethod(org.opaeum.demo1.structuredbusiness.branch.Branch.class,"914890@_dOodgMzBEeGKe7Qm4dvydQ",338091957186500850l);
		putClass(org.opaeum.demo1.structuredbusiness.branch.branch.PrepareQuote.class,"914890@_HHdlgBYUEeKsDbmQL25eBw");
		putMethod(org.opaeum.demo1.structuredbusiness.branch.branch.PrepareQuote.class,"914890@_SvOOoBYbEeKsDbmQL25eBw",60362420042796755l);
		putMethod(org.opaeum.demo1.structuredbusiness.branch.branch.PrepareQuote.class,"914890@_Y9DC0BYbEeKsDbmQL25eBw",2827266857271290249l);
		putClass(org.opaeum.demo1.structuredbusiness.branch.branch.StandaloneSingleScreenTask1.class,"914890@_ylMisBYQEeKIFJAOfPz88A");
		putClass(org.opaeum.demo1.structuredbusiness.branch.City.class,"914890@_HSme4JKIEeGFkOm2e1MJNQ");
		putClass(org.opaeum.demo1.structuredbusiness.branch.CustomerAssistant.class,"914890@_bX-ooJJPEeGW4L5IejZxpA");
		putMethod(org.opaeum.demo1.structuredbusiness.branch.CustomerAssistant.class,"914890@_A1hu8JKiEeGiJMBDeZRymA",3915947986409510033l);
		putClass(org.opaeum.demo1.structuredbusiness.branch.IdBook.class,"914890@_oiVeEGCfEeG6xvYqJACneg");
		putClass(org.opaeum.demo1.structuredbusiness.branch.Manager.class,"914890@_u7lZEGQWEeGbL9nlXe9lTQ");
		putClass(org.opaeum.demo1.structuredbusiness.branch.Province.class,"914890@_CkljUJJWEeGFkOm2e1MJNQ");
		putClass(org.opaeum.demo1.structuredbusiness.branch.Technician.class,"914890@_loLrkJHrEeGtApeO0lzlHQ");
		putClass(org.opaeum.demo1.structuredbusiness.jobs.Activity.class,"914890@_DlV9oJLCEeGnpuq6_ber_Q");
		putClass(org.opaeum.demo1.structuredbusiness.jobs.ApplianceComponentSale.class,"914890@_tGMQAJLBEeGnpuq6_ber_Q");
		putClass(org.opaeum.demo1.structuredbusiness.jobs.Job.class,"914890@_PgzU8JLAEeGnpuq6_ber_Q");
		putEventHandler(org.opaeum.demo1.structuredbusiness.branch.branch.preparequote.Transition1ChangeEventHandler.class,"914890@_q8beEHgGEeKNG8mFSp3Ijg");
	}


}