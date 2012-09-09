package org.opaeum.hibernate.domain;

import static org.opaeum.hibernate.domain.FormatHelper.*;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
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
public class Duration{
	@Temporal(TemporalType.TIMESTAMP)
	private Date fromDate;
	@Temporal(TemporalType.TIMESTAMP)
	private Date toDate;
	@Basic
	private Double quantity;
	@Enumerated
	private BusinessTimeUnit timeUnit;
	private static ThreadLocal<SimpleDateFormat> simpleDateFormat = new ThreadLocal<SimpleDateFormat>();
	private static ThreadLocal<NumberFormat> decimalFormat = new ThreadLocal<NumberFormat>();
	public Duration(){
	}
	public Duration(BusinessTimeUnit tu){
		this.timeUnit = tu;
	}
	public Duration(String s){
		initFormats();
		fromDate = new Date();
		String[] split = s.split("\\ ");
		quantity = parseDouble(decimalFormat, split[0]);
		try{
			String substring = s.substring(split[1].length());
			substring = substring.replaceAll("\\ ", "");
			if(substring.endsWith("s")){
				substring = substring.substring(0, substring.length() - 1);
			}
			timeUnit = BusinessTimeUnit.valueOf(substring.toUpperCase());
		}catch(Exception e){
			timeUnit = BusinessTimeUnit.BUSINESSHOUR;
		}
		if(split.length > 2){
			fromDate = parse(simpleDateFormat, split[2]);
		}
		if(split.length > 3){
			toDate = parse(simpleDateFormat, split[3]);
		}
	}
	public String toString(){
		initFormats();
		return format(decimalFormat, quantity) + " " + timeUnit.toString() + (quantity != 1.0d ? "s" : "") + " " + format(simpleDateFormat, fromDate) + " "
				+ format(simpleDateFormat, toDate);
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
				}else{
					// Already recorded
				}
			}else{
				if(toDate != null){
					dateToCalculateFrom = toDate;
				}else{
					dateToCalculateFrom = fromDate;
				}
			}
			if(dateToCalculateFrom != null){
				Date to = new Date();
				quantity = bc.calculateDifference(dateToCalculateFrom, to, timeUnit);
				this.timeUnit = timeUnit;
				toDate = to;
			}
		}
	}
	public BusinessTimeUnit getTimeUnit(){
		return timeUnit;
	}
	public Date getFromDate(){
		return fromDate;
	}
	public Double getQuantity(){
		return quantity;
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
