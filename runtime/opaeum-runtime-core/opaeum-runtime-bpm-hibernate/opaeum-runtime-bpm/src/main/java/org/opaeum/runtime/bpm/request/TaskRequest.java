package org.opaeum.runtime.bpm.request;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;

import org.hibernate.annotations.AccessType;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.Proxy;
import org.opaeum.annotation.NumlMetaInfo;
import org.opaeum.runtime.bpm.organization.IBusinessRole;
import org.opaeum.runtime.bpm.organization.IParticipant;
import org.opaeum.runtime.bpm.requestobject.ITaskObject;
import org.opaeum.runtime.environment.Environment;

@NumlMetaInfo(uuid = "252060@_zFmsEIoVEeCLqpffVZYAlw")
@DiscriminatorValue("task_request")
@Proxy(lazy = false)
@Filter(name = "noDeletedObjects")
@org.hibernate.annotations.Entity(dynamicUpdate = true)
@AccessType("field")
@Table(name = "task_request")
@Inheritance(strategy = javax.persistence.InheritanceType.JOINED)
@Entity(name = "TaskRequest")
public class TaskRequest extends TaskRequestGenerated{
	private static final long serialVersionUID = -2776837620316759729L;
	public TaskRequest(ITaskObject owningObject){
	}
	public TaskRequest(){
	}
	public void setPotentialOwners(Collection<? extends IParticipant> readAll){
		for(IParticipant p:getPotentialOwners()){
			removeTaskRequestParticipant(p, TaskParticipationKind.POTENTIALOWNER);
		}
		for(IParticipant p:readAll){
			addTaskRequestParticipant(p, TaskParticipationKind.POTENTIALOWNER);
		}
	}
	public void setOwner(IParticipant newOwner){
		IParticipant oldOwner = getOwner();
		if(oldOwner != null){
			removeTaskRequestParticipant(oldOwner, TaskParticipationKind.OWNER);
		}
		if(newOwner != null){
			addTaskRequestParticipant(newOwner, TaskParticipationKind.OWNER);
		}
	}
	public void addTaskRequestParticipant(IParticipant newParticipant,TaskParticipationKind kind){
		super.addTaskRequestParticipant(newParticipant, kind);
		ParticipationInTask result = new ParticipationInTask();
		this.addToParticipationInTask(result);
		result.setParticipant(newParticipant);
		result.setKind(kind);
	}
	public void delegate(IBusinessRole delegate){
		super.delegate(delegate);
		setOwner(delegate);
		if(getRequestObject() instanceof ITaskObject){
			((ITaskObject) this.getRequestObject()).onDelegated((IParticipant) Environment.getInstance().getCurrentRole(), delegate);
		}
	}
	public void removeTaskRequestParticipant(IParticipant participant,TaskParticipationKind kind){
		generateRemoveTaskRequestParticipantEvent(participant, kind);
		Set<ParticipationInTask> result = new HashSet<ParticipationInTask>();
		for(ParticipationInTask p:this.getParticipationInTask()){
			if((p.getParticipant().equals(participant) && (p.getKind().equals(kind)))){
				result.add(p);
				p.getParticipant().removeFromParticipation(p);
			}
		}
		TaskRequest tgtRemoveParticipation = (TaskRequest) this;
		tgtRemoveParticipation.removeAllFromParticipationInTask(result);
	}
}