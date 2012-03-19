package structuredbusiness;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.opaeum.runtime.domain.IActiveObject;
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
	
	public boolean getIsEvent() {
		return this.isEvent;
	}
	
	public String getQueueName() {
		return "structuredbusiness::ProductAnnouncement";
	}
	
	public boolean handleOn(Object targets) {
		boolean consumed = false;
		for ( Object target : (Collection<?>)targets ) {
			if ( target instanceof ProductAnnouncementReceiver ) {
				if ( isEvent ) {
					consumed |=((ProductAnnouncementReceiver)target).consumeProductAnnouncementEvent(signal);
				} else {
					((ProductAnnouncementReceiver)target).receiveProductAnnouncement(signal);
					consumed = true;
				}
			}
		}
		return consumed;
	}
	
	public Collection<PropertyValue> marshall() {
		Collection<PropertyValue> result = new ArrayList<PropertyValue>();
		result.add(new PropertyValue(-6l, Value.valueOf(isEvent)));
		return result;
	}
	
	public Date scheduleNextOccurrence() {
		return new Date(System.currentTimeMillis() + 1000*60*60*24);
	}
	
	public void setIsEvent(boolean isEvent) {
		this.isEvent=isEvent;
	}
	
	public void unmarshall(Collection<PropertyValue> ps, AbstractPersistence persistence) {
		for ( PropertyValue p : ps ) {
			if ( p.getId()==-6l ) {
				this.isEvent=(Boolean)Value.valueOf(p.getValue(),persistence);
			}
		}
	}

}