package org.opaeum.runtime.bpm.request.taskrequest;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.opaeum.annotation.PropertyMetaInfo;
import org.opaeum.hibernate.domain.InternalHibernatePersistence;
import org.opaeum.runtime.bpm.organization.IBusinessRole;
import org.opaeum.runtime.bpm.request.TaskRequest;
import org.opaeum.runtime.environment.Environment;
import org.opaeum.runtime.environment.marshall.PropertyValue;
import org.opaeum.runtime.environment.marshall.Value;
import org.opaeum.runtime.event.ICallEventHandler;
import org.opaeum.runtime.persistence.AbstractPersistence;

public class DelegateHandler6837298467385087869 implements ICallEventHandler {
	private IBusinessRole delegate;
	private Date firstOccurrenceScheduledFor;
	private boolean isEvent;

	/** Constructor for DelegateHandler6837298467385087869
	 * 
	 * @param delegate 
	 * @param isEvent 
	 */
	public DelegateHandler6837298467385087869(IBusinessRole delegate, boolean isEvent) {
		this.firstOccurrenceScheduledFor=new Date(System.currentTimeMillis()+1000);
		setDelegate(delegate);
		this.isEvent=isEvent;
	}
	
	/** Default constructor for DelegateHandler6837298467385087869
	 */
	public DelegateHandler6837298467385087869() {
	}

	public int getConsumerPoolSize() {
		return 5;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=1321108594749684495l,uuid="252060@_TsfTcJTyEeChgI0v02SJHQ")
	public IBusinessRole getDelegate() {
		return this.delegate;
	}
	
	public Date getFirstOccurrenceScheduledFor() {
		return this.firstOccurrenceScheduledFor;
	}
	
	public String getHandlerUuid() {
		return "252060@_0lAQAIoaEeCPduia_-NbFw";
	}
	
	public String getQueueName() {
		return "OpaeumLibraryForBPM::request::TaskRequest::delegate";
	}
	
	public boolean handleOn(Object t, AbstractPersistence persistence) {
		TaskRequest target = (TaskRequest)t;
		if ( isEvent ) {
			return target.consumeDelegateOccurrence(getDelegate());
		} else {
			target.delegate(getDelegate());
			return true;
		}
	}
	
	public boolean isIsEvent() {
		return this.isEvent;
	}
	
	public Collection<PropertyValue> marshall(Environment env) {
		Collection result = new ArrayList<PropertyValue>();
		result.add(new PropertyValue(-6l, Value.valueOf(isEvent,env)));
		result.add(new PropertyValue(8205705053048523991l, Value.valueOf(this.getDelegate(),env)));
		return result;
	}
	
	public Date scheduleNextOccurrence(Object object) {
		return new Date(System.currentTimeMillis() + 1000*60*60*24*10);
	}
	
	public void setDelegate(IBusinessRole delegate) {
		this.delegate=delegate;
	}
	
	public void setIsEvent(boolean isEvent) {
		this.isEvent=isEvent;
	}
	
	public void unmarshall(Collection<PropertyValue> ps, AbstractPersistence persistence) {
		for ( PropertyValue p : ps ) {
			if ( p.getId()==8205705053048523991l ) {
				this.setDelegate((IBusinessRole)Value.valueOf(p.getValue(),(InternalHibernatePersistence)persistence));
			} else {
			
			}
			if ( p.getId()==-6l ) {
				this.isEvent=(Boolean)Value.valueOf(p.getValue(),(InternalHibernatePersistence)persistence);
			}
		}
	}

}