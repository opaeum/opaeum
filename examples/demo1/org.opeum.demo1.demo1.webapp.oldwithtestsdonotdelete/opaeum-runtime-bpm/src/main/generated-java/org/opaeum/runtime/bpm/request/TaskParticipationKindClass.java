package org.opaeum.runtime.bpm.request;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.opaeum.hibernate.domain.AbstractPersistentEnum;

@Table(name="task_participation_kind")
@Entity(name="TaskParticipationKindClass")
public class TaskParticipationKindClass extends AbstractPersistentEnum {


	/** Constructor for TaskParticipationKindClass
	 * 
	 * @param e 
	 */
	public TaskParticipationKindClass(TaskParticipationKind e) {
		super(e);
	}
	
	/** Default constructor for TaskParticipationKindClass
	 */
	public TaskParticipationKindClass() {
	}


}