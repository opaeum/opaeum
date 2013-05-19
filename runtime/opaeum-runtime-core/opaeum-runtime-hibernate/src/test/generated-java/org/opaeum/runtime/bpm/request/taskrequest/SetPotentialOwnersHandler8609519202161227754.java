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

public class SetPotentialOwnersHandler8609519202161227754 implements ICallEventHandler {
	private Date firstOccurrenceScheduledFor;
	private boolean isEvent;
	private Collection<IBusinessRole> po;

	/** Constructor for SetPotentialOwnersHandler8609519202161227754
	 * 
	 * @param po 
	 * @param isEvent 
	 */
	public SetPotentialOwnersHandler8609519202161227754(Collection<IBusinessRole> po, boolean isEvent) {
		this.firstOccurrenceScheduledFor=new Date(System.currentTimeMillis()+1000);
		setPo(po);
		this.isEvent=isEvent;
	}
	
	/** Default constructor for SetPotentialOwnersHandler8609519202161227754
	 */
	public SetPotentialOwnersHandler8609519202161227754() {
	}

	public int getConsumerPoolSize() {
		return 5;
	}
	
	public Date getFirstOccurrenceScheduledFor() {
		return this.firstOccurrenceScheduledFor;
	}
	
	public String getHandlerUuid() {
		return "252060@_cQs8sOj8EeGqMbZBSj5Rng";
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=7962697304479678063l,uuid="252060@_etZooOj8EeGqMbZBSj5Rng")
	public Collection<IBusinessRole> getPo() {
		return this.po;
	}
	
	public String getQueueName() {
		return "OpaeumLibraryForBPM::request::TaskRequest::setPotentialOwners";
	}
	
	public boolean handleOn(Object t, AbstractPersistence persistence) {
		TaskRequest target = (TaskRequest)t;
		if ( isEvent ) {
			return target.consumeSetPotentialOwnersOccurrence(getPo());
		} else {
			target.setPotentialOwners(getPo());
			return true;
		}
	}
	
	public boolean isIsEvent() {
		return this.isEvent;
	}
	
	public Collection<PropertyValue> marshall(Environment env) {
		Collection result = new ArrayList<PropertyValue>();
		result.add(new PropertyValue(-6l, Value.valueOf(isEvent,env)));
		result.add(new PropertyValue(8413390915277987936l, Value.valueOf(this.getPo(),env)));
		return result;
	}
	
	public Date scheduleNextOccurrence(Object object) {
		return new Date(System.currentTimeMillis() + 1000*60*60*24*10);
	}
	
	public void setIsEvent(boolean isEvent) {
		this.isEvent=isEvent;
	}
	
	public void setPo(Collection<IBusinessRole> po) {
		this.po=po;
	}
	
	public void unmarshall(Collection<PropertyValue> ps, AbstractPersistence persistence) {
		for ( PropertyValue p : ps ) {
			if ( p.getId()==8413390915277987936l ) {
				this.setPo((Collection<IBusinessRole>)Value.valueOf(p.getValue(),(InternalHibernatePersistence)persistence));
			} else {
			
			}
			if ( p.getId()==-6l ) {
				this.isEvent=(Boolean)Value.valueOf(p.getValue(),(InternalHibernatePersistence)persistence);
			}
		}
	}

}