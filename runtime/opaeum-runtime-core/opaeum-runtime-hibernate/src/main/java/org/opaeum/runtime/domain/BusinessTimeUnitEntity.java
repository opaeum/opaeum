package org.opaeum.runtime.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.opaeum.hibernate.domain.AbstractPersistentOpaeumIdEnum;

@Table(name="business_time_unit")
@Entity(name="BusinessTimeUnitEntity")
public class BusinessTimeUnitEntity extends AbstractPersistentOpaeumIdEnum {


	/** Constructor for BusinessTimeUnitEntity
	 * 
	 * @param e 
	 */
	public BusinessTimeUnitEntity(BusinessTimeUnit e) {
		super(e);
	}
	
	/** Default constructor for BusinessTimeUnitEntity
	 */
	public BusinessTimeUnitEntity() {
	}


}