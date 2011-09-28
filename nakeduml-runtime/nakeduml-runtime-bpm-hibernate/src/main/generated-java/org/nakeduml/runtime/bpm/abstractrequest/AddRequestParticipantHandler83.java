package org.nakeduml.runtime.bpm.abstractrequest;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.nakeduml.runtime.bpm.AbstractRequest;
import org.nakeduml.runtime.bpm.Participant;
import org.nakeduml.runtime.bpm.RequestParticipationKind;
import org.nakeduml.runtime.environment.marshall.PropertyValue;
import org.nakeduml.runtime.environment.marshall.Value;
import org.nakeduml.runtime.event.ICallEventHandler;
import org.nakeduml.runtime.persistence.AbstractPersistence;

public class AddRequestParticipantHandler83 implements ICallEventHandler {
	private boolean isEvent;
	private Date firstOccurrenceScheduledFor;
	private Participant newParticipant;
	private RequestParticipationKind kind;

	/** Default constructor for AddRequestParticipantHandler83
	 */
	public AddRequestParticipantHandler83() {
	}
	
	/** Constructor for AddRequestParticipantHandler83
	 * 
	 * @param newParticipant 
	 * @param kind 
	 * @param isEvent 
	 */
	public AddRequestParticipantHandler83(Participant newParticipant, RequestParticipationKind kind, boolean isEvent) {
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
		return "252060@_Qo338I6QEeCrtavWRHwoHg";
	}
	
	public boolean getIsEvent() {
		return this.isEvent;
	}
	
	public RequestParticipationKind getKind() {
		return this.kind;
	}
	
	public Participant getNewParticipant() {
		return this.newParticipant;
	}
	
	public String getQueueName() {
		return "OpiumLibraryForBPM::AbstractRequest::addRequestParticipant";
	}
	
	public boolean handleOn(Object t) {
		AbstractRequest target = (AbstractRequest)t;
		if ( isEvent ) {
			return target.consumeAddRequestParticipantOccurrence(getNewParticipant(),getKind());
		} else {
			target.addRequestParticipant(getNewParticipant(),getKind());
			return true;
		}
	}
	
	public Collection<PropertyValue> marshall() {
		Collection<PropertyValue> result = new ArrayList<PropertyValue>();
		result.add(new PropertyValue(84, Value.valueOf(this.getNewParticipant())));
		result.add(new PropertyValue(85, Value.valueOf(this.getKind())));
		result.add(new PropertyValue(-6, Value.valueOf(isEvent)));
		return result;
	}
	
	public Date scheduleNextOccurrence() {
		return new Date(System.currentTimeMillis() + 1000*60*60*24*10);
	}
	
	public void setIsEvent(boolean isEvent) {
		this.isEvent=isEvent;
	}
	
	public void setKind(RequestParticipationKind kind) {
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
			
				case 85:
					this.setKind((RequestParticipationKind)Value.valueOf(p.getValue(),persistence));
				break;
			
				case 84:
					this.setNewParticipant((Participant)Value.valueOf(p.getValue(),persistence));
				break;
			
			}
		
		}
	}

}