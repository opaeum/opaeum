package org.opaeum.runtime.bpm.taskrequest;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.opaeum.runtime.bpm.Participant;
import org.opaeum.runtime.bpm.TaskParticipationKind;
import org.opaeum.runtime.bpm.TaskRequest;
import org.opaeum.runtime.environment.marshall.PropertyValue;
import org.opaeum.runtime.environment.marshall.Value;
import org.opaeum.runtime.event.ICallEventHandler;
import org.opaeum.runtime.persistence.AbstractPersistence;

public class RemoveTaskRequestParticipantHandler246 implements ICallEventHandler {
	private TaskParticipationKind _kind;
	private Participant _participant;
	private boolean isEvent;
	private Date firstOccurrenceScheduledFor;

	/** Default constructor for RemoveTaskRequestParticipantHandler246
	 */
	public RemoveTaskRequestParticipantHandler246() {
	}
	
	/** Constructor for RemoveTaskRequestParticipantHandler246
	 * 
	 * @param _participant 
	 * @param _kind 
	 * @param isEvent 
	 */
	public RemoveTaskRequestParticipantHandler246(Participant _participant, TaskParticipationKind _kind, boolean isEvent) {
		this.firstOccurrenceScheduledFor=new Date(System.currentTimeMillis()+1000);
		setParticipant(_participant);
		setKind(_kind);
		this.isEvent=isEvent;
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
	
	public TaskParticipationKind getKind() {
		return this._kind;
	}
	
	public Participant getParticipant() {
		return this._participant;
	}
	
	public String getQueueName() {
		return "OpaeumLibraryForBPM::TaskRequest::removeTaskRequestParticipant";
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
		result.add(new PropertyValue(248, Value.valueOf(this.getParticipant())));
		result.add(new PropertyValue(247, Value.valueOf(this.getKind())));
		result.add(new PropertyValue(-6, Value.valueOf(isEvent)));
		return result;
	}
	
	public Date scheduleNextOccurrence() {
		return new Date(System.currentTimeMillis() + 1000*60*60*24*10);
	}
	
	public void setIsEvent(boolean isEvent) {
		this.isEvent=isEvent;
	}
	
	public void setKind(TaskParticipationKind _kind) {
		this._kind=_kind;
	}
	
	public void setParticipant(Participant _participant) {
		this._participant=_participant;
	}
	
	public void unmarshall(Collection<PropertyValue> ps, AbstractPersistence persistence) {
		for ( PropertyValue p : ps ) {
			switch ( p.getId() ) {
				case -6:
					this.isEvent=(Boolean)Value.valueOf(p.getValue(),persistence);
				break;
			
				case 247:
					this.setKind((TaskParticipationKind)Value.valueOf(p.getValue(),persistence));
				break;
			
				case 248:
					this.setParticipant((Participant)Value.valueOf(p.getValue(),persistence));
				break;
			
			}
		
		}
	}

}