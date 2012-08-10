package org.opaeum.runtime.bpm.request.taskrequest;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.opaeum.annotation.PropertyMetaInfo;
import org.opaeum.runtime.bpm.organization.IBusinessRole;
import org.opaeum.runtime.bpm.request.TaskRequest;
import org.opaeum.runtime.environment.marshall.PropertyValue;
import org.opaeum.runtime.environment.marshall.Value;
import org.opaeum.runtime.event.ICallEventHandler;
import org.opaeum.runtime.persistence.AbstractPersistence;

public class ForwardHandler7251280809563715157 implements ICallEventHandler {
	private Date firstOccurrenceScheduledFor;
	private boolean isEvent;
	private IBusinessRole toPerson;

	/** Constructor for ForwardHandler7251280809563715157
	 * 
	 * @param toPerson 
	 * @param isEvent 
	 */
	public ForwardHandler7251280809563715157(IBusinessRole toPerson, boolean isEvent) {
		this.firstOccurrenceScheduledFor=new Date(System.currentTimeMillis()+1000);
		setToPerson(toPerson);
		this.isEvent=isEvent;
	}
	
	/** Default constructor for ForwardHandler7251280809563715157
	 */
	public ForwardHandler7251280809563715157() {
	}

	public int getConsumerPoolSize() {
		return 5;
	}
	
	public Date getFirstOccurrenceScheduledFor() {
		return this.firstOccurrenceScheduledFor;
	}
	
	public String getHandlerUuid() {
		return "252060@__6uyIIoaEeCPduia_-NbFw";
	}
	
	public boolean getIsEvent() {
		return this.isEvent;
	}
	
	public String getQueueName() {
		return "OpaeumLibraryForBPM::request::TaskRequest::forward";
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=8021916698648036746l,uuid="252060@_kN7FcJTyEeChgI0v02SJHQ")
	public IBusinessRole getToPerson() {
		return this.toPerson;
	}
	
	public boolean handleOn(Object t) {
		TaskRequest target = (TaskRequest)t;
		if ( isEvent ) {
			return target.consumeForwardOccurrence(getToPerson());
		} else {
			target.forward(getToPerson());
			return true;
		}
	}
	
	public Collection<PropertyValue> marshall() {
		Collection<PropertyValue> result = new ArrayList<PropertyValue>();
		result.add(new PropertyValue(-6l, Value.valueOf(isEvent)));
		result.add(new PropertyValue(3350895467208403091l, Value.valueOf(this.getToPerson())));
		return result;
	}
	
	public Date scheduleNextOccurrence() {
		return new Date(System.currentTimeMillis() + 1000*60*60*24*10);
	}
	
	public void setIsEvent(boolean isEvent) {
		this.isEvent=isEvent;
	}
	
	public void setToPerson(IBusinessRole toPerson) {
		this.toPerson=toPerson;
	}
	
	public void unmarshall(Collection<PropertyValue> ps, AbstractPersistence persistence) {
		for ( PropertyValue p : ps ) {
			if ( p.getId()==3350895467208403091l ) {
				this.setToPerson((IBusinessRole)Value.valueOf(p.getValue(),persistence));
			} else {
			
			}
			if ( p.getId()==-6l ) {
				this.isEvent=(Boolean)Value.valueOf(p.getValue(),persistence);
			}
		}
	}

}