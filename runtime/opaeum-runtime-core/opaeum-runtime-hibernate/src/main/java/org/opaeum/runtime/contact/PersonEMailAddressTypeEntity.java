package org.opaeum.runtime.contact;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.opaeum.hibernate.domain.AbstractPersistentEnum;

@Table(name="person_e_mail_address_type")
@Entity(name="PersonEMailAddressTypeEntity")
public class PersonEMailAddressTypeEntity extends AbstractPersistentEnum {


	private static final long serialVersionUID = 2398822667475310494L;

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