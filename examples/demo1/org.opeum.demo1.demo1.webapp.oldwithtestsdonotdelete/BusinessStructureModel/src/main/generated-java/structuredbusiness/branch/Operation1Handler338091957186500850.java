package structuredbusiness.branch;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.opaeum.annotation.PropertyMetaInfo;
import org.opaeum.runtime.environment.marshall.PropertyValue;
import org.opaeum.runtime.environment.marshall.Value;
import org.opaeum.runtime.event.ICallEventHandler;
import org.opaeum.runtime.persistence.AbstractPersistence;

import structuredbusiness.Branch;

public class Operation1Handler338091957186500850 implements ICallEventHandler {
	private Date firstOccurrenceScheduledFor;
	private boolean isEvent;
	private String sdfgd;

	/** Constructor for Operation1Handler338091957186500850
	 * 
	 * @param sdfgd 
	 * @param isEvent 
	 */
	public Operation1Handler338091957186500850(String sdfgd, boolean isEvent) {
		this.firstOccurrenceScheduledFor=new Date(System.currentTimeMillis()+1000);
		setSdfgd(sdfgd);
		this.isEvent=isEvent;
	}
	
	/** Default constructor for Operation1Handler338091957186500850
	 */
	public Operation1Handler338091957186500850() {
	}

	public int getConsumerPoolSize() {
		return 5;
	}
	
	public Date getFirstOccurrenceScheduledFor() {
		return this.firstOccurrenceScheduledFor;
	}
	
	public String getHandlerUuid() {
		return "914890@_dOodgMzBEeGKe7Qm4dvydQ";
	}
	
	public boolean getIsEvent() {
		return this.isEvent;
	}
	
	public String getQueueName() {
		return "structuredbusiness::Branch::Operation1";
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=7908037741838922253l,uuid="914890@_k_VZsMzBEeGKe7Qm4dvydQ")
	public String getSdfgd() {
		return this.sdfgd;
	}
	
	public boolean handleOn(Object t) {
		Branch target = (Branch)t;
		if ( isEvent ) {
			return target.consumeOperation1Occurrence(getSdfgd());
		} else {
			target.Operation1(getSdfgd());
			return true;
		}
	}
	
	public Collection<PropertyValue> marshall() {
		Collection<PropertyValue> result = new ArrayList<PropertyValue>();
		result.add(new PropertyValue(-6l, Value.valueOf(isEvent)));
		result.add(new PropertyValue(4983800839352232442l, Value.valueOf(this.getSdfgd())));
		return result;
	}
	
	public Date scheduleNextOccurrence() {
		return new Date(System.currentTimeMillis() + 1000*60*60*24*10);
	}
	
	public void setIsEvent(boolean isEvent) {
		this.isEvent=isEvent;
	}
	
	public void setSdfgd(String sdfgd) {
		this.sdfgd=sdfgd;
	}
	
	public void unmarshall(Collection<PropertyValue> ps, AbstractPersistence persistence) {
		for ( PropertyValue p : ps ) {
			if ( p.getId()==4983800839352232442l ) {
				this.setSdfgd((String)Value.valueOf(p.getValue(),persistence));
			} else {
			
			}
			if ( p.getId()==-6l ) {
				this.isEvent=(Boolean)Value.valueOf(p.getValue(),persistence);
			}
		}
	}

}