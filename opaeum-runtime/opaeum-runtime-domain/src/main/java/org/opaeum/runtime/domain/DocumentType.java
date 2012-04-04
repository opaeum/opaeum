package org.opaeum.runtime.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import org.opaeum.annotation.NumlMetaInfo;

@NumlMetaInfo(uuid="OpaeumBPMCommon.library.uml@_Ckeg4F9ZEeG3X_yvufTVmw")public enum DocumentType implements IEnum, Serializable {
	SPREADSHEET("OpaeumBPMCommon.library.uml@_FHq2YF9ZEeG3X_yvufTVmw"),
	PRESENTATION("OpaeumBPMCommon.library.uml@_HHmt0F9ZEeG3X_yvufTVmw"),
	READONLYDOCUMENT("OpaeumBPMCommon.library.uml@_IwrkcF9ZEeG3X_yvufTVmw"),
	DOCUMENT("OpaeumBPMCommon.library.uml@_iIQ3wF9ZEeG3X_yvufTVmw"),
	IMAGE("OpaeumBPMCommon.library.uml@_pU6rsF9ZEeG3X_yvufTVmw");
	private String uuid;
	/** Constructor for DocumentType
	 * 
	 * @param uuid 
	 */
	private DocumentType(String uuid) {
		this.uuid=uuid;
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