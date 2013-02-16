package org.opaeum.runtime.bpm.contact;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.opaeum.hibernate.domain.AbstractPersistentEnum;

@Table(name="organization_e_mail_address_type")
@Entity(name="OrganizationEMailAddressTypeClass")
public class OrganizationEMailAddressTypeClass extends AbstractPersistentEnum {


	/** Constructor for OrganizationEMailAddressTypeClass
	 * 
	 * @param e 
	 */
	public OrganizationEMailAddressTypeClass(OrganizationEMailAddressType e) {
		super(e);
	}
	
	/** Default constructor for OrganizationEMailAddressTypeClass
	 */
	public OrganizationEMailAddressTypeClass() {
	}


}