package org.opaeum.demo1.structuredbusiness.appliance;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.opaeum.hibernate.domain.InternalHibernatePersistence;
import org.opaeum.runtime.environment.Environment;
import org.opaeum.runtime.environment.marshall.PropertyValue;
import org.opaeum.runtime.environment.marshall.Value;
import org.opaeum.runtime.event.ISignalEventHandler;
import org.opaeum.runtime.persistence.AbstractPersistence;

public class ProductAnnouncementHandler implements ISignalEventHandler {
	private Date firstOccurrenceScheduledFor;
	private boolean isEvent;
	private ProductAnnouncement signal = new ProductAnnouncement();

	/** Constructor for ProductAnnouncementHandler
	 * 
	 * @param signal 
	 * @param isEvent 
	 */
	public ProductAnnouncementHandler(ProductAnnouncement signal, boolean isEvent) {
		this.signal=signal;
		this.firstOccurrenceScheduledFor=new Date(System.currentTimeMillis()+1000);
		this.isEvent=isEvent;
	}
	
	/** Default constructor for ProductAnnouncementHandler
	 */
	public ProductAnnouncementHandler() {
	}

	public int getConsumerPoolSize() {
		return 5;
	}
	
	public Date getFirstOccurrenceScheduledFor() {
		return this.firstOccurrenceScheduledFor;
	}
	
	public String getHandlerUuid() {
		return "914890@_o9aQgGCfEeG6xvYqJACneg";
	}
	
	public String getQueueName() {
		return "structuredbusiness::appliance::ProductAnnouncement";
	}
	
	public boolean handleOn(Object targets, AbstractPersistence persistence) {
		boolean result = false;
		for ( Object target : (Collection<?>)targets ) {
			if ( target instanceof ProductAnnouncementReceiver ) {
				if ( isEvent ) {
					result |=((ProductAnnouncementReceiver)target).consumeProductAnnouncementEvent(signal);
				} else {
					((ProductAnnouncementReceiver)target).receiveProductAnnouncement(signal);
					result = true;
				}
			}
		}
		return result;
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
		return new Date(System.currentTimeMillis() + 1000*60*60*24);
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