package org.opaeum.runtime.bpm.requestobject.iprocessobject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.opaeum.annotation.PropertyMetaInfo;
import org.opaeum.hibernate.domain.InternalHibernatePersistence;
import org.opaeum.runtime.bpm.organization.IBusinessRole;
import org.opaeum.runtime.bpm.requestobject.IProcessObject;
import org.opaeum.runtime.environment.Environment;
import org.opaeum.runtime.environment.marshall.PropertyValue;
import org.opaeum.runtime.environment.marshall.Value;
import org.opaeum.runtime.event.ICallEventHandler;
import org.opaeum.runtime.persistence.AbstractPersistence;

public class OnCompletedHandler267318476342637340 implements ICallEventHandler {
	private IBusinessRole completedBy;
	private Date firstOccurrenceScheduledFor;
	private boolean isEvent;

	/** Constructor for OnCompletedHandler267318476342637340
	 * 
	 * @param completedBy 
	 * @param isEvent 
	 */
	public OnCompletedHandler267318476342637340(IBusinessRole completedBy, boolean isEvent) {
		this.firstOccurrenceScheduledFor=new Date(System.currentTimeMillis()+1000);
		setCompletedBy(completedBy);
		this.isEvent=isEvent;
	}
	
	/** Default constructor for OnCompletedHandler267318476342637340
	 */
	public OnCompletedHandler267318476342637340() {
	}

	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=1768312763986569573l,uuid="252060@_yDSYZ0uBEeGElKTCe2jfDw")
	public IBusinessRole getCompletedBy() {
		return this.completedBy;
	}
	
	public int getConsumerPoolSize() {
		return 5;
	}
	
	public Date getFirstOccurrenceScheduledFor() {
		return this.firstOccurrenceScheduledFor;
	}
	
	public String getHandlerUuid() {
		return "252060@_yDSYYEuBEeGElKTCe2jfDw";
	}
	
	public String getQueueName() {
		return "OpaeumLibraryForBPM::requestobject::IProcessObject::onCompleted";
	}
	
	public boolean handleOn(Object t, AbstractPersistence persistence) {
		IProcessObject target = (IProcessObject)t;
		if ( isEvent ) {
			return target.consumeOnCompletedOccurrence(getCompletedBy());
		} else {
			target.onCompleted(getCompletedBy());
			return true;
		}
	}
	
	public boolean isIsEvent() {
		return this.isEvent;
	}
	
	public Collection<PropertyValue> marshall(Environment env) {
		Collection result = new ArrayList<PropertyValue>();
		result.add(new PropertyValue(-6l, Value.valueOf(isEvent,env)));
		result.add(new PropertyValue(4095523919410592856l, Value.valueOf(this.getCompletedBy(),env)));
		return result;
	}
	
	public Date scheduleNextOccurrence(Object object) {
		return new Date(System.currentTimeMillis() + 1000*60*60*24*10);
	}
	
	public void setCompletedBy(IBusinessRole completedBy) {
		this.completedBy=completedBy;
	}
	
	public void setIsEvent(boolean isEvent) {
		this.isEvent=isEvent;
	}
	
	public void unmarshall(Collection<PropertyValue> ps, AbstractPersistence persistence) {
		for ( PropertyValue p : ps ) {
			if ( p.getId()==4095523919410592856l ) {
				this.setCompletedBy((IBusinessRole)Value.valueOf(p.getValue(),(InternalHibernatePersistence)persistence));
			} else {
			
			}
			if ( p.getId()==-6l ) {
				this.isEvent=(Boolean)Value.valueOf(p.getValue(),(InternalHibernatePersistence)persistence);
			}
		}
	}

}