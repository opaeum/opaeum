package org.opaeum.runtime.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.opaeum.annotation.NumlMetaInfo;
import org.opaeum.runtime.domain.IEnum;

@NumlMetaInfo(uuid="OpaeumBPMCommon.library.uml@_Ckeg4F9ZEeG3X_yvufTVmw")public enum DocumentType implements IEnum, Serializable {
	SPREADSHEET("OpaeumBPMCommon.library.uml@_FHq2YF9ZEeG3X_yvufTVmw",4510870660847370576l),
	PRESENTATION("OpaeumBPMCommon.library.uml@_HHmt0F9ZEeG3X_yvufTVmw",8255221197372907126l),
	READONLYDOCUMENT("OpaeumBPMCommon.library.uml@_IwrkcF9ZEeG3X_yvufTVmw",3715427553278174788l),
	DOCUMENT("OpaeumBPMCommon.library.uml@_iIQ3wF9ZEeG3X_yvufTVmw",3753040871797758258l),
	IMAGE("OpaeumBPMCommon.library.uml@_pU6rsF9ZEeG3X_yvufTVmw",1766041522512898104l);
	private long opaeumId;
	private String uuid;
	/** Constructor for DocumentType
	 * 
	 * @param uuid 
	 * @param opaeumId 
	 */
	private DocumentType(String uuid, long opaeumId) {
		this.uuid=uuid;
		this.opaeumId=opaeumId;
	}

	public long getOpaeumId() {
		return this.opaeumId;
	}
	
	public String getUid() {
		String result = getUuid();
		
		return result;
	}
	
	public String getUuid() {
		return this.uuid;
	}
	
	static public Set<DocumentType> getValues() {
		return new HashSet<DocumentType>(java.util.Arrays.asList(values()));
	}

}