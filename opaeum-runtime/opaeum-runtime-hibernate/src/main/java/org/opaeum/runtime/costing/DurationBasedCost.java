package org.opaeum.runtime.costing;

import static org.opaeum.hibernate.domain.FormatHelper.format;
import static org.opaeum.hibernate.domain.FormatHelper.parse;
import static org.opaeum.hibernate.domain.FormatHelper.parseDouble;
import static org.opaeum.hibernate.domain.FormatHelper.parseInt;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.opaeum.runtime.domain.IBusinessCalendar;

@Embeddable
public class DurationBasedCost{
	@Temporal(TemporalType.TIMESTAMP)
	private Date fromDate;
	@Temporal(TemporalType.TIMESTAMP)
	private Date toDate;
	@Basic
	private Double costToCompany = 0d;
	@Basic
	private Double costToCustomer = 0d;
	@Basic
	private int measurementCount = 0;
	private static ThreadLocal<SimpleDateFormat> simpleDateFormat = new ThreadLocal<SimpleDateFormat>();
	private static ThreadLocal<NumberFormat> decimalFormat = new ThreadLocal<NumberFormat>();
	private static ThreadLocal<NumberFormat> integerFormat = new ThreadLocal<NumberFormat>();
	public DurationBasedCost(){
	}
	public DurationBasedCost(String value){
		initFormats();
		String[] split = value.split("\\|");
		fromDate = parse(simpleDateFormat, split[0]);
		toDate = parse(simpleDateFormat, split[1]);
		costToCompany = parseDouble(decimalFormat, split[2]);
		costToCustomer = parseDouble(decimalFormat, split[3]);
		this.measurementCount = parseInt(integerFormat, split[4]);
	}
	public String toString(){
		initFormats();
		return format(simpleDateFormat, fromDate) + "|" + format(simpleDateFormat, toDate) + "|" + format(decimalFormat, costToCompany) + "|"
				+ format(decimalFormat, costToCustomer) + format(integerFormat, (Object) measurementCount);
	}
	public void fromEventOccurred(boolean firstEvent){
		if(firstEvent){
			if(fromDate == null){
				fromDate = new Date();
			}else{
				// already recorded
			}
		}else{
			fromDate = new Date();
		}
	}
	public List<DurationBasedCostEntry> toEventOccurred(Collection<ITimedResourceBase> resources,boolean firstEvent){
		List<DurationBasedCostEntry> result = new ArrayList<DurationBasedCostEntry>();
		Date dateToCalculateFrom = null;
		boolean incrementMeasurementCount = false;
		if(firstEvent){
			if(toDate == null){
				dateToCalculateFrom = fromDate;
				incrementMeasurementCount = true;
			}else{
				// Already recorded
			}
		}else{
			if(toDate == null){
				dateToCalculateFrom = fromDate;
				incrementMeasurementCount = true;
			}else{
				// AN adjustment, don't increment measurementCount
				dateToCalculateFrom = toDate;
			}
		}
		if(dateToCalculateFrom != null){
			for(ITimedResourceBase r:resources){
				takeMeasurement(r, dateToCalculateFrom, incrementMeasurementCount);
				result.add(new DurationBasedCostEntry(dateToCalculateFrom, toDate, r, incrementMeasurementCount));
				incrementMeasurementCount = false;
			}
		}
		return result;
	}
	public DurationBasedCostEntry addCostEntry(Date fromDate,Date toDate,ITimedResourceBase resource){
		IRatePerTimeUnit rate = resource.getRateEffectiveOn(fromDate);
		if(rate == null){
			// INVALID measurement -abort;
		}else{
			this.measurementCount = getMeasurementCount() + 1;
			IBusinessCalendar bc = resource.getBusinessCalendarToUse();
			double duration = bc.calculateDifference(fromDate, toDate, rate.getTimeUnit());
			this.costToCompany += (rate.getRatePaidByCompany() * duration) + (rate.getAdditionalCostToCompany() * duration);
			this.costToCustomer += (rate.getRatePaidByCustomer() * duration);
		}
		return new DurationBasedCostEntry(fromDate, toDate, resource, true);
	}
	public void recalculate(List<DurationBasedCostEntry> entries){
		costToCompany = 0d;
		costToCustomer = 0d;
		this.measurementCount = 0;
		for(DurationBasedCostEntry e:entries){
			takeMeasurement(e.getResource(), e.getFromDate(), e.isNewMeasurement());
		}
	}
	private void takeMeasurement(ITimedResourceBase resource,Date dateToCalculateFrom,boolean incrementMeasurementCount){
		IRatePerTimeUnit rate = resource.getRateEffectiveOn(dateToCalculateFrom);
		if(rate == null){
			// INVALID measurement -abort;
		}else{
			if(incrementMeasurementCount){
				this.measurementCount = getMeasurementCount() + 1;
			}
			IBusinessCalendar bc = resource.getBusinessCalendarToUse();
			Date date = new Date();
			double duration = bc.calculateDifference(dateToCalculateFrom, date, rate.getTimeUnit());
			this.costToCompany += (rate.getRatePaidByCompany() * duration) + (rate.getAdditionalCostToCompany() * duration);
			this.costToCustomer += (rate.getRatePaidByCustomer() * duration);
			toDate = date;
		}
	}
	public int getMeasurementCount(){
		return measurementCount;
	}
	private void initFormats(){
		if(decimalFormat.get() == null){
			decimalFormat.set(NumberFormat.getInstance());
		}
		if(integerFormat.get() == null){
			integerFormat.set(NumberFormat.getIntegerInstance());
		}
		if(simpleDateFormat.get() == null){
			simpleDateFormat.set(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ"));
		}
	}
	
}
