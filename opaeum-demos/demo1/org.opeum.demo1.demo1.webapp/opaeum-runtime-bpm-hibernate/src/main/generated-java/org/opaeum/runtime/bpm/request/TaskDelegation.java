package org.opaeum.runtime.bpm.request;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.opaeum.annotation.NumlMetaInfo;
import org.opaeum.runtime.domain.IEnum;

@NumlMetaInfo(uuid="252060@_5lxp4LRZEeCilvbXE8KmHA")public enum TaskDelegation implements IEnum, Serializable {
	POTENTIALOWNERS("252060@_MmeToLRaEeCilvbXE8KmHA"),
	NOBODY("252060@_OFIC8LRaEeCilvbXE8KmHA"),
	ANYBODY("252060@_PFIHYLRaEeCilvbXE8KmHA"),
	OTHER("252060@_PtH7gLRaEeCilvbXE8KmHA");
	private String uuid;
	/** Constructor for TaskDelegation
	 * 
	 * @param uuid 
	 */
	private TaskDelegation(String uuid) {
		this.uuid=uuid;
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