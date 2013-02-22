package org.opaeum.demo1.structuredbusiness.branch.branch.preparequote;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.opaeum.demo1.structuredbusiness.branch.branch.PrepareQuote;
import org.opaeum.hibernate.domain.AbstractToken;
import org.opaeum.hibernate.domain.InternalHibernatePersistence;
import org.opaeum.hibernate.domain.ReturnInfo;
import org.opaeum.runtime.environment.Environment;
import org.opaeum.runtime.environment.marshall.PropertyValue;
import org.opaeum.runtime.environment.marshall.Value;
import org.opaeum.runtime.event.IChangeEventHandler;
import org.opaeum.runtime.persistence.AbstractPersistence;

public class Transition1ChangeEventHandler implements IChangeEventHandler {
	private Date firstOccurrenceScheduledFor;
	private ReturnInfo returnInfo;

	/** Constructor for Transition1ChangeEventHandler
	 * 
	 * @param token 
	 */
	public Transition1ChangeEventHandler(AbstractToken token) {
		this.returnInfo=new ReturnInfo(token);
	}
	
	/** Constructor for Transition1ChangeEventHandler
	 */
	public Transition1ChangeEventHandler() {
	}

	public int getConsumerPoolSize() {
		return 5;
	}
	
	public Date getFirstOccurrenceScheduledFor() {
		return this.firstOccurrenceScheduledFor;
	}
	
	public String getHandlerUuid() {
		return "914890@_q8beEHgGEeKNG8mFSp3Ijg";
	}
	
	public String getQueueName() {
		return "Transition1ChangeEvent";
	}
	
	public boolean handleOn(Object object, AbstractPersistence persistence) {
		PrepareQuote target = (PrepareQuote)object;
		if ( target.evaluateTransition1ChangeEvent() ) {
			return target.onOccurrenceOfTransition1ChangeEvent(returnInfo.getValue((InternalHibernatePersistence)persistence));
		}
		return false;
	}
	
	public Collection<PropertyValue> marshall(Environment env) {
		Collection result = new ArrayList<PropertyValue>();
		result.add(new PropertyValue(-5l, Value.valueOf(returnInfo,env)));
		return result;
	}
	
	public Date scheduleNextOccurrence(Object object) {
		return ((PrepareQuote)object).calculateNextOccurrenceOfTransition1ChangeEvent();
	}
	
	public void unmarshall(Collection<PropertyValue> ps, AbstractPersistence persistence) {
		for ( PropertyValue p : ps ) {
			if ( p.getId()==-5 ) {
				this.returnInfo=(ReturnInfo)Value.valueOf(p.getValue(),(InternalHibernatePersistence)persistence);
			}
		}
	}

}