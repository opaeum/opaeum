package org.opaeum.runtime.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import org.opaeum.annotation.NumlMetaInfo;
import org.opaeum.util.Hasher;

@NumlMetaInfo(uuid="OpaeumBPMCommon.library.uml@_5lxp4LRZEeCilvbXE8KmHA")public enum TaskDelegation implements IEnum, Serializable {
	POTENTIALOWNERS("OpaeumBPMCommon.library.uml@_MmeToLRaEeCilvbXE8KmHA"),
	NOBODY("OpaeumBPMCommon.library.uml@_OFIC8LRaEeCilvbXE8KmHA"),
	ANYBODY("OpaeumBPMCommon.library.uml@_PFIHYLRaEeCilvbXE8KmHA"),
	OTHER("OpaeumBPMCommon.library.uml@_PtH7gLRaEeCilvbXE8KmHA");
	private String uuid;
	/** Constructor for TaskDelegation
	 * 
	 * @param uuid 
	 */
	private TaskDelegation(String uuid) {
		this.uuid=uuid;
	}
	@Override
	public long getOpaeumId(){
		return Hasher.getOpaeumId(uuid);
	}

	public String getUid() {
		String result = getUuid();
		
		return result;
	}
	
	public String getUuid() {
		return this.uuid;
	}
	
	static public Set<TaskDelegation> getValues() {
		return new HashSet<TaskDelegation>(java.util.Arrays.asList(values()));
	}

}