package org.opaeum.runtime.bpm.contact;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.opaeum.annotation.NumlMetaInfo;
import org.opaeum.runtime.domain.IEnum;

@NumlMetaInfo(uuid="252060@_uImqUEtnEeGd4cpyhpib9Q")public enum OrganizationPhoneNumberType implements IEnum, Serializable {
	LANDLINE("252060@_y2_sEEtnEeGd4cpyhpib9Q",4917448850126404380l),
	FAX("252060@_0AOlAEtnEeGd4cpyhpib9Q",3657378413704772210l);
	private long opaeumId;
	private String uuid;
	/** Constructor for OrganizationPhoneNumberType
	 * 
	 * @param uuid 
	 * @param opaeumId 
	 */
	private OrganizationPhoneNumberType(String uuid, long opaeumId) {
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
	
	static public Set<OrganizationPhoneNumberType> getValues() {
		return new HashSet<OrganizationPhoneNumberType>(java.util.Arrays.asList(values()));
	}

}