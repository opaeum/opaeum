package org.opaeum.runtime.bpm.contact;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.opaeum.audit.AbstractPersistentEnum;

@Table(name="organization_phone_number_type")
@Entity(name="OrganizationPhoneNumberTypeEntity")
public class OrganizationPhoneNumberTypeEntity extends AbstractPersistentEnum {


	/** Constructor for OrganizationPhoneNumberTypeEntity
	 * 
	 * @param e 
	 */
	public OrganizationPhoneNumberTypeEntity(OrganizationPhoneNumberType e) {
		super(e);
	}
	
	/** Default constructor for OrganizationPhoneNumberTypeEntity
	 */
	public OrganizationPhoneNumberTypeEntity() {
	}


}