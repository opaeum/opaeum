package org.opaeum.runtime.bpm.abstractrequest;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.opaeum.runtime.bpm.AbstractRequest;
import org.opaeum.runtime.bpm.Participant;
import org.opaeum.runtime.bpm.RequestParticipationKind;
import org.opaeum.runtime.environment.marshall.PropertyValue;
import org.opaeum.runtime.environment.marshall.Value;
import org.opaeum.runtime.event.ICallEventHandler;
import org.opaeum.runtime.persistence.AbstractPersistence;

public class AddRequestParticipantHandler210 implements ICallEventHandler {
	private RequestParticipationKind _kind;
	private boolean isEvent;
	private Participant _newParticipant;
	private Date firstOccurrenceScheduledFor;

	/** Default constructor for AddRequestParticipantHandler210
	 */
	public AddRequestParticipantHandler210() {
	}
	
	/** Constructor for AddRequestParticipantHandler210
	 * 
	 * @param _newParticipant 
	 * @param _kind 
	 * @param isEvent 
	 */
	public AddRequestParticipantHandler210(Participant _newParticipant, RequestParticipationKind _kind, boolean isEvent) {
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
		return "252060@_Qo338I6QEeCrtavWRHwoHg";
	}
	
	public boolean getIsEvent() {
		return this.isEvent;
	}
	
	public RequestParticipationKind getKind() {
		return this._kind;
	}
	
	public Participant getNewParticipant() {
		return this._newParticipant;
	}
	
	public String getQueueName() {
		return "OpaeumLibraryForBPM::AbstractRequest::addRequestParticipant";
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
		result.add(new PropertyValue(212, Value.valueOf(this.getNewParticipant())));
		result.add(new PropertyValue(211, Value.valueOf(this.getKind())));
		result.add(new PropertyValue(-6, Value.valueOf(isEvent)));
		return result;
	}
	
	public Date scheduleNextOccurrence() {
		return new Date(System.currentTimeMillis() + 1000*60*60*24*10);
	}
	
	public void setIsEvent(boolean isEvent) {
		this.isEvent=isEvent;
	}
	
	public void setKind(RequestParticipationKind _kind) {
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
			
				case 211:
					this.setKind((RequestParticipationKind)Value.valueOf(p.getValue(),persistence));
				break;
			
				case 212:
					this.setNewParticipant((Participant)Value.valueOf(p.getValue(),persistence));
				break;
			
			}
		
		}
	}

}