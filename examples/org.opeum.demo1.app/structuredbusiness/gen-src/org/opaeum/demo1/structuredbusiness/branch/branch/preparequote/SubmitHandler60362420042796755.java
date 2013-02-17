package org.opaeum.demo1.structuredbusiness.branch.branch.preparequote;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.opaeum.demo1.structuredbusiness.branch.branch.PrepareQuote;
import org.opaeum.hibernate.domain.InternalHibernatePersistence;
import org.opaeum.runtime.environment.Environment;
import org.opaeum.runtime.environment.marshall.PropertyValue;
import org.opaeum.runtime.environment.marshall.Value;
import org.opaeum.runtime.event.ICallEventHandler;
import org.opaeum.runtime.persistence.AbstractPersistence;

public class SubmitHandler60362420042796755 implements ICallEventHandler {
	private Date firstOccurrenceScheduledFor;
	private boolean isEvent;

	/** Constructor for SubmitHandler60362420042796755
	 * 
	 * @param isEvent 
	 */
	public SubmitHandler60362420042796755(boolean isEvent) {
		this.firstOccurrenceScheduledFor=new Date(System.currentTimeMillis()+1000);
		this.isEvent=isEvent;
	}
	
	/** Default constructor for SubmitHandler60362420042796755
	 */
	public SubmitHandler60362420042796755() {
	}

	public int getConsumerPoolSize() {
		return 5;
	}
	
	public Date getFirstOccurrenceScheduledFor() {
		return this.firstOccurrenceScheduledFor;
	}
	
	public String getHandlerUuid() {
		return "914890@_SvOOoBYbEeKsDbmQL25eBw";
	}
	
	public String getQueueName() {
		return "structuredbusiness::branch::Branch::PrepareQuote::submit";
	}
	
	public boolean handleOn(Object t, AbstractPersistence persistence) {
		PrepareQuote target = (PrepareQuote)t;
		if ( isEvent ) {
			return target.consumeSubmitOccurrence();
		} else {
			target.submit();
			return true;
		}
	}
	
	public boolean isIsEvent() {
		return this.isEvent;
	}
	
	public Collection<PropertyValue> marshall(Environment env) {
		Collection result = new ArrayList<PropertyValue>();
		result.add(new PropertyValue(-6l, Value.valueOf(isEvent,env)));
		return result;
	}
	
	public Date scheduleNextOccurrence(Object object) {
		return new Date(System.currentTimeMillis() + 1000*60*60*24*10);
	}
	
	public void setIsEvent(boolean isEvent) {
		this.isEvent=isEvent;
	}
	
	public void unmarshall(Collection<PropertyValue> ps, AbstractPersistence persistence) {
		for ( PropertyValue p : ps ) {
			if ( p.getId()==-6l ) {
				this.isEvent=(Boolean)Value.valueOf(p.getValue(),(InternalHibernatePersistence)persistence);
			}
		}
	}

}