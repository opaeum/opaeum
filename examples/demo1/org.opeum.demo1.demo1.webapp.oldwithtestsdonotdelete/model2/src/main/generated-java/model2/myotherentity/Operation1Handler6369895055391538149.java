package model2.myotherentity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import model2.MyOtherEntity;

import org.opaeum.runtime.environment.marshall.PropertyValue;
import org.opaeum.runtime.environment.marshall.Value;
import org.opaeum.runtime.event.ICallEventHandler;
import org.opaeum.runtime.persistence.AbstractPersistence;

public class Operation1Handler6369895055391538149 implements ICallEventHandler {
	private Date firstOccurrenceScheduledFor;
	private boolean isEvent;

	/** Constructor for Operation1Handler6369895055391538149
	 * 
	 * @param isEvent 
	 */
	public Operation1Handler6369895055391538149(boolean isEvent) {
		this.firstOccurrenceScheduledFor=new Date(System.currentTimeMillis()+1000);
		this.isEvent=isEvent;
	}
	
	/** Default constructor for Operation1Handler6369895055391538149
	 */
	public Operation1Handler6369895055391538149() {
	}

	public int getConsumerPoolSize() {
		return 5;
	}
	
	public Date getFirstOccurrenceScheduledFor() {
		return this.firstOccurrenceScheduledFor;
	}
	
	public String getHandlerUuid() {
		return "model2.uml@_DcYK8LbJEeG62dzgiRb2WA";
	}
	
	public boolean getIsEvent() {
		return this.isEvent;
	}
	
	public String getQueueName() {
		return "model2::MyOtherEntity::Operation1";
	}
	
	public boolean handleOn(Object t) {
		MyOtherEntity target = (MyOtherEntity)t;
		if ( isEvent ) {
			return target.consumeOperation1Occurrence();
		} else {
			target.Operation1();
			return true;
		}
	}
	
	public Collection<PropertyValue> marshall() {
		Collection<PropertyValue> result = new ArrayList<PropertyValue>();
		result.add(new PropertyValue(-6l, Value.valueOf(isEvent)));
		return result;
	}
	
	public Date scheduleNextOccurrence() {
		return new Date(System.currentTimeMillis() + 1000*60*60*24*10);
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