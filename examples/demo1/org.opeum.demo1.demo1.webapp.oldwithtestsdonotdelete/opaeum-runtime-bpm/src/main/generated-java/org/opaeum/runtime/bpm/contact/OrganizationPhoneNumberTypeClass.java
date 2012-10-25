package org.opaeum.runtime.bpm.contact;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.opaeum.audit.AbstractPersistentEnum;

@Table(name="organization_phone_number_type")
@Entity(name="OrganizationPhoneNumberTypeClass")
public class OrganizationPhoneNumberTypeClass extends AbstractPersistentEnum {


	/** Constructor for OrganizationPhoneNumberTypeClass
	 * 
	 * @param e 
	 */
	public OrganizationPhoneNumberTypeClass(OrganizationPhoneNumberType e) {
		super(e);
	}
	
	/** Default constructor for OrganizationPhoneNumberTypeClass
	 */
	public OrganizationPhoneNumberTypeClass() {
	}


}