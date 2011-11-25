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

public class AddTaskRequestParticipantHandler259 implements ICallEventHandler {
	private TaskParticipationKind _kind;
	private boolean isEvent;
	private Participant _newParticipant;
	private Date firstOccurrenceScheduledFor;

	/** Default constructor for AddTaskRequestParticipantHandler259
	 */
	public AddTaskRequestParticipantHandler259() {
	}
	
	/** Constructor for AddTaskRequestParticipantHandler259
	 * 
	 * @param _newParticipant 
	 * @param _kind 
	 * @param isEvent 
	 */
	public AddTaskRequestParticipantHandler259(Participant _newParticipant, TaskParticipationKind _kind, boolean isEvent) {
		this.firstOccurrenceScheduledFor=new Date(System.currentTimeMillis()+1000);
		setNewParticipant(_newParticipant);
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
		return "252060@_v52VoI6SEeCrtavWRHwoHg";
	}
	
	public boolean getIsEvent() {
		return this.isEvent;
	}
	
	public TaskParticipationKind getKind() {
		return this._kind;
	}
	
	public Participant getNewParticipant() {
		return this._newParticipant;
	}
	
	public String getQueueName() {
		return "OpaeumLibraryForBPM::TaskRequest::addTaskRequestParticipant";
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
		result.add(new PropertyValue(260, Value.valueOf(this.getNewParticipant())));
		result.add(new PropertyValue(261, Value.valueOf(this.getKind())));
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
	
	public void setNewParticipant(Participant _newParticipant) {
		this._newParticipant=_newParticipant;
	}
	
	public void unmarshall(Collection<PropertyValue> ps, AbstractPersistence persistence) {
		for ( PropertyValue p : ps ) {
			switch ( p.getId() ) {
				case -6:
					this.isEvent=(Boolean)Value.valueOf(p.getValue(),persistence);
				break;
			
				case 261:
					this.setKind((TaskParticipationKind)Value.valueOf(p.getValue(),persistence));
				break;
			
				case 260:
					this.setNewParticipant((Participant)Value.valueOf(p.getValue(),persistence));
				break;
			
			}
		
		}
	}

}