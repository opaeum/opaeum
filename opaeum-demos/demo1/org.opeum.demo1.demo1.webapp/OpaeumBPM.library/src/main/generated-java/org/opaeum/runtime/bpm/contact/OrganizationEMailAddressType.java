package org.opaeum.runtime.bpm.contact;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.opaeum.annotation.NumlMetaInfo;
import org.opaeum.runtime.domain.IEnum;

@NumlMetaInfo(uuid="252060@_58k8wEtpEeGd4cpyhpib9Q")public enum OrganizationEMailAddressType implements IEnum, Serializable {
	INFO("252060@_746GkEtpEeGd4cpyhpib9Q"),
	ADMIN("252060@_z4RAUFYyEeGj5_I7bIwNoA");
	private String uuid;
	/** Constructor for OrganizationEMailAddressType
	 * 
	 * @param uuid 
	 */
	private OrganizationEMailAddressType(String uuid) {
		this.uuid=uuid;
	}

	public String getUid() {
		String result = getUuid();
		
		return result;
	}
	
	public String getUuid() {
		return this.uuid;
	}
	
	static public Set<OrganizationEMailAddressType> getValues() {
		return new HashSet<OrganizationEMailAddressType>(java.util.Arrays.asList(values()));
	}

}