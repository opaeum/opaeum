package org.opaeum.demo1.structuredbusiness.branch;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.opaeum.hibernate.domain.AbstractPersistentOpaeumIdEnum;

@Table(name="city",schema="structuredbusiness")
@Entity(name="CityEntity")
public class CityEntity extends AbstractPersistentOpaeumIdEnum {


	/** Constructor for CityEntity
	 * 
	 * @param e 
	 */
	public CityEntity(City e) {
		super(e);
	}
	
	/** Default constructor for CityEntity
	 */
	public CityEntity() {
	}


}