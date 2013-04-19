package org.opaeum.runtime.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.opaeum.audit.AbstractPersistentEnum;

@Table(name="task_delegation")
@Entity(name="TaskDelegationEntity")
public class TaskDelegationEntity extends AbstractPersistentEnum {


	/** Constructor for TaskDelegationEntity
	 * 
	 * @param e 
	 */
	public TaskDelegationEntity(TaskDelegation e) {
		super(e);
	}
	
	/** Default constructor for TaskDelegationEntity
	 */
	public TaskDelegationEntity() {
	}


}