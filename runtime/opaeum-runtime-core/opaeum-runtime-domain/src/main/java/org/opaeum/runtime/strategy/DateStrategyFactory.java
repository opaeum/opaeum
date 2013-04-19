package org.opaeum.runtime.strategy;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.opaeum.runtime.environment.SimpleTypeRuntimeStrategyFactory;

public class DateStrategyFactory extends SimpleTypeRuntimeStrategyFactory{
	public static class MyFromStringConverter implements FromStringConverter{
		private ThreadLocal<SimpleDateFormat> format=new ThreadLocal<SimpleDateFormat>();
				

		@Override
		public Object fromString(String val) throws ParseException {
			if(format.get()==null){
				format.set(new SimpleDateFormat("yyyy-MM-dd"));
			}
			return format.get().parseObject(val);
		}
		
	}
	public DateStrategyFactory() {
		super("",Date.class, MyFromStringConverter.class);
	}
	
}
