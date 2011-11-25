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

public class RemoveRequestParticipantHandler198 implements ICallEventHandler {
	private RequestParticipationKind _kind;
	private Participant _participant;
	private boolean isEvent;
	private Date firstOccurrenceScheduledFor;

	/** Default constructor for RemoveRequestParticipantHandler198
	 */
	public RemoveRequestParticipantHandler198() {
	}
	
	/** Constructor for RemoveRequestParticipantHandler198
	 * 
	 * @param _participant 
	 * @param _kind 
	 * @param isEvent 
	 */
	public RemoveRequestParticipantHandler198(Participant _participant, RequestParticipationKind _kind, boolean isEvent) {
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
		return "252060@_Nl5kQI6SEeCrtavWRHwoHg";
	}
	
	public boolean getIsEvent() {
		return this.isEvent;
	}
	
	public RequestParticipationKind getKind() {
		return this._kind;
	}
	
	public Participant getParticipant() {
		return this._participant;
	}
	
	public String getQueueName() {
		return "OpaeumLibraryForBPM::AbstractRequest::removeRequestParticipant";
	}
	
	public boolean handleOn(Object t) {
		AbstractRequest target = (AbstractRequest)t;
		if ( isEvent ) {
			return target.consumeRemoveRequestParticipantOccurrence(getParticipant(),getKind());
		} else {
			target.removeRequestParticipant(getParticipant(),getKind());
			return true;
		}
	}
	
	public Collection<PropertyValue> marshall() {
		Collection<PropertyValue> result = new ArrayList<PropertyValue>();
		result.add(new PropertyValue(200, Value.valueOf(this.getParticipant())));
		result.add(new PropertyValue(199, Value.valueOf(this.getKind())));
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
	
	public void setParticipant(Participant _participant) {
		this._participant=_participant;
	}
	
	public void unmarshall(Collection<PropertyValue> ps, AbstractPersistence persistence) {
		for ( PropertyValue p : ps ) {
			switch ( p.getId() ) {
				case -6:
					this.isEvent=(Boolean)Value.valueOf(p.getValue(),persistence);
				break;
			
				case 199:
					this.setKind((RequestParticipationKind)Value.valueOf(p.getValue(),persistence));
				break;
			
				case 200:
					this.setParticipant((Participant)Value.valueOf(p.getValue(),persistence));
				break;
			
			}
		
		}
	}

}