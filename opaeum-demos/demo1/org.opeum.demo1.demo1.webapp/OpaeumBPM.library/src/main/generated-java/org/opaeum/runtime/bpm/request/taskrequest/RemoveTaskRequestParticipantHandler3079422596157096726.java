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

public class RemoveTaskRequestParticipantHandler3079422596157096726 implements ICallEventHandler {
	private Date firstOccurrenceScheduledFor;
	private boolean isEvent;
	private TaskParticipationKind kind;
	private Participant participant;

	/** Constructor for RemoveTaskRequestParticipantHandler3079422596157096726
	 * 
	 * @param participant 
	 * @param kind 
	 * @param isEvent 
	 */
	public RemoveTaskRequestParticipantHandler3079422596157096726(Participant participant, TaskParticipationKind kind, boolean isEvent) {
		this.firstOccurrenceScheduledFor=new Date(System.currentTimeMillis()+1000);
		setParticipant(participant);
		setKind(kind);
		this.isEvent=isEvent;
	}
	
	/** Default constructor for RemoveTaskRequestParticipantHandler3079422596157096726
	 */
	public RemoveTaskRequestParticipantHandler3079422596157096726() {
	}

	public int getConsumerPoolSize() {
		return 5;
	}
	
	public Date getFirstOccurrenceScheduledFor() {
		return this.firstOccurrenceScheduledFor;
	}
	
	public String getHandlerUuid() {
		return "252060@_wuzAoI6SEeCrtavWRHwoHg";
	}
	
	public boolean getIsEvent() {
		return this.isEvent;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=4755164557368116669l,uuid="252060@_wuzAoo6SEeCrtavWRHwoHg")
	public TaskParticipationKind getKind() {
		return this.kind;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=6224957730936735057l,uuid="252060@_wuzAoY6SEeCrtavWRHwoHg")
	public Participant getParticipant() {
		return this.participant;
	}
	
	public String getQueueName() {
		return "OpaeumLibraryForBPM::request::TaskRequest::removeTaskRequestParticipant";
	}
	
	public boolean handleOn(Object t) {
		TaskRequest target = (TaskRequest)t;
		if ( isEvent ) {
			return target.consumeRemoveTaskRequestParticipantOccurrence(getParticipant(),getKind());
		} else {
			target.removeTaskRequestParticipant(getParticipant(),getKind());
			return true;
		}
	}
	
	public Collection<PropertyValue> marshall() {
		Collection<PropertyValue> result = new ArrayList<PropertyValue>();
		result.add(new PropertyValue(7590474051790031114l, Value.valueOf(this.getParticipant())));
		result.add(new PropertyValue(-6l, Value.valueOf(isEvent)));
		result.add(new PropertyValue(6120680878221412726l, Value.valueOf(this.getKind())));
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
	
	public void setParticipant(Participant participant) {
		this.participant=participant;
	}
	
	public void unmarshall(Collection<PropertyValue> ps, AbstractPersistence persistence) {
		for ( PropertyValue p : ps ) {
			if ( p.getId()==7590474051790031114l ) {
				this.setParticipant((Participant)Value.valueOf(p.getValue(),persistence));
			} else {
				if ( p.getId()==6120680878221412726l ) {
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