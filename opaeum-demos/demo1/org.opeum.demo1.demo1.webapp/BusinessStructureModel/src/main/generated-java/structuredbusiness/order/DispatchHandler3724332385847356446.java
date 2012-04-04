package structuredbusiness.order;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.opaeum.annotation.PropertyMetaInfo;
import org.opaeum.runtime.environment.marshall.PropertyValue;
import org.opaeum.runtime.environment.marshall.Value;
import org.opaeum.runtime.event.ICallEventHandler;
import org.opaeum.runtime.persistence.AbstractPersistence;

import structuredbusiness.Accountant;
import structuredbusiness.Order;

public class DispatchHandler3724332385847356446 implements ICallEventHandler {
	private Accountant accountant;
	private Boolean complete;
	private Date date;
	private Date firstOccurrenceScheduledFor;
	private boolean isEvent;

	/** Constructor for DispatchHandler3724332385847356446
	 * 
	 * @param accountant 
	 * @param date 
	 * @param complete 
	 * @param isEvent 
	 */
	public DispatchHandler3724332385847356446(Accountant accountant, Date date, Boolean complete, boolean isEvent) {
		this.firstOccurrenceScheduledFor=new Date(System.currentTimeMillis()+1000);
		setAccountant(accountant);
		setDate(date);
		setComplete(complete);
		this.isEvent=isEvent;
	}
	
	/** Default constructor for DispatchHandler3724332385847356446
	 */
	public DispatchHandler3724332385847356446() {
	}

	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=8931706039232190181l,uuid="914890@_5a6WYH47EeGarqqEaoJFHg")
	public Accountant getAccountant() {
		return this.accountant;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=2088720645100870365l,uuid="914890@_5eWr8H47EeGarqqEaoJFHg")
	public Boolean getComplete() {
		return this.complete;
	}
	
	public int getConsumerPoolSize() {
		return 5;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=9216221382068427107l,uuid="914890@_5cxXkH47EeGarqqEaoJFHg")
	public Date getDate() {
		return this.date;
	}
	
	public Date getFirstOccurrenceScheduledFor() {
		return this.firstOccurrenceScheduledFor;
	}
	
	public String getHandlerUuid() {
		return "914890@_2Wwo4H47EeGarqqEaoJFHg";
	}
	
	public boolean getIsEvent() {
		return this.isEvent;
	}
	
	public String getQueueName() {
		return "structuredbusiness::Order::dispatch";
	}
	
	public boolean handleOn(Object t) {
		Order target = (Order)t;
		if ( isEvent ) {
			return target.consumeDispatchOccurrence(getAccountant(),getDate(),getComplete());
		} else {
			target.dispatch(getAccountant(),getDate(),getComplete());
			return true;
		}
	}
	
	public Collection<PropertyValue> marshall() {
		Collection<PropertyValue> result = new ArrayList<PropertyValue>();
		result.add(new PropertyValue(244889823387463052l, Value.valueOf(this.getAccountant())));
		result.add(new PropertyValue(39625519448773874l, Value.valueOf(this.getDate())));
		result.add(new PropertyValue(-6l, Value.valueOf(isEvent)));
		result.add(new PropertyValue(7181427565989028018l, Value.valueOf(this.getComplete())));
		return result;
	}
	
	public Date scheduleNextOccurrence() {
		return new Date(System.currentTimeMillis() + 1000*60*60*24*10);
	}
	
	public void setAccountant(Accountant accountant) {
		this.accountant=accountant;
	}
	
	public void setComplete(Boolean complete) {
		this.complete=complete;
	}
	
	public void setDate(Date date) {
		this.date=date;
	}
	
	public void setIsEvent(boolean isEvent) {
		this.isEvent=isEvent;
	}
	
	public void unmarshall(Collection<PropertyValue> ps, AbstractPersistence persistence) {
		for ( PropertyValue p : ps ) {
			if ( p.getId()==244889823387463052l ) {
				this.setAccountant((Accountant)Value.valueOf(p.getValue(),persistence));
			} else {
				if ( p.getId()==39625519448773874l ) {
					this.setDate((Date)Value.valueOf(p.getValue(),persistence));
				} else {
					if ( p.getId()==7181427565989028018l ) {
						this.setComplete((Boolean)Value.valueOf(p.getValue(),persistence));
					} else {
					
					}
				}
			}
			if ( p.getId()==-6l ) {
				this.isEvent=(Boolean)Value.valueOf(p.getValue(),persistence);
			}
		}
	}

}