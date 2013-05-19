package org.opaeum.runtime.bpm.contact;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.opaeum.hibernate.domain.AbstractPersistentEnum;

@Table(name="organization_e_mail_address_type",schema="bpm")
@Entity(name="OrganizationEMailAddressTypeEntity")
public class OrganizationEMailAddressTypeEntity extends AbstractPersistentEnum {


	/** Constructor for OrganizationEMailAddressTypeEntity
	 * 
	 * @param e 
	 */
	public OrganizationEMailAddressTypeEntity(OrganizationEMailAddressType e) {
		super(e);
	}
	
	/** Default constructor for OrganizationEMailAddressTypeEntity
	 */
	public OrganizationEMailAddressTypeEntity() {
	}


}