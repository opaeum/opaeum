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

public class AddTaskRequestParticipantHandler741 implements IEventHandler {
	private Participant newParticipant;
	private TaskParticipationKind kind;
	private boolean isEvent;
	private Date firstOccurrenceScheduledFor;

	/** Default constructor for AddTaskRequestParticipantHandler741
	 */
	public AddTaskRequestParticipantHandler741() {
	}
	
	/** Constructor for AddTaskRequestParticipantHandler741
	 * 
	 * @param newParticipant 
	 * @param kind 
	 * @param isEvent 
	 */
	public AddTaskRequestParticipantHandler741(Participant newParticipant, TaskParticipationKind kind, boolean isEvent) {
		this.firstOccurrenceScheduledFor=new Date(System.currentTimeMillis()+1000);
		setNewParticipant(newParticipant);
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
		return "OpiumBPM.library.uml@_v52VoI6SEeCrtavWRHwoHg";
	}
	
	public boolean getIsEvent() {
		return this.isEvent;
	}
	
	public TaskParticipationKind getKind() {
		return this.kind;
	}
	
	public Participant getNewParticipant() {
		return this.newParticipant;
	}
	
	public String getQueueName() {
		return "OpiumLibraryForBPM::TaskRequest::addTaskRequestParticipant";
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
		result.add(new PropertyValue(743, Value.valueOf(this.getNewParticipant())));
		result.add(new PropertyValue(742, Value.valueOf(this.getKind())));
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
	
	public void setNewParticipant(Participant newParticipant) {
		this.newParticipant=newParticipant;
	}
	
	public void unmarshall(Collection<PropertyValue> ps, AbstractPersistence persistence) {
		for ( PropertyValue p : ps ) {
			switch ( p.getId() ) {
				case -6:
					this.isEvent=(Boolean)Value.valueOf(p.getValue(),persistence);
				break;
			
				case 742:
					this.setKind((TaskParticipationKind)Value.valueOf(p.getValue(),persistence));
				break;
			
				case 743:
					this.setNewParticipant((Participant)Value.valueOf(p.getValue(),persistence));
				break;
			
			}
		
		}
	}

}