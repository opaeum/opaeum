package org.opaeum.demo1.structuredbusiness.appliance;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.opaeum.hibernate.domain.AbstractPersistentOpaeumIdEnum;

@Table(name="vendor",schema="structuredbusiness")
@Entity(name="VendorEntity")
public class VendorEntity extends AbstractPersistentOpaeumIdEnum {


	/** Constructor for VendorEntity
	 * 
	 * @param e 
	 */
	public VendorEntity(Vendor e) {
		super(e);
	}
	
	/** Default constructor for VendorEntity
	 */
	public VendorEntity() {
	}


}