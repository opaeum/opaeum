package org.opaeum.runtime.bpm.requestobject.iprocessobject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.opaeum.annotation.PropertyMetaInfo;
import org.opaeum.hibernate.domain.InternalHibernatePersistence;
import org.opaeum.runtime.bpm.organization.IBusinessRole;
import org.opaeum.runtime.bpm.requestobject.IProcessObject;
import org.opaeum.runtime.environment.Environment;
import org.opaeum.runtime.environment.marshall.PropertyValue;
import org.opaeum.runtime.environment.marshall.Value;
import org.opaeum.runtime.event.ICallEventHandler;
import org.opaeum.runtime.persistence.AbstractPersistence;

public class OnSuspendedHandler7740529359203122672 implements ICallEventHandler {
	private Date firstOccurrenceScheduledFor;
	private boolean isEvent;
	private IBusinessRole suspendedBy;

	/** Constructor for OnSuspendedHandler7740529359203122672
	 * 
	 * @param suspendedBy 
	 * @param isEvent 
	 */
	public OnSuspendedHandler7740529359203122672(IBusinessRole suspendedBy, boolean isEvent) {
		this.firstOccurrenceScheduledFor=new Date(System.currentTimeMillis()+1000);
		setSuspendedBy(suspendedBy);
		this.isEvent=isEvent;
	}
	
	/** Default constructor for OnSuspendedHandler7740529359203122672
	 */
	public OnSuspendedHandler7740529359203122672() {
	}

	public int getConsumerPoolSize() {
		return 5;
	}
	
	public Date getFirstOccurrenceScheduledFor() {
		return this.firstOccurrenceScheduledFor;
	}
	
	public String getHandlerUuid() {
		return "252060@_xCSOYEuBEeGElKTCe2jfDw";
	}
	
	public String getQueueName() {
		return "OpaeumLibraryForBPM::requestobject::IProcessObject::onSuspended";
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=45088604918160901l,uuid="252060@_xCSOZ0uBEeGElKTCe2jfDw")
	public IBusinessRole getSuspendedBy() {
		return this.suspendedBy;
	}
	
	public boolean handleOn(Object t, AbstractPersistence persistence) {
		IProcessObject target = (IProcessObject)t;
		if ( isEvent ) {
			return target.consumeOnSuspendedOccurrence(getSuspendedBy());
		} else {
			target.onSuspended(getSuspendedBy());
			return true;
		}
	}
	
	public boolean isIsEvent() {
		return this.isEvent;
	}
	
	public Collection<PropertyValue> marshall(Environment env) {
		Collection result = new ArrayList<PropertyValue>();
		result.add(new PropertyValue(-6l, Value.valueOf(isEvent,env)));
		result.add(new PropertyValue(3377686963449892476l, Value.valueOf(this.getSuspendedBy(),env)));
		return result;
	}
	
	public Date scheduleNextOccurrence(Object object) {
		return new Date(System.currentTimeMillis() + 1000*60*60*24*10);
	}
	
	public void setIsEvent(boolean isEvent) {
		this.isEvent=isEvent;
	}
	
	public void setSuspendedBy(IBusinessRole suspendedBy) {
		this.suspendedBy=suspendedBy;
	}
	
	public void unmarshall(Collection<PropertyValue> ps, AbstractPersistence persistence) {
		for ( PropertyValue p : ps ) {
			if ( p.getId()==3377686963449892476l ) {
				this.setSuspendedBy((IBusinessRole)Value.valueOf(p.getValue(),(InternalHibernatePersistence)persistence));
			} else {
			
			}
			if ( p.getId()==-6l ) {
				this.isEvent=(Boolean)Value.valueOf(p.getValue(),(InternalHibernatePersistence)persistence);
			}
		}
	}

}