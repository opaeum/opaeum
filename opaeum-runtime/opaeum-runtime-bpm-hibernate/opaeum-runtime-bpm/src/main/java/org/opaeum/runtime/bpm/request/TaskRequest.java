package org.opaeum.runtime.bpm.request;

import java.io.Serializable;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.Table;

import org.hibernate.annotations.AccessType;
import org.hibernate.annotations.Filter;
import org.opaeum.annotation.NumlMetaInfo;
import org.opaeum.annotation.ParameterMetaInfo;
import org.opaeum.runtime.bpm.organization.IBusinessRole;
import org.opaeum.runtime.bpm.organization.Participant;
import org.opaeum.runtime.bpm.requestobject.ITaskObject;
import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.runtime.domain.HibernateEntity;
import org.opaeum.runtime.domain.IEventGenerator;
import org.opaeum.runtime.domain.IPersistentObject;
import org.opaeum.runtime.statemachines.IStateMachineExecution;

@NumlMetaInfo(uuid = "252060@_zFmsEIoVEeCLqpffVZYAlw")
@Filter(name = "noDeletedObjects")
@org.hibernate.annotations.Entity(dynamicUpdate = true)
@AccessType("field")
@Table(name = "task_request")
@Inheritance(strategy = javax.persistence.InheritanceType.JOINED)
@Entity(name = "TaskRequest")
@DiscriminatorValue("task_request")
@DiscriminatorColumn(discriminatorType = javax.persistence.DiscriminatorType.STRING,name = "type_descriminator")
public class TaskRequest extends TaskRequestGenerated implements IStateMachineExecution,IPersistentObject,IEventGenerator,HibernateEntity,CompositionNode,
		Serializable{
	private static final long serialVersionUID = -1262025779559701941L;
	public TaskRequest(ITaskObject owningObject){
		init(owningObject);
		addToOwningObject();
	}
	public TaskRequest(){
	}
	@NumlMetaInfo(uuid = "252060@_v52VoI6SEeCrtavWRHwoHg")
	public void addTaskRequestParticipant(@ParameterMetaInfo(name = "newParticipant",opaeumId = 5015842494571072006l,uuid = "252060@_v52VoY6SEeCrtavWRHwoHg")
	Participant newParticipant,@ParameterMetaInfo(name = "kind",opaeumId = 3546049321002453618l,uuid = "252060@_v52Voo6SEeCrtavWRHwoHg")
	TaskParticipationKind kind){
		super.addTaskRequestParticipant(newParticipant, kind);
		ParticipationInTask result = new ParticipationInTask();
		this.addToParticipationInTask(result);
		result.setParticipant(newParticipant);
		result.setKind(kind);
	}
	@NumlMetaInfo(uuid = "252060@_0lAQAIoaEeCPduia_-NbFw")
	public void delegate(@ParameterMetaInfo(name = "delegate",opaeumId = 8205705053048523991l,uuid = "252060@_TsfTcJTyEeChgI0v02SJHQ")
	IBusinessRole delegate){
		super.delegate(delegate);
		Participant currentRole = null;
		if((this.getOwner() == null)){
			this.addTaskRequestParticipant(delegate, TaskParticipationKind.OWNER);
			ITaskObject tgtOnDelegated = this.getTaskObject();
			tgtOnDelegated.onDelegated(currentRole, delegate);
		}else{
			if((!(this.getOwner() == null))){
				this.removeTaskRequestParticipant(delegate, TaskParticipationKind.OWNER);
				this.addTaskRequestParticipant(delegate, TaskParticipationKind.OWNER);
				ITaskObject tgtOnDelegated = this.getTaskObject();
				tgtOnDelegated.onDelegated(currentRole, delegate);
			}
		}
	}
}