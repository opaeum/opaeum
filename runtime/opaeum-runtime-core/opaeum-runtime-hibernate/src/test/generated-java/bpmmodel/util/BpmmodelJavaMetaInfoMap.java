package bpmmodel.util;

import org.opaeum.runtime.environment.JavaMetaInfoMap;

public class BpmmodelJavaMetaInfoMap extends JavaMetaInfoMap {
	static final public BpmmodelJavaMetaInfoMap INSTANCE = new BpmmodelJavaMetaInfoMap();

	/** Constructor for BpmmodelJavaMetaInfoMap
	 */
	public BpmmodelJavaMetaInfoMap() {
		this.importMetaInfo(org.opaeum.runtime.bpm.util.OpaeumLibraryForBPMJavaMetaInfoMap.INSTANCE);
		putClass(bpmmodel.mybusiness.PrepareQuote.class,"bpm.uml@_D4xCQI_hEeK855GX2Z3x4Q@MT");
		putClass(bpmmodel.BackOfficeWorker.class,"bpm.uml@_ur_igI_gEeK855GX2Z3x4Q");
		putClass(bpmmodel.BusinessCollaboration1.class,"bpm.uml@_WOOI4I_cEeKWQLWOG6WHmA");
		putClass(bpmmodel.Customer.class,"bpm.uml@_FEtqQI_cEeKWQLWOG6WHmA");
		putClass(bpmmodel.MyBusiness.class,"bpm.uml@_b9ZSMI_cEeKWQLWOG6WHmA");
		putMethod(bpmmodel.MyBusiness.class,"bpm.uml@_D4xCQI_hEeK855GX2Z3x4Q",7284376369192582928l);
		putClass(bpmmodel.mybusiness.PrepareQuoteImpl.class,"bpm.uml@_Gld5EI_hEeK855GX2Z3x4Q");
		putClass(bpmmodel.Product.class,"bpm.uml@_Obc94I_hEeK855GX2Z3x4Q");
		putClass(bpmmodel.SalesPerson.class,"bpm.uml@_qPapgI_gEeK855GX2Z3x4Q");
		putClass(bpmmodel.Supplier.class,"bpm.uml@_F-ntwI_cEeKWQLWOG6WHmA");
		putClass(bpmmodel.custom.CustomBusinessNetwork.class,"bpm.uml@_qYfVgMB-EeKVzPWAF6TUwg");
		putClass(bpmmodel.custom.CustomOrganizationNode.class,"bpm.uml@_2nyj8MB-EeKVzPWAF6TUwg");
	}


}