package org.opaeum.runtime.contact;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.opaeum.audit.AbstractPersistentEnum;

@Table(name="person_phone_number_type")
@Entity(name="PersonPhoneNumberTypeEntity")
public class PersonPhoneNumberTypeEntity extends AbstractPersistentEnum {


	/** Constructor for PersonPhoneNumberTypeEntity
	 * 
	 * @param e 
	 */
	public PersonPhoneNumberTypeEntity(PersonPhoneNumberType e) {
		super(e);
	}
	
	/** Default constructor for PersonPhoneNumberTypeEntity
	 */
	public PersonPhoneNumberTypeEntity() {
	}


}