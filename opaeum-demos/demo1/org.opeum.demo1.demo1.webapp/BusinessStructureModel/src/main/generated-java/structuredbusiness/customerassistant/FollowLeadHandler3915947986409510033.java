package structuredbusiness.customerassistant;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.opaeum.annotation.PropertyConstraint;
import org.opaeum.annotation.PropertyMetaInfo;
import org.opaeum.runtime.environment.marshall.PropertyValue;
import org.opaeum.runtime.environment.marshall.Value;
import org.opaeum.runtime.event.ICallEventHandler;
import org.opaeum.runtime.persistence.AbstractPersistence;
import org.opaeum.runtime.strategy.DateTimeStrategyFactory;

import structuredbusiness.CustomerAssistant;
import structuredbusiness.Online_Customer;

public class FollowLeadHandler3915947986409510033 implements ICallEventHandler {
	private Online_Customer customer;
	private Date firstOccurrenceScheduledFor;
	private boolean isEvent;
	private Date timeOfLead;

	/** Constructor for FollowLeadHandler3915947986409510033
	 * 
	 * @param customer 
	 * @param timeOfLead 
	 * @param isEvent 
	 */
	public FollowLeadHandler3915947986409510033(Online_Customer customer, Date timeOfLead, boolean isEvent) {
		this.firstOccurrenceScheduledFor=new Date(System.currentTimeMillis()+1000);
		setCustomer(customer);
		setTimeOfLead(timeOfLead);
		this.isEvent=isEvent;
	}
	
	/** Default constructor for FollowLeadHandler3915947986409510033
	 */
	public FollowLeadHandler3915947986409510033() {
	}

	public int getConsumerPoolSize() {
		return 5;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=577283965044448219l,uuid="914890@_Dzy24JKiEeGiJMBDeZRymA")
	public Online_Customer getCustomer() {
		return this.customer;
	}
	
	public Date getFirstOccurrenceScheduledFor() {
		return this.firstOccurrenceScheduledFor;
	}
	
	public String getHandlerUuid() {
		return "914890@_A1hu8JKiEeGiJMBDeZRymA";
	}
	
	public boolean getIsEvent() {
		return this.isEvent;
	}
	
	public String getQueueName() {
		return "structuredbusiness::CustomerAssistant::followLead";
	}
	
	@PropertyMetaInfo(constraints=
		@PropertyConstraint(message="New constraint",name="NewConstraint"),isComposite=false,opaeumId=2393352033743834460l,strategyFactory=DateTimeStrategyFactory.class,uuid="914890@_D1Kv4JKiEeGiJMBDeZRymA")
	public Date getTimeOfLead() {
		return this.timeOfLead;
	}
	
	public boolean handleOn(Object t) {
		CustomerAssistant target = (CustomerAssistant)t;
		if ( isEvent ) {
			return target.consumeFollowLeadOccurrence(getCustomer(),getTimeOfLead());
		} else {
			target.followLead(getCustomer(),getTimeOfLead());
			return true;
		}
	}
	
	public boolean isNewConstraint() {
		boolean result = false;
		Date now = new Date();
		result = (now.after(timeOfLead) == true);
		return result;
	}
	
	public Collection<PropertyValue> marshall() {
		Collection<PropertyValue> result = new ArrayList<PropertyValue>();
		result.add(new PropertyValue(5501124320077697795l, Value.valueOf(this.getCustomer())));
		result.add(new PropertyValue(-6l, Value.valueOf(isEvent)));
		result.add(new PropertyValue(3684593961093410895l, Value.valueOf(this.getTimeOfLead())));
		return result;
	}
	
	public Date scheduleNextOccurrence() {
		return new Date(System.currentTimeMillis() + 1000*60*60*24*10);
	}
	
	public void setCustomer(Online_Customer customer) {
		this.customer=customer;
	}
	
	public void setIsEvent(boolean isEvent) {
		this.isEvent=isEvent;
	}
	
	public void setTimeOfLead(Date timeOfLead) {
		this.timeOfLead=timeOfLead;
	}
	
	public void unmarshall(Collection<PropertyValue> ps, AbstractPersistence persistence) {
		for ( PropertyValue p : ps ) {
			if ( p.getId()==5501124320077697795l ) {
				this.setCustomer((Online_Customer)Value.valueOf(p.getValue(),persistence));
			} else {
				if ( p.getId()==3684593961093410895l ) {
					this.setTimeOfLead((Date)Value.valueOf(p.getValue(),persistence));
				} else {
				
				}
			}
			if ( p.getId()==-6l ) {
				this.isEvent=(Boolean)Value.valueOf(p.getValue(),persistence);
			}
		}
	}

}