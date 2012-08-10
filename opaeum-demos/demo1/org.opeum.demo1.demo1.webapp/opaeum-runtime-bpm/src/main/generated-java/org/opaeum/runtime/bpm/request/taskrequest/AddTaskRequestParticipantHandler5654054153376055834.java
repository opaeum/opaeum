package org.opaeum.runtime.bpm.request.taskrequest;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.opaeum.annotation.PropertyMetaInfo;
import org.opaeum.runtime.bpm.organization.Participant;
import org.opaeum.runtime.bpm.request.TaskParticipationKind;
import org.opaeum.runtime.bpm.request.TaskRequest;
import org.opaeum.runtime.environment.marshall.PropertyValue;
import org.opaeum.runtime.environment.marshall.Value;
import org.opaeum.runtime.event.ICallEventHandler;
import org.opaeum.runtime.persistence.AbstractPersistence;

public class AddTaskRequestParticipantHandler5654054153376055834 implements ICallEventHandler {
	private Date firstOccurrenceScheduledFor;
	private boolean isEvent;
	private TaskParticipationKind kind;
	private Participant newParticipant;

	/** Constructor for AddTaskRequestParticipantHandler5654054153376055834
	 * 
	 * @param newParticipant 
	 * @param kind 
	 * @param isEvent 
	 */
	public AddTaskRequestParticipantHandler5654054153376055834(Participant newParticipant, TaskParticipationKind kind, boolean isEvent) {
		this.firstOccurrenceScheduledFor=new Date(System.currentTimeMillis()+1000);
		setNewParticipant(newParticipant);
		setKind(kind);
		this.isEvent=isEvent;
	}
	
	/** Default constructor for AddTaskRequestParticipantHandler5654054153376055834
	 */
	public AddTaskRequestParticipantHandler5654054153376055834() {
	}

	public int getConsumerPoolSize() {
		return 5;
	}
	
	public Date getFirstOccurrenceScheduledFor() {
		return this.firstOccurrenceScheduledFor;
	}
	
	public String getHandlerUuid() {
		return "252060@_v52VoI6SEeCrtavWRHwoHg";
	}
	
	public boolean getIsEvent() {
		return this.isEvent;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=5206778049333870195l,uuid="252060@_v52Voo6SEeCrtavWRHwoHg")
	public TaskParticipationKind getKind() {
		return this.kind;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=4577333692529719267l,uuid="252060@_v52VoY6SEeCrtavWRHwoHg")
	public Participant getNewParticipant() {
		return this.newParticipant;
	}
	
	public String getQueueName() {
		return "OpaeumLibraryForBPM::request::TaskRequest::addTaskRequestParticipant";
	}
	
	public boolean handleOn(Object t) {
		TaskRequest target = (TaskRequest)t;
		if ( isEvent ) {
			return target.consumeAddTaskRequestParticipantOccurrence(getNewParticipant(),getKind());
		} else {
			target.addTaskRequestParticipant(getNewParticipant(),getKind());
			return true;
		}
	}
	
	public Collection<PropertyValue> marshall() {
		Collection<PropertyValue> result = new ArrayList<PropertyValue>();
		result.add(new PropertyValue(5015842494571072006l, Value.valueOf(this.getNewParticipant())));
		result.add(new PropertyValue(-6l, Value.valueOf(isEvent)));
		result.add(new PropertyValue(3546049321002453618l, Value.valueOf(this.getKind())));
		return result;
	}
	
	public Date scheduleNextOccurrence() {
		return new Date(System.currentTimeMillis() + 1000*60*60*24*10);
	}
	
	public void setIsEvent(boolean isEvent) {
		this.isEvent=isEvent;
	}
	
	public void setKind(TaskParticipationKind kind) {
		this.kind=kind;
	}
	
	public void setNewParticipant(Participant newParticipant) {
		this.newParticipant=newParticipant;
	}
	
	public void unmarshall(Collection<PropertyValue> ps, AbstractPersistence persistence) {
		for ( PropertyValue p : ps ) {
			if ( p.getId()==5015842494571072006l ) {
				this.setNewParticipant((Participant)Value.valueOf(p.getValue(),persistence));
			} else {
				if ( p.getId()==3546049321002453618l ) {
					this.setKind((TaskParticipationKind)Value.valueOf(p.getValue(),persistence));
				} else {
				
				}
			}
			if ( p.getId()==-6l ) {
				this.isEvent=(Boolean)Value.valueOf(p.getValue(),persistence);
			}
		}
	}

}