package structuredbusiness.branch.businessstatemachine1;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.opaeum.runtime.environment.marshall.PropertyValue;
import org.opaeum.runtime.environment.marshall.Value;
import org.opaeum.runtime.event.ICallEventHandler;
import org.opaeum.runtime.persistence.AbstractPersistence;

import structuredbusiness.branch.BusinessStateMachine1;

public class ApproveHandler2827266857271290249 implements ICallEventHandler {
	private Date firstOccurrenceScheduledFor;
	private boolean isEvent;

	/** Constructor for ApproveHandler2827266857271290249
	 * 
	 * @param isEvent 
	 */
	public ApproveHandler2827266857271290249(boolean isEvent) {
		this.firstOccurrenceScheduledFor=new Date(System.currentTimeMillis()+1000);
		this.isEvent=isEvent;
	}
	
	/** Default constructor for ApproveHandler2827266857271290249
	 */
	public ApproveHandler2827266857271290249() {
	}

	public int getConsumerPoolSize() {
		return 5;
	}
	
	public Date getFirstOccurrenceScheduledFor() {
		return this.firstOccurrenceScheduledFor;
	}
	
	public String getHandlerUuid() {
		return "914890@_Y9DC0BYbEeKsDbmQL25eBw";
	}
	
	public boolean getIsEvent() {
		return this.isEvent;
	}
	
	public String getQueueName() {
		return "structuredbusiness::Branch::BusinessStateMachine1::approve";
	}
	
	public boolean handleOn(Object t) {
		BusinessStateMachine1 target = (BusinessStateMachine1)t;
		if ( isEvent ) {
			return target.consumeApproveOccurrence();
		} else {
			target.approve();
			return true;
		}
	}
	
	public void isIsEvent(boolean isEvent) {
		this.isEvent=isEvent;
	}
	
	public Collection<PropertyValue> marshall() {
		Collection<PropertyValue> result = new ArrayList<PropertyValue>();
		result.add(new PropertyValue(-6l, Value.valueOf(isEvent)));
		return result;
	}
	
	public Date scheduleNextOccurrence() {
		return new Date(System.currentTimeMillis() + 1000*60*60*24*10);
	}
	
	public void unmarshall(Collection<PropertyValue> ps, AbstractPersistence persistence) {
		for ( PropertyValue p : ps ) {
			if ( p.getId()==-6l ) {
				this.isEvent=(Boolean)Value.valueOf(p.getValue(),persistence);
			}
		}
	}

}