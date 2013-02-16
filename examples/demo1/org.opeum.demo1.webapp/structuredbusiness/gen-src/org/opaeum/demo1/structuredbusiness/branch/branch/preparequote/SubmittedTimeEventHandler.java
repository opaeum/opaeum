package org.opaeum.demo1.structuredbusiness.branch.branch.preparequote;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.opaeum.demo1.structuredbusiness.branch.branch.PrepareQuote;
import org.opaeum.runtime.domain.IToken;
import org.opaeum.runtime.environment.Environment;
import org.opaeum.runtime.environment.marshall.PropertyValue;
import org.opaeum.runtime.environment.marshall.Value;
import org.opaeum.runtime.event.ITimeEventHandler;
import org.opaeum.runtime.persistence.AbstractPersistence;

public class SubmittedTimeEventHandler implements ITimeEventHandler {
	private Date firstOccurrenceScheduledFor;
	private IToken returnInfo;

	/** Constructor for SubmittedTimeEventHandler
	 * 
	 * @param time 
	 * @param returnInfo 
	 */
	public SubmittedTimeEventHandler(Date time, IToken returnInfo) {
		this.firstOccurrenceScheduledFor=time;
		this.returnInfo=returnInfo;
	}
	
	/** Default constructor for SubmittedTimeEventHandler
	 */
	public SubmittedTimeEventHandler() {
	}

	public int getConsumerPoolSize() {
		return 5;
	}
	
	public Date getFirstOccurrenceScheduledFor() {
		return this.firstOccurrenceScheduledFor;
	}
	
	public String getHandlerUuid() {
		return "914890@_TYw30BcAEeK9SoQSQYApGA";
	}
	
	public String getQueueName() {
		return "SubmittedTimeEvent";
	}
	
	public boolean handleOn(Object object) {
		PrepareQuote target = (PrepareQuote)object;
		return target.onOccurrenceOfSubmittedTimeEvent(returnInfo,firstOccurrenceScheduledFor);
	}
	
	public Collection<PropertyValue> marshall(Environment env) {
		Collection<PropertyValue> result = new ArrayList<PropertyValue>();
		result.add(new PropertyValue(-5l, Value.valueOf(returnInfo,env)));
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