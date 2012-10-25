package ocltests;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.opaeum.annotation.NumlMetaInfo;
import org.opaeum.runtime.domain.IEnum;

@NumlMetaInfo(uuid="ocltests.uml@_zLJVAOHyEeGQTZorD0E5LA")public enum SailPosition implements IEnum, Serializable {
	FRONT("ocltests.uml@_29vc4OHyEeGQTZorD0E5LA",7973713438283700445l),
	REAR("ocltests.uml@_3pJxYOHyEeGQTZorD0E5LA",1321889112493011585l);
	private long opaeumId;
	private String uuid;
	/** Constructor for SailPosition
	 * 
	 * @param uuid 
	 * @param opaeumId 
	 */
	private SailPosition(String uuid, long opaeumId) {
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
	
	static public Set<SailPosition> getValues() {
		return new HashSet<SailPosition>(java.util.Arrays.asList(values()));
	}

}