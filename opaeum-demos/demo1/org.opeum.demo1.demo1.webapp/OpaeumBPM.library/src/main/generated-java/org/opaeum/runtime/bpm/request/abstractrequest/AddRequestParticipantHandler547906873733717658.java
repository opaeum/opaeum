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

public class AddRequestParticipantHandler547906873733717658 implements ICallEventHandler {
	private Date firstOccurrenceScheduledFor;
	private boolean isEvent;
	private RequestParticipationKind kind;
	private Participant newParticipant;

	/** Constructor for AddRequestParticipantHandler547906873733717658
	 * 
	 * @param newParticipant 
	 * @param kind 
	 * @param isEvent 
	 */
	public AddRequestParticipantHandler547906873733717658(Participant newParticipant, RequestParticipationKind kind, boolean isEvent) {
		this.firstOccurrenceScheduledFor=new Date(System.currentTimeMillis()+1000);
		setNewParticipant(newParticipant);
		setKind(kind);
		this.isEvent=isEvent;
	}
	
	/** Default constructor for AddRequestParticipantHandler547906873733717658
	 */
	public AddRequestParticipantHandler547906873733717658() {
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
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=2860115282787638650l,uuid="252060@_TsHmgI6QEeCrtavWRHwoHg")
	public RequestParticipationKind getKind() {
		return this.kind;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=7770024505365574114l,uuid="252060@_TKUhAI6QEeCrtavWRHwoHg")
	public Participant getNewParticipant() {
		return this.newParticipant;
	}
	
	public String getQueueName() {
		return "OpaeumLibraryForBPM::request::AbstractRequest::addRequestParticipant";
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
		result.add(new PropertyValue(4174829400602250316l, Value.valueOf(this.getNewParticipant())));
		result.add(new PropertyValue(-6l, Value.valueOf(isEvent)));
		result.add(new PropertyValue(9084738623180185780l, Value.valueOf(this.getKind())));
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
			if ( p.getId()==4174829400602250316l ) {
				this.setNewParticipant((Participant)Value.valueOf(p.getValue(),persistence));
			} else {
				if ( p.getId()==9084738623180185780l ) {
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