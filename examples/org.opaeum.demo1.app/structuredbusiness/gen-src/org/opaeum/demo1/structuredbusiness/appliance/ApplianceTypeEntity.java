package org.opaeum.demo1.structuredbusiness.appliance;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.opaeum.hibernate.domain.AbstractPersistentOpaeumIdEnum;

@Table(name="appliance_type",schema="structuredbusiness")
@Entity(name="ApplianceTypeEntity")
public class ApplianceTypeEntity extends AbstractPersistentOpaeumIdEnum {


	/** Constructor for ApplianceTypeEntity
	 * 
	 * @param e 
	 */
	public ApplianceTypeEntity(ApplianceType e) {
		super(e);
	}
	
	/** Default constructor for ApplianceTypeEntity
	 */
	public ApplianceTypeEntity() {
	}


}