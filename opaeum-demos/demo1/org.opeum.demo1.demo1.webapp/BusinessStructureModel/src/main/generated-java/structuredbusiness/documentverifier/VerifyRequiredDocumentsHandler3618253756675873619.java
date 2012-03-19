package structuredbusiness.documentverifier;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.opaeum.runtime.environment.marshall.PropertyValue;
import org.opaeum.runtime.environment.marshall.Value;
import org.opaeum.runtime.event.ICallEventHandler;
import org.opaeum.runtime.persistence.AbstractPersistence;

import structuredbusiness.DocumentVerifier;

public class VerifyRequiredDocumentsHandler3618253756675873619 implements ICallEventHandler {
	private Date firstOccurrenceScheduledFor;
	private String id;
	private boolean isEvent;

	/** Constructor for VerifyRequiredDocumentsHandler3618253756675873619
	 * 
	 * @param id 
	 * @param isEvent 
	 */
	public VerifyRequiredDocumentsHandler3618253756675873619(String id, boolean isEvent) {
		this.firstOccurrenceScheduledFor=new Date(System.currentTimeMillis()+1000);
		setId(id);
		this.isEvent=isEvent;
	}
	
	/** Default constructor for VerifyRequiredDocumentsHandler3618253756675873619
	 */
	public VerifyRequiredDocumentsHandler3618253756675873619() {
	}

	public int getConsumerPoolSize() {
		return 5;
	}
	
	public Date getFirstOccurrenceScheduledFor() {
		return this.firstOccurrenceScheduledFor;
	}
	
	public String getHandlerUuid() {
		return "914890@_vaiUUGK1EeGb14EjInbIAA";
	}
	
	public String getId() {
		return this.id;
	}
	
	public boolean getIsEvent() {
		return this.isEvent;
	}
	
	public String getQueueName() {
		return "structuredbusiness::DocumentVerifier::verifyRequiredDocuments";
	}
	
	public boolean handleOn(Object t) {
		DocumentVerifier target = (DocumentVerifier)t;
		if ( isEvent ) {
			return target.consumeVerifyRequiredDocumentsOccurrence(getId());
		} else {
			target.verifyRequiredDocuments(getId());
			return true;
		}
	}
	
	public Collection<PropertyValue> marshall() {
		Collection<PropertyValue> result = new ArrayList<PropertyValue>();
		result.add(new PropertyValue(-6l, Value.valueOf(isEvent)));
		result.add(new PropertyValue(7031023243174406977l, Value.valueOf(this.getId())));
		return result;
	}
	
	public Date scheduleNextOccurrence() {
		return new Date(System.currentTimeMillis() + 1000*60*60*24*10);
	}
	
	public void setId(String id) {
		this.id=id;
	}
	
	public void setIsEvent(boolean isEvent) {
		this.isEvent=isEvent;
	}
	
	public void unmarshall(Collection<PropertyValue> ps, AbstractPersistence persistence) {
		for ( PropertyValue p : ps ) {
			if ( p.getId()==7031023243174406977l ) {
				this.setId((String)Value.valueOf(p.getValue(),persistence));
			} else {
			
			}
			if ( p.getId()==-6l ) {
				this.isEvent=(Boolean)Value.valueOf(p.getValue(),persistence);
			}
		}
	}

}