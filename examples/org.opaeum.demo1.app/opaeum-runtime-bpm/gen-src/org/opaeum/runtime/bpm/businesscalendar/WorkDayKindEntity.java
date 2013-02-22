package org.opaeum.runtime.bpm.businesscalendar;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.opaeum.hibernate.domain.AbstractPersistentOpaeumIdEnum;

@Table(name="work_day_kind")
@Entity(name="WorkDayKindEntity")
public class WorkDayKindEntity extends AbstractPersistentOpaeumIdEnum {


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