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

public class OnDelegatedHandler6478136403880983034 implements ICallEventHandler {
	private IParticipant delegatedBy;
	private IParticipant delegatedTo;
	private Date firstOccurrenceScheduledFor;
	private boolean isEvent;

	/** Constructor for OnDelegatedHandler6478136403880983034
	 * 
	 * @param delegatedBy 
	 * @param delegatedTo 
	 * @param isEvent 
	 */
	public OnDelegatedHandler6478136403880983034(IParticipant delegatedBy, IParticipant delegatedTo, boolean isEvent) {
		this.firstOccurrenceScheduledFor=new Date(System.currentTimeMillis()+1000);
		setDelegatedBy(delegatedBy);
		setDelegatedTo(delegatedTo);
		this.isEvent=isEvent;
	}
	
	/** Default constructor for OnDelegatedHandler6478136403880983034
	 */
	public OnDelegatedHandler6478136403880983034() {
	}

	public int getConsumerPoolSize() {
		return 5;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=1270088593242523029l,uuid="252060@_Fe698K0OEeCK48ywUpk_rg")
	public IParticipant getDelegatedBy() {
		return this.delegatedBy;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=6809713964567240780l,uuid="252060@_GAGYYK0OEeCK48ywUpk_rg")
	public IParticipant getDelegatedTo() {
		return this.delegatedTo;
	}
	
	public Date getFirstOccurrenceScheduledFor() {
		return this.firstOccurrenceScheduledFor;
	}
	
	public String getHandlerUuid() {
		return "252060@_EE4B0K0OEeCK48ywUpk_rg";
	}
	
	public String getQueueName() {
		return "OpaeumLibraryForBPM::requestobject::ITaskObject::onDelegated";
	}
	
	public boolean handleOn(Object t, AbstractPersistence persistence) {
		ITaskObject target = (ITaskObject)t;
		if ( isEvent ) {
			return target.consumeOnDelegatedOccurrence(getDelegatedBy(),getDelegatedTo());
		} else {
			target.onDelegated(getDelegatedBy(),getDelegatedTo());
			return true;
		}
	}
	
	public boolean isIsEvent() {
		return this.isEvent;
	}
	
	public Collection<PropertyValue> marshall(Environment env) {
		Collection result = new ArrayList<PropertyValue>();
		result.add(new PropertyValue(2937187766882494462l, Value.valueOf(this.getDelegatedBy(),env)));
		result.add(new PropertyValue(-6l, Value.valueOf(isEvent,env)));
		result.add(new PropertyValue(9102602523982706512l, Value.valueOf(this.getDelegatedTo(),env)));
		return result;
	}
	
	public Date scheduleNextOccurrence(Object object) {
		return new Date(System.currentTimeMillis() + 1000*60*60*24*10);
	}
	
	public void setDelegatedBy(IParticipant delegatedBy) {
		this.delegatedBy=delegatedBy;
	}
	
	public void setDelegatedTo(IParticipant delegatedTo) {
		this.delegatedTo=delegatedTo;
	}
	
	public void setIsEvent(boolean isEvent) {
		this.isEvent=isEvent;
	}
	
	public void unmarshall(Collection<PropertyValue> ps, AbstractPersistence persistence) {
		for ( PropertyValue p : ps ) {
			if ( p.getId()==2937187766882494462l ) {
				this.setDelegatedBy((IParticipant)Value.valueOf(p.getValue(),(InternalHibernatePersistence)persistence));
			} else {
				if ( p.getId()==9102602523982706512l ) {
					this.setDelegatedTo((IParticipant)Value.valueOf(p.getValue(),(InternalHibernatePersistence)persistence));
				} else {
				
				}
			}
			if ( p.getId()==-6l ) {
				this.isEvent=(Boolean)Value.valueOf(p.getValue(),(InternalHibernatePersistence)persistence);
			}
		}
	}

}