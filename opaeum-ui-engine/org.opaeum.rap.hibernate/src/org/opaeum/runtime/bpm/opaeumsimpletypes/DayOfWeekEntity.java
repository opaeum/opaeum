package org.opaeum.runtime.bpm.opaeumsimpletypes;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.opaeum.audit.AbstractPersistentEnum;

@Table(name="day_of_week")
@Entity(name="DayOfWeekEntity")
public class DayOfWeekEntity extends AbstractPersistentEnum {


	/** Constructor for DayOfWeekEntity
	 * 
	 * @param e 
	 */
	public DayOfWeekEntity(DayOfWeek e) {
		super(e);
	}
	
	/** Default constructor for DayOfWeekEntity
	 */
	public DayOfWeekEntity() {
	}


}