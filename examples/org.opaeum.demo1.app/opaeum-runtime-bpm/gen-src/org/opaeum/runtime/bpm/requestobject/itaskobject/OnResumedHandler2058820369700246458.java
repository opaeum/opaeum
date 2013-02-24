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

public class OnResumedHandler2058820369700246458 implements ICallEventHandler {
	private Date firstOccurrenceScheduledFor;
	private boolean isEvent;
	private IParticipant resumedBy;

	/** Constructor for OnResumedHandler2058820369700246458
	 * 
	 * @param resumedBy 
	 * @param isEvent 
	 */
	public OnResumedHandler2058820369700246458(IParticipant resumedBy, boolean isEvent) {
		this.firstOccurrenceScheduledFor=new Date(System.currentTimeMillis()+1000);
		setResumedBy(resumedBy);
		this.isEvent=isEvent;
	}
	
	/** Default constructor for OnResumedHandler2058820369700246458
	 */
	public OnResumedHandler2058820369700246458() {
	}

	public int getConsumerPoolSize() {
		return 5;
	}
	
	public Date getFirstOccurrenceScheduledFor() {
		return this.firstOccurrenceScheduledFor;
	}
	
	public String getHandlerUuid() {
		return "252060@_8ba9IK0NEeCK48ywUpk_rg";
	}
	
	public String getQueueName() {
		return "OpaeumLibraryForBPM::requestobject::ITaskObject::onResumed";
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=3957506224988779019l,uuid="252060@_9Y_YoK0NEeCK48ywUpk_rg")
	public IParticipant getResumedBy() {
		return this.resumedBy;
	}
	
	public boolean handleOn(Object t, AbstractPersistence persistence) {
		ITaskObject target = (ITaskObject)t;
		if ( isEvent ) {
			return target.consumeOnResumedOccurrence(getResumedBy());
		} else {
			target.onResumed(getResumedBy());
			return true;
		}
	}
	
	public boolean isIsEvent() {
		return this.isEvent;
	}
	
	public Collection<PropertyValue> marshall(Environment env) {
		Collection result = new ArrayList<PropertyValue>();
		result.add(new PropertyValue(-6l, Value.valueOf(isEvent,env)));
		result.add(new PropertyValue(4926573096485933090l, Value.valueOf(this.getResumedBy(),env)));
		return result;
	}
	
	public Date scheduleNextOccurrence(Object object) {
		return new Date(System.currentTimeMillis() + 1000*60*60*24*10);
	}
	
	public void setIsEvent(boolean isEvent) {
		this.isEvent=isEvent;
	}
	
	public void setResumedBy(IParticipant resumedBy) {
		this.resumedBy=resumedBy;
	}
	
	public void unmarshall(Collection<PropertyValue> ps, AbstractPersistence persistence) {
		for ( PropertyValue p : ps ) {
			if ( p.getId()==4926573096485933090l ) {
				this.setResumedBy((IParticipant)Value.valueOf(p.getValue(),(InternalHibernatePersistence)persistence));
			} else {
			
			}
			if ( p.getId()==-6l ) {
				this.isEvent=(Boolean)Value.valueOf(p.getValue(),(InternalHibernatePersistence)persistence);
			}
		}
	}

}