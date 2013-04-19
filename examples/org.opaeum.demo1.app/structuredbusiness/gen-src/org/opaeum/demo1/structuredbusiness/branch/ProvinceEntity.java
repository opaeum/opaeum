package org.opaeum.demo1.structuredbusiness.branch;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.opaeum.hibernate.domain.AbstractPersistentOpaeumIdEnum;

@Table(name="province",schema="structuredbusiness")
@Entity(name="ProvinceEntity")
public class ProvinceEntity extends AbstractPersistentOpaeumIdEnum {


	/** Constructor for ProvinceEntity
	 * 
	 * @param e 
	 */
	public ProvinceEntity(Province e) {
		super(e);
	}
	
	/** Default constructor for ProvinceEntity
	 */
	public ProvinceEntity() {
	}


}