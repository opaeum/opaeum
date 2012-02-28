package org.opaeum.runtime.bpm.contact;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.opaeum.annotation.NumlMetaInfo;
import org.opaeum.runtime.domain.IEnum;

@NumlMetaInfo(uuid="252060@_uImqUEtnEeGd4cpyhpib9Q")public enum OrganizationPhoneNumberType implements IEnum, Serializable {
	LANDLINE("252060@_y2_sEEtnEeGd4cpyhpib9Q"),
	FAX("252060@_0AOlAEtnEeGd4cpyhpib9Q");
	private String uuid;
	/** Constructor for OrganizationPhoneNumberType
	 * 
	 * @param uuid 
	 */
	private OrganizationPhoneNumberType(String uuid) {
		this.uuid=uuid;
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