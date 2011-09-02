package org.nakeduml.runtime.bpm;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import org.nakeduml.annotation.NumlMetaInfo;
import org.nakeduml.runtime.domain.IEnum;

@NumlMetaInfo(qualifiedPersistentName="opium_library_for_bpm.task_delegation",uuid="5aee6ba0_7517_4887_ba4f_f7766e191b03")public enum TaskDelegation implements IEnum, Serializable {
	POTENTIALOWNERS("09690faf_0088_4aed_afcd_53c93bbfd864"),
	ANYBODY("73f07074_caf6_42dc_89c1_e85e2224530e"),
	NOBODY("4ace4b26_ba08_425c_9451_3ec2adf2809e"),
	OTHER("2114477e_6d86_406f_a9fb_8b310094bc77");
	private String uuid;
	/** Constructor for TaskDelegation
	 * 
	 * @param uuid 
	 */
	private TaskDelegation(String uuid) {
		this.uuid=uuid;
	}

	public String getUuid() {
		return this.uuid;
	}
	
	static public Set<TaskDelegation> getValues() {
		return new HashSet<TaskDelegation>(java.util.Arrays.asList(values()));
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString());
		return sb.toString();
	}

}