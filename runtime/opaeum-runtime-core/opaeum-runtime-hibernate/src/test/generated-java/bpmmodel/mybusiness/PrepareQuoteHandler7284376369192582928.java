package bpmmodel.mybusiness;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.opaeum.annotation.PropertyMetaInfo;
import org.opaeum.hibernate.domain.InternalHibernatePersistence;
import org.opaeum.runtime.environment.Environment;
import org.opaeum.runtime.environment.marshall.PropertyValue;
import org.opaeum.runtime.environment.marshall.Value;
import org.opaeum.runtime.event.ICallEventHandler;
import org.opaeum.runtime.persistence.AbstractPersistence;
import org.opaeum.runtime.strategy.DateStrategyFactory;

import bpmmodel.MyBusiness;
import bpmmodel.Product;

public class PrepareQuoteHandler7284376369192582928 implements ICallEventHandler {
	private Date firstOccurrenceScheduledFor;
	private boolean isEvent;
	private Product product;
	private Integer quantity;
	private Date requiredByDate;

	/** Constructor for PrepareQuoteHandler7284376369192582928
	 * 
	 * @param product 
	 * @param quantity 
	 * @param requiredByDate 
	 * @param isEvent 
	 */
	public PrepareQuoteHandler7284376369192582928(Product product, Integer quantity, Date requiredByDate, boolean isEvent) {
		this.firstOccurrenceScheduledFor=new Date(System.currentTimeMillis()+1000);
		setProduct(product);
		setQuantity(quantity);
		setRequiredByDate(requiredByDate);
		this.isEvent=isEvent;
	}
	
	/** Default constructor for PrepareQuoteHandler7284376369192582928
	 */
	public PrepareQuoteHandler7284376369192582928() {
	}

	public int getConsumerPoolSize() {
		return 5;
	}
	
	public Date getFirstOccurrenceScheduledFor() {
		return this.firstOccurrenceScheduledFor;
	}
	
	public String getHandlerUuid() {
		return "bpm.uml@_D4xCQI_hEeK855GX2Z3x4Q";
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=1131942331364790025l,uuid="bpm.uml@_Kcyv8I_hEeK855GX2Z3x4Q")
	public Product getProduct() {
		return this.product;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=3714362005109338164l,uuid="bpm.uml@_40ws0I_hEeK855GX2Z3x4Q")
	public Integer getQuantity() {
		return this.quantity;
	}
	
	public String getQueueName() {
		return "bpmmodel::MyBusiness::prepareQuote";
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=3765310416962651107l,strategyFactory=DateStrategyFactory.class,uuid="bpm.uml@_6S-XQI_hEeK855GX2Z3x4Q")
	public Date getRequiredByDate() {
		return this.requiredByDate;
	}
	
	public boolean handleOn(Object t, AbstractPersistence persistence) {
		MyBusiness target = (MyBusiness)t;
		if ( isEvent ) {
			return target.consumePrepareQuoteOccurrence(getProduct(),getQuantity(),getRequiredByDate());
		} else {
			PrepareQuote result = target.prepareQuote(null,getProduct(),getQuantity(),getRequiredByDate());
			return true;
		}
	}
	
	public boolean isIsEvent() {
		return this.isEvent;
	}
	
	public Collection<PropertyValue> marshall(Environment env) {
		Collection result = new ArrayList<PropertyValue>();
		result.add(new PropertyValue(1045825153413114698l, Value.valueOf(this.getProduct(),env)));
		result.add(new PropertyValue(7459204235176453452l, Value.valueOf(this.getQuantity(),env)));
		result.add(new PropertyValue(-6l, Value.valueOf(isEvent,env)));
		result.add(new PropertyValue(2288080870635458250l, Value.valueOf(this.getRequiredByDate(),env)));
		return result;
	}
	
	public Date scheduleNextOccurrence(Object object) {
		return new Date(System.currentTimeMillis() + 1000*60*60*24*10);
	}
	
	public void setIsEvent(boolean isEvent) {
		this.isEvent=isEvent;
	}
	
	public void setProduct(Product product) {
		this.product=product;
	}
	
	public void setQuantity(Integer quantity) {
		this.quantity=quantity;
	}
	
	public void setRequiredByDate(Date requiredByDate) {
		this.requiredByDate=requiredByDate;
	}
	
	public void unmarshall(Collection<PropertyValue> ps, AbstractPersistence persistence) {
		for ( PropertyValue p : ps ) {
			if ( p.getId()==1045825153413114698l ) {
				this.setProduct((Product)Value.valueOf(p.getValue(),(InternalHibernatePersistence)persistence));
			} else {
				if ( p.getId()==7459204235176453452l ) {
					this.setQuantity((Integer)Value.valueOf(p.getValue(),(InternalHibernatePersistence)persistence));
				} else {
					if ( p.getId()==2288080870635458250l ) {
						this.setRequiredByDate((Date)Value.valueOf(p.getValue(),(InternalHibernatePersistence)persistence));
					} else {
					
					}
				}
			}
			if ( p.getId()==-6l ) {
				this.isEvent=(Boolean)Value.valueOf(p.getValue(),(InternalHibernatePersistence)persistence);
			}
		}
	}

}