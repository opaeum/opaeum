package org.opaeum.runtime.strategy;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

import org.opaeum.runtime.environment.SimpleTypeRuntimeStrategyFactory;

public class DateStrategyFactory extends SimpleTypeRuntimeStrategyFactory{
	static ThreadLocal<DateFormat> format = new ThreadLocal<DateFormat>(); 
	public static class DateToStringConversionStrategy implements ToStringConverter{
		@Override
		public String toString(Object val){
			if(val == null){
				return "";
			}else{
				return getFormat().format(val);
			}
		}

	}
	private static DateFormat getFormat(){
		if(format.get()==null){
			format.set(DateFormat.getDateInstance(DateFormat.MEDIUM));
		}
		return format.get();
	}
	public static class StringToDateConversionStrategy implements FromStringConverter{
		@Override
		public Date fromString(String val) throws ParseException{
				return getFormat().parse(val);
		}
	}
	public DateStrategyFactory(){
		super(Date.class, DateToStringConversionStrategy.class,StringToDateConversionStrategy.class);
	}
}
