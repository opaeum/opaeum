package org.opaeum.runtime.contact;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.opaeum.hibernate.domain.AbstractPersistentOpaeumIdEnum;
import org.opaeum.runtime.contact.PersonPhoneNumberType;

@Table(name="person_phone_number_type")
@Entity(name="PersonPhoneNumberTypeEntity")
public class PersonPhoneNumberTypeEntity extends AbstractPersistentOpaeumIdEnum {


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