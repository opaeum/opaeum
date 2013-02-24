package org.opaeum.runtime.bpm.requestobject.itaskobject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.opaeum.annotation.PropertyMetaInfo;
import org.opaeum.hibernate.domain.InternalHibernatePersistence;
import org.opaeum.runtime.bpm.organization.IParticipant;
import org.opaeum.runtime.bpm.requestobject.ITaskObject;
import org.opaeum.runtime.environment.Environment;
import org.opaeum.runtime.environment.marshall.PropertyValue;
import org.opaeum.runtime.environment.marshall.Value;
import org.opaeum.runtime.event.ICallEventHandler;
import org.opaeum.runtime.persistence.AbstractPersistence;

public class OnSuspendedHandler1581487435816793754 implements ICallEventHandler {
	private Date firstOccurrenceScheduledFor;
	private boolean isEvent;
	private IParticipant suspendedBy;

	/** Constructor for OnSuspendedHandler1581487435816793754
	 * 
	 * @param suspendedBy 
	 * @param isEvent 
	 */
	public OnSuspendedHandler1581487435816793754(IParticipant suspendedBy, boolean isEvent) {
		this.firstOccurrenceScheduledFor=new Date(System.currentTimeMillis()+1000);
		setSuspendedBy(suspendedBy);
		this.isEvent=isEvent;
	}
	
	/** Default constructor for OnSuspendedHandler1581487435816793754
	 */
	public OnSuspendedHandler1581487435816793754() {
	}

	public int getConsumerPoolSize() {
		return 5;
	}
	
	public Date getFirstOccurrenceScheduledFor() {
		return this.firstOccurrenceScheduledFor;
	}
	
	public String getHandlerUuid() {
		return "252060@_ug7_QK0NEeCK48ywUpk_rg";
	}
	
	public String getQueueName() {
		return "OpaeumLibraryForBPM::requestobject::ITaskObject::onSuspended";
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=1462930275972771497l,uuid="252060@_wGUosK0NEeCK48ywUpk_rg")
	public IParticipant getSuspendedBy() {
		return this.suspendedBy;
	}
	
	public boolean handleOn(Object t, AbstractPersistence persistence) {
		ITaskObject target = (ITaskObject)t;
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
		result.add(new PropertyValue(7500393394354827722l, Value.valueOf(this.getSuspendedBy(),env)));
		return result;
	}
	
	public Date scheduleNextOccurrence(Object object) {
		return new Date(System.currentTimeMillis() + 1000*60*60*24*10);
	}
	
	public void setIsEvent(boolean isEvent) {
		this.isEvent=isEvent;
	}
	
	public void setSuspendedBy(IParticipant suspendedBy) {
		this.suspendedBy=suspendedBy;
	}
	
	public void unmarshall(Collection<PropertyValue> ps, AbstractPersistence persistence) {
		for ( PropertyValue p : ps ) {
			if ( p.getId()==7500393394354827722l ) {
				this.setSuspendedBy((IParticipant)Value.valueOf(p.getValue(),(InternalHibernatePersistence)persistence));
			} else {
			
			}
			if ( p.getId()==-6l ) {
				this.isEvent=(Boolean)Value.valueOf(p.getValue(),(InternalHibernatePersistence)persistence);
			}
		}
	}

}