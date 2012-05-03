package structuredbusiness;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.opaeum.annotation.NumlMetaInfo;
import org.opaeum.runtime.domain.IEnum;

@NumlMetaInfo(uuid="914890@_8mTZ0JK_EeGnpuq6_ber_Q")public enum ApplianceType implements IEnum, Serializable {
	DISHWASHER("914890@_9uUx0JK_EeGnpuq6_ber_Q"),
	TUMBLEDRYER("914890@_-uwUEJK_EeGnpuq6_ber_Q"),
	WASHINGMACHINE("914890@_BBJNcJLAEeGnpuq6_ber_Q");
	private String uuid;
	/** Constructor for ApplianceType
	 * 
	 * @param uuid 
	 */
	private ApplianceType(String uuid) {
		this.uuid=uuid;
	}

	public String getUid() {
		String result = getUuid();
		
		return result;
	}
	
	public String getUuid() {
		return this.uuid;
	}
	
	static public Set<ApplianceType> getValues() {
		return new HashSet<ApplianceType>(java.util.Arrays.asList(values()));
	}

}