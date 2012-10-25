package structuredbusiness.branch.businessstatemachine1;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.opaeum.runtime.domain.IToken;
import org.opaeum.runtime.environment.marshall.PropertyValue;
import org.opaeum.runtime.environment.marshall.Value;
import org.opaeum.runtime.event.ITimeEventHandler;
import org.opaeum.runtime.persistence.AbstractPersistence;

import structuredbusiness.branch.BusinessStateMachine1;

public class Transition1TimeEventHandler implements ITimeEventHandler {
	private Date firstOccurrenceScheduledFor;
	private IToken returnInfo;

	/** Constructor for Transition1TimeEventHandler
	 * 
	 * @param time 
	 * @param returnInfo 
	 */
	public Transition1TimeEventHandler(Date time, IToken returnInfo) {
		this.firstOccurrenceScheduledFor=time;
		this.returnInfo=returnInfo;
	}
	
	/** Default constructor for Transition1TimeEventHandler
	 */
	public Transition1TimeEventHandler() {
	}

	public int getConsumerPoolSize() {
		return 5;
	}
	
	public Date getFirstOccurrenceScheduledFor() {
		return this.firstOccurrenceScheduledFor;
	}
	
	public String getHandlerUuid() {
		return "914890@_eu1uUBb3EeKI68QaBu0uBA";
	}
	
	public String getQueueName() {
		return "Transition1TimeEvent";
	}
	
	public boolean handleOn(Object object) {
		BusinessStateMachine1 target = (BusinessStateMachine1)object;
		return target.onOccurrenceOfTransition1TimeEvent(returnInfo,firstOccurrenceScheduledFor);
	}
	
	public Collection<PropertyValue> marshall() {
		Collection<PropertyValue> result = new ArrayList<PropertyValue>();
		result.add(new PropertyValue(-5l, Value.valueOf(returnInfo)));
		return result;
	}
	
	public Date scheduleNextOccurrence() {
		return new Date(System.currentTimeMillis() + 1000*60*60*24*10);
	}
	
	public void unmarshall(Collection<PropertyValue> ps, AbstractPersistence persistence) {
		for ( PropertyValue p : ps ) {
			if ( p.getId()==-5 ) {
				this.returnInfo=(IToken)Value.valueOf(p.getValue(),persistence);
			}
		}
	}

}