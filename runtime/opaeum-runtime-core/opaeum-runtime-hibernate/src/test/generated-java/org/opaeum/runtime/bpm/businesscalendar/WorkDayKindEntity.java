package org.opaeum.runtime.bpm.businesscalendar;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.opaeum.hibernate.domain.AbstractPersistentEnum;

@Table(name="work_day_kind",schema="bpm")
@Entity(name="WorkDayKindEntity")
public class WorkDayKindEntity extends AbstractPersistentEnum {


	/** Constructor for WorkDayKindEntity
	 * 
	 * @param e 
	 */
	public WorkDayKindEntity(WorkDayKind e) {
		super(e);
	}
	
	/** Default constructor for WorkDayKindEntity
	 */
	public WorkDayKindEntity() {
	}


}