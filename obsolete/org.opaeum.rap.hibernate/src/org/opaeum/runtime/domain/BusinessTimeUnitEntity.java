package org.opaeum.runtime.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.opaeum.audit.AbstractPersistentEnum;

@Table(name="business_time_unit")
@Entity(name="BusinessTimeUnitEntity")
public class BusinessTimeUnitEntity extends AbstractPersistentEnum {


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