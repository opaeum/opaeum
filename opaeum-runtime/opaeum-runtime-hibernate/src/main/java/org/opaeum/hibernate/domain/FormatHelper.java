package org.opaeum.hibernate.domain;

import java.text.Format;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FormatHelper{

	public static Date parse(ThreadLocal<SimpleDateFormat> simpleDateFormat2,String source){
		Date measurementCount1 = null;
		try{
			measurementCount1 = simpleDateFormat2.get().parse(source);
		}catch(ParseException e){
		}
		return measurementCount1;
	}
	public static Double parseDouble(ThreadLocal<NumberFormat> decimalFormat2,String source){
		try{
			return decimalFormat2.get().parse(source).doubleValue();
		}catch(ParseException e){
			return null;
		}
	}
	public static Integer parseInt(ThreadLocal<NumberFormat> integerFormat2,String source){
		try{
			return integerFormat2.get().parse(source).intValue();
		}catch(ParseException e){
			return null;
		}
	}
	public static String format(ThreadLocal<? extends Format> simpleDateFormat2,Object fromDate2){
		if(fromDate2 == null){
			return "null";
		}
		return simpleDateFormat2.get().format(fromDate2);
	}
}
