package model;

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

public class Signal1Handler implements ISignalEventHandler {
	private Date firstOccurrenceScheduledFor;
	private boolean isEvent;
	private Signal1 signal = new Signal1();

	/** Constructor for Signal1Handler
	 * 
	 * @param signal 
	 * @param isEvent 
	 */
	public Signal1Handler(Signal1 signal, boolean isEvent) {
		this.signal=signal;
		this.firstOccurrenceScheduledFor=new Date(System.currentTimeMillis()+1000);
		this.isEvent=isEvent;
	}
	
	/** Default constructor for Signal1Handler
	 */
	public Signal1Handler() {
	}

	public int getConsumerPoolSize() {
		return 5;
	}
	
	public Date getFirstOccurrenceScheduledFor() {
		return this.firstOccurrenceScheduledFor;
	}
	
	public String getHandlerUuid() {
		return "model.uml@_67TgwLlEEeG-Ou4fV0X62w";
	}
	
	public boolean getIsEvent() {
		return this.isEvent;
	}
	
	public String getQueueName() {
		return "model::Signal1";
	}
	
	public boolean handleOn(Object targets) {
		boolean consumed = false;
		for ( Object target : (Collection<?>)targets ) {
			if ( target instanceof Signal1Receiver ) {
				if ( isEvent ) {
					consumed |=((Signal1Receiver)target).consumeSignal1Event(signal);
				} else {
					((Signal1Receiver)target).receiveSignal1(signal);
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