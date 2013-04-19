package org.opaeum.runtime.contact;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.opaeum.audit.AbstractPersistentEnum;

@Table(name="person_e_mail_address_type")
@Entity(name="PersonEMailAddressTypeEntity")
public class PersonEMailAddressTypeEntity extends AbstractPersistentEnum {


	/** Constructor for PersonEMailAddressTypeEntity
	 * 
	 * @param e 
	 */
	public PersonEMailAddressTypeEntity(PersonEMailAddressType e) {
		super(e);
	}
	
	/** Default constructor for PersonEMailAddressTypeEntity
	 */
	public PersonEMailAddressTypeEntity() {
	}


}