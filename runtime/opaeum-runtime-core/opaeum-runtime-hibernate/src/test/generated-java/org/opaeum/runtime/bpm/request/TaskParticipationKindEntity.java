package org.opaeum.runtime.bpm.request;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.opaeum.hibernate.domain.AbstractPersistentEnum;

@Table(name="task_participation_kind",schema="bpm")
@Entity(name="TaskParticipationKindEntity")
public class TaskParticipationKindEntity extends AbstractPersistentEnum {


	/** Constructor for TaskParticipationKindEntity
	 * 
	 * @param e 
	 */
	public TaskParticipationKindEntity(TaskParticipationKind e) {
		super(e);
	}
	
	/** Default constructor for TaskParticipationKindEntity
	 */
	public TaskParticipationKindEntity() {
	}


}