package org.nakeduml.runtime.bpm.taskrequest;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.nakeduml.runtime.bpm.Participant;
import org.nakeduml.runtime.bpm.TaskParticipationKind;
import org.nakeduml.runtime.bpm.TaskRequest;
import org.nakeduml.runtime.environment.marshall.PropertyValue;
import org.nakeduml.runtime.environment.marshall.Value;
import org.nakeduml.runtime.event.IEventHandler;
import org.nakeduml.runtime.persistence.AbstractPersistence;

public class RemoveTaskRequestParticipantHandler734 implements IEventHandler {
	private Participant participant;
	private TaskParticipationKind kind;
	private boolean isEvent;
	private Date firstOccurrenceScheduledFor;

	/** Default constructor for RemoveTaskRequestParticipantHandler734
	 */
	public RemoveTaskRequestParticipantHandler734() {
	}
	
	/** Constructor for RemoveTaskRequestParticipantHandler734
	 * 
	 * @param participant 
	 * @param kind 
	 * @param isEvent 
	 */
	public RemoveTaskRequestParticipantHandler734(Participant participant, TaskParticipationKind kind, boolean isEvent) {
		this.firstOccurrenceScheduledFor=new Date(System.currentTimeMillis()+1000);
		setParticipant(participant);
		setKind(kind);
		this.isEvent=isEvent;
	}

	public int getConsumerPoolSize() {
		return 5;
	}
	
	public Date getFirstOccurrenceScheduledFor() {
		return this.firstOccurrenceScheduledFor;
	}
	
	public String getHandlerUuid() {
		return "OpiumBPM.library.uml@_wuzAoI6SEeCrtavWRHwoHg";
	}
	
	public boolean getIsEvent() {
		return this.isEvent;
	}
	
	public TaskParticipationKind getKind() {
		return this.kind;
	}
	
	public Participant getParticipant() {
		return this.participant;
	}
	
	public String getQueueName() {
		return "OpiumLibraryForBPM::TaskRequest::removeTaskRequestParticipant";
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
		result.add(new PropertyValue(735, Value.valueOf(this.getParticipant())));
		result.add(new PropertyValue(736, Value.valueOf(this.getKind())));
		result.add(new PropertyValue(-6, Value.valueOf(isEvent)));
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
			switch ( p.getId() ) {
				case -6:
					this.isEvent=(Boolean)Value.valueOf(p.getValue(),persistence);
				break;
			
				case 736:
					this.setKind((TaskParticipationKind)Value.valueOf(p.getValue(),persistence));
				break;
			
				case 735:
					this.setParticipant((Participant)Value.valueOf(p.getValue(),persistence));
				break;
			
			}
		
		}
	}

}