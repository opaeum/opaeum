package org.opaeum.runtime.bpm.contact;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.opaeum.annotation.NumlMetaInfo;
import org.opaeum.runtime.domain.IEnum;

@NumlMetaInfo(uuid="252060@_Z-VwcEtnEeGd4cpyhpib9Q")public enum PersonPhoneNumberType implements IEnum, Serializable {
	HOME("252060@_do3hcEtnEeGd4cpyhpib9Q"),
	CELL("252060@_et7FAEtnEeGd4cpyhpib9Q"),
	WORK("252060@_e0fAAEtnEeGd4cpyhpib9Q"),
	FAX("252060@_e4mrAEtnEeGd4cpyhpib9Q");
	private String uuid;
	/** Constructor for PersonPhoneNumberType
	 * 
	 * @param uuid 
	 */
	private PersonPhoneNumberType(String uuid) {
		this.uuid=uuid;
	}

	public String getUuid() {
		return this.uuid;
	}
	
	static public Set<PersonPhoneNumberType> getValues() {
		return new HashSet<PersonPhoneNumberType>(java.util.Arrays.asList(values()));
	}

}