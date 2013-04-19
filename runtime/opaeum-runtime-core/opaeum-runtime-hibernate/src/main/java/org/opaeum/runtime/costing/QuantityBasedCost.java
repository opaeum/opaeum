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

import org.opaeum.hibernate.domain.AbstractAnyValue;
import org.opaeum.runtime.domain.IAnyValue;
import org.opaeum.runtime.domain.IPersistentObject;
import org.opaeum.runtime.environment.JavaMetaInfoMap;

@Embeddable
public class QuantityBasedCost extends AbstractAnyValue implements IAnyValue{
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
	public void eventOccurred(IQuantifiedResourceBase resource,boolean firstEvent,Double quantity){
		if(firstEvent = false || date == null){
			takeMeasurement(resource, new Date(), quantity);
		}
	}
	public IQuantifiedResourceBase getResource(){
		return (IQuantifiedResourceBase) getValue();
	}
	private void takeMeasurement(IQuantifiedResourceBase resource,Date date,Double duration){
		IPricePerUnit rate = resource.getPriceEffectiveOn(new Date());
		if(rate == null){
			// INVALID measurement -abort;
		}else{
			setValueInternal(resource);
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
	public IPersistentObject getValue(){
		return resource;
	}
	public String getClassIdentifier(){
		return resourceClassIdentifier;
	}
	public void setClassIdentifier(String classIdentifier){
		this.resourceClassIdentifier = classIdentifier;
	}
	private void initFormats(){
		if(decimalFormat.get() == null){
			decimalFormat.set(NumberFormat.getInstance());
		}
		if(simpleDateFormat.get() == null){
			simpleDateFormat.set(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ"));
		}
	}
	@Override
	protected String getClassIdentifier(Class<?> c,JavaMetaInfoMap p){
		return p.getUuidFor(c);
	}
	@Override
	protected Class<?> getClass(String classUuid,JavaMetaInfoMap p){
		return p.getClass(classUuid);
	}
	@Override
	public void setValueInternal(IPersistentObject v){
		this.resource=v;
		
	}
	
}
