package structuredbusiness;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import javax.persistence.Transient;

import org.opaeum.runtime.domain.IActiveObject;
import org.opaeum.runtime.environment.Environment;
import org.opaeum.runtime.environment.marshall.PropertyValue;
import org.opaeum.runtime.environment.marshall.Value;
import org.opaeum.runtime.event.INotificationReceiver;
import org.opaeum.runtime.event.ISignalEventHandler;
import org.opaeum.runtime.persistence.AbstractPersistence;

public class ProductAnnouncementHandler implements ISignalEventHandler {
	@Transient
	private HashSet<INotificationReceiver> bcc;
	@Transient
	private HashSet<INotificationReceiver> cc;
	private Date firstOccurrenceScheduledFor;
	@Transient
	private INotificationReceiver from;
	private boolean isEvent;
	private ProductAnnouncement signal = new ProductAnnouncement();
	@Transient
	private HashSet<INotificationReceiver> to;

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

	public HashSet<INotificationReceiver> getBcc() {
		return this.bcc;
	}
	
	public HashSet<INotificationReceiver> getCc() {
		return this.cc;
	}
	
	public int getConsumerPoolSize() {
		return 5;
	}
	
	public Date getFirstOccurrenceScheduledFor() {
		return this.firstOccurrenceScheduledFor;
	}
	
	public INotificationReceiver getFrom() {
		return this.from;
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
	
	public HashSet<INotificationReceiver> getTo() {
		return this.to;
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
		if ( isEvent ) {
			Environment.getInstance().getNotificationService().sendNotification(signal, from,(Collection<? extends INotificationReceiver>)targets,cc,bcc);
		}
		return consumed;
	}
	
	public Collection<PropertyValue> marshall() {
		Collection<PropertyValue> result = new ArrayList<PropertyValue>();
		result.add(new PropertyValue(-20l, Value.valueOf(from)));
		result.add(new PropertyValue(-21l, Value.valueOf(cc)));
		result.add(new PropertyValue(-22l, Value.valueOf(bcc)));
		result.add(new PropertyValue(-6l, Value.valueOf(isEvent)));
		result.add(new PropertyValue(-23l, Value.valueOf(to)));
		return result;
	}
	
	public Date scheduleNextOccurrence() {
		return new Date(System.currentTimeMillis() + 1000*60*60*24);
	}
	
	public void setBcc(HashSet<INotificationReceiver> bcc) {
		this.bcc=bcc;
	}
	
	public void setCc(HashSet<INotificationReceiver> cc) {
		this.cc=cc;
	}
	
	public void setFrom(INotificationReceiver from) {
		this.from=from;
	}
	
	public void setIsEvent(boolean isEvent) {
		this.isEvent=isEvent;
	}
	
	public void setTo(HashSet<INotificationReceiver> to) {
		this.to=to;
	}
	
	public void unmarshall(Collection<PropertyValue> ps, AbstractPersistence persistence) {
		for ( PropertyValue p : ps ) {
			if ( p.getId()==-20l ) {
				this.from=(INotificationReceiver)Value.valueOf(p.getValue(),persistence);
			}
			if ( p.getId()==-21l ) {
				this.cc=(HashSet<INotificationReceiver>)Value.valueOf(p.getValue(),persistence);
			}
			if ( p.getId()==-22l ) {
				this.bcc=(HashSet<INotificationReceiver>)Value.valueOf(p.getValue(),persistence);
			}
			if ( p.getId()==-23l ) {
				this.to=(HashSet<INotificationReceiver>)Value.valueOf(p.getValue(),persistence);
			}
			if ( p.getId()==-6l ) {
				this.isEvent=(Boolean)Value.valueOf(p.getValue(),persistence);
			}
		}
	}

}