package org.opaeum.demo1.structuredbusiness.branch.branch.preparequote;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.opaeum.demo1.structuredbusiness.branch.branch.PrepareQuote;
import org.opaeum.hibernate.domain.InternalHibernatePersistence;
import org.opaeum.hibernate.domain.ReturnInfo;
import org.opaeum.runtime.domain.IToken;
import org.opaeum.runtime.environment.Environment;
import org.opaeum.runtime.environment.marshall.PropertyValue;
import org.opaeum.runtime.environment.marshall.Value;
import org.opaeum.runtime.event.ITimeEventHandler;
import org.opaeum.runtime.persistence.AbstractPersistence;

public class SubmittedTimeEventHandler implements ITimeEventHandler {
	private Date firstOccurrenceScheduledFor;
	private ReturnInfo returnInfo;

	/** Constructor for SubmittedTimeEventHandler
	 * 
	 * @param time 
	 * @param token 
	 */
	public SubmittedTimeEventHandler(Date time, IToken token) {
		this.firstOccurrenceScheduledFor=time;
		this.returnInfo=new ReturnInfo(token);
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
	
	public boolean handleOn(Object object, AbstractPersistence persistence) {
		PrepareQuote target = (PrepareQuote)object;
		return target.onOccurrenceOfSubmittedTimeEvent(returnInfo.getValue((InternalHibernatePersistence)persistence),firstOccurrenceScheduledFor);
	}
	
	public Collection<PropertyValue> marshall(Environment env) {
		Collection result = new ArrayList<PropertyValue>();
		result.add(new PropertyValue(-5l, Value.valueOf(returnInfo,env)));
		return result;
	}
	
	public Date scheduleNextOccurrence(Object object) {
		return new Date(System.currentTimeMillis() + 1000*60*60*24*10);
	}
	
	public void unmarshall(Collection<PropertyValue> ps, AbstractPersistence persistence) {
		for ( PropertyValue p : ps ) {
			if ( p.getId()==-5 ) {
				this.returnInfo=(ReturnInfo)Value.valueOf(p.getValue(),(InternalHibernatePersistence)persistence);
			}
		}
	}

}