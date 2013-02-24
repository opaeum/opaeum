package org.opaeum.runtime.strategy;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;

import org.opaeum.runtime.environment.SimpleTypeRuntimeStrategyFactory;

public class MoneyInDefaultCurrencyStrategyFactory extends SimpleTypeRuntimeStrategyFactory{
	public static class MyFromStringConverter implements FromStringConverter{
		ThreadLocal<DecimalFormat> format=new ThreadLocal<DecimalFormat>();
		@Override
		public Object fromString(String val) throws ParseException {
			if(format.get()==null){
				format.set(new DecimalFormat("#.00"));
			}
			return format.get().parse(val).doubleValue();
		}
		
	}
	public MoneyInDefaultCurrencyStrategyFactory() {
		super("",Double.class,MyFromStringConverter.class);
	}
}
