package org.opaeum.runtime.costing;

import static org.opaeum.hibernate.domain.FormatHelper.format;
import static org.opaeum.hibernate.domain.FormatHelper.parse;
import static org.opaeum.hibernate.domain.FormatHelper.parseDouble;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.opaeum.hibernate.domain.AbstractInterfaceValue;
import org.opaeum.runtime.domain.IPersistentObject;
import org.opaeum.runtime.environment.Environment;
import org.opaeum.runtime.environment.JavaMetaInfoMap;
import org.opaeum.runtime.persistence.AbstractPersistence;

@Embeddable
public class QuantityBasedCost extends AbstractInterfaceValue{
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;
	@Basic
	private Double costToCompany = 0d;
	@Basic
	private Double costToCustomer = 0d;
	@Transient
	private IPersistentObject resource;
	private Long resourceIdentifier;
	private String resourceClassIdentifier;
	@Transient
	AbstractPersistence persistence;
	private static ThreadLocal<SimpleDateFormat> simpleDateFormat = new ThreadLocal<SimpleDateFormat>();
	private static ThreadLocal<NumberFormat> decimalFormat = new ThreadLocal<NumberFormat>();
	public QuantityBasedCost(){
	}
	public QuantityBasedCost(String value){
		initFormats();
		String[] split = value.split("\\|");
		date = parse(simpleDateFormat, split[0]);
		costToCompany = parseDouble(decimalFormat, split[1]);
		costToCustomer = parseDouble(decimalFormat, split[2]);
		resourceIdentifier = Long.valueOf(split[3]);
		resourceClassIdentifier = split[4];
	}
	@Override
	public String toString(){
		initFormats();
		return format(simpleDateFormat, date) + "|" + format(decimalFormat, costToCompany) + "|" + format(decimalFormat, costToCustomer) + "|" + resourceIdentifier
				+ "|" + resourceClassIdentifier;
	}
	public void setPersistence(AbstractPersistence e){
		this.persistence = e;
	}
	public void eventOccurred(IQuantifiedResourceBase resource,boolean firstEvent,Double quantity,JavaMetaInfoMap env){
		if(firstEvent = false || date == null){
			takeMeasurement(resource, new Date(), quantity,env);
		}
	}
	public IQuantifiedResourceBase getResource(){
		return (IQuantifiedResourceBase) getValue(persistence);
	}
	private void takeMeasurement(IQuantifiedResourceBase resource,Date date,Double duration,JavaMetaInfoMap e){
		IPricePerUnit rate = resource.getPriceEffectiveOn(new Date());
		if(rate == null){
			// INVALID measurement -abort;
		}else{
			setValue(resource,e);
			this.costToCompany += (rate.getPricePaidByCompany() * duration) + (rate.getAdditionalCostToCompany() * duration);
			this.costToCustomer += (rate.getPricePaidByCustomer() * duration);
			this.date = date;
		}
	}
	public void setIdentifier(Long identifier){
		this.resourceIdentifier = identifier;
	}
	public Long getIdentifier(){
		return resourceIdentifier;
	}
	protected IPersistentObject getValue(){
		return resource;
	}
	protected String getClassIdentifier(){
		return resourceClassIdentifier;
	}
	protected void setClassIdentifier(String classIdentifier){
		this.resourceClassIdentifier = classIdentifier;
	}
	protected void setValueImpl(IPersistentObject value){
		this.resource = value;
	}
	private void initFormats(){
		if(decimalFormat.get() == null){
			decimalFormat.set(NumberFormat.getInstance());
		}
		if(simpleDateFormat.get() == null){
			simpleDateFormat.set(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ"));
		}
	}
}
