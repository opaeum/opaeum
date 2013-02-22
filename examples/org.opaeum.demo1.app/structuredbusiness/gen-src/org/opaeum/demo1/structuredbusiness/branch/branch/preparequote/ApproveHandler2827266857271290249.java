package org.opaeum.demo1.structuredbusiness.branch.branch.preparequote;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.opaeum.demo1.structuredbusiness.branch.branch.PrepareQuote;
import org.opaeum.hibernate.domain.InternalHibernatePersistence;
import org.opaeum.runtime.environment.Environment;
import org.opaeum.runtime.environment.marshall.PropertyValue;
import org.opaeum.runtime.environment.marshall.Value;
import org.opaeum.runtime.event.ICallEventHandler;
import org.opaeum.runtime.persistence.AbstractPersistence;

public class ApproveHandler2827266857271290249 implements ICallEventHandler {
	private Date firstOccurrenceScheduledFor;
	private boolean isEvent;

	/** Constructor for ApproveHandler2827266857271290249
	 * 
	 * @param isEvent 
	 */
	public ApproveHandler2827266857271290249(boolean isEvent) {
		this.firstOccurrenceScheduledFor=new Date(System.currentTimeMillis()+1000);
		this.isEvent=isEvent;
	}
	
	/** Default constructor for ApproveHandler2827266857271290249
	 */
	public ApproveHandler2827266857271290249() {
	}

	public int getConsumerPoolSize() {
		return 5;
	}
	
	public Date getFirstOccurrenceScheduledFor() {
		return this.firstOccurrenceScheduledFor;
	}
	
	public String getHandlerUuid() {
		return "914890@_Y9DC0BYbEeKsDbmQL25eBw";
	}
	
	public String getQueueName() {
		return "structuredbusiness::branch::Branch::PrepareQuote::approve";
	}
	
	public boolean handleOn(Object t, AbstractPersistence persistence) {
		PrepareQuote target = (PrepareQuote)t;
		if ( isEvent ) {
			return target.consumeApproveOccurrence();
		} else {
			target.approve();
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