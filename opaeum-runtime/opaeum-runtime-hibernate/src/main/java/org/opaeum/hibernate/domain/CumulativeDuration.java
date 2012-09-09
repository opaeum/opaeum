package org.opaeum.hibernate.domain;

import java.text.NumberFormat;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Embeddable;
import javax.persistence.Enumerated;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.AccessType;
import org.opaeum.runtime.domain.BusinessTimeUnit;
import org.opaeum.runtime.domain.IBusinessCalendar;

@Embeddable
@AccessType("field")
public class CumulativeDuration{
	@Temporal(TemporalType.TIMESTAMP)
	private Date fromDate;
	@Temporal(TemporalType.TIMESTAMP)
	private Date toDate;
	@Basic
	private Double cumulativeQuantity;
	@Basic
	private Integer measurementCount;
	@Enumerated
	private BusinessTimeUnit timeUnit;
	public CumulativeDuration(){
	}
	public CumulativeDuration(BusinessTimeUnit tu){
		this.timeUnit = tu;
	}
	public CumulativeDuration(String s){
		String[] split = s.split("\\ ");
		try{
			cumulativeQuantity = NumberFormat.getNumberInstance().parse(split[0]).doubleValue();
		}catch(Exception e){
			cumulativeQuantity = 0d;
		}
		try{
			String substring = s.substring(split[0].length());
			substring = substring.replaceAll("\\ ", "");
			if(substring.endsWith("s")){
				substring = substring.substring(0, substring.length() - 1);
			}
			timeUnit = BusinessTimeUnit.valueOf(substring.toUpperCase());
		}catch(Exception e){
			timeUnit = BusinessTimeUnit.BUSINESSHOUR;
		}
	}
	public String toString(){
		return NumberFormat.getInstance().format(cumulativeQuantity) + " " + timeUnit.toString() + (cumulativeQuantity != 1.0d ? "s" : "") + " over "
				+ measurementCount + " measurements";
	}
	public void fromEventOccurred(boolean firstEvent){
		if(firstEvent){
			if(fromDate == null){
				fromDate = new Date();
				toDate = null;
			}else{
				// already recorded
			}
		}else{
			fromDate = new Date();
			toDate = null;
		}
	}
	public void toEventOccurred(IBusinessCalendar bc,BusinessTimeUnit timeUnit,boolean firstEvent){
		if(fromDate != null){
			Date dateToCalculateFrom = null;
			if(firstEvent){
				if(toDate == null){
					dateToCalculateFrom = fromDate;
					measurementCount++;
				}else{
					// already recorded
				}
			}else{
				if(toDate == null){
					dateToCalculateFrom = fromDate;
					measurementCount++;
				}else{
					dateToCalculateFrom = toDate;
				}
			}
			if(dateToCalculateFrom != null){
				if(this.timeUnit != timeUnit){
					cumulativeQuantity = bc.convertBusinessTime(cumulativeQuantity, this.timeUnit, timeUnit);
					this.timeUnit = timeUnit;
				}
				Date date = new Date();
				cumulativeQuantity += bc.calculateDifference(dateToCalculateFrom, date, timeUnit);
				toDate = date;
			}
		}
	}
	public BusinessTimeUnit getTimeUnit(){
		return timeUnit;
	}
	public Double getCumulativeQuantity(){
		return cumulativeQuantity;
	}
}
