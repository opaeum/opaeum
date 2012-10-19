package org.opaeum.runtime.bpm.request.abstractrequest;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.opaeum.annotation.PropertyMetaInfo;
import org.opaeum.runtime.bpm.organization.Participant;
import org.opaeum.runtime.bpm.request.AbstractRequest;
import org.opaeum.runtime.bpm.request.RequestParticipationKind;
import org.opaeum.runtime.environment.marshall.PropertyValue;
import org.opaeum.runtime.environment.marshall.Value;
import org.opaeum.runtime.event.ICallEventHandler;
import org.opaeum.runtime.persistence.AbstractPersistence;

public class RemoveRequestParticipantHandler4679685790909812900 implements ICallEventHandler {
	private Date firstOccurrenceScheduledFor;
	private boolean isEvent;
	private RequestParticipationKind kind;
	private Participant participant;

	/** Constructor for RemoveRequestParticipantHandler4679685790909812900
	 * 
	 * @param participant 
	 * @param kind 
	 * @param isEvent 
	 */
	public RemoveRequestParticipantHandler4679685790909812900(Participant participant, RequestParticipationKind kind, boolean isEvent) {
		this.firstOccurrenceScheduledFor=new Date(System.currentTimeMillis()+1000);
		setParticipant(participant);
		setKind(kind);
		this.isEvent=isEvent;
	}
	
	/** Default constructor for RemoveRequestParticipantHandler4679685790909812900
	 */
	public RemoveRequestParticipantHandler4679685790909812900() {
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
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=8968399258929852618l,uuid="252060@_P8scgI6SEeCrtavWRHwoHg")
	public RequestParticipationKind getKind() {
		return this.kind;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=8015810361586611792l,uuid="252060@_P68JAI6SEeCrtavWRHwoHg")
	public Participant getParticipant() {
		return this.participant;
	}
	
	public String getQueueName() {
		return "OpaeumLibraryForBPM::request::AbstractRequest::removeRequestParticipant";
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
		result.add(new PropertyValue(5904449775692844716l, Value.valueOf(this.getParticipant())));
		result.add(new PropertyValue(-6l, Value.valueOf(isEvent)));
		result.add(new PropertyValue(5058842751504084224l, Value.valueOf(this.getKind())));
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
	
	public void setParticipant(Participant participant) {
		this.participant=participant;
	}
	
	public void unmarshall(Collection<PropertyValue> ps, AbstractPersistence persistence) {
		for ( PropertyValue p : ps ) {
			if ( p.getId()==5904449775692844716l ) {
				this.setParticipant((Participant)Value.valueOf(p.getValue(),persistence));
			} else {
				if ( p.getId()==5058842751504084224l ) {
					this.setKind((RequestParticipationKind)Value.valueOf(p.getValue(),persistence));
				} else {
				
				}
			}
			if ( p.getId()==-6l ) {
				this.isEvent=(Boolean)Value.valueOf(p.getValue(),persistence);
			}
		}
	}

}