package org.opaeum.runtime.bpm.businesscalendar;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.opaeum.hibernate.domain.AbstractPersistentEnum;

@Table(name="work_day_kind")
@Entity(name="WorkDayKindClass")
public class WorkDayKindClass extends AbstractPersistentEnum {


	/** Constructor for WorkDayKindClass
	 * 
	 * @param e 
	 */
	public WorkDayKindClass(WorkDayKind e) {
		super(e);
	}
	
	/** Default constructor for WorkDayKindClass
	 */
	public WorkDayKindClass() {
	}


}