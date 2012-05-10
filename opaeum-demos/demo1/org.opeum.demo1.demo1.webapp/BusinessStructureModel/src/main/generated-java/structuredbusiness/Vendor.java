package structuredbusiness;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.opaeum.annotation.NumlMetaInfo;
import org.opaeum.runtime.domain.IEnum;

@NumlMetaInfo(uuid="914890@_z8IcwHsKEeGBGZr9IpIa3A")public enum Vendor implements IEnum, Serializable {
	WHIRLPOOL("914890@_19w9UHsKEeGBGZr9IpIa3A",7658578000613130252l),
	BOSCH("914890@_3FQw4HsKEeGBGZr9IpIa3A",377325257822563020l),
	KELVINATOR("914890@_4C-9YHsKEeGBGZr9IpIa3A",7281287795211476570l);
	private long opaeumId;
	private String uuid;
	/** Constructor for Vendor
	 * 
	 * @param uuid 
	 * @param opaeumId 
	 */
	private Vendor(String uuid, long opaeumId) {
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
	
	static public Set<Vendor> getValues() {
		return new HashSet<Vendor>(java.util.Arrays.asList(values()));
	}

}